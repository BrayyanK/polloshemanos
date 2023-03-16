package com.sinensia.polloshermanos.backend.integration.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.model.EstadoPedido;
import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.LineaPedido;
import com.sinensia.polloshermanos.backend.business.model.Pedido;
import com.sinensia.polloshermanos.backend.business.model.Producto;

@Service
public class FakeDatabase {

	private final Map<String, Empleado> EMPLEADOS = new HashMap<>();
	private final TreeMap<Long, Producto> PRODUCTOS = new TreeMap<>();
	private final TreeMap<Long, Pedido> PEDIDOS = new TreeMap<>();

	public FakeDatabase(){
		init();
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
	
	// *************************************************************************
	//
	// PRIVATE METHODS
	//
	// *************************************************************************
	
	private void init(){
		
		try {
			
			EMPLEADOS.clear();
			PRODUCTOS.clear();
			PEDIDOS.clear();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
			Producto producto1 = new Producto();
			Producto producto2 = new Producto();
			Producto producto3 = new Producto();
			Producto producto4 = new Producto();
			Producto producto5 = new Producto();
			
			producto1.setCodigo(10L);
			producto1.setNombre("Pollo Frito");
			producto1.setPrecio(12.0);
			producto1.setDescripcion("Delicioso pollo rico con la receta tradicional de Texas.");
			producto1.setFechaAlta(sdf.parse("20/10/2020"));
			producto1.setDescatalogado(false);
			producto1.setFamilia(Familia.COMIDA);
			
			producto2.setCodigo(11L);
			producto2.setNombre("Cocacola");
			producto2.setPrecio(2.0);
			producto2.setDescripcion("Lata de Cocacola 33cl");
			producto2.setFechaAlta(sdf.parse("20/10/2020"));
			producto2.setDescatalogado(false);
			producto2.setFamilia(Familia.REFRESCO);
			
			producto3.setCodigo(12L);
			producto3.setNombre("Tarta de Queso");
			producto3.setPrecio(4.5);
			producto3.setDescripcion("Deliciosa tarta de queso casera.");
			producto3.setFechaAlta(sdf.parse("21/10/2020"));
			producto3.setDescatalogado(false);
			producto3.setFamilia(Familia.POSTRE);
			
			producto4.setCodigo(13L);
			producto4.setNombre("Fanta de naranja");
			producto4.setPrecio(2.0);
			producto4.setDescripcion("Lata de Fanta de naranja 33cl");
			producto4.setFechaAlta(sdf.parse("22/10/2020"));
			producto4.setDescatalogado(false);
			producto4.setFamilia(Familia.REFRESCO);
			
			producto5.setCodigo(14L);
			producto5.setNombre("Estrella Galicia");
			producto5.setPrecio(2.5);
			producto5.setDescripcion("Deliciosa cerveza nacional.");
			producto5.setFechaAlta(sdf.parse("22/10/2020"));
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
			
			Pedido pedido1 = new Pedido();
			Pedido pedido2 = new Pedido();
			Pedido pedido3 = new Pedido();
			
			sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			LineaPedido lp11 = new LineaPedido();
			LineaPedido lp12 = new LineaPedido();
			LineaPedido lp21 = new LineaPedido();
			LineaPedido lp22 = new LineaPedido();
			LineaPedido lp23 = new LineaPedido();
			LineaPedido lp24 = new LineaPedido();
			LineaPedido lp31 = new LineaPedido();
			LineaPedido lp32 = new LineaPedido();
			
			lp11.setProducto(producto1);
			lp11.setCantidad(1);
			
			lp12.setProducto(producto3);
			lp12.setCantidad(3);
			
			lp21.setProducto(producto1);
			lp21.setCantidad(1);
			
			lp22.setProducto(producto2);
			lp22.setCantidad(2);
			
			lp23.setProducto(producto3);
			lp23.setCantidad(1);
			
			lp24.setProducto(producto4);
			lp24.setCantidad(4);
			
			lp31.setProducto(producto1);
			lp31.setCantidad(7);
			
			lp32.setProducto(producto4);
			lp32.setCantidad(1);
			
			pedido1.setCodigo(1000L);
			pedido1.setFechaHora(sdf.parse("10/03/2023 11:23"));
			pedido1.setEmpleado(empleado1);
			pedido1.setEstado(EstadoPedido.ENTREGADO);
			pedido1.setLineas(Arrays.asList(lp11, lp12));
			
			pedido2.setCodigo(1001L);
			pedido2.setFechaHora(sdf.parse("10/03/2023 11:24"));
			pedido2.setEmpleado(empleado2);
			pedido2.setEstado(EstadoPedido.ENTREGADO);
			pedido2.setLineas(Arrays.asList(lp21, lp22, lp23, lp24 ));
			
			pedido3.setCodigo(1002L);
			pedido3.setFechaHora(sdf.parse("10/03/2023 11:32"));
			pedido3.setEmpleado(empleado1);
			pedido3.setEstado(EstadoPedido.EN_PROCESO);
			pedido3.setLineas(Arrays.asList(lp31, lp32));
			
			PEDIDOS.put(pedido1.getCodigo(), pedido1);
			PEDIDOS.put(pedido2.getCodigo(), pedido2);
			PEDIDOS.put(pedido3.getCodigo(), pedido3);
		
		} catch(Exception e) {
			 
		}
	}	

}
