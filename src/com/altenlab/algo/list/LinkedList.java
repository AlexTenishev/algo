package com.altenlab.algo.list;

public class LinkedList {
    public static class Node {
        public int val;
        public Node next;

        public Node() {}
        public Node(int val) {
            this.val = val;
        }
    }

    private Node head;

    public LinkedList() {

    }

    public LinkedList(int[] data) {
        Node tail;
        Node prev = null;
        for(int i = 0; i < data.length; ++i ) {
            tail = new Node(data[i]);
            if( head == null ) {
                head = tail;
            }
            if( prev != null ) {
                prev.next = tail;
            }
            prev = tail;
        }
    }

    public LinkedList(Node head) {
        this.head = head;
    }

    public Node getHead() {
        return head;
    }

    public void push(int val) {
        final Node node = new Node(val);
        if( head != null ) {
            node.next = head;
        }
        head = node;
    }

    public void dump() {
        boolean first = true;
        System.out.print("LinkedList: [ ");
        Node current  = head;
        while( current != null ) {
            if( first ) {
                first = false;
            } else {
                System.out.print(" -> ");
            }
            System.out.print(current.val);
            current = current.next;
        }
        System.out.println(" ]");
    }

    public static void dump(Node head) {
        boolean first = true;
        System.out.print("LinkedList: [ ");
        Node current  = head;
        while( current != null ) {
            if( first ) {
                first = false;
            } else {
                System.out.print(" -> ");
            }
            System.out.print(current.val);
            current = current.next;
        }
        System.out.println(" ]");
    }

    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    private Node reverseRecursive(Node prev, Node current) {
        if( current == null ) {
            return prev;
        }

        final Node next = current.next;
        current.next = prev;
        return reverseRecursive(current, next);
    }
    public void reverseRecursive() {
        if( head == null ) {
            return;
        }
        final Node rest = head.next;
        head.next = null;
        head = reverseRecursive(head, rest);
    }

    public static Node mergeSortedLists(Node first, Node second) {
        Node head = null;
        Node prev = null;
        while( first != null && second != null ) {
            if( first.val <= second.val ) {
                final Node next = first.next;
                first.next = second;
                if( head == null ) {
                    head = first;
                }
                if( prev != null ) {
                    prev.next = first;
                }
                prev = first;
                first = next;
            } else {
                final Node next = second.next;
                second.next = first;
                if( head == null ) {
                    head = second;
                }
                if( prev != null ) {
                    prev.next = second;
                }
                prev = second;
                second = next;
            }
        }
        return head;
    }
    public static Node mergeSortedListsRecursive(Node first, Node second) {
        if( first == null ) {
            return second;
        } else if( second == null ) {
            return first;
        }
        if( first.val <= second.val ) {
            first.next = mergeSortedListsRecursive(first.next, second);
            return first;
        }
        second.next = mergeSortedListsRecursive(first, second.next);
        return second;
    }

    // FIXME: assertain do we need this at all
    public static LinkedList mergeSortedLists(LinkedList first, LinkedList second) {
        LinkedList result = new LinkedList();
        Node hfirst = first != null ? first.head : null;
        Node hsecond = second != null ? second.head : null;
        while( hfirst != null && hsecond != null ) {
            if( hfirst.val <= hsecond.val ) {
                result.push(hfirst.val);
                hfirst = hfirst.next;
            } else {
                result.push(hsecond.val);
                hsecond = hsecond.next;
            }
        }
        while( hfirst != null ) {
            result.push(hfirst.val);
            hfirst = hfirst.next;
        }
        while( hsecond != null ) {
            result.push(hsecond.val);
            hsecond = hsecond.next;
        }

        return result;
    }

    public static Node getMiddleNode(Node head) {
        if( head == null ) {
            return head;
        }

        Node midNode = head;
        Node endNode = head.next;
        while( endNode != null ) {
            endNode = endNode.next;
            if( endNode != null ) {
                midNode = midNode.next;
                endNode = endNode.next;
            }
        }
        return midNode;

    }
    public static Node[] split(Node head) {
        Node[] result = {head, null};
        int count = 0;
        Node curr = head;
        while( curr != null ) {
            count++;
            curr = curr.next;
        }
        int half = count / 2;
        count = 0;
        curr = head;
        Node prev = null;
        while( count < half ) {
            count++;
            prev = curr;
            curr = curr.next;
        }
        if( count > 0 ) {
            prev.next = null;
            result[1] = curr;
        }

        return result;
    }
    public static Node mergeSort(Node head) {
        if( head == null ) {
            return null;
        }
        Node[] halves = split(head);

        if( halves[0] != null && halves[1] == null ) {
            return halves[0];
        }
        Node first = mergeSort(halves[0]);
        Node second = mergeSort(halves[1]);
        return mergeSortedLists(first, second);
    }

    private Node mergeSort2(Node start) {
        // best case, do nothin'
        if( start == null || start.next == null ) {
            return start;
        }

        final Node mid = getMiddleNode(start);
        Node midNext = mid.next;
        mid.next = null;

        final Node left = mergeSort(start);
        final Node right = mergeSort(midNext);

        return mergeSortedLists(left, right);
    }
    public void mergeSort2() {
        head = mergeSort2(head);
    }

    @Override
    public boolean equals(Object other) throws ClassCastException {
        if( !(other instanceof LinkedList) ) {
            throw new ClassCastException("An LinkedList object expected.");
        }
        final LinkedList otherList = (LinkedList) other;
        if( this == otherList ) {
            return true;
        }

        Node left = head;
        Node right = otherList.head;
        while( left != null || right != null ) {
            if( left == null || right == null ) {
                return false;
            }
            if( left.val != right.val ) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public Node getSomeLoopNode() {
        if( head == null ) {
            return null;
        }

        Node slow = head;
        Node fast = head.next;
        while( fast != null && fast.next != null ) {
            if( fast == slow ) {
                return slow;
            }
            fast = fast.next;
            if( fast != null ) {
                if( fast == slow ) {
                    return slow;
                }
                fast = fast.next;
            }
            slow = slow.next;
        }
        return null;
    }

    public Node getSomeLoopNode2() {
        if( head == null || head.next == null ) {
            return null;
        }

        Node slow = head;
        Node fast = head.next.next;
        while( fast != null && fast.next != null ) {
            System.out.println("Slow: "+slow.val+", Fast: "+fast.val);
            if( fast == slow ) {
                return slow;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return null;
    }

    public boolean removeLoopIfAny0() {
        Node loop = getSomeLoopNode2();
        if( loop == null ) {
            return false;
        }

        Node current = head;
        while( current != loop.next ) {
            current = current.next;
            loop = loop.next.next;
        }

        // now loop will point to the proposed tail of the list
        // and here we can fix it (see https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/)
        loop.next = null;
        return true;
    }

    // this does not work at all!!!
    public boolean removeLoopIfAny() {
        Node loop = getSomeLoopNode2();
        if( loop == null ) {
            return false;
        }

        Node current = head;
        Node fast = loop;
        while( current.next != loop.next ) {
            current = current.next;
            fast = fast.next;
        }

        // now loop will point to the proposed tail of the list
        // and here we can fix it (see https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/)
        fast.next = null;
        return true;
    }

    public boolean hasLoop() {
        return getSomeLoopNode() != null;
    }
}
