package model;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by ShinD on 2022/04/30.
 */
public class ArrayList<E extends Comparable<E>> implements Stack<E> {
    // Constants
    private static final int DEFAULT_CAPACITY = 5;

    // Private Instance Variables
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

    @Override
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
    public ArrayList() {
        this(ArrayList.DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int givenCapacity) {
        this.setCapacity(givenCapacity);
        this.setElements((E[]) new Comparable[this.capacity()]);
    }

    //private methods
    private void makeRoomAt(int aPosition) {
        for(int i = this.size(); i > aPosition; i--) {
            this.elements()[i] = this.elements()[i - 1];
        }
    }

    private void removeGapAt(int aPosition) {
        for(int i = aPosition + 1; i < this.size(); i++) {
            this.elements()[i - 1] = this.elements()[i];
        }
        this.elements()[this.size() - 1] = null;
    }

    // public methods

	/**
	 * 리스트가 비었는지 확인한다.
	 * @return 비었으면 true
	 */
	@Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

	/**
	 * 리스트가 가득 찼는지 확인한다.
	 * @return 가득 찼으면 true
	 */
    @Override
    public boolean isFull() {
        return (this.size() == this._capacity);
    }

	/**
	 * 주어진 원소가 존재하는지 확인한다.
	 * @param anElement 존재여부를 확인할 원소
	 * @return 존재한다면 true
	 */
	public boolean doesContain(E anElement) {
        return (this.orderOf(anElement) >= 0);
    }



	/**
	 * 주어진 원소가 들어있는 순서를 받환한다.
	 * @param anElement 순서를 찾을 원소
	 * @return 없다면 -1
	 */
    public int orderOf(E anElement) {
		return IntStream.range(0, this.size())
				.filter(i-> this.elements()[i].equals(anElement))
				.findFirst()
				.orElse(-1);
    }


	/**
	 * 주어진 인덱스의 원소를 반환한다.
	 * @param anOrder 원소의 인덱스
	 * @return 인덱스에 해당하는 원서
	 */
	public E elementAt(int anOrder) {
         return (anOrder < 0 || anOrder >= this.size())
				 ? null
				 : this.elements()[anOrder];
    }

	/**
	 * 주어진 순서에 원소를 배치한다.
	 * @param anOrder 순서
	 * @param anElement 원소
	 */
	protected void setElementAt(int anOrder, E anElement) {
        if (anOrder >= 0 && anOrder < this.size()) {
			this.elements()[anOrder] = anElement;
        }
    }


	/**
	 * 맨 처음 위치에 원소를 삽입한다.
	 * @param anElement 삽입할 원소
	 * @return 성공하면 true
	 */
    public boolean addToFirst(E anElement) {
        if (this.isFull()) {
            return false;
        }

		this.makeRoomAt(0);
		this.elements()[0] = anElement;
		this.setSize(this.size() + 1);
		return true;
    }

	/**
	 * 맨 마지막 위치에 원소를 삽입한다.
	 * @param anElement 삽입할 원소
	 * @return 성공하면 true
	 */
    public boolean addToLast(E anElement) {
        if (this.isFull()) {
            return false;
        }
		this.elements()[this.size()] = anElement;
		this.setSize(this.size() + 1);
		return true;
    }

	/**
	 * 임의의 위치(여기서는 마지막)에 원소를 삽입한다.
	 * @param anElement 임의의 위치
	 * @return 성공하면 true
	 */
    public boolean add(E anElement) { // 마지막 자리 삽입
        return this.addToLast(anElement);
    }

	/**
	 * 맨 앞 원소를 삭제한다
	 * @return 삭제한 원소
	 */
	public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
		E removedElement = this.elements()[0];
		this.removeGapAt(0);
		this.setSize(this.size() - 1);
		return removedElement;
    }


	/**
	 * 맨 마지막 원소를 삭제한다
	 * @return 삭제한 원소
	 */
    public E removeLast() {
        if (this.isEmpty()) {
            return null;
        }
		E removedElement = this.elements()[this.size() - 1];
		this.setSize(this.size() - 1);
		return removedElement;
    }

	/**
	 * 임의의(여기서는 맨 마지막) 원소를 삭제한다
	 * @return 삭제한 원소
	 */
    public E removeAny() {
        return this.removeLast();
    }

	/**
	 * 주어진 원소를 삭제한다.
	 * @param anElement 삭제할 원소
	 * @return 성공하면 true
	 */
    public boolean remove(E anElement) {
        //위치 찾기
        int foundIndex = this.orderOf(anElement);
        //존재하면 삭제
        if (foundIndex < 0) {
            return false; //not found.
        } else {
            //삭제 후 배열 정렬
            this.removeGapAt(foundIndex);
            this.setSize(this.size() - 1);
            this.elements()[this.size()] = null;
            return true;
        }
    }



    @Override
    public boolean push(E anElement) {
        return this.addToLast(anElement);
    }

    @Override
    public E pop() {
        return this.removeLast();
    }

    @Override
    public E peek() {
		if (this.isEmpty()) {
			return null;
		}
		else {
			return this.elementAt(this.size() - 1);
		}
    }


    @Override
    public void clear() {

		final Object[] es = this.elements();
		int size = this.size();
		for (int to = size, i = size = 0; i < to; i++)
			es[i] = null;

    }


}