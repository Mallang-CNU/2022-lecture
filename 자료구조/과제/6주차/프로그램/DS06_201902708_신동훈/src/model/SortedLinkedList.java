
package model;


/**
 * Created by ShinD on 2022-04-14.
 */

public class SortedLinkedList <E extends Comparable<E>> {
    private int _size;
    private LinkedNode<E> _head;

    // Constructor
    public SortedLinkedList() {
        this.setHead(null);
        this.setSize(0);
    }

    // Getter / Setter
    public int size() {
        return this._size;
    }

    private void setSize(int newSize) {
        this._size = newSize;
    }

    private LinkedNode<E> head() {
        return this._head;
    }

    private void setHead(LinkedNode<E> newHead) {
        this._head = newHead;
    }

    private boolean anElementDoesExistAt(int order) {
        return ((order >= 0) && (order < this.size()));
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public boolean isFull() {
        return false;
    }

    public boolean add(E anElement) {
        if(this.isFull()) {
            return false;
        }
        else {
            LinkedNode<E> nodeForAdd = new LinkedNode<E>(anElement, null);
            if (this.isEmpty()) {
                this.setHead(nodeForAdd);
            }
            else {
                LinkedNode<E> current = this.head();
                LinkedNode<E> previous = null;
                while ( current != null ) {
                    if ( current.element().compareTo(anElement) > 0 ) {
                        break;
                    }
                    previous = current;
                    current = current.next();
                }
                if ( previous == null ) {
                    nodeForAdd.setNext(this.head());
                    this.setHead(nodeForAdd);
                }
                else {
                    previous.setNext(nodeForAdd);
                    nodeForAdd.setNext(current);
                }
            }
            this.setSize(this.size() + 1);
            return true;
        }
    }

    public E max() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            LinkedNode<E> current = this.head();
            LinkedNode<E> previous = null;
            while(current != null) {
                previous = current;
                current = current.next();
            }
            return previous.element();
        }
    }
}
