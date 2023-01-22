package Game.CustomCollections;

import Exceptions.EmptyCollectionException;
import Exceptions.NoSuchElementException;
import Exceptions.IllegalArgumentException;
import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.ListADT;

public class HashMap<K, V> implements MapADT<K, V> {

    private final static int DEFAULT_CAPACITY = 25;
    private TwoTypePair<K, V>[] map;
    private int size;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int initialCapacity) {
        this.map = new TwoTypePair[initialCapacity];
        this.size = 0;
    }

    private void resize() {
        TwoTypePair<K, V>[] oldMap = map;
        map = new TwoTypePair[oldMap.length * 2];
        size = 0;
        for (TwoTypePair<K, V> pair : oldMap) {
            try {
                put(pair.getKey(), pair.getValue());
            } catch (IllegalArgumentException ignored) {}
        }
    }

    @Override
    public boolean put(K key, V value) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (size == map.length) {
            resize();
        }
        int index = Math.abs(key.hashCode()) % map.length;
        if (map[index] != null) {
            map[index].setValue(value);
            return false;
        } else {
            map[index] = new TwoTypePair<>(key, value);
            size++;
            return true;
        }
    }

    @Override
    public V remove(K key) throws EmptyCollectionException, NoSuchElementException, IllegalArgumentException {
        V value = get(key);
        int index = Math.abs(key.hashCode()) % map.length;
        map[index] = null;
        size--;
        return value;
    }

    @Override
    public V get(K key) throws EmptyCollectionException, NoSuchElementException, IllegalArgumentException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = Math.abs(key.hashCode()) % map.length;
        if (map[index] == null) {
            throw new NoSuchElementException();
        }
        return map[index].getValue();
    }

    @Override
    public boolean containsKey(K key) throws IllegalArgumentException {
        try {
            get(key);
            return true;
        } catch (NoSuchElementException | EmptyCollectionException e) {
            return false;
        }
    }

    @Override
    public boolean containsValue(V value) throws IllegalArgumentException {
        if (isEmpty()) {
            return false;
        }
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        for (TwoTypePair<K, V> pair : map) {
            if (pair != null && pair.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ListADT<K> getKeys() {
        LinkedUnorderedList<K> keys = new LinkedUnorderedList<>();
        for (TwoTypePair<K, V> pair : map) {
            if (pair != null) {
                keys.addToRear(pair.getKey());
            }
        }
        return keys;
    }

    @Override
    public ListADT<K> getKeys(V value) {
        LinkedUnorderedList<K> keys = new LinkedUnorderedList<>();
        for (TwoTypePair<K, V> pair : map) {
            if (pair != null && pair.getValue().equals(value)) {
                keys.addToRear(pair.getKey());
            }
        }
        return keys;
    }

    @Override
    public ListADT<V> getValues() {
        LinkedUnorderedList<V> values = new LinkedUnorderedList<>();
        for (TwoTypePair<K, V> pair : map) {
            if (pair != null) {
                values.addToRear(pair.getValue());
            }
        }
        return values;
    }

    @Override
    public ListADT<TwoTypePair<K, V>> entrySet() {
        LinkedUnorderedList<TwoTypePair<K, V>> entrySet = new LinkedUnorderedList<>();
        for (TwoTypePair<K, V> pair : map) {
            if (pair != null) {
                entrySet.addToRear(pair);
            }
        }
        return entrySet;
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
    public void clear() {
        map = new TwoTypePair[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public String toString() {
        String result = "HashMap { ";
        for (TwoTypePair<K, V> pair : map) {
            if (pair != null) {
                result += pair + " ";
            }
        }
        return result + "}";
    }


}
