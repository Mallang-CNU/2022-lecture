package model;

import java.util.Iterator;

/**
 * Created by ShinD on 2022/04/01.
 */
public class ArrayList <E>{
    //Constant
    private static final int DEFAULT_CAPACITY = 100;

    //Private Instance Variables
    private int _capacity;
    private int _size;
    private E[] _elements;

    //Getter/Setter
    public int capacity(){
        return this._capacity;
    }
    public void setCapacity(int newCapacity) {
        this._capacity = newCapacity;
    }

    public int size() {
        return _size;
    }

    public void setSize(int newSize) {
        this._size = newSize;
    }

    public E[] elements() {
        return _elements;
    }

    public void setElements(E[] newElements) {
        this._elements = newElements;
    }



    //Constructor
    public ArrayList(int givenCapacity) {
        this.setCapacity(givenCapacity);
        this.setElements((E[])new Object[this.capacity()]);
    }

    public ArrayList(){
        this(ArrayList.DEFAULT_CAPACITY);
    }


    /**
     * ArrayList 가 비어있는지 확인한다.
     * @return 비어있다면 true
     */
    public boolean isEmpty(){
        return (this.size() == 0);
    }

    /**
     * ArrayList 가 가득 찬 상태인지 확인한다.
     * @return 가득 차 있다면 true
     */
    public boolean isFull(){
        return (this.size() == this.capacity());
    }


    /**
     * 매개변수로 들어온 원소에 대해, 해당 원소가 ArrayList 안에 존재하는지
     * 여부를 반환한다.
     * @param anElement 존재하는지 확인할 원소
     * @return 존재한다면 true
     */
    public boolean doesContain(E anElement){
        for(int i =0; i < this.size(); i++){//처음부터 ArrayList 의 size 만큼 배열을 순환
            if(this.elements()[i].equals(anElement)){//equals 를 통해 같은 원소인지 비교
                return true;
            }
        }
        return false;
    }


    /**
     * 매개변수로 들어온 원소가 ArrayList 속에 몇 개 존재하는지 확인한다.
     * @param anElement 빈도를 확인할 원소
     * @return 빈도수
     */
    public int frequencyOf(E anElement){
        int frequencyCount = 0;
        for (int i =0; i < this.size(); i++){
            if(this.elements()[i].equals(anElement)){
                frequencyCount++;
            }
        }
        return frequencyCount;
    }


    /**
     * 매개변수로 주어진 숫자에 해당하는 ArrayList 의 원소를 가져온다.
     * @param order 인덱스 번호
     * @return 해당 인덱스 번호에 해당하는 요소
     */
    public E elementAt(int order) {
        if(this.anElementDoesExistAt(order)){
            int position = order;
            return this.elements()[position];
        }
        else {
            return null;
        }
    }

    /**
     * 입력으로 주어진 인덱스 번호가 올바른지 확인한다.
     * @param order 인덱스 번호
     * @return 올바르다면 true
     */
    private boolean anElementDoesExistAt(int order) {
        return ((order >= 0) && (order < this.size()));
    }

    /**
     * ArrayList 속 존재하는 가장 처음 요소를 반환한다
     * @return 가장 처음 요소
     */
    public E first(){
        if(this.isEmpty()){
            return null;
        }
        else {
            return this.elements()[1];
        }
    }

    /**
     * ArrayList 속 존재하는 가장 마지막 요소를 반환한다
     * @return 가장 마지막 요소
     */
    public E last(){
        if(this.isEmpty()){
            return null;
        }
        else {
            return this.elements()[this.size() - 1];
        }
    }

    /**
     * 주어진 원소가 존재하는 인덱스의 번호를 반환한다.
     * @param anElement 찾을 원소
     * @return 원소의 인덱스 혹은 존재하지 않는다면 -1
     */
    public int orderOf(E anElement){
        //원소 anElement 가 리스트 안에 존재하면 해당 위치를 돌려준다.
        //존재하지 않으면 -1을 돌려준다
        for (int order = 0; order < this.size(); order ++){
            if (this.elements()[order].equals(anElement)) {
                return order;
            }
        }
        return -1;
    }


    /**
     * 주어진 순서에 주어진 원소를 삽입한다.
     * @param anElement 주어진 원소
     * @param anOrder 주어진 순서
     * @return 삽입에 성공하면 true
     */
    public boolean addTo(E anElement, int anOrder) {
        if (this.isFull()) {
            return false;
        } else if (anOrder < 0 || anOrder > this.size()) {
            return false;
        } else {
            this.makeRoomAt(anOrder);
            this.elements()[anOrder] = anElement;
            this.setSize(this.size() +1);
            return true;
        }
    }

