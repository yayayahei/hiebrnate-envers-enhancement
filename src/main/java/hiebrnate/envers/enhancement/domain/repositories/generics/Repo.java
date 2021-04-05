package hiebrnate.envers.enhancement.domain.repositories.generics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Repo {
    interface DeleteAllInBatch {
        void deleteAllInBatch();
    }

    interface FindAll<T> {
        List<T> findAll();
    }

    interface FindById<T> {
        T getOne(Long id);
    }

    interface FindAllWithPage<T> {
        Page<T> findAll(Pageable pageable);
    }

    interface SaveAll<T> {
        <S extends T> List<S> saveAll(Iterable<S> entities);
    }

    interface Save<T> {
        <S extends T> S save(S entity);
    }
}
