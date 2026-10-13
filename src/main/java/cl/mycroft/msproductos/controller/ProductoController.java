package cl.mycroft.msproductos.controller;

import cl.mycroft.msproductos.bean.rest.ControllerResponse;
import cl.mycroft.msproductos.bean.rest.PageResponse;
import cl.mycroft.msproductos.model.dto.ProductoDTO;
import cl.mycroft.msproductos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping(value = "/productos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ControllerResponse<PageResponse<ProductoDTO>>> getProductos(
            @PageableDefault(size = 20, sort = "sku") Pageable pageable) {
        log.debug("GET productos");
        try {
            PageResponse<ProductoDTO> page = PageResponse.of(this.productoService.getList(pageable));
            return ResponseEntity.ok(new ControllerResponse<>(HttpStatus.OK.value(), "OK", page));
        } catch (Exception e) {
            return error(e);
        }
    }

    @GetMapping(value = "/productosByMinPrecio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ControllerResponse<PageResponse<ProductoDTO>>> getProductosByMinPrecio(
            @RequestParam(defaultValue = "0")  Double minPrecio,
            @PageableDefault(sort = "sku", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("GET productos by minPrecio");
        try {
            PageResponse<ProductoDTO> page = PageResponse.of(this.productoService.findByMinPrecio(minPrecio, pageable));
            return ResponseEntity.ok(new ControllerResponse<>(HttpStatus.OK.value(), "OK", page));
        } catch (Exception e) {
            return error(e);
        }
    }

    @GetMapping("/status")
    public String status() {
        String value = "ProductoController " + "productos.mapping" + "is OK";
        log.info(value);
        return value;
    }

    private static <T> ResponseEntity<ControllerResponse<T>> error(Exception e) {
        log.error("Error en ProductoController", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ControllerResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
    }
}
