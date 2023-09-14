package by.teachmeskills.linkedlist;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Collection;

public class MyLinkedList<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    public MyLinkedList() {
        clear();
    }

    public MyLinkedList(Collection<? extends T> collection) {
        this();
        addAll(collection);
    }

    public void add(int index, T elem) {
        Node<T> node = getNode(index, size);
        addBeforeNode(node, elem);
        ++size;
    }

    public void add(T elem) {
        add(size, elem);
    }

    public T remove(int index) {
        Node<T> result = getNode(index, size - 1);
        removeNode(result);
        --size;
        return result.item;
    }

    public boolean remove(T elem) {
        int index = getIndexOfElem(elem, true);
        if (index != -1) {
            removeNode(getNode(index, size - 1));
            --size;
        }
        return index != -1;
    }

    public T get(int index) {
        return getNode(index, size - 1).item;
    }

    public boolean contains(T elem) {
        return getIndexOfElem(elem, true) != -1;
    }

    public void clear() {
        size = 0;
        last = new Node<T>();
        first = new Node<T>(null, last, null);
        last.prev = first;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(T elem) {
        return getIndexOfElem(elem, true);
    }

    public int lastIndexOf(T elem) {
        return getIndexOfElem(elem, false);
    }

    public void addAll(Collection<? extends T> c) {
        for (T elem : c) {
            add(elem);
        }
    }

    public void retainAll(Collection<? extends T> c) {
        Node<T> current = first.next;
        int initSize = size;
        for (int i = 0; i < initSize; ++i) {
            if (!c.contains(current.item)) {
                removeNode(current);
                --size;
            }
            current = current.next;
        }
    }

    public void removeAll(Collection<? extends T> c) {
        Node<T> current = first.next;
        int initSize = size;
        for (int i = 0; i < initSize; ++i) {
            if (c.contains(current.item)) {
                removeNode(current);
                --size;
            }
            current = current.next;
        }
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = first.next;
        for (int i = 0; i < size; ++i) {
            array[i] = current.item;
            current = current.next;
        }
        return array;
    }

    @Override
    public String toString() {
        Node<T> node = first.next;
        StringBuilder result = new StringBuilder("[");
        if (node.item != null) {
            result.append(node.item);
            node = node.next;
        }
        if (node != null) {
            while (!node.equals(last)) {
                result.append(", ").append(node.item);
                node = node.next;
            }
        }
        result.append("]");
        return result.toString();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;
    }

    private void addAfterNode(Node<T> node, T elem) {
        Node<T> newNode = new Node<>(elem, node.next, node);
        node.next.prev = newNode;
        node.next = newNode;
    }

    private void addBeforeNode(Node<T> node, T elem) {
        Node<T> newNode = new Node<>(elem, node, node.prev);
        node.prev.next = newNode;
        node.prev = newNode;
    }

    private void removeNode(Node<T> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private Node<T> getNode(int index, int topBorder) {
        if (index > topBorder || index < 0) {
            throw new IndexOutOfBoundsException("Index" + index + " out of bound!");
        }
        Node<T> current;
        if (size - index > index) {
            current = first;
            for (int i = 0; i <= index; ++i) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = size; i > index; --i) {
                current = current.prev;
            }
        }
        return current;
    }

    private int getIndexOfElem(T elem, boolean isFirstIndex) {
        Node<T> current;
        if (isFirstIndex) {
            current = first.next;
            for (int i = 0; i < size; ++i) {
                if (current.item.equals(elem)) {
                    return i;
                }
                current = current.next;
            }
        } else {
            current = last.prev;
            for (int i = size - 1; i >= 0; --i) {
                if (current.item.equals(elem)) {
                    return i;
                }
                current = current.prev;
            }
        }
        return -1;
    }
}
