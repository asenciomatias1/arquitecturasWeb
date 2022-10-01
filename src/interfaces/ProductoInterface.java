package interfaces;

import java.sql.SQLException;
import java.util.List;

import entidad.Producto;

public interface ProductoInterface {
 boolean insert(Producto obj) throws SQLException;
	
         List<Producto> list();

		 Producto getOne(int idProducto);

		 boolean update(Producto obj);

		 boolean delete(int idProducto);
}
