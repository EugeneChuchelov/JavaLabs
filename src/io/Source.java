package io;

public interface Source<T> {
    void load(T t);
    void store(T t);
    void delete(T t);
    void create(T t);
}
