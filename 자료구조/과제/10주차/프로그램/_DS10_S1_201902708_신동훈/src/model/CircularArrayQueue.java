package model;

import java.math.BigInteger;

/**
 * Created by ShinD on 2022-05-13.
 */
public class CircularArrayQueue<E> implements Queue<E>{
    private static final int DEFAULT_CAPACITY = 100;

    private int _maxLength; //capacity 보다 1 더 큰 수
    private int _frontPosition;
    private int _rearPosition;
    private E[] _elements;

    public CircularArrayQueue(int givenCapacity) {
        this.setFrontPosition(0);
        this.setRearPosition(0);
        this.setMaxLength(givenCapacity+1);
        this.setElements((E[]) new Object[this.maxLength()]);
    }


    private int maxLength() {
        return _maxLength;
    }

    private void setMaxLength(int newMaxLength) {
        this._maxLength = newMaxLength;
    }

    private int frontPosition() {
        return _frontPosition;
    }

    private void setFrontPosition(int newFrontPosition) {
        this._frontPosition = newFrontPosition;
    }

    private int rearPosition() {
        return _rearPosition;
    }

    private void setRearPosition(int newRearPosition) {
        this._rearPosition = newRearPosition;
    }

    private E[] elements() {
        return _elements;
    }

    private void setElements(E[] newElements) {
        this._elements = newElements;
    }


    /**
     * Circular Array Queue 로 구현할 경우 저장 가능한 원소의 최대 개수는,
     * 최대 길이보다 작다.
     * @return  maxLength -1
     */
    public int capacity(){
        return this.maxLength() -1;
    }


    /**
     * 큐의 사이즈를 반환한다
     * @return 큐의 사이즈
     */
    @Override
    public int size() {
        if(this.rearPosition() >= this.frontPosition()){
            return this.rearPosition() - this.frontPosition();
        }

        return this.rearPosition() +this.maxLength() - this.frontPosition();

        //return (this.rearPosition() + this.maxLength() - this.frontPosition()) % this.maxLength();
    }



    @Override
    public boolean isFull() {
        return this.frontPosition()==
                ( (this.rearPosition()+1) % this.maxLength() );
    }

    @Override
    public boolean isEmpty() {
        return this.frontPosition()==this.rearPosition();
    }

    /**
     * 큐의 front 즉, 가장 먼저 들어온 원소를 반환한다.
     * @return 가장 먼저 들어온 원소를 반환한다.
     */
    @Override
    public E front() {
        if (this.isEmpty()) {
            return null;
        }
        return this.elements()[this.frontPosition()+1];
    }
    /**
     * 큐의 rear 즉, 가장 마지막에 들어온 원소를 반환한다.
     * @return  가장 마지막에 들어온 원소를 반환한다.
     */
    @Override
    public E rear() {
        if (this.isEmpty()) {
            return null;
        }
        return this.elements()[this.rearPosition()];
    }


    /**
     * 큐에 원소를 삽입한다.
     * @param anElement 삽입할 원소
     * @return 성공여부
     */
    @Override
    public boolean enQueue(E anElement) {
        if (this.isFull()) {
            return false;
        }
        else {
            this.setRearPosition((this.rearPosition()+1)%this.maxLength());
            this.elements()[this.rearPosition()] = anElement;
            return true;
        }
    }


    /**
     * 큐에서 원소를 삭제한다
     * @return 성공여부
     */
    @Override
    public E deQueue() {
        E frontElement = null;

        if (! this.isEmpty()) {
            this.setFrontPosition((this.frontPosition() + 1) % this.maxLength());
            frontElement = this.elements()[this.frontPosition()];
            this.elements()[this.frontPosition()] = null;
        }
        return frontElement;
    }



    @Override
    public void clear(){
        this.setFrontPosition(0);
        this.setRearPosition(0);
        for(int i=0;i<this.maxLength();i++){
            this.elements()[i] = null;
        }
    }

    @Override
    public E elementAt(int anOrder){
        if(this.isEmpty()){
            return null;
        }
        else{
            return this.elements()[(this.frontPosition()+1+anOrder)%this.maxLength()];
        }
    }

    public Iterator<E> iterator(){
        return new CircularArrayQueueIterator();
    }

    private class CircularArrayQueueIterator implements Iterator<E>{
        private int _nextOrder;

        private int nextOrder(){
            return this._nextOrder;
        }
        private void setNextOrder(int newNextOrder){
            this._nextOrder = newNextOrder;
        }
        private CircularArrayQueueIterator(){
            this.setNextOrder(0);
        }

        @Override
        public boolean hasNext() {
            return this.nextOrder() < CircularArrayQueue.this.size();
        }

        @Override
        public E next() {
            E nextElement = null;
            if (this.hasNext()) {
                nextElement = (E) CircularArrayQueue.this.elementAt(this.nextOrder());
                this.setNextOrder(this.nextOrder() + 1);
            }
            return nextElement;
        }
    }
}
