package model;

/**
 * Created by ShinD on 2022-05-13.
 */
public interface Queue<T> {
    public int size();
    public boolean isFull();
    public boolean isEmpty();
    public boolean enQueue(T item);
    public T deQueue();

    public T front();
    public T rear();

    public void clear();

    public T elementAt(int anOrder);
    public Iterator<T> iterator();
}
