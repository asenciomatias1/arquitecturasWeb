package interfaces;

import java.sql.SQLException;
import java.util.List;
import entidad.Factura;

public interface FacturaInterface {
 boolean insert(Factura obj) throws SQLException;
	
 	List<Factura> list();
	
	Factura getOne(int idFactura);
	
	boolean update(Factura obj);
	
	boolean delete(int idFactura);
}
