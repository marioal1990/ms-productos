package cl.mycroft.msproductos.model.repository;

import cl.mycroft.msproductos.model.dto.ProductoDTO;
import cl.mycroft.msproductos.model.entity.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findBySku(String sku);

    // Proyección directa a DTO; Slice evita el SELECT COUNT(*) de cada página
    @Query("""
           SELECT new cl.mycroft.msproductos.model.dto.ProductoDTO(p.id, p.sku, p.nombre, p.stock, p.precio)
           FROM Producto p
           """)
    Slice<ProductoDTO> findAllDto(Pageable pageable);

    @Query("""
           SELECT new cl.mycroft.msproductos.model.dto.ProductoDTO(p.id, p.sku, p.nombre, p.stock, p.precio)
           FROM Producto p
           WHERE p.precio >= :minPrecio
           """)
    Slice<ProductoDTO> findByMinPrecio(@Param("minPrecio") Double minPrecio, Pageable pageable);
}
