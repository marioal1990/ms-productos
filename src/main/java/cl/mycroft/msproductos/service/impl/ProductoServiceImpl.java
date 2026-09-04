package cl.mycroft.msproductos.service.impl;

import cl.mycroft.msproductos.model.entity.Producto;
import cl.mycroft.msproductos.model.repository.ProductoRepository;
import cl.mycroft.msproductos.service.ProductoService;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getList() {
        return (List<Producto>) this.productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> getById(String id) {
        return this.productoRepository.findBySku(id);
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
    public Optional<Producto> insert(String object) {
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(object, Producto.class);
        return Optional.of(this.productoRepository.save(producto));
    }

    @Override
    @Transactional
    public Optional<Producto> update(String object) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void delete(String id) {
        log.info("delete");
    }
}
