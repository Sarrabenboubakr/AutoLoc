package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public abstract class GenericServiceImpl<T, I> implements IGenericService<T, I> { // <-- ID devient I

    protected final JpaRepository<T, I> repository;

    protected abstract String getEntityName();

    protected abstract I getId(T entity); // <-- ID devient I

    protected abstract void setFields(T source, T target);

    @Override
    public T create(T entity) {
        if (getId(entity) != null) {
            throw new IllegalArgumentException("Identifiant non null !!!!!!");
        }
        if (!check(entity, true)) {
            throw new IllegalArgumentException("Certains champs sont invalides !");
        }
        return repository.save(entity);
    }

    @Override
    public T findById(I id) { // <-- ID devient I
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(getEntityName(), id.toString()));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T update(I id, T entity) { // <-- ID devient I
        T existing = findById(id);
        if (!check(entity, false)) {
            throw new IllegalArgumentException("Certains champs sont invalides !");
        }
        setFields(entity, existing);
        return repository.save(existing);
    }

    @Override
    public void deleteById(I id) { // <-- ID devient I
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(getEntityName(), id.toString());
        }
        repository.deleteById(id);
    }
}