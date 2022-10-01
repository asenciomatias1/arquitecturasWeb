package interfaces;

import java.sql.SQLException;
import java.util.List;
import entidad.Factura_Producto;

public interface Factura_ProductoInterface {
		 boolean insert(Factura_Producto obj) throws SQLException;

		 List<Factura_Producto> list();

		 Factura_Producto getOne(int idFactura, int idProducto);

		 boolean update(Factura_Producto obj);

		 boolean delete(int idFactura, int idProducto);
}
