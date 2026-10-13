CREATE TABLE lab_producto
(
    pro_id     INT AUTO_INCREMENT COMMENT 'Identificador de tabla Productos' PRIMARY KEY,
    pro_sku    VARCHAR(100) NOT NULL COMMENT 'Sku del producto',
    pro_nombre VARCHAR(255) NOT NULL COMMENT 'Nombre del producto',
    pro_stock  INT          NOT NULL COMMENT 'Stock del Producto',
    pro_precio DOUBLE       NOT NULL COMMENT 'Precio del Producto'
);

CREATE UNIQUE INDEX ux_producto_sku ON lab_producto (pro_sku);

CREATE INDEX ix_producto_precio ON lab_producto (pro_precio);