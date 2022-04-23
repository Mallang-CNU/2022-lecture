package model;

/**
 * Created by ShinD on 2022/04/22.
 */
public class UnsortedArrayList<E extends Comparable<E>>
{

    private static final int DEFAULT_CAPACITY = 100;

    private int _capacity;
    private int _size;
    private E[] _elements;

    // Getters / Setters
    public int capacity() {
        return this._capacity;
    }

    public void setCapacity(int newCapacity) {
        this._capacity = newCapacity;
    }

    public int size() {
        return this._size;
    }

    private void setSize(int newSize) {
        this._size = newSize;
    }

    private E[] elements() {
        return this._elements;
    }

    private void setElements(E[] newElements) {
        this._elements = newElements;
    }

    // Constructor
    @SuppressWarnings("unchecked")
    public UnsortedArrayList(int givenCapacity) {
        this.setCapacity(givenCapacity);
        this.setElements((E[]) new Comparable[this.capacity()]);
    }

    public UnsortedArrayList() {
        this(UnsortedArrayList.DEFAULT_CAPACITY);
    }

    /**
     * 특정 인덱스에 값을 집어넣기 위해 빈 공간을 만든다.
     * @param aPosition 공간을 만들 자리
     */
    private void makeRoomAt(int aPosition) {
        for (int i = this.size(); i > aPosition; i--) {
            this.elements()[i] = this.elements()[i - 1];
        }
    }

    /**
     * 특정 인덱스의 원소를 제거할 경우, 그 이후 위치의 원소들을 한칸씩 땡겨줄 때 사용한다.
     * @param aPosition 채울 인덱스
     */
    private void removeGapAt(int aPosition) {
        for (int i = aPosition + 1; i < this.size(); i++) {
            this.elements()[i - 1] = this.elements()[i];
        }
        this.elements()[this.size() - 1] = null;
    }


    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public boolean isFull() {
        return (this.size() == this._capacity);
    }

    /**
     * 원소의 존재를 확인한다.
     * @param anElement 존재를 확인할 원소
     * @return 있으면 true
     */
    public boolean doesContain (E anElement) {
        return (this.orderOf(anElement) >= 0 ) ;
    }

    /**
     * 원소의 순서를 반환한다.
     * @param anElement 순서를 찾을 원소
     * @return 원소의 순서, 없으면 -1
     */
    public int orderOf (E anElement) {

        int order = -1;// 가방이 비어있다면 return -1;

        for( int index = 0; index < this.size() && order <0; index++) {
            if(this.elements()[index].equals(anElement)) {
                order = index;
            }
        }
        return order;
    }

    /**
     * 주어진 순서에 있는 원소를 반환한다.
     * @param anOrder 순서
     * @return 순서에 존재하는 원소, 없으면 null
     */
    public E elementAt(int anOrder) {
        if(anOrder < 0 || anOrder >= this.size()) {
            return null;
        }
        else {
            return this.elements()[anOrder];
        }
    }

    /**
     * 특정 인덱스에 원소를 삽입한다.
     * @param anOrder 삽입할 인덱스
     * @param anElement 삽입할 원소
     */
    protected void setElementAt (int anOrder, E anElement) {
        if(anOrder < 0 || anOrder >= this.size()) {
            return ;
        }
        else {
            this.elements()[anOrder] = anElement;
        }
    }

    /**
     * 원소를 맨 앞에 삽입한다.
     * @param anElement 삽입할 원소
     * @return 성공시 true
     */
    public boolean addToFirst(E anElement) {
        if(this.isFull()) {
            return false;
        }
        else {
            this.makeRoomAt(0);
            this.elements()[0] = anElement;
            this.setSize(this.size() +1);
            return true;
        }
    }

    /**
     * 원소를 가장 마지막에 삽입한다.
     * @param anelement 삽입할 원소
     * @return 성공하면 true
     */
    public boolean addToLast (E anelement) {
        if(this.isFull()) {
            return false;
        }
        else {
            this.elements()[this.size()] = anelement;
            this.setSize(this.size() +1);
            return true;
        }
    }

    /**
     * 원소를 임의의 순서(ArrayList의 경우 가장 마지막)에 삽입한다.
     * @param anElement 삽입할 원소
     * @return 성공시 true
     */
    public boolean add(E anElement) {
        return this.addToLast(anElement);
    }

    /**
     * 가장 첫 원소를 삭제한다.
     * @return 삭제한 원소
     */
    public E removeFirst() {
        if(this.isEmpty()) {
            return null;
        }
        else {
            E removedElement = this.elements()[0];
            this.removeGapAt(0);
            this.setSize(this.size() -1);
            return removedElement;
        }
    }

    /**
     * 맨 마지막 원소를 삭제한다.
     * @return 삭제한 원소
     */
    public E removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            E removedElement = this.elements()[this.size()];
            this.setSize(this.size() -1);
            return removedElement;
        }
    }

    /**
     * 임의의 순서(ArrayList의 경우 가장 마지막)의 원소를 삭제한다.
     * @return 삭제한 원소
     */
    public E removeAny() {
        return this.removeLast();
    }

    /**
     * 주어진 원소를 삭제한다.
     * @param anelement 삭제할 원소
     * @return 성공시 true
     */
    public boolean remove (E anelement) {
        //위치 찾기
        int foundIndex = this.orderOf(anelement);
        //존재하면 삭제
        if(foundIndex < 0) {
            return false; //not found.
        }
        else {
            //삭제 후 배열 정렬
            this.removeGapAt(foundIndex);
            this.setSize(this.size() -1);
            this.elements()[this.size()] = null;
            return true;
        }
    }

    /**
     * 반복자룰 반환한다.
     * @return 반복자 객체
     */
    public Iterator<E> iterator() {
        return (new ListIterator());
    }

    //inner class Iterator
    private class ListIterator implements Iterator<E> {
        //private value
        private int _nextPosition;

        //Getter / Setter
        private int nextPosition() {
            return this._nextPosition;
        }

        private void setNextPosition(int newNextPosition) {
            this._nextPosition = newNextPosition;
        }

        private ListIterator() {
            this.setNextPosition(0);
        }



        @Override
        public boolean hasNext() {
            return (this.nextPosition() < UnsortedArrayList.this.size());
        }

        public E next() {
            E nextElement = null;
            if (this.hasNext()) {
                nextElement = UnsortedArrayList.this.elements()[this.nextPosition()];
                this.setNextPosition(this.nextPosition() + 1);
            }
            return nextElement;
        }
    }


}