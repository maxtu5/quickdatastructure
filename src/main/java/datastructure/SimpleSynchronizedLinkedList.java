package datastructure;

import java.util.Comparator;

import static java.lang.Thread.sleep;

public class SimpleSynchronizedLinkedList<E> implements SimpleList<E> {

    private Node<E> head;
    private Comparator<E> comparator;

    public SimpleSynchronizedLinkedList() {
        this.head = null;
    }

    public SimpleSynchronizedLinkedList(Comparator<E> comparator) {
        this.head = null;
        this.comparator = comparator;
    }

    @Override
    public synchronized void addFirst(E element) {
        Node<E> temp = new Node<>(element);
        temp.next = head;

        doSomethingLong();

        head = temp;
    }

    private void doSomethingLong() {
        double a = 100.45334;
        double b = 1.00453;
        for (int i = 0; i < 100000; i++) {
            a=a/b;
        }
    }

    @Override
    public synchronized E removeFirst() {
        if (head == null) return null;
        E retval = head.data;
        head = head.next;
        return retval;
    }

    @Override
    public synchronized void addDescOrder(E element) {
        Node<E> temp = new Node<>(element);
        if (head == null) {
            head = temp;
            return;
        }

        Node<E> beforeInsert = head;
        Node<E> afterInsert = head;
        while (afterInsert.next != null && compare(afterInsert.data, element) >= 0) {
            beforeInsert = afterInsert;
            afterInsert = afterInsert.next;
        }
        if (afterInsert.next == null && compare(afterInsert.data, element) >= 0) {
            afterInsert.next = temp;
        } else {
            temp.next = afterInsert;
            if (afterInsert == head) {
                head = temp;
            } else {
                beforeInsert.next = temp;
            }
        }
    }

    @Override
    public synchronized E removeMax() {
        if (head == null) return null;

        Node<E> maxNode = head;
        Node<E> beforeMaxNode = head;
        Node<E> currentNode = head.next;
        Node<E> beforeCurrentNode = head;

        while (currentNode != null) {
            if (compare(currentNode.data, maxNode.data) > 0) {
                maxNode = currentNode;
                beforeMaxNode = beforeCurrentNode;
            }
            beforeCurrentNode = currentNode;
            currentNode = currentNode.next;
        }

        E retval = maxNode.data;

        if (maxNode == beforeMaxNode) {
            head = maxNode.next;
        } else {
            beforeMaxNode.next = maxNode.next;
        }

        return retval;
    }

    private int compare(E o1, E o2) {
        if (comparator != null) {
            return comparator.compare(o1, o2);
        }
        return ((Comparable) o1).compareTo(o2);
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

}
