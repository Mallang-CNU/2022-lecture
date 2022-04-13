package model;

/**
 * Created by ShinD on 2022/03/28.
 */
public class LinkedNode<E> {
    // 비공개 인스턴스 변수
    private E _element; // 현재 노드에 있는 코인
    private LinkedNode<E> _next;// 다음 노드


    /**
     * LinkedNode 객체를 생성한다.
     */
    public LinkedNode(){
        this._element = null;   //_element 를 null 로 초기화 한다.
        this._next = null;      //_next 를 null 로 초기화 한다.
    }

    /**
     * 원소 givenElement 를 갖는 LinkedNode 객체를 생성
     * @param givenElement LinkedNode 가 가지고 있을 요소
     */
    public LinkedNode (E givenElement){
        this._element = givenElement;       //_element 를 givenElement 로 초기화 한다.
        this._next = null;                  //_next 를 null 로 초기화 한다.
    }


    /**
     * 원소 givenElement 와 다음 노드 givenNext 를 갖는 LinkedNode 객체를 생성
     * @param givenElement LinkedNode 가 가지고 있을 요소
     * @param givenNext 연결될 다음 노드
     */
    public LinkedNode (E givenElement, LinkedNode<E> givenNext){
        this._element = givenElement;   //_element 를 givenElement 로 초기화 한다.
        this._next = givenNext;         //_next 를 givenNext 로 초기화 한다.
    }

    /**
     * LinkedNode 객체에 있는 element 를 얻는다.
     * @return 가지고 있는 요소
     */
    public E element(){
        return this._element;
    }


    /**
     * LinkedNode 객체의 다음 LinkedNode 객체를 얻는다.
     * @return 연결된 다음 Node
     */
    public LinkedNode<E> next(){
        return this._next;
    }

    /**
     * LinkedNode 에 있는 element 를 newElement 로 변경한다.
     * @param newElement 변경할 요소
     */
    public void setElement (E newElement){
        this._element = newElement;
    }

    /**
     * LinkedNode 객체의 next 를 newNext 로 변경한다.
     * @param newNext 연결될 다음 Node
     */
    public void setNext (LinkedNode<E> newNext){
        this._next = newNext;
    }
}
