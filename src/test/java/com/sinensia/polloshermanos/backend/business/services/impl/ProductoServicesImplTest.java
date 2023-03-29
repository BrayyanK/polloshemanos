package com.sinensia.polloshermanos.backend.business.services.impl;

import static org.assertj.core.api.Assertions.entry;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sinensia.polloshermanos.backend.business.model.Empleado;
import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.model.dtos.ProductoDTO1;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;
import com.sinensia.polloshermanos.backend.integration.repositories.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServicesImplTest {

	@Mock
	private ProductoRepository productoRepository;

	@InjectMocks
	private ProductoServicesImpl productoServices;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void creamos_producto_con_codigo() {

		Producto p1 = new Producto();

		p1.setCodigo(10L);

		lenient().when(productoRepository.existsById(10L)).thenReturn(true);

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.create(p1);
		});

		String message = exception.getMessage();

		assertEquals("Para crear un producto el código ha de ser null", message);
	}

	@Test
	void creamos_producto_ok() {

		Producto producto = new Producto();
		producto.setCodigo(null);
		producto.setNombre("Patatas");

		lenient().when(productoRepository.existsById(null)).thenReturn(false);
		productoServices.create(producto);

		verify(productoRepository, times(1)).save(producto);
	}

	@Test
	void read_producto() {
		Producto producto = new Producto();
		producto.setCodigo(5L);
		producto.setNombre("Patatas");

		lenient().when(productoRepository.findById(5L)).thenReturn(Optional.of(producto));

		producto = null;

		producto = productoServices.read(5L);

		assertNotNull(producto);
		assertEquals("Patatas", producto.getNombre());

	}

	@Test
	void read_producto_no_existente() {

		lenient().when(productoRepository.findById(12000L)).thenReturn(Optional.empty());

		Producto producto = productoServices.read(12000L);

		assertNull(producto);

	}

	@Test
	void actualizamos_producto_codigo_null() {

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.update(new Producto());
		});

		String message = exception.getMessage();

		assertEquals("No existe el producto con código null", message);
	}

	@Test
	void actualizamos_producto_con_codigo_no_existente() {

		Producto producto = new Producto();
		producto.setCodigo(99L);

		lenient().when(productoRepository.existsById(99L)).thenReturn(false);

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.update(producto);
		});

		String message = exception.getMessage();

		assertEquals("No existe el producto con código 99", message);
	}

	@Test
	void actualizamos_producto_ok() {

		Producto producto = new Producto();
		producto.setCodigo(70L);

		when(productoRepository.existsById(70L)).thenReturn(true);

		productoServices.update(producto);

		verify(productoRepository, times(1)).save(producto);
	}

	@Test
	void testFindAll() {

		Producto producto1 = new Producto();
		Producto producto2 = new Producto();

		producto1.setCodigo(25L);
		producto2.setCodigo(26L);

		when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

		List<Producto> productos = productoServices.findAll();

		assertNotNull(productos);
		assertEquals(2, productos.size());
		assertTrue(productos.contains(producto1));
		assertTrue(productos.contains(producto2));

	}

	@Test
	void testFindBetweenDates() throws ParseException {

		Producto producto1 = new Producto();
		Producto producto2 = new Producto();

		Date desde = new Date(100L);
		Date hasta = new Date(200L);

		when(productoRepository.findByFechaAltaBetween(desde, hasta)).thenReturn(Arrays.asList(producto1, producto2));

		List<Producto> productos = productoServices.findBetweenDates(desde, hasta);

		assertNotNull(productos);
		assertEquals(2, productos.size());
		assertTrue(productos.contains(producto1));
		assertTrue(productos.contains(producto2));

	}

	@Test
	void testFindBetweenPriceRange() {

		Producto producto1 = new Producto();
		Producto producto2 = new Producto();

		double precioMin = 1000.0;
		double precioMax = 5000.0;

		when(productoRepository.findByPrecioBetween(precioMin, precioMax))
				.thenReturn(Arrays.asList(producto1, producto2));

		List<Producto> productos = productoServices.findBetweenPriceRange(precioMin, precioMax);

		assertNotNull(productos);
		assertEquals(2, productos.size());
		assertTrue(productos.contains(producto1));
		assertTrue(productos.contains(producto2));

	}

	@Test
	void testFindByFamilia() {

		Producto producto1 = new Producto();
		Producto producto2 = new Producto();
		producto1.setFamilia(Familia.BOCADILLO);
		producto2.setFamilia(Familia.CERVEZA);

		when(productoRepository.findByFamilia(Familia.BOCADILLO)).thenReturn(Arrays.asList(producto1));
		List<Producto> productos = productoServices.findByFamilia(Familia.BOCADILLO);

		assertNotNull(productos);
		assertEquals(2, productos.size());
		assertTrue(productos.contains(producto1));
		assertTrue(productos.contains(producto2));
	}

	@Test
	void testFindDescatalogados() {

		Producto producto1 = new Producto();
		Producto producto2 = new Producto();
		producto1.setDescatalogado(true);
		producto2.setDescatalogado(false);

		when(productoRepository.findDescatalogados()).thenReturn(Arrays.asList(producto1, producto2));
		List<Producto> productos = productoServices.findDescatalogados();
		assertNotNull(productos);
		assertEquals(2, productos.size());
		assertTrue(productos.contains(producto1));
		assertTrue(productos.contains(producto2));

	}

	@Test
	void testFindByNombreLikeIgnoreCase() {

		Producto producto1 = new Producto();
		Producto producto2 = new Producto();

		producto1.setNombre("Tortas");
		producto2.setNombre("Patatas");

		when(productoRepository.findByNombreContainingIgnoreCase("tas"))
				.thenReturn(Arrays.asList(producto1, producto2));

		List<Producto> productos = productoServices.findByNombreLikeIgnoreCase("tas");

		assertNotNull(productos);
		assertEquals(2, productos.size());
		assertEquals("Patatas", productos.get(1).getNombre());
		assertEquals("Tortas", productos.get(0).getNombre());

	}

	@Test
	void testIncrementarPreciosByFamilia() {

		Producto p1 = new Producto();
		Producto p2 = new Producto();

		p1.setFamilia(Familia.CERVEZA);
		p1.setPrecio(5.0);

		p2.setFamilia(Familia.CAFE);
		p2.setPrecio(30.0);

		productoServices.create(p1);
		productoServices.create(p2);

		productoServices.incrementarPreciosByFamilia(Familia.CERVEZA, 50.0);
		List<Producto> Lista = productoServices.findByFamilia(Familia.CERVEZA);

		assertEquals(7.5, p1.getPrecio());
		assertEquals(45.0, p2.getPrecio());

	}

	@Test
	void testGetNumeroTotalProductos() {
		
		when(productoRepository.count()).thenReturn(26895L);
		

		int numeroTotalProductos = productoServices.getNumeroTotalProductos();
		
		assertEquals(26895, numeroTotalProductos);
	}

	@Test
	void testGetNumeroTotalProductosByFamilia() {
		

		when(productoRepository.getNumeroTotalProductosByFamilia(Familia.BOCADILLO)).thenReturn(26895L);
		

		int numeroTotalProductos = productoServices.getNumeroTotalProductosByFamilia(Familia.BOCADILLO);
		
		assertEquals(26895, numeroTotalProductos);
	}

	@Test
	void testGetEstadisticaNumeroProductosByFamilia() {

		Object[] resultado1 = new Object[] { Familia.LICOR, 2578L };
		Object[] resultado2 = new Object[] { Familia.CERVEZA, 1200L };

		when(productoRepository.getEstadisticaNumeroProductosPorFamilia())
				.thenReturn(Arrays.asList(resultado1, resultado2));

		Map<Familia, Integer> estadistica = productoServices.getEstadisticaNumeroProductosByFamilia();

		assertNotNull(estadistica);
		assertEquals(8, estadistica.size());
		assertEquals(0, estadistica.get(Familia.BOCADILLO));
		assertEquals(2578, estadistica.get(Familia.LICOR));
		assertEquals(1200, estadistica.get(Familia.CERVEZA));

	}

	@Test
	void testGetEstadisticaPrecioMedioProductosByFamilia() {

		Object[] resultado1 = new Object[] { Familia.LICOR, 2.3 };
		Object[] resultado2 = new Object[] { Familia.CERVEZA, 1.2 };

		when(productoRepository.getEstadisticaPreciomedioProductosPorFamilia())
				.thenReturn(Arrays.asList(resultado1, resultado2));

		Map<Familia, Double> estadistica = productoServices.getEstadisticaPrecioMedioProductosByFamilia();

		assertNotNull(estadistica);
		assertEquals(8, estadistica.size());
		assertEquals(null, estadistica.get(Familia.BOCADILLO));
		assertEquals(2.3, estadistica.get(Familia.LICOR));
		assertEquals(1.2, estadistica.get(Familia.CERVEZA));

	}

	@Test
	void testGetFamilias() {

		List<Familia> familias = productoServices.getFamilias();

		assertTrue(familias.containsAll(Arrays.asList(Familia.values())));

	}

	@Test
	void testGetProductosDTO1() {

		ProductoDTO1 productoDTO11 = new ProductoDTO1("00000000000000000001", "[REFRESCO] Cocacola (2.5)");
		ProductoDTO1 productoDTO12 = new ProductoDTO1("00000000000000000555", "[CAFE] Cafe solo (1.10)");

		when(productoRepository.getProductosDTO1()).thenReturn(Arrays.asList(
				new Object[] { 1L, "[REFRESCO] Cocacola (2.5)" }, new Object[] { 555L, "[CAFE] Cafe solo (1.10)" }));

		List<ProductoDTO1> productosDTO1 = productoServices.getProductosDTO1();

		assertNotNull(productosDTO1);
		assertEquals(2, productosDTO1.size());
		assertTrue(productosDTO1.contains(productoDTO11));
		assertTrue(productosDTO1.contains(productoDTO12));
	}

}
