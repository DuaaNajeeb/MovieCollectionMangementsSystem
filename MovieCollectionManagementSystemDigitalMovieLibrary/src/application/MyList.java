package application;

public class MyList<T> implements Listable<T> {

	private int counter;
	private T[] list;

	@SuppressWarnings("unchecked")
	public MyList(int capacity) {
		counter = 0;
		list = (T[]) new Object[capacity];
	}
	
	

	@Override
	public void add(T data) {
		if (counter == list.length) {
			resize();
		}
		list[counter++] = data;
	}

	@Override
	public T get(int idx) {
		if (idx < 0 || idx >= counter) {
			System.out.println("Index out of bounds: " + idx);
			return null;
		}
		return list[idx];
	}

	@Override
	public T remove(int idx) {
		if (idx < 0 || idx >= counter) {
			System.out.println("Index out of bounds: " + idx);
			return null;
		}
		T removed = list[idx];
		for (int i = idx; i < counter - 1; i++) {
			list[i] = list[i + 1];
		}
		list[--counter] = null;
		return removed;
	}

	@Override
	public void set(T data, int idx) {
		if (idx < 0 || idx >= counter) {
			System.out.println("Index out of bounds: " + idx);
			return;
		}
		list[idx] = data;
	}

	@Override
	public int size() {
		return counter;
	}

	@Override
	public boolean isEmpty() {
		return counter == 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < counter; i++) {
			list[i] = null;
		}
		counter = 0;
	}

	private void resize() {
		T[] newList = (T[]) new Object[list.length * 2];
		System.arraycopy(list, 0, newList, 0, list.length);
		list = newList;

	}
}
