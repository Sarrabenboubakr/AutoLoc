package tn.esprit.autoloc.autolocapi.service;

public interface ICheckGenericService<T, ID> {

    boolean check(T entity, boolean isNew);


}