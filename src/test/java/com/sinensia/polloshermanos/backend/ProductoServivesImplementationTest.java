package com.sinensia.polloshermanos.backend;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;

@SpringBootTest
@Sql(scripts = { "/data/h2/schema.sql", "/data/h2/data.sql" })
class ProductoServivesImplementationTest {

	@Autowired
	private ProductoServices productoServices;

	private Producto p1;
	private Producto p2;
	private Producto p3;
	private Producto p4;

	@BeforeEach
	void beforeEach() throws ParseException {
		init();
	}

	@Test
	void creamos_producto_con_codigo_null() {

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.create(new Producto());
		});

		String message = exception.getMessage();

		assertEquals("No se puede crear un producto con codigo null", message);

	}

	@Test
	void creamos_producto_con_codigo_existente() {

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.create(p1);
		});

		String message = exception.getMessage();

		assertEquals("El producto con el codigo 100 ya existe", message);
	}

	@Test
	void testCreate() {

		Producto producto = new Producto();

		producto.setCodigo(1000L);
		producto.setDescatalogado(false);
		producto.setDescripcion("Patatas");
		producto.setFamilia(Familia.COMIDA);
		producto.setFechaAlta(new Date());
		producto.setNombre("Patata Deluxe");
		producto.setPrecio(23.0);

		producto = productoServices.create(producto);

		int numeroProductos = productoServices.getNumeroTotalProductos();

		assertEquals(8, numeroProductos);

		producto = null;

		producto = productoServices.read(1000L);

		assertNotNull(producto);
		assertEquals("Patatas", producto.getDescripcion());
		assertEquals("Patata Deluxe", producto.getNombre());
		assertEquals(1000L, producto.getCodigo());
		assertEquals(Familia.COMIDA, producto.getFamilia());

	}

	@Test
	void read_producto_existente() throws ParseException {
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse("2015-10-23");


		Producto producto = productoServices.read(100L);
		

		assertNotNull(producto);
		assertEquals(100L, producto.getCodigo());
		assertEquals("Producto 1", producto.getNombre());
		assertEquals("Descripción Producto 1", producto.getDescripcion());
		assertEquals(date,producto.getFechaAlta());
		assertTrue(producto.isDescatalogado());
	}

	@Test
	void read_producto_no_existente() {

		Producto producto = productoServices.read(99L);

		assertNull(producto);

	}

	@Test
	void actualizamos_producto_con_codigo_null() {

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.update(new Producto());
		});

		String message = exception.getMessage();

		assertEquals("No se puede actualizar un producto con codigo null", message);

	}

	@Test
	void actualizamos_producto_con_codigo_no_existente() {

		Producto producto = new Producto();
		producto.setCodigo(99L);

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.update(producto);
		});

		String message = exception.getMessage();

		assertEquals("El producto 99 no existe", message);
	}

	@Test
	void actualizamos_producto_ok() {

		p1.setDescatalogado(true);
		p1.setDescripcion("Pescado");
		p1.setFamilia(Familia.BOCADILLO);
		p1.setNombre("Anchoas");
		p1.setPrecio(2.0);

		productoServices.update(p1);

		p1 = null;
		p1 = productoServices.read(100L);

		assertNotNull(p1);
		assertEquals("Pescado", p1.getDescripcion());
		assertEquals("Anchoas", p1.getNombre());
		assertEquals(Familia.BOCADILLO, p1.getFamilia());
		assertTrue(p1.isDescatalogado());

	}

	@Test
	@Disabled
	void testDelete() {
		// Producto producto = new Producto();

		p1.setCodigo(10L);
		p1.setDescatalogado(true);
		p1.setDescripcion("Patatas mancas");
		p1.setFamilia(Familia.COMIDA);
		p1.setFechaAlta(new Date());
		p1.setNombre("Patata Deluxe XL");
		p1.setPrecio(23.0);

		productoServices.create(p1);

		//productoServices.delete(10L);
		assertNull(productoServices.read(10L));

	}

	@Test
	void testFindAll() {

		List<Producto> productosEsperados = Arrays.asList(p1, p2, p3, p4);

		List<Producto> productos = productoServices.findAll();

		assertNotNull(productos);
		assertEquals(7, productos.size());
		assertTrue(productos.containsAll(productosEsperados));

	}

	@Test
	void testFindBetweenDates() throws ParseException {

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse("12-01-2018");

		List<Producto> productosEsperados = Arrays.asList(p1, p2, p3, p4);

		List<Producto> productos = productoServices.findBetweenDates(simpleDateFormat.parse("2015-10-23"),
				simpleDateFormat.parse("2015-10-26"));

		assertEquals(productosEsperados.get(0).getCodigo(), productos.get(0).getCodigo());
		assertEquals(productosEsperados.get(1).getCodigo(), productos.get(1).getCodigo());
		assertEquals(productosEsperados.get(2).getCodigo(), productos.get(2).getCodigo());
		assertEquals(productosEsperados.get(3).getCodigo(), productos.get(3).getCodigo());

	}

	@Test
	void testFindBetweenPriceRange() {

		List<Producto> productosEsperados = Arrays.asList(p3, p4);

		List<Producto> productos = productoServices.findBetweenPriceRange(4.0, 6.0);

		assertEquals(productosEsperados.size(), productos.size());
	}

	@Test
	void testFindByFamilia() {

		List<Producto> productosEsperados = Arrays.asList(p2, p3);

		List<Producto> productos = productoServices.findByFamilia(Familia.COMIDA);

		assertEquals(productosEsperados.size(), productos.size());
	}

	@Test
	void testFindDescatalogados() {
		List<Producto> productosEsperados = Arrays.asList(p1, p3);
		List<Producto> productos = productoServices.findDescatalogados();

		assertEquals(productosEsperados.size(), productos.size());

	}

	@Test
	void testFindByNombreLikeIgnoreCase() {
		List<Producto> productosEsperados = Arrays.asList(p1, p3);
		List<Producto> productos = productoServices.findByNombreLikeIgnoreCase("Producto 1");

		assertEquals(productosEsperados.get(0).getCodigo(), productos.get(0).getCodigo());

	}

	@Test
	void testIncrementarPreciosByFamilia() {

		productoServices.incrementarPreciosByFamilia(Familia.CERVEZA, 50.0);

		Producto p1 = productoServices.read(101L);
		Producto p2 = productoServices.read(102L);

		assertEquals(3.0, p1.getPrecio());
		assertEquals(4.0, p2.getPrecio());

	}

	@Test
	void testGetNumeroTotalProductos() {

		List<Producto> productos = productoServices.findAll();

		int numeroTotal = productoServices.getNumeroTotalProductos();

		assertEquals(productos.size(), numeroTotal);

	}

	@Test
	void testGetNumeroTotalProductosByFamilia() {

		int numeroProductosFamilia = productoServices.getNumeroTotalProductosByFamilia(Familia.CERVEZA);

		assertEquals(numeroProductosFamilia, 4);

	}

	@Test
	void testGetEstadisticaNumeroProductosByFamilia() {

		Map<Familia,Integer > numeroProductosFamilia = productoServices.getEstadisticaNumeroProductosByFamilia();
		
		assertEquals(numeroProductosFamilia.get(Familia.COMIDA), 3);
	}

	@Test
	void testGetEstadisticaPrecioMedioProductosByFamilia() {
		Map<Familia,Double > numeroProductosFamilia = productoServices.getEstadisticaPrecioMedioProductosByFamilia();
		
		assertEquals(numeroProductosFamilia.get(Familia.COMIDA), 3);
	}

	@Test
	void testGetFamilias() {
		fail("Not yet implemented");
	}

	// *************************************************************************
	//
	// PRIVATE METHODS
	//
	// *************************************************************************

	private void init() throws ParseException {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse("12-01-2018");

		p1 = new Producto();
		p2 = new Producto();
		p3 = new Producto();
		p4 = new Producto();

		p1.setCodigo(100L);
		p2.setCodigo(101L);
		p3.setCodigo(102L);
		p4.setCodigo(103L);

		p1.setFechaAlta(simpleDateFormat.parse("2015-10-23"));
		p2.setFechaAlta(simpleDateFormat.parse("2015-10-24"));
		p3.setFechaAlta(simpleDateFormat.parse("2015-10-25"));
		p4.setFechaAlta(simpleDateFormat.parse("2015-10-26"));

		p1.setPrecio(2.5);
		p2.setPrecio(2.0);
		p3.setPrecio(4.0);
		p4.setPrecio(5.5);

		p1.setFamilia(Familia.CERVEZA);
		p2.setFamilia(Familia.COMIDA);
		p3.setFamilia(Familia.COMIDA);
		p4.setFamilia(Familia.CERVEZA);

		p1.setDescatalogado(true);
		p2.setDescatalogado(false);
		p3.setDescatalogado(true);
		p4.setDescatalogado(false);

		p1.setNombre("Producto 1");
		p2.setNombre("Producto 2");
		p3.setNombre("Producto 3");
		p4.setNombre("Producto 4");
	}

}
