package tn.esprit.autoloc.autolocapi.service;

public interface IGenericService<T, I> extends
        IReadGenericService<T, I>,
        IWriteGenericService<T, I>,
        ICheckGenericService<T, I> {
    // ...
}