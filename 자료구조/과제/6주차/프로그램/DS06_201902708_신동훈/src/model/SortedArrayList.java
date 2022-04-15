package model;

/**
 * Created by ShinD on 2022/04/13.
 */
public class SortedArrayList <E extends Comparable<E>>{
    // Constants
    private static final int DEFAULT_CAPACITY = 100 ;
    // Private Instances
    private int _capacity ;
    private int _size ;
    private E[] _elements ;

    // Getter/Setter
    public int capacity() {
        return this._capacity;
    }
    public void setCapacity(int newCapacity) {
        this._capacity = newCapacity;
    }
    public int size() {
        return this._size;
    }
    public void setSize(int newSize) {
        this._size = newSize;
    }
    public E[] elements() {
        return this._elements;
    }
    public void setElements (E[] newElements) {
        this._elements = newElements;
    }

    // Constructors
    public SortedArrayList () {
        this (SortedArrayList.DEFAULT_CAPACITY) ;
    }

    @SuppressWarnings("unchecked")
    public SortedArrayList (int givenCapacity) {
        this.setCapacity (givenCapacity) ;
        this.setElements ( (E[]) new Comparable[this.capacity()] );
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
     * 주어진 순서의 위치를 빈칸으로 만들기 위해
     * 주어진 순서에 존재하는 원소부터 마지막 원소까지 옆으로 한 칸씩 이동시킨다.
     * @param aPosition 빈 공간을 만들 위치
     */
    private void makeRoomAt(int aPosition) {
        for (int i = this.size(); i > aPosition; i--) {
            this.elements()[i] = this.elements()[i-1];
        }
    }


    public boolean add(E anElement){
        if (this.isFull()) {
            return false;
        }
        int start = 0;
        int end = this.size()-1;

        while (start < end){
            int mid = (start + end) >>> 1;

            if (this.elements()[mid].compareTo(anElement) > 0){//중간값이 더 큰 경우
                end = mid -1;
            }else {
                start = mid + 1;
            }

        }
        int anOrder = end+1;

        this.makeRoomAt(anOrder);
        this.elements()[anOrder] = anElement;
        this.setSize(this.size() +1);
        return true;


    }

    public E max(){
        if ( this.isEmpty() ) {
            return null ;
        }
        else {
            return this.elements()[this.size()-1] ;
        }
    }


}
