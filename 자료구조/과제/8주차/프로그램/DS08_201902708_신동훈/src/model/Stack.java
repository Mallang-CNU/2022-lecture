package model;

/**
 * Created by ShinD on 2022/04/30.
 */
public interface Stack<E>{
	
	public int size();
	public boolean isFull();
	public boolean isEmpty();

	/**
	 * 주어진 원소를 Stack 의 맨 마지막에 추가한다.
	 * @param anElement 추가할 원소
	 * @return 성공하면 true
	 */
	public boolean push(E anElement);


	/**
	 * Stack 에 가장 마지막으로 들어온 원소를 삭제한다.
	 * @return 삭제한 원소
	 */
	public E pop();

	/**
	 * Stack 에 가장 마지막으로 들어온 원소를 보여준다
	 * @return 가장 마지막으로 삽입된 원소
	 */
	public E peek();

	/**
	 * Stack 을 비운다
	 */
	public void clear();
}
