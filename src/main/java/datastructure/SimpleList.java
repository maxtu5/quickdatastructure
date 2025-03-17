package datastructure;

public interface SimpleList<E> {

    void addFirst(E element);

    E removeMax();

    E removeFirst();

    public void addDescOrder(E element);

}
