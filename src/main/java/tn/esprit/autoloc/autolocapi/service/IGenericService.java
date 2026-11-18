package tn.esprit.autoloc.autolocapi.service;

public interface IGenericService<T, I> extends
        IReadGenericService<T, I>,
        IWriteGenericService<T, I>,
        ICheckGenericService<T> { // <-- C'est ici qu'il faut enlever le ", I"
    // ...
}