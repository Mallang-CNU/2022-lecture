package model;

import java.util.Iterator;

/**
 * Created by ShinD on 2022/04/01.
 */
public class UnsortedArrayList <E extends Comparable<E>>{
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
    public UnsortedArrayList(int givenCapacity) {
        this.setCapacity(givenCapacity);
        this.setElements((E[])new Comparable[this.capacity()]);
    }

    public UnsortedArrayList(){
        this(UnsortedArrayList.DEFAULT_CAPACITY);
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
     * 배열의 맨 끝에 원소를 삽입한다.
     * @param anElement 주어진 원소
     * @return 삽입에 성공하면 true
     */
    public boolean add(E anElement) {
        if (this.isFull()) {
            return false;
        }
        this.elements()[this.size()] = anElement;
        this.setSize(this.size() + 1);
        return true;

    }

    public E max(){
        if ( this.isEmpty() ) {
            return null ;
        }

        E max = elements()[0];
        for (int i = 0; i < size(); i++) {
            if(elements()[i].compareTo(max) > 0){
                max = elements()[i];
            }
        }
        return max;
    }




}
