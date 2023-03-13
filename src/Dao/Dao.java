package Dao;

import TableModel.Food;

import java.io.Serializable;

public interface Dao <ID extends Serializable, T> {
    T get(ID id);

    public Food get(Integer id);

    void delete(Integer key);

    void save(T t);
}
