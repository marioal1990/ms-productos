USE db_docker;

-- Índices para una tabla lab_producto ya existente (lab_producto.sql los crea en instalaciones nuevas).
-- Antes de crear el índice único, verificar que no haya SKU duplicados:
--   SELECT pro_sku, COUNT(*) FROM lab_producto GROUP BY pro_sku HAVING COUNT(*) > 1;

-- findBySku y el ORDER BY pro_sku de los listados paginados
ALTER TABLE lab_producto ADD UNIQUE INDEX ux_producto_sku (pro_sku);

-- Filtro WHERE pro_precio >= ? de productosByMinPrecio
ALTER TABLE lab_producto ADD INDEX ix_producto_precio (pro_precio);
