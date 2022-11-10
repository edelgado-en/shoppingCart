package dao;

/**
 *
 * Interface for the persistence service. By using this interface, we can
 * easily switch between different persistence services.
 *
 * @author Enrique Delgado
 *
 * @param <E>
 */
public interface PersistenceService<E> {
    void save(E o);
    E load(E o);
}
