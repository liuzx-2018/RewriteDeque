package com.noah.model.datastructure.queue;

//基于数组实现双向队列
public class MyArrayDeque<E> implements Deque<E> {

	public static final int DEFAULT_CAPACITY = 10;

	private E[] data;// 存储队列元素的数组

	private int size;// 存储队列元素数量

	private int head;// 队头指针

	private int tail;// 队尾指针

	private int count;// 判断转换指针的次数

	public MyArrayDeque(int capacity) {
		data = (E[]) new Object[capacity];
		size = 0;
		head = -1;
		tail = -1;
	}

	public MyArrayDeque() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean empty() {
		return size == 0;
	}

	// 队首入队
	@Override
	public void addFirst(E e) {
		if (e == null) {
			throw new NullPointerException("can't add null elements");
		}
		if (size == data.length) {
			growthCapacity(2 * data.length, 0);
		}
		head = (head + 1) % data.length;
		data[head] = e;
		if (size == 0)
			tail = head;
		size++;

	}

	// 队尾入队
	@Override
	public void addLast(E e) {
		if (e == null) {
			throw new NullPointerException("can't add null elements");
		}
		if (size == data.length) {
			growthCapacity(2 * data.length, 1);
		}
		tail = (tail + 1) % data.length;
		data[tail] = e;
		if (size == 0)
			head = tail;
		size++;

	}

	// 扩容
	public void growthCapacity(int newCapacity, int flag) {
		E[] oldData = data;
		data = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			if (flag == 0)
				data[i] = oldData[(tail + i) % oldData.length];
			else
				data[i] = oldData[(head + i) % oldData.length];
		}
		if (flag == 0) {
			head = size - 1;
			tail = 0;
		} else {
			tail = size - 1;
			head = 0;
		}
	}

	// 队首出队
	@Override
	public E removeFirst() {
		if (size == 0) {
			throw new RuntimeException("queue is Null..");
		}
		if (head == size - 1 && count == 0) {
			count++;
			int tmp = head;
			head = tail;
			tail = tmp;
		}
		E result = data[head];
		head = (head + 1) % data.length;

		if (size <= data.length / 2) {
			growthCapacity(data.length / 2, 1);
		}
		size--;
		return result;
	}

	// 队尾出队
	@Override
	public E removeLast() {
		if (size == 0) {
			throw new RuntimeException("queue is Null..");
		}
		if (tail == 0) {
			int tmp = tail;
			tail = head;
			head = tmp;
		}
		E result = data[tail];
		tail = (tail - 1) % data.length;
		size--;
		if (size <= data.length / 2) {
			growthCapacity(data.length / 2, 1);
			// growth(data.length / 2);
		}
		return result;
	}

	@Override
	public E peekFirst() {
		return data[head];
	}

	@Override
	public E peekLast() {
		return data[tail];
	}

	public static void main(String[] args) {
		Deque<Integer> queue = new MyArrayDeque<>();
		for (int i = 0; i < 10; i++) {
			// queue.addLast(i + 1);
			queue.addFirst(i + 1);
		}
		for (int i = 10; i < 20; i++) {
			queue.addLast(i + 1);
		}

		for (int i = 0; i < 10; i++) {
			System.out.println("The removing top element is: " + queue.removeFirst());
			System.out.println("The removing tail element is: " + queue.removeLast());
		}
	}

}
