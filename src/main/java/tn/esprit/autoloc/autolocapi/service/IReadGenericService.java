package tn.esprit.autoloc.autolocapi.service;

import java.util.List;

public interface IReadGenericService<T, ID> {

    T findById(ID id);

    List<T> findAll();

}