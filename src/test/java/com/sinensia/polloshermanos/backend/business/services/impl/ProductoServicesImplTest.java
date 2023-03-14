package com.sinensia.polloshermanos.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;
import com.sinensia.polloshermanos.backend.integration.utils.FakeDatabase;

class ProductoServicesImplTest {

	private ProductoServices productoServices;
	
	private Producto p1;
	private Producto p2;
	private Producto p3;
	private Producto p4;
	private Producto p5;
	
	@BeforeEach
	void beforeEach() {
		productoServices = new ProductoServicesStreamsImpl();
		FakeDatabase.getInstance().init();
		initObjects();	
	}
	
	@Test
	void create() {

		Producto producto = new Producto();
		
		producto.setCodigo(null);
		producto.setNombre("Producto Nuevo");
		producto.setPrecio(2.0);
		producto.setFamilia(Familia.REFRESCO);
		producto.setFechaAlta(new Date(109222333));
		
		Producto createdProducto = productoServices.create(producto);
		
		assertNotNull(createdProducto);
		assertNotNull(createdProducto.getCodigo());
		
		Long codigo = createdProducto.getCodigo();
		
		createdProducto = null;
		createdProducto = productoServices.read(codigo);
		
		assertNotNull(createdProducto);
		assertEquals("Producto Nuevo", createdProducto.getNombre());
		assertEquals(2.0, createdProducto.getPrecio());
		assertEquals(Familia.REFRESCO, createdProducto.getFamilia());

		int numeroTotalProductos = productoServices.getNumeroTotalProductos();
		
		assertEquals(6, numeroTotalProductos);
	}
	
