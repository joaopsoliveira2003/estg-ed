package Collections.HashTables;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;

import java.util.Iterator;

public class HashSet<T> implements SetADT<T> {

    private final static int DEFAULT_CAPACITY = 25;
    private T[] set;
    private int size;

    public HashSet() {
        this(DEFAULT_CAPACITY);
    }

    public HashSet(int initialCapacity) {
        this.set = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    private void resize() {
        T[] oldSet = set;
        set = (T[]) new Object[oldSet.length * 2];
        size = 0;
        for (T element : oldSet) {
            add(element);
        }
    }


    @Override
    public void add(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (size == set.length) {
            resize();
        }
        int index = Math.abs(element.hashCode()) % set.length;
        if (set[index] != null) {
            set[index] = element;
        } else {
            set[index] = element;
            size++;
        }
    }

    @Override
    public T remove(T element) throws IllegalArgumentException, NoSuchElementException, EmptyCollectionException {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (isEmpty()) {
            throw new EmptyCollectionException("Set is empty");
        }
        int index = Math.abs(element.hashCode()) % set.length;
        T removed = set[index];
        if (removed == null) {
            throw new NoSuchElementException("Element not found");
        }
        set[index] = null;
        size--;
        return removed;
    }

    @Override
    public T find(T element) throws IllegalArgumentException, EmptyCollectionException, NoSuchElementException {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (isEmpty()) {
            throw new EmptyCollectionException("Set is empty");
        }
        int index = Math.abs(element.hashCode()) % set.length;
        T found = set[index];
        if (found == null) {
            throw new NoSuchElementException("Element not found");
        }
        return found;
    }

    @Override
    public boolean contains(T target) throws IllegalArgumentException {
        if (target == null) {
            throw new IllegalArgumentException("Target cannot be null");
        }
        if (isEmpty()) {
            return false;
        }
        int index = Math.abs(target.hashCode()) % set.length;
        return set[index] != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        UnorderedListADT<T> list = new LinkedUnorderedList<>();
        for (T element : set) {
            if (element != null) {
                list.addToRear(element);
            }
        }
        return list.iterator();
    }
}
