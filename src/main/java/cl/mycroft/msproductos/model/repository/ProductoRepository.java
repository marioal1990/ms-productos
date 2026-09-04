package cl.mycroft.msproductos.model.repository;

import cl.mycroft.msproductos.model.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

    Optional<Producto> findBySku(String sku);
}