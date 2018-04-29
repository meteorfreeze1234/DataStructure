package collection;

import java.util.Arrays;

public class HashMap<K, V> {
    private int capacity;

    private final float loadFactor;

    private float threshold;

    static final int DEFAULT_CAPACITY = 8;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;

    private int tableUsed;

    private int size;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int initCapacity) {
        this.capacity = (initCapacity >> 2) << 2;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.threshold = this.capacity * this.loadFactor;
        this.table = new Node[this.capacity];
    }


    static class Node<K, V> {
        private K key;
        private V value;
        private int hash;
        Node<K, V> next;

        public Node(int hash, K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public Node put(K key, V value) {
        return putVal(hash(key), key, value, false);
    }

    public Node putIfAbsent(K key, V value) {
        return putVal(hash(key), key, value, true);
    }

    private Node putVal(int hash, K key, V val, boolean putIfAbsent) {
        int index;
        Node<K, V> node;
        if ((node = table[index = (capacity - 1) & hash]) == null) {
            table[index] = new Node<>(hash, key, val);
            size++;
            tableUsed++;
            if (tableUsed >= threshold) {
                resize();
            }
            return table[index];
        } else {
            while (node.next != null) {
                if (node.getKey().equals(key)) {
                    if (!putIfAbsent) {
                        node.setValue(val);
                    }
                    return node;
                } else {
                    node = node.next;
                }
            }
            node.next = new Node<>(hash, key, val);
            size++;
            return node.next;
        }
    }

    public V get(K key) {
        Node<K, V> node;
        if ((node = table[hash(key.hashCode()) & (capacity - 1)]) == null) {
            return null;
        } else {
            do {
                if (node.getKey().equals(key)) {
                    return node.getValue();
                } else {
                    node = node.next;
                }
            } while (node != null);
            return null;
        }
    }

    private void resize() {
        table = Arrays.copyOf(table, capacity << 1);
        int index;
        Node<K, V> oldNode;
        for (int i = 0; i < capacity; i++) {
            Node<K, V> preNode = null;
            oldNode = table[i];
            while (oldNode != null) {
                index = hash(oldNode.getKey().hashCode() & (capacity));
                if (index > 0) {
                    Node<K, V> newNode;
                    if ((newNode = table[capacity + i]) == null) {
                        table[capacity + 1] = oldNode;
                        tableUsed++;
                    } else {
                        while (newNode.next != null) {
                            newNode = newNode.next;
                        }
                        newNode.next = oldNode;
                    }
                    if (preNode !=null) {
                        preNode.next = oldNode.next;
                        oldNode.next = null;
                        oldNode = preNode.next;
                    } else {
                        table[i] = oldNode.next;
                        oldNode.next = null;
                    }
                } else {
                    preNode = oldNode;
                    oldNode = oldNode.next;
                }
            }
        }
        capacity *= 2;
        threshold = capacity * DEFAULT_LOAD_FACTOR;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>(4);
        map.put("111", "asd1");
        map.put("1112", "asd2");
        map.put("1113", "asd3");
        map.put("1114", "asd4");
        map.put("1115", "asd5");
        map.put("1116", "asd6");
        map.put("1117", "asd7");
        System.out.println(map.get("1117"));


    }
}
