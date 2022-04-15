package model;

/**
 * Created by ShinD on 2022/04/03.
 */
public class LinkedNode<E> {
    private E _element;
    private LinkedNode<E> _next;


    private LinkedNode() {
        this(null, null);
    }
    public LinkedNode(E givenElement, LinkedNode<E> givenNext) {
        this.setElement(givenElement);
        this.setNext(givenNext);
    }

    public E element() {
        return this._element;
    }

    public void setElement(E newElement) {
        this._element = newElement;
    }

    public LinkedNode<E> next() {
        return this._next;
    }

    public void setNext(LinkedNode<E> newNext) {
        this._next = newNext;
    }
}
