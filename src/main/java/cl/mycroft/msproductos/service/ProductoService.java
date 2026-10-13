package cl.mycroft.msproductos.service;

import cl.mycroft.msproductos.model.dto.ProductoDTO;
import cl.mycroft.msproductos.model.entity.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductoService {

    @Transactional(readOnly = true)
    Slice<ProductoDTO> getList(Pageable pageable);

    @Transactional(readOnly = true)
    Optional<Producto> getById(String id);

    @Transactional(readOnly = true)
    Slice<ProductoDTO> findByMinPrecio(Double minPrecio, Pageable pageable);

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
