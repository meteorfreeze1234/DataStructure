package collection;

// 单向链表反转
public class LinkedList {
    Node headNode;
    Node tailNode;
    public void reverse() {
        if (headNode == null) {
            return;
        }
        Node preNode = headNode;
        Node currentNode = headNode.next;
        if (currentNode != null) {
            Node nextNode = currentNode.next;
            while (nextNode != null) {
                currentNode.next = preNode;
                preNode = currentNode;
                currentNode = nextNode;
                nextNode = nextNode.next;
            }
        }
        currentNode.next = preNode;
        headNode.next = null;
        headNode = currentNode;
    }

    public void add(Node ele) {
        if (headNode == null) {
            headNode = ele;
            tailNode = ele;
        }
        tailNode.next = ele;
        tailNode = tailNode.next;
    }

    public void print() {
        Node node = headNode;
        while (node != null) {
           System.out.println(node.data);
           node = node.next;
        }
    }

    public static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        for (int i=0;i<10;i++) {
            linkedList.add(new Node(i));
        }

        linkedList.print();
        linkedList.reverse();
        System.out.println("--------------------");
        linkedList.print();
    }
}
