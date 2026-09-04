package cl.mycroft.msproductos.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "lab_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Long id;
    @Column(name = "pro_sku")
    private String sku;
    @Column(name = "pro_nombre")
    private String nombre;
    @Column(name = "pro_stock")
    private int stock;
    @Column(name = "pro_precio")
    private Double precio;
}
