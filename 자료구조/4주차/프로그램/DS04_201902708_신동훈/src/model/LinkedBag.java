package model;

/**
 * Created by ShinD on 2022/03/17.
 */
public class LinkedBag<E> {


    private int _size;           // 가방이 가지고 있는 원소의 갯수
    private LinkedNode<E> _head; // 연결 체인의 맨 앞 노드를 소유한다.


    /**
     * head Node 를 반환한다.
     * @return head Node
     */
    private LinkedNode<E> head(){
        return this._head;
    }

    /**
     * head Node 를 설정한다
     * @param newHead 설정할 head Node
     */
    private void setHead(LinkedNode<E> newHead) {
        this._head = newHead;
    }

    /**
     * size 를 설정한다.
     * @param newSize 설정할 size
     */
    private void setSize(int newSize) {
        this._size = newSize;
    }



    /**
     * size 는 0이고, head 는 null 인 LinkedBag 객체를 생성한다.
     */
    public LinkedBag() {
        this.setSize(0);
        this.setHead(null);
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
    public boolean isEmpty() {
        return size()==0;
    }

    /**
     * 가방이 가득 찼는지의 여부를 반환한다.
     * @return 항상 false 를 반환
     */
    public boolean isFull() {
        return false;//LinkedBag 은 용량에 제한이 없으므로 항상 false 를 반환한다.
    }


    /**
     * 주어진 원소와 동일한 원소가 가방 속에 존재하는지 확인한다
     * @param anElement 포함되어있는지 확인할 원소
     * @return 포함되어 있다면 true, 포함되어있지 않다면 false
     */
    public boolean doesContain(E anElement) {

        //같지 않다면 반복할 임시 노드인 temp 를 설정
        LinkedNode<E> currentNode = this.head();

        while (currentNode   != null){
            if(currentNode.element().equals(anElement)){
                return true;
            }
            currentNode = currentNode.next();
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

        //반복할 임시 노드인 temp 를 설정
        LinkedNode<E> currentNode = this.head();


        while (currentNode != null){
            if(currentNode.element().equals(anElement)){
                frequencyCount++;
            }
            currentNode = currentNode.next();
        }
        return frequencyCount;
    }




    /**
     * 주어진 순서에 있는 원소를 돌려준다
     * @param order 찾아낼 순서
     * @return order 위치에 존재하는 요소
     */
    public E elementAt(int order){
        if(order == 0){
            return this.head().element();
        }

        int count = 0;
        LinkedNode<E> temp = this.head();

        while (temp.next() != null && count < order){
            temp = temp.next();
            count++;
        }
        return temp.element();
    }




    /**
     * 주어진 원소를 가방에 추가한디.
     * @param anElement 가방에 추가할 원소
     * @return 원소 추가 성공 여부를 반환한다.
     *
     */
    public boolean add(E anElement){

        LinkedNode<E> newHead = new LinkedNode<>(anElement, this.head());
        this.setHead(newHead);
        setSize(this.size() + 1);

        return true;
    }



    /**
     * 주어진 원소를 가방에서 제거한다.
     * @param anElement 가방에서 제거할 원소.
     * @return 가방이 비어있거나, 주어진 원소를 가방에서 찾지 못한다면 false 를 반환한다.
     */
    public boolean remove(E anElement){
        if(isEmpty()) return false;

        LinkedNode<E> previousNode = null;
        LinkedNode<E> currentNode = this.head();

        while (currentNode != null){
            if(currentNode.element().equals(anElement)){
                previousNode.setNext(currentNode.next());
                this.setSize(this.size()-1);
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.next();
        }

        return false;
    }




    /**
     * 가방 속 아무 원소를 삭제한다.
     * @return 삭제된 원소
     */
    public E removeAny(){
        if(isEmpty()) return null;

        //맨 앞 원소를 지운다.
        E removeElement = this.head().element();
        this.setHead(this.head().next());
        return removeElement;
    }


    /**
     * 가방을 비운다
     */
    public void clear(){
        this.setSize(0);
        this.setHead(null);
    }

}
