package model;

public class CircularlyLinkedQueue<E> implements Queue<E> {
    private int _size;
    private LinkedNode<E> _rearNode;



    private void setSize(int newSize) {
        this._size = newSize;
    }

    private LinkedNode<E> rearNode() {
        return this._rearNode;
    }

    private void setRearNode(LinkedNode<E> newRearNode) {
        this._rearNode = newRearNode;
    }

    public CircularlyLinkedQueue() {
        this.setSize(0);
        this.setRearNode(null);
    }

    public int capacity() {
        return Integer.MAX_VALUE;
    }


    public int size() {
        return this._size;
    }

    public boolean isEmpty() {
        return this.rearNode() == null;
    }

    public boolean isFull() {
        return false;
    }
    /**
     * 큐의 front 즉, 가장 먼저 들어온 원소를 반환한다.
     * @return 가장 먼저 들어온 원소를 반환한다.
     */
    public E front() {
        if (this.isEmpty()) {
            return null;
        }
        return this.rearNode().next().element();
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
        return this.rearNode().element();
    }
    /**
     * 큐에 원소를 삽입한다.
     * @param anElement 삽입할 원소
     * @return 성공여부
     */
    public boolean enQueue(E anElement) {

        LinkedNode<E> newRearNode = new LinkedNode<E>(anElement, null);
        if (this.isEmpty()) {
            newRearNode.setNext(newRearNode);
        }
        else {
            newRearNode.setNext(this.rearNode().next());
            this.rearNode().setNext(newRearNode);

        }
        this.setRearNode(newRearNode);
        this.setSize(this.size() + 1);
        return true;
    }
    /**
     * 큐에서 원소를 삭제한다
     * @return 성공여부
     */
    public E deQueue() {
        if (this.isEmpty()) {
            return null;
        }
        E element = this.rearNode().next().element();
        this.setRearNode(this.rearNode().next());
        this.setSize(this.size() - 1);
        if (this.size() == 0) {
            this.setRearNode(null);
        }
        return element;
    }

    public void clear() {
        this.setRearNode(null);
        this.setSize(0);
    }

    public E elementAt(int anOrder) {
        if (this.isEmpty()) {
            return null;
        }
        LinkedNode<E> node = this.rearNode().next();
        for (int i = 0; i < anOrder; i++) {
            node = node.next();
        }
        return node.element();
    }






    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedQueueIterator<E>();
    }

    private class CircularlyLinkedQueueIterator<E> implements Iterator<E> {
        private LinkedNode<E> _nextNode;
        private int _count;

        private LinkedNode<E> nextNode() {
            return this._nextNode;
        }

        private void setNextNode(LinkedNode<E> newNextNode) {
            this._nextNode = newNextNode;
        }

        private int count() {
            return this._count;
        }

        private void setCount(int newCount) {
            this._count = newCount;
        }

        private CircularlyLinkedQueueIterator() {
            this.setNextNode((LinkedNode<E>) CircularlyLinkedQueue.this.rearNode());
            this.setCount(CircularlyLinkedQueue.this.size());
        }

        @Override
        public boolean hasNext() {
            return this.count() > 0;
        }

        @Override
        public E next() {
            if (this.hasNext()) {
                this.setNextNode(this.nextNode().next());
                E nextElement = this.nextNode().element();
                this.setCount(this.count() - 1);
                return nextElement;
            } else {
                return null;
            }
        }
    }
}
