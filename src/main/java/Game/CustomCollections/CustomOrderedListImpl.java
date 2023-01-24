package Game.CustomCollections;

import Collections.Exceptions.NonComparableElementException;
import Collections.Lists.LinkedOrderedList;
import Collections.Lists.Node;

public class CustomOrderedListImpl<T> extends LinkedOrderedList<T> implements CustomOrderedList<T> {

    public CustomOrderedListImpl() {
        super();
    }

    @Override
    public void add(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) throw new NonComparableElementException();
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            Node<T> current = head;
            Node<T> previous = null;
            while (current != null && ((Comparable<T>) element).compareTo(current.getElement()) > 0) {
                previous = current;
                current = current.getNext();
            }
            if (previous == null) {
                newNode.setNext(head);
                head = newNode;
            } else {
                newNode.setNext(current);
                previous.setNext(newNode);
            }
            if (newNode.getNext() == null) {
                tail = newNode;
            }
        }
        size++;
        modCount++;
    }

}
