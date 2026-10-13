package cl.mycroft.msproductos.service.impl;

import cl.mycroft.msproductos.model.dto.ProductoDTO;
import cl.mycroft.msproductos.model.entity.Producto;
import cl.mycroft.msproductos.model.repository.ProductoRepository;
import cl.mycroft.msproductos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    static final String CACHE_PRODUCTOS = "productos";
    static final String CACHE_PRODUCTO_POR_SKU = "productoPorSku";

    private final ProductoRepository productoRepository;
    // Mapper administrado por Spring: se crea una sola vez y es thread-safe
    private final ObjectMapper objectMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CACHE_PRODUCTOS, key = "'all:' + #pageable")
    public Slice<ProductoDTO> getList(Pageable pageable) {
        return this.productoRepository.findAllDto(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CACHE_PRODUCTO_POR_SKU, unless = "#result == null")
    public Optional<Producto> getById(String id) {
        return this.productoRepository.findBySku(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CACHE_PRODUCTOS, key = "'minPrecio:' + #minPrecio + ':' + #pageable")
    public Slice<ProductoDTO> findByMinPrecio(Double minPrecio, Pageable pageable) {
        return this.productoRepository.findByMinPrecio(minPrecio, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> getByFilter(String filter) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> getByFilter(String filter, int page, int pageSize) {
        return Optional.empty();
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_PRODUCTOS, allEntries = true),
            @CacheEvict(cacheNames = CACHE_PRODUCTO_POR_SKU, allEntries = true)
    })
    public Optional<Producto> insert(String object) {
        Producto producto = this.objectMapper.readValue(object, Producto.class);
        return Optional.of(this.productoRepository.save(producto));
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_PRODUCTOS, allEntries = true),
            @CacheEvict(cacheNames = CACHE_PRODUCTO_POR_SKU, allEntries = true)
    })
    public Optional<Producto> update(String object) {
        return Optional.empty();
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_PRODUCTOS, allEntries = true),
            @CacheEvict(cacheNames = CACHE_PRODUCTO_POR_SKU, allEntries = true)
    })
    public void delete(String id) {
        log.info("delete");
    }
}
