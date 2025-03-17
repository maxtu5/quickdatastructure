package quick;

import datastructure.SimpleList;
import datastructure.SimpleSynchronizedLinkedList;

import java.util.Comparator;

public class QuickPopDataStructure<E> implements QuickDataStructure<E> {

    private final SimpleList<E> dataStorage;

    public QuickPopDataStructure() {
        dataStorage = new SimpleSynchronizedLinkedList<>();
    }

    public QuickPopDataStructure(Comparator<E> comparator) {
        dataStorage = new SimpleSynchronizedLinkedList<>(comparator);
    }

    @Override
    public E pop() {
        return dataStorage.removeFirst();
    }

    @Override
    public void push(E element) {
        dataStorage.addDescOrder(element);
    }

}
