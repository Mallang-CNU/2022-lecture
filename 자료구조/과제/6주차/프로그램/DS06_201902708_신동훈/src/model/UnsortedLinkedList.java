package model;

/**
 * Created by ShinD on 2022/04/03.
 */
public class UnsortedLinkedList<E extends Comparable<E>> {

    private int _size;//리스트가 가지고 있는 원소의 개수
    private LinkedNode<E> _head;//LinkedChain 의 맨 앞 노드

    //Getters/Setters
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

    //Constructor
    public UnsortedLinkedList() {
        this.setHead(null);
        this.setSize(0);
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public boolean isFull() {
        return false;
    }

    /**
     * 원소를 삽입한다.
     * @param anElement 삽입할 원소
     * @return 삽입에 성공하면 true
     */
    public boolean add(E anElement){
        LinkedNode<E> nodeForAdd = new LinkedNode<>(anElement, null);
        nodeForAdd.setNext(this.head());//추가할 노드의 다음 노드에 현재의 head Node 를 설정한다.
        this.setHead(nodeForAdd);//LinkedList 의 Head Node 를 추가할 노드로 설정해준
        this.setSize(this.size() + 1);
        return true;
    }


    public E max() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            LinkedNode<E> current = this.head();
            LinkedNode<E> previous = null;

            E max = current.element();

            while(current != null) {
                previous = current;
                if(current.element().compareTo(max) > 0){
                    max = current.element();
                }
                current = current.next();
            }
            return max;
        }
    }



}