	@Test
	void create_con_codigo_no_null_lanza_exception() {
		
		Producto producto = new Producto();
		producto.setCodigo(40L);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.create(producto);
		});
		
		String mensaje = exception.getMessage();
		
		assertEquals("No se puede crear un producto que ya tienen código.", mensaje);
	}

	@Test
	void read() {
		
		Producto producto = productoServices.read(10L);
		
		assertNotNull(producto);
		assertEquals(10L, producto.getCodigo());
		assertEquals("Pollo Frito", producto.getNombre());
	}
	
	@Test
	void read_producto_no_existente() {
		
		Producto producto = productoServices.read(50L);
		
		assertNull(producto);	
	}

	@Test
	void update() {
		
		Producto p1 = productoServices.read(10L);
		
		p1.setDescripcion("DESCRIPCION EDITADA");
		p1.setNombre("NOMBRE EDITADO");
		p1.setFechaAlta(new Date(0));
		p1.setFamilia(Familia.LICOR);
		p1.setPrecio(500.0);
		p1.setDescatalogado(true);
		
		productoServices.update(p1);
		
		p1 = null;
		
		p1 = productoServices.read(10L);
		
		assertNotNull(p1);
		assertEquals("DESCRIPCION EDITADA", p1.getDescripcion());
		assertEquals("NOMBRE EDITADO", p1.getNombre());
		assertEquals(new Date(0), p1.getFechaAlta());
		assertEquals(Familia.LICOR, p1.getFamilia());
		assertEquals(500.0, p1.getPrecio());
		assertTrue(p1.isDescatalogado());	
	}
	
	@Test
	void update_con_codigo_null() {
		
		Producto producto = new Producto();
		producto.setCodigo(null);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.update(producto);
		});
		
		String mensaje = exception.getMessage();
		
		assertEquals(mensaje, "No se puede actualizar un producto con código null.");	
	}
	
	@Test
	void update_con_codigo_producto_inexistente() {
		
		Producto producto = new Producto();
		producto.setCodigo(500L);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.update(producto);
		});
		
		String mensaje = exception.getMessage();
		
		assertEquals(mensaje, "El producto con código 500 no existe.");	
	}

	@Test
	void delete() {
		
		productoServices.delete(10L);
		
		Producto producto = productoServices.read(10L);
		
		assertNull(producto);	
	}
	
	@Test
	void delete_producto_no_existente() {
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.delete(50L);
		});
		
		String mensaje = exception.getMessage();
		
		assertEquals(mensaje, "El producto 50 no existe.");
	}

	@Test
	void testFindAll() {
		
		List<Producto> productosEsperados = Arrays.asList(p1, p2, p3, p4, p5);
		
		List<Producto> productos = productoServices.findAll();
		
		assertNotNull(productos);
		assertEquals(5, productos.size());
		assertTrue(productos.containsAll(productosEsperados));
	}

	@Test
	void testFindBetweenDates() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date desde = sdf.parse("20/10/2020");
		Date hasta = sdf.parse("22/10/2020");
		
		List<Producto> productos = productoServices.findBetweenDates(desde, hasta);
		
		assertNotNull(productos);
		assertEquals(1, productos.size());
		assertTrue(productos.contains(p3));	
	}

	@Test
	void testFindBetweenPriceRange() {
		
		List<Producto> productosEsperados = Arrays.asList(p3, p5);
		
		List<Producto> productos = productoServices.findBetweenPriceRange(2.5, 4.5);
		
		assertNotNull(productos);
		assertEquals(2, productos.size());
		assertTrue(productos.containsAll(productosEsperados));	
	}

	@Test
	void testFindByFamilia() {
		
		List<Producto> productosEsperados = Arrays.asList(p2, p4);
	
		List<Producto> productos = productoServices.findByFamilia(Familia.REFRESCO);
		
		assertNotNull(productos);
		assertEquals(2, productos.size());
		assertTrue(productos.containsAll(productosEsperados));
	}

	@Test
	void testFindDescatalogados() {
		
		List<Producto> productos = productoServices.findDescatalogados();
		
		assertNotNull(productos);
		assertTrue(productos.isEmpty());
	}

	@Test
	void testFindByNombreLikeIgnoreCase() {
		
		List<Producto> productosEsperados = Arrays.asList(p1, p2);
		
		List<Producto> productos = productoServices.findByNombreLikeIgnoreCase("oL");	
		
		assertNotNull(productos);
		assertEquals(2, productos.size());
		assertTrue(productos.containsAll(productosEsperados));		
	}

	@Test
	void testIncrementarPreciosByFamilia() {
		
		productoServices.incrementarPreciosByFamilia(Familia.CERVEZA, 100.0);
		
		Producto productoCerveza = productoServices.read(14L);
		Producto productoPostre = productoServices.read(12L);
		
		assertEquals(5.0, productoCerveza.getPrecio());
		assertEquals(4.5, productoPostre.getPrecio());	
	}

	@Test
	void testGetNumeroTotalProductos() {
	
		int numeroTotalProductos = productoServices.getNumeroTotalProductos();
		
		assertEquals(5, numeroTotalProductos);
	}

	@Test
	void testGetNumeroTotalProductosByFamilia() {
		
		int numeroRefrescos = productoServices.getNumeroTotalProductosByFamilia(Familia.REFRESCO);
		int numeroBocadillos = productoServices.getNumeroTotalProductosByFamilia(Familia.BOCADILLO);
		
		assertEquals(2, numeroRefrescos);
		assertEquals(0, numeroBocadillos);
	}

	@Test
	void testGetEstadisticaNumeroProductosByFamilia() {
		
		Map<Familia, Integer> estadistica = productoServices.getEstadisticaNumeroProductosByFamilia();
		
		assertNotNull(estadistica);
		assertEquals(8, estadistica.size());
		assertEquals(0, estadistica.get(Familia.BOCADILLO));
		assertEquals(0, estadistica.get(Familia.CAFE));
		assertEquals(1, estadistica.get(Familia.CERVEZA));
		assertEquals(1, estadistica.get(Familia.COMIDA));
		assertEquals(0, estadistica.get(Familia.LICOR));
		assertEquals(1, estadistica.get(Familia.POSTRE));
		assertEquals(2, estadistica.get(Familia.REFRESCO));
		assertEquals(0, estadistica.get(Familia.TAPA));
	}

	@Test
	void testGetEstadisticaPrecioMedioProductosByFamilia() {
		
		Map<Familia, Double> estadistica = productoServices.getEstadisticaPrecioMedioProductosByFamilia();
		
		assertNotNull(estadistica);
		assertEquals(8, estadistica.size());
		assertEquals(null, estadistica.get(Familia.BOCADILLO));
		assertEquals(null, estadistica.get(Familia.CAFE));
		assertEquals(2.5, estadistica.get(Familia.CERVEZA));
		assertEquals(12.0, estadistica.get(Familia.COMIDA));
		assertEquals(null, estadistica.get(Familia.LICOR));
		assertEquals(4.5, estadistica.get(Familia.POSTRE));
		assertEquals(2.0, estadistica.get(Familia.REFRESCO));
		assertEquals(null, estadistica.get(Familia.TAPA));
		
	}
	
	// *************************************************************************
	//
	// PRIVATE METHODS
	//
	// *************************************************************************

	private void initObjects() {
		
		p1 = new Producto();
		p2 = new Producto();
		p3 = new Producto();
		p4 = new Producto();
		p5 = new Producto();
		
		p1.setCodigo(10L);
		p2.setCodigo(11L);
		p3.setCodigo(12L);
		p4.setCodigo(13L);
		p5.setCodigo(14L);
		
	}
}
