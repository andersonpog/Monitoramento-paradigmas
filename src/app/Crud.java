package app;

import java.util.LinkedHashSet;

public interface Crud<T> {
    public LinkedHashSet<T> getAll();
    public T get(int id);

    public int post(T dado);

    public int delete(int id);
}
