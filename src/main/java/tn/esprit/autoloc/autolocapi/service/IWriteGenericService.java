package tn.esprit.autoloc.autolocapi.service;

public interface IWriteGenericService<T, I> {
    T create(T entity);
    T update(I id, T entity); // <-- I au lieu de ID
    void deleteById(I id);    // <-- I au lieu de ID
}