USE db_docker;

-- Cambiar el delimitador para poder usar punto y coma dentro del procedimiento
DELIMITER $$

CREATE PROCEDURE PrepararTablaProducto()
BEGIN
    -- 1. Declarar una variable para verificar la existencia de la tabla
    DECLARE tablaExiste INT DEFAULT 0;

    -- 2. Buscar si la tabla existe en la base de datos actual
    SELECT COUNT(*) INTO tablaExiste
    FROM information_schema.tables
    WHERE table_schema = 'db_docker' AND table_name = 'lab_producto';

    -- 3. Aplicar la lógica condicional
    IF tablaExiste > 0 THEN
        -- Si existe, la borramos
        DROP TABLE lab_producto;
    ELSE
        -- Si no existe, la creamos
        CREATE TABLE lab_producto
        (
            pro_id     INT AUTO_INCREMENT COMMENT 'Identificador de tabla Productos' PRIMARY KEY,
            pro_sku    VARCHAR(100) NOT NULL COMMENT 'Sku del producto',
            pro_nombre VARCHAR(255) NOT NULL COMMENT 'Nombre del producto',
            pro_stock  INT          NOT NULL COMMENT 'Stock del Producto',
            pro_precio DOUBLE       NOT NULL COMMENT 'Precio del Producto'
        );

        -- Poblar tabla
        INSERT INTO lab_producto(pro_sku, pro_nombre, pro_stock, pro_precio)
            values ('SKU-PANA-001', 'Pan', 50, 150.0),
                   ('SKU-BEB-002', 'Bebida', 50, 1250.0),
                   ('SKU-CHOC-003', 'Ricochoc', 50, 890.0),
                   ('SKU-PC-004', 'Laptop Gamer Pro', 15, 1200000.50),
                   ('SKU-PC-005', 'Mouse Óptico Inalámbrico', 150, 45000.00),
                   ('SKU-PC-006', 'Teclado Mecánico RGB', 45, 75000.99);
    END IF;
END $$

DELIMITER ;

-- 4. Ejecutar el script condicional
CALL PrepararTablaProducto();

-- 5. Limpiar el procedimiento para no dejar basura en la base de datos
DROP PROCEDURE IF EXISTS PrepararTablaProducto;