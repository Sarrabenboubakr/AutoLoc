package tn.esprit.autoloc.autolocapi.service;

import java.util.List;

public interface IGenericService<T, ID> extends IReadGenericService<T, ID>, IWriteGenericService<T, ID>, ICheckGenericService<T, ID> {
}