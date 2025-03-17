package quick;

import datastructure.SimpleList;
import datastructure.SimpleSynchronizedLinkedList;

import java.util.Comparator;

public class QuickPushDataStructure<E> implements QuickDataStructure<E> {

    private final SimpleList<E> dataStorage;

    public QuickPushDataStructure() {
        dataStorage = new SimpleSynchronizedLinkedList<>();
    }

    public QuickPushDataStructure(Comparator<E> comparator) {
        dataStorage = new SimpleSynchronizedLinkedList<>(comparator);
    }

    @Override
    public E pop() {
        return dataStorage.removeMax();
    }

    @Override
    public void push(E element) {
        dataStorage.addFirst(element);
    }

}
