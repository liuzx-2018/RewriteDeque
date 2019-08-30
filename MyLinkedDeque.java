package com.noah.model.datastructure.queue;

//基于链表实现双向队列
public class MyLinkedDeque<E> implements Deque<E> {

	public class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;

		public Node(E data, Node<E> next, Node<E> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

	}

	private Node<E> head; // 队头指针
	private Node<E> tail; // 队尾指针

	private int size; // 队列元素数量

	public MyLinkedDeque() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean empty() {
		return size == 0;
	}

	@Override
	public void addFirst(E e) {
		if (e == null)
			throw new NullPointerException("can't add null element!");
		Node<E> oldNode = head;
		head = new Node<E>(e, null, null);
		if (size == 0) {
			tail = head;
		} else {
			head.next = oldNode;
			oldNode.prev = head;
		}
		size++;

	}

	@Override
	public void addLast(E e) {
		if (e == null) {
			throw new NullPointerException("can't add null element!");
		}
		Node<E> oldNode = tail;
		tail = new Node<E>(e, null, null);
		if (size == 0) {
			head = tail;
		} else {
			tail.prev = oldNode;
			oldNode.next = tail;
		}
		size++;

	}

	@Override
	public E removeFirst() {
		if (size == 0)
			throw new RuntimeException("Can't remove from empty deque");
		E result = head.data;
		head = head.next;
		size--;
		/*if (size == 0)
		tail = null;
		else
		head.prev = null;*/
		return result;
	}

	@Override
	public E removeLast() {
		if (size == 0)
			throw new RuntimeException("Can't remove from empty deque");
		E result = tail.data;
		tail = tail.prev;
		size--;
		/*if (size == 0)
			head = null;
		else
			tail.next = null;*/
		return result;
	}

	@Override
	public E peekFirst() {
		if (size == 0) {
			throw new RuntimeException("队列为空...");
		}
		return head.data;
	}

	@Override
	public E peekLast() {
		if (size == 0) {
			throw new RuntimeException("队列为空...");
		}
		return tail.data;
	}

	public static void main(String[] args) {
		Deque queue = new MyLinkedDeque();
		for (int i = 0; i < 20; i++) {
			queue.addLast(i + 1);
			queue.addFirst(i + 1);
		}

		for (int i = 0; i < 10; i++) {
			System.out.println("The removing top element is: "
			        + queue.removeFirst());
			System.out.println("The removing tail element is: "
			        + queue.removeLast());
		}

	}
}
