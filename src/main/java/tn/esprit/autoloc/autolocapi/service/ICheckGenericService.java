package tn.esprit.autoloc.autolocapi.service;

public interface ICheckGenericService<T> { // <-- On enlève ", ID"

    boolean check(T entity, boolean isNew);

}