package model;

/**
 * Created by ShinD on 2022/03/17.
 */
public class ArrayBag<E> {

    private static final int DEFAULT_CAPACITY = 100;
    private int _capacity;
    private int _size;
    private E _elements[];

    /**
     * _capacity 를 DEFAULT_CAPACITY 로 초기화
     * _elements 를 _capacity 만큼 생성
     * _size 를 0으로 초기화
     */
    @SuppressWarnings("unchecked")
    public ArrayBag() {
        this.setCapacity(ArrayBag.DEFAULT_CAPACITY);
        this.setElements((E[]) new Object[_capacity]);
        this.setSize(0);
    }

    /**
     * _capacity 를 givenCapacity 로 초기화
     * _elements 를 _capacity 만큼 생성
     * _size 를 0으로 초기화
     */
    @SuppressWarnings("unchecked")
    public ArrayBag(int givenCapacity) {
        this.setCapacity(givenCapacity);
        this.setElements((E[]) new Object[_capacity]);
        this.setSize(0);
    }


    //== 공개함수 ==//

    /**
     * 현재 가방의 사이즈를 반환한다
     * @return 현재 가방의 사이즈
     */
    public int size() {
        return this._size;
    }

    /**
     * 가방이 현재 비어있는지를 확인한다.
     * @return 가방이 비었으면 true, 비어있지 않다면 false
     */
    private boolean isEmpty() {
        return size()==0;
    }

    /**
     * 가방이 현재 가득찬 상태인지를 반환한다.
     * @return 가방이 가득 차있다면 true, 공간이 남아있다면 false
     */
    public boolean isFull() {
        return size() ==capacity();
    }

    /**
     * 주어진 원소와 동일한 원소가 가방 속에 존재하는지 확인한다
     * @param anElement 포함되어있는지 확인할 원소
     * @return 포함되어 있다면 true, 포함되어있지 않다면 false
     */
    public boolean doesContain(E anElement) {
        for (int i = 0; i < size(); i++) {
            if(anElement.equals(this.elements()[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * 가방 안에 주어진 원소가 몇 개 있는지를 돌려준다.
     * @param anElement 빈도수를 확인할 원소
     * @return 가방 속에 포함된 주어진 원소의 개수
     */
    public int frequencyOf(E anElement) {
        int frequencyCount = 0;
        for (int i = 0; i < size(); i++) {
            if(anElement.equals(elements()[i])){
                frequencyCount++;
            }
        }
        return frequencyCount;
    }

    /**
     * 주어진 원소를 가방에 추가한디.
     * @param anElement 가방에 추가할 원소
     * @return 주어진 가방이 가득 차 있는 경우 false 를 반환한다.
     */
    public boolean add(E anElement){
        if(isFull()) return false; //가방이 가득 차 있는 경우 false 를 반환

        this.elements()[this.size()] = anElement;
        this.setSize(this.size()+1);
        return true;
    }



    /**
     * 주어진 원소를 가방에서 제거한다.
     * @param anElement 가방에서 제거할 원소.
     * @return 가방이 비어있거나, 주어진 원소를 가방에서 찾지 못한다면 false 를 반환한다.
     */
    public boolean remove(E anElement){
        if(isEmpty()) return false;

        int foundIndex = -1;//제거할 원소가 존재하는 위치(인덱스)


        //주어진 원소의 의치를 찾는다
        for (int i = 0; i < size() && foundIndex < 0; i++) {
            if(this.elements()[i].equals(anElement)){//만약 제거할 원소를 찾았다면 해당 원소가 존재하는 인덱스를 저장해준다
                foundIndex = i;
            }
        }

        //제거할 원소를 찾지 못했다면 false 를 반환한다.
        if(foundIndex < 0){
            return false;
        }

        //제거할 원소 이후의 모든 원소들을 앞쪽으로 한 칸씩 이동시킨다
        for (int i = foundIndex; i < size()-1; i++) {
            this.elements()[i] = this.elements()[i+1];
        }

        this.elements()[this.size()-1] = null;//다 쓴 참조를 해제

        this.setSize(this.size()-1); //사이즈를 1 줄인다.
        return true;
    }


    /**
     * 가방을 비운다
     */
    public void clear(){
        for (int i = 0; i < size(); i++) {
            this.elements()[i] = null;//참조를 모두 해제한다
        }
        this.setSize(0);//size를 0으로 설정한다
    }

    /**
     * 주어진 순서에 있는 원소를 돌려준다
     * @param order 찾아낼 순서
     * @return order 위치에 존재하는 요소
     */
    public E elementAt(int order){
        if( 0<= order && order< this.size()){
            return this.elements()[order];
        }
        else {
            return null;
        }
    }








    private int capacity() {
        return this._capacity;
    }

    private void setCapacity(int newCapacity) {
        this._capacity = newCapacity;
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
}
