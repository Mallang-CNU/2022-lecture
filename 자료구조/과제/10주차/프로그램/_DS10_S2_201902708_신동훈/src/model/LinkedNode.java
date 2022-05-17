package model;

public class LinkedNode<E> {
    private E _element;
    private LinkedNode<E> _next;

    public E element(){
        return this._element;
    }
    public void setElement(E newElement){
        this._element = newElement;
    }
    public LinkedNode<E> next(){
        return this._next;
    }
    public void setNext(LinkedNode<E> newNext){
        this._next = newNext;
    }

    public LinkedNode(E givenElement, LinkedNode<E> givenNext){
        this._element = givenElement;
        this._next = givenNext;
    }
    public LinkedNode(){
        this._element = null;
        this._next = null;
    }

}
