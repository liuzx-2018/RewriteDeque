package com.noah.model.datastructure.queue;

//双端队列 支持一段进一端出  "先进先出"
public interface Deque<E> {
	// 返回队列元素数量
	public int size();

	// 队列是否为空
	public boolean empty();

	// 队头入队
	public void addFirst(E e);

	// 队尾入队
	public void addLast(E e);

	// 队头出队
	public E removeFirst();

	// 队尾出队
	public E removeLast();

	// 查看队首元素
	public E peekFirst();

	// 查看队尾元素
	public E peekLast();
}
