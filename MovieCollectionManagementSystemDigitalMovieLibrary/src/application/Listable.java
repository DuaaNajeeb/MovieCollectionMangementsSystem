package application;



public interface Listable<T> {
    void add(T data);
    T get(int idx);
    T remove(int idx);
    void set(T data, int idx);
    int size();
    boolean isEmpty();
    void clear();
}

