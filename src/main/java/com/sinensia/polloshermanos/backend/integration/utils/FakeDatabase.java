package com.sinensia.polloshermanos.backend.integration.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Pedido;
import com.sinensia.polloshermanos.backend.business.model.Producto;

public class FakeDatabase {

	private static FakeDatabase instance;
	
	private final Map<String, Empleado> EMPLEADOS = new HashMap<>();
	private final TreeMap<Long, Producto> PRODUCTOS = new TreeMap<>();
	private final TreeMap<Long, Pedido> PEDIDOS = new TreeMap<>();

	private FakeDatabase() {
		init();
	}
	
	public static FakeDatabase getInstance() {
		
		if (instance == null) {
			instance = new FakeDatabase();
		} 
	
		return instance;
	}
	
	public Map<String, Empleado> getEmpleadosMap(){
		return EMPLEADOS;
	}
	
	public Map<Long, Producto> getProductosMap(){
		return PRODUCTOS;
	}
	
	public Map<Long, Pedido> getPedidosMap(){
		return PEDIDOS;
	}
	
	public void init() {
		
		EMPLEADOS.clear();
		PRODUCTOS.clear();
		PEDIDOS.clear();
		
		Date fecha1 = null;
		Date fecha2 = null;
		Date fecha3 = null;
		Date fecha4 = null;
		Date fecha5 = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			fecha1 = sdf.parse("20/10/2020");
			fecha2 = sdf.parse("20/10/2020");
			fecha3 = sdf.parse("21/10/2020");
			fecha4 = sdf.parse("22/10/2020");
			fecha5 = sdf.parse("22/10/2020");
		} catch (ParseException e) {
			
		}
		
		Producto producto1 = new Producto();
		Producto producto2 = new Producto();
		Producto producto3 = new Producto();
		Producto producto4 = new Producto();
		Producto producto5 = new Producto();
		
		producto1.setCodigo(10L);
		producto1.setNombre("Pollo Frito");
		producto1.setPrecio(12.0);
		producto1.setDescripcion("Delicioso pollo rico con la receta tradicional de Texas.");
		producto1.setFechaAlta(fecha1);
		producto1.setDescatalogado(false);
		producto1.setFamilia(Familia.COMIDA);
		
		producto2.setCodigo(11L);
		producto2.setNombre("Cocacola");
		producto2.setPrecio(2.0);
		producto2.setDescripcion("Lata de Cocacola 33cl");
		producto2.setFechaAlta(fecha2);
		producto2.setDescatalogado(false);
		producto2.setFamilia(Familia.REFRESCO);
		
		producto3.setCodigo(12L);
		producto3.setNombre("Tarta de Queso");
		producto3.setPrecio(4.5);
		producto3.setDescripcion("Deliciosa tarta de queso casera.");
		producto3.setFechaAlta(fecha3);
		producto3.setDescatalogado(false);
		producto3.setFamilia(Familia.POSTRE);
		
		producto4.setCodigo(13L);
		producto4.setNombre("Fanta de naranja");
		producto4.setPrecio(2.0);
		producto4.setDescripcion("Lata de Fanta de naranja 33cl");
		producto4.setFechaAlta(fecha4);
		producto4.setDescatalogado(false);
		producto4.setFamilia(Familia.REFRESCO);
		
		producto5.setCodigo(14L);
		producto5.setNombre("Estrella Galicia");
		producto5.setPrecio(2.5);
		producto5.setDescripcion("Deliciosa cerveza nacional.");
		producto5.setFechaAlta(fecha5);
		producto5.setDescatalogado(false);
		producto5.setFamilia(Familia.CERVEZA);
		
		PRODUCTOS.put(producto1.getCodigo(), producto1);
		PRODUCTOS.put(producto2.getCodigo(), producto2);
		PRODUCTOS.put(producto3.getCodigo(), producto3);
		PRODUCTOS.put(producto4.getCodigo(), producto4);
		PRODUCTOS.put(producto5.getCodigo(), producto5);
		
		Empleado empleado1 = new Empleado();
		Empleado empleado2 = new Empleado();
		Empleado empleado3 = new Empleado();
		
		empleado1.setDni("46722342L");
		empleado1.setNombre("Pepín");
		empleado1.setApellido1("Gálvez");
		empleado1.setApellido2("Ridruejo");
		empleado1.setActivo(true);
		
		empleado2.setDni("33699251K");
		empleado2.setNombre("Carlota");
		empleado2.setApellido1("Cifuentes");
		empleado2.setApellido2("Merino");
		empleado2.setActivo(true);
		
		empleado3.setDni("25040991R");
		empleado3.setNombre("Honorio");
		empleado3.setApellido1("Martín");
		empleado3.setApellido2("Salvador");
		empleado3.setActivo(false);
		
		EMPLEADOS.put(empleado1.getDni(), empleado1);
		EMPLEADOS.put(empleado2.getDni(), empleado2);
		EMPLEADOS.put(empleado3.getDni(), empleado3);
		
	}	

}
