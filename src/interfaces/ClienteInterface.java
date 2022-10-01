package interfaces;

import java.sql.SQLException;
import java.util.List;
import entidad.Cliente;

public interface ClienteInterface {
		 boolean insert(Cliente obj) throws SQLException;

		 List<Cliente> list();

		 Cliente getOne(int id);

		 boolean update(Cliente obj);

		 boolean delete(int id);
}
