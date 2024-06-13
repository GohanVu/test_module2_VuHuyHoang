package Service;

import Models.Person;

import java.util.List;

public interface IService <T extends Person> {
    boolean add(T t);
    List<T> getAll();
    T findByCode(int code);
    void remove(T t);
//    boolean update(T t);
//    List<T> searchByName(String name);
//    List<T> searchByCode(int code);
//    boolean codeExists(int code);
}