    /**
     * 주어진 순서의 위치를 빈칸으로 만들기 위해
     * 주어진 순서에 존재하는 원소부터 마지막 원소까지 옆으로 한 칸씩 이동시킨다.
     * @param aPosition 빈 공간을 만들 위치
     */
    private void makeRoomAt(int aPosition) {
        for (int i = this.size(); i > aPosition; i--) {
            this.elements()[i] = this.elements()[i-1];
        }
    }


    /**
     * ArrayList 의 가장 첫번째 부분에 주어진 원소를 삽입한다.
     * @param anElement 삽입할 원소
     * @return 삽입에 성공하면 true
     */
    public boolean addToFirst(E anElement){
        return this.addTo(anElement, 0);
    }

    /**
     * ArrayList 의 가장 마지막 부분에 주어진 원소를 삽입한다.
     * @param anElement 삽입할 원소
     * @return 삽입에 성공하면 true
     */
    public boolean addToLast(E anElement){
        return this.addTo(anElement, this.size());
    }


    /**
     * 가장 효과적으로 삽입할 수 있는 곳에 주어진 원소를 삽입한다.
     * ArrayList 의 경우 가장 마지막 부분에 주어진 원소를 삽입한다.
     * @param anElement 삽입할 원소
     * @return 삽입에 성공하면 true
     */
    public boolean add(E anElement){
        return this.addToLast(anElement);
    }

    /**
     * 주어진 순서에 존재하는 원소를 삭제한다.
     * @param order 삭제할 원소의 순서
     * @return 삭제된 원소, 없다면 null
     */
    public E removeFrom(int order) {
        //주어진 순서 order 에 원소가 없으면 null 을 return 한다
        //원소가 있으면 리스트에서 제거하여 return 한다.

        E removedElement = null;
        if (this.anElementDoesExistAt(order)) {
            //리스트가 empty 이면 이 조건은 false 를 얻는다.
            //따라서, 별도의 empty 검사를 하지 않아도 안전하다.
            removedElement = this.elements()[order];
            this.removeGapAt(order);
            this.setSize(this.size() -1);
        }
        return removedElement;
    }


    /**
     * 주어진 순서에 해당하는 빈 공간을 제거한다.
     * @param position 주어진 순서
     */
    private void removeGapAt(int position) {

        // 해당 메서드가 불러졌을 때 리스트는 empty 가 아니다.
        // 즉 언제나 (this.size() >0)
        // position 은 valid, 즉 언제나 (0 <= position < this.size())

        for (int i = position + 1; i < this.size(); i++) {
            this.elements()[i-1] = this.elements()[i];
        }

        //마지막 원소에 대한 참조를 지워 GC의 대상이 되도록 한다.
        this.elements()[this.size()-1] = null;

    }


    /**
     * ArrayList 의 가장 처음 원소를 삭제한다.
     * @return 삭제한 원소
     */
    public E removeFirst(){
        return removeFrom(0);
    }

    /**
     * ArrayList 의 가장 마지막 원소를 삭제한다.
     * @return 삭제한 원소
     */
    public E removeLast(){
        return removeFrom(this.size() -1);
    }

    /**
     * 아무 원소나 삭제한다.
     * ArrayList 의 경우 효율적으로 삭제하기 위해 가장 마지막 원소를 삭제한다.
     */
    public E removeAny(){
        return removeLast();
    }


    /**
     * 주어진 순서의 원소를 주어진 원소로 변경한다.
     * @param anElement 변경할 원소
     * @param anOrder 변경할 순서
     * @return 변경에 성공하면 true
     */
    public boolean replaceAt(E anElement, int anOrder) {
        if(!anElementDoesExistAt(anOrder)){
            return false;
        }
        this.elements()[anOrder] = anElement;
        return true;
    }



    public Iterator<E> iterator() {
        return (new ListIterator());
    }

    private class ListIterator implements Iterator<E> {
        private int _nextPosition;

        public int nextPosition() {
            return _nextPosition;
        }

        public void setNextPosition(int newNextPosition) {
            this._nextPosition = newNextPosition;
        }

        private ListIterator(){
            this.setNextPosition(0);
        }

        @Override
        public boolean hasNext() {
            return (this.nextPosition() < ArrayList.this.size());
        }

        @Override
        public E next() {
            E nextElement = null;
            if(this.hasNext()){
                nextElement = ArrayList.this.elements()[this.nextPosition()];
                this.setNextPosition(this.nextPosition() +1);
            }
            return nextElement;
        }
    }


}
