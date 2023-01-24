package Game.CustomCollections;

import Collections.Exceptions.NonComparableElementException;
import Collections.Lists.OrderedListADT;

public interface CustomOrderedList<T> extends OrderedListADT<T> {

    @Override
    void add(T element) throws NonComparableElementException;

}
