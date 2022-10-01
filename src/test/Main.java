package test;

import controllers.ClienteController;
import controllers.FacturaController;
import controllers.Factura_ProductoController;
import controllers.ProductoController;
import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.Factura_ProductoDAO;
import dao.ProductoDAO;
import entidad.Cliente;
import entidad.Factura;
import entidad.Factura_Producto;
import entidad.Producto;

public class Main {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Cliente c = new Cliente();
		ClienteDAO dao = new ClienteDAO();
		ClienteController cController = new ClienteController(dao,c);
		
		Factura f = new Factura();
		FacturaDAO daoF = new FacturaDAO();
		FacturaController fController = new FacturaController(daoF,f);
		
		Producto p = new Producto();
		ProductoDAO daoP = new ProductoDAO();
		ProductoController pController = new ProductoController(daoP, p);
		
		@SuppressWarnings("unused")
		Factura_Producto fp = new Factura_Producto();
		@SuppressWarnings("unused")
		Factura_ProductoDAO daoFP = new Factura_ProductoDAO();
		Factura_ProductoController fpController = new Factura_ProductoController();
		
		try {
			/* creamos la tabla persona y agregamos personas*/
			cController.createTable();
			cController.addClients();
			
			/* creamos la tabla factura y agregamos facturas*/
			fController.createTable();
			fController.addBills();
			
			/* creamos la tabla producto y agregamos productos*/
			pController.createTable();
			pController.addProducts();
			
			/* creamos la tabla factura_producto y agregamos factura_producto*/
			Factura_ProductoController.createTable();
			Factura_ProductoController.addBillsProducts();
			
			/* imprimimos el producto que mas facturo*/
			fpController.getTopBilledProduct();
			
			/* imprimimos el cliente que mas facturo*/
			fpController.getTopBilledClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
