package cl.mycroft.msproductos.controller;

import cl.mycroft.msproductos.bean.rest.ControllerResponse;
import cl.mycroft.msproductos.model.entity.Producto;
import cl.mycroft.msproductos.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Controller
@CrossOrigin
@Slf4j
@RequestMapping("/api/v1/producto")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping(value = "/productos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ControllerResponse> getProductos() {
        log.info("GET productos");
        ControllerResponse controllerResponse;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Producto> lista = this.productoService.getList();
            String response = objectMapper.writeValueAsString(lista);
            controllerResponse = new ControllerResponse(HttpStatus.OK.value(), response);
        } catch (Exception e) {
            controllerResponse = new ControllerResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
        return new ResponseEntity<>(controllerResponse, HttpStatus.valueOf(controllerResponse.code()));
    }

    @GetMapping("/status")
    public String status() {
        String value = "ProductoController " + "productos.mapping" + "is OK";
        log.info(value);
        return value;
    }
}
