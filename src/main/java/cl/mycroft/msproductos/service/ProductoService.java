package cl.mycroft.msproductos.service;

import cl.mycroft.msproductos.model.entity.Producto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    @Transactional(readOnly = true)
    List<Producto> getList();

    @Transactional(readOnly = true)
    Optional<Producto> getById(String id);

    @Transactional(readOnly = true)
    Optional<Producto> getByFilter(String filter);
    @Transactional(readOnly = true)
    Optional<Producto> getByFilter(String filter, int page, int pageSize);

    @Transactional
    Optional<Producto> insert(String object);
    @Transactional
    Optional<Producto> update(String object);
    @Transactional
    void delete(String id);
}
