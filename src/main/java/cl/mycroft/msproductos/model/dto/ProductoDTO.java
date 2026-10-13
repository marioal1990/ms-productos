package cl.mycroft.msproductos.model.dto;

public record ProductoDTO(Long id, String sku, String nombre, int stock, Double precio) {
}
