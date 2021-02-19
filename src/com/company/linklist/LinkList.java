package com.company.linklist;

public class LinkList {

    private static Node thirdNode = new Node(3, null);

    private static Node secondNode = new Node(2, thirdNode);

    private static Node head = new Node(1, secondNode);

    private static Node third1Node = new Node(6, null);

    private static Node second1Node = new Node(5, third1Node);

    private static Node head1 = new Node(4, second1Node);


    /**
     * 单链表反转
     * @param node
     * @return
     */
    public static Node reverse(Node node){
        if (node == null)
            return null;

        Node prev = null, cur = node;
        while (cur != null){
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 检测环
     * @param head
     * @return
     */
    public static boolean checkCircle(Node head){
        if (head == null)
            return false;

        Node fast = head, slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }


    /**
     * 合并有序链表
     * @param l1
     * @param l2
     * @return
     */
    public static Node merge(Node l1, Node l2){
        Node solider = new Node(0, null);

        Node cur = solider;
        while (l1 != null && l2 != null){
            if (l1.getData() <= l2.getData()){
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;

            if (l1 != null){
                cur.next = l1;
            }
            if (l2 != null){
                cur.next = l2;
            }
        }
        return solider.next;
    }

    /**
     * 删除倒数第K个节点
     * @param node
     * @param k
     * @return
     */
    public static Node deleteKNode(Node node, int k){
        if (node == null || k < 1){
            return null;
        }


        Node fast = node, slow = node;
        for (int i = 1; i < k; i++) {
            fast = fast.next;
            if (fast == null){
                return null;
            }
        }

        Node prev = null;
        while (fast.next != null){
            fast = fast.next;

            prev = slow;
            slow = slow.next;
        }


        if (prev != null){
            prev.next = slow.next;
        } else {
            node = node.next;
        }
        return node;
    }

    /**
     * 求中间节点
     * @param node
     * @return
     */
    public static Node findMiddleNode(Node node){
        if (node == null)
            return null;

        Node fast = node, slow = node;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }




    public static void main(String[] args) {
        Node node = deleteKNode(head, 4);


        printAll(node);
    }

    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
