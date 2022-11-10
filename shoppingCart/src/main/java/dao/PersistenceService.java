package dao;

public interface PersistenceService<E> {
    void save(E o);
    E load(E o);
}
