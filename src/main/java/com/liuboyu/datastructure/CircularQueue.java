package com.liuboyu.datastructure;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 环形队列
 *
 * @param <E>
 */
public class CircularQueue<E> {

    public static void main(String[] args) {
        CircularQueue<String> timeWheel = new CircularQueue<>();
        timeWheel.add("1", 0);
        timeWheel.add("2", 0);
        timeWheel.add("3", 0);
        timeWheel.add("4", 0);
        timeWheel.add("5", 0);

        while (true) {
            String next = timeWheel.next();
            if (next == null)
                break;
            System.out.println(next);
        }
    }

    private int size;

    private Node<E> node;
    private Node<E> first;
    private Node<E> last;

    public CircularQueue() {
        this.size = 0;
    }

    public boolean add(E e, int layer) {
        final Node<E> newNode = new Node<>(layer, e, null, null);
        if (node == null) node = newNode;
        if (size == 0) {
            first = newNode;
            last = newNode;
        } else {
            if (first.next == null) first.next = newNode;
            if (node.next == null) node.next = newNode;
            last.next = newNode;
            newNode.pre = last;
            newNode.next = first;
            last = newNode;
            first.pre = newNode;
        }
        size++;
        return true;
    }

    public E remove() {
        return null;
    }

    public E next() {
        if (node == null)
            return null;
        Node<E> n = node;
        final E e = n.e;
        if (size == 1) {
            node = null;
        } else {
            node = n.next;
            last.next = node;
            node.pre = last;
        }
        size--;
        return e;
    }

    public E peek() {
        return node.e;
    }

    @Data
    @AllArgsConstructor
    private static class Node<E> {
        private int layer;
        private E e;
        private Node<E> pre;
        private Node<E> next;
    }
}
