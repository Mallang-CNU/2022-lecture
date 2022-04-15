package model;

/**
 * Created by ShinD on 2022/04/03.
 */
public class LinkedList<E> {

    private int _size;//리스트가 가지고 있는 원소의 개수
    private LinkedNode<E> _head;//LinkedChain 의 맨 앞 노드

    //Getters/Setters
    public int size() {
        return this._size;
    }

    private void setSize(int newSize) {
        this._size = newSize;
    }

    private LinkedNode<E> head() {
        return this._head;
    }

    private void setHead(LinkedNode<E> newHead) {
        this._head = newHead;
    }

    private boolean anElementDoesExistAt(int order) {
        return ((order >= 0) && (order < this.size()));
    }

    //Constructor
    public LinkedList() {
        this.setHead(null);
        this.setSize(0);
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public boolean isFull() {
        return false;
    }

    /**
     * 주어진 순서의 원소를 반환한다
     *
     * @param order 원소의 순서
     * @return 주어진 순서에 있는 원소
     */
    public E elementAt(int order) {
        if (this.anElementDoesExistAt(order)) {
            LinkedNode<E> currentNode = this.head();
            int nodeCount = 0;
            while (nodeCount < order) {
                currentNode = currentNode.next();
                nodeCount++;
            }
            return currentNode.element();
        } else {
            return null;
        }
    }


    /**
     * 리스트의 맨 앞 원소를 반환한다
     *
     * @return 맨 앞 원소
     */
    public E first() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.elementAt(0);
        }
    }

    /**
     * 리스트의 마지막 원소를 반환한다
     *
     * @return 마지막 원소
     */
    public E last() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.elementAt(this.size() - 1);
        }
    }

    /**
     * 주어진 원소가 존재하는 위치를 반환한다
     *
     * @param anElement 찾을 원소
     * @return 원소의 위치, 존재하지 않으면 -1
     */
    public int orderOf(E anElement) {
        int order = 0;
        LinkedNode<E> currentNode = this.head();
        while ((currentNode != null) && (!currentNode.element().equals(anElement))) {
            order++;
            currentNode = currentNode.next();
        }
        //찾지 못한 경우
        if(currentNode == null){
            return -1;
        }else {
            return order;
        }
    }

    /**
     * 주어진 원소가 들어있는지 여부를 반환한다.
     * @param anElement 찾을 원소
     * @return 있다면 true
     */
    public boolean doesContain(E anElement){
        LinkedNode<E> currentNode = this.head();

        while (currentNode != null){
            if(currentNode.element().equals(anElement)){
                return true;
            }
            currentNode = currentNode.next();
        }
        return false;
    }

    /**
     * 주어진 순서에 주어진 원소를 삽입한다.
     * @param anElement 삽입할 원소
     * @param order 삽입할 순서
     * @return 삽입에 성공하면 true
     */
    public boolean addTo(E anElement, int order) {
        if ((order < 0) || (order > this.size())) {//order 가 유효한지 검사
            return false;
        }
        else if (this.isFull()) {
            return false;
        }
        else {
            LinkedNode<E> nodeForAdd = new LinkedNode<>(anElement, null);
            if (order == 0){//맨 앞 순서에 삽입, 앞 (previous) 노드가 존재하지 않는다.
                nodeForAdd.setNext(this.head());//추가할 노드의 다음 노드에 현재의 head Node 를 설정한다.
                this.setHead(nodeForAdd);//LinkedList 의 Head Node 를 추가할 노드로 설정해준
            }
            else {//순서가 맨 앞이 아니기 때문에, 반드시 앞 노드가 존재한다.
                LinkedNode<E> previousNode = this.head();
                for (int i = 1; i < order; i++) {//0이 아니라 1부터 시작하여 삽입할 위치의 앞 노드를 찾는
                    previousNode = previousNode.next();
                }
                nodeForAdd.setNext(previousNode.next());
                previousNode.setNext(nodeForAdd);
            }
            this.setSize(this.size() + 1);
            return true;
        }
    }


    /**
     * 주어진 원소를 맨 앞에 삽입한다.
     * @param anElement 주어진 원소
     * @return 삽입에 성공하면 true
     */
    public boolean addToFirst(E anElement){
        return this.addTo(anElement, 0);
    }

    /**
     * 주어진 원소를 맨 뒤에 삽입한다.
     * @param anElement 주어진 원소
     * @return 삽입에 성공하면 true
     */
    public boolean addToLast(E anElement){
        return this.addTo(anElement, this.size());
    }

    /**
     * 주어진 원소를 임의의 위치에 삽입한다.
     * LinkedList의 경우 맨 앞에 삽입하는 경우 시간이 가장 짧게 걸리므로
     * 맨 앞에 삽입한다.
     * @param anElement 삽입할 원소
     * @return 삽입에 성공하면 true
     */
    public boolean add(E anElement){
        return this.addToFirst(anElement);
    }

    /**
     * 주어진 순서의 원소를 삭제한다
     * @param order 삭제할 원소의 순서
     * @return 삭제된 원소
     */
    public E removeFrom(int order) {
        if (!this.anElementDoesExistAt(order)) {//삭제할 원소가 없거나 잘못된 위치
            return null;
        }
        else {
            //리스트는 비어 있지 않으며, 삭제할 원소가 있음
            LinkedNode<E> removeNode = null;


            if (order == 0) {//삭제할 원소가 맨 앞 원소
                removeNode = this.head();
                this.setHead(this.head().next());
            }

            else {//삭제할 원소의 위치는 맨 앞이 아니며, 따라서 원소가 두 개 이상
                LinkedNode<E> previousNode = this.head();
                for (int i = 1; i < order; i++) {
                    previousNode = previousNode.next();
                }
                removeNode = previousNode.next();
                previousNode.setNext(removeNode.next());
            }
            this.setSize(this.size() - 1);
            return removeNode.element();
        }
    }

    /**
     * 맨 앞 원소를 삭제한다.
     * @return 삭제된 원소
     */
    public E removeFirst(){
        return this.removeFrom(0);
    }

    /**
     * 맨 뒤 원소를 삭제한다.
     * @return 삭제된 원소
     */
    public E removeLast(){
        return this.removeFrom(this.size()-1);
    }


    /**
     * 아무 원소나 삭제한다.
     * LinkedList 의 경우 맨 앞 노드를 삭제하는 것이 가장 빠르므로
     * 맨 앞 원소를 삭제한다.
     * @return 삭제된 원소
     */
    public E removeAny(){
        return this.removeFirst();
    }


    /**
     * 주어진 원소를 리스트에서 삭제한다.
     * @param anElement 삭제할 원소
     * @return 삭제에 성공하면 true
     */
    public boolean remove(E anElement) {
        LinkedNode<E> previousNode = null;
        LinkedNode<E> currentNode = this.head();
        while ((currentNode != null) && (!currentNode.element().equals(anElement))) {
            previousNode = currentNode;
            currentNode = currentNode.next();
        }

        if(currentNode == null){
            return false;
        }
        else {
            if(currentNode == this.head()){
                this.setHead(this.head().next());
            }
            else {
                previousNode.setNext(currentNode.next());
            }
            this.setSize(this.size() - 1);
            return true;
        }

    }


    /**
     * 주어진 순서의 원소를 주어진 원소로 교체한다.
     * @param anElement 교체할 원소
     * @param order 교체할 순서
     * @return 교체에 성공하면 true
     */
    public boolean replaceAt(E anElement, int order) {
        if (!this.anElementDoesExistAt(order)) {
            return false;
        }
        else {
            LinkedNode<E> currentNode = this.head();
            for (int i = 0; i < order; i++) {
                currentNode = currentNode.next();
            }
            currentNode.setElement(anElement);
            return true;
        }
    }



    public Iterator<E> iterator() {
        return (new ListIterator());
    }



    private class ListIterator implements Iterator<E> {
        private LinkedNode<E> _nextNode;

        private ListIterator(){
            this._nextNode = LinkedList.this._head;
        }

        @Override
        public boolean hasNext() {
            return (this._nextNode != null);
        }

        @Override
        public E next() {
            if(this._nextNode == null){
                return null;
            }
            else {
                E e = this._nextNode.element();
                this._nextNode = this._nextNode.next();
                return e;
            }
        }
    }


}
