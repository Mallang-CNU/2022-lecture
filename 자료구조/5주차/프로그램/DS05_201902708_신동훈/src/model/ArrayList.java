package model;

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

    public E[] getElements() {
        return _elements;
    }

    public void setElements(E[] newElements) {
        this._elements = newElements;
    }

    public ArrayList(int givenCapacity) {
        this.setCapacity(givenCapacity);
        this.setElements((E[])new Object[this.capacity()]);
    }

    public ArrayList(){
        this(ArrayList.DEFAULT_CAPACITY);
    }

}
