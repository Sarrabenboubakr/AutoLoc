package tn.esprit.autoloc.autolocapi.service;

import java.util.List;

public interface IReadGenericService<T, I> {
    T findById(I id);  // <-- I au lieu de ID
    List<T> findAll();
}