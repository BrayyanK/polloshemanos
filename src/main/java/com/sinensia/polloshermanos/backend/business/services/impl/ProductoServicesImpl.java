package com.sinensia.polloshermanos.backend.business.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.polloshermanos.backend.business.model.Familia;
import com.sinensia.polloshermanos.backend.business.model.Producto;
import com.sinensia.polloshermanos.backend.business.model.dtos.ProductoDTO1;
import com.sinensia.polloshermanos.backend.business.services.ProductoServices;
import com.sinensia.polloshermanos.backend.integration.repositories.ProductoRepository;

@Service
public class ProductoServicesImpl implements ProductoServices {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	@Transactional
	public Producto create(Producto producto) {

		if (producto.getCodigo() != null) {
			throw new IllegalStateException("Para crear un producto el código ha de ser null");
		}

		return productoRepository.save(producto);
	}

	@Override
	public Producto read(Long codigo) {
		return productoRepository.findById(codigo).orElse(null);
	}

	@Override
	@Transactional
	public void update(Producto producto) {

		boolean existe = productoRepository.existsById(producto.getCodigo());

		if (!existe) {
			throw new IllegalStateException("No existe el producto con código " + producto.getCodigo());
		}

		productoRepository.save(producto);
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> findBetweenDates(Date desde, Date hasta) {
		return productoRepository.findByFechaAltaBetween(desde, hasta);
	}

	@Override
	public List<Producto> findBetweenPriceRange(double min, double max) {
		return productoRepository.findByPrecioBetween(min, max);
	}

	@Override
	public List<Producto> findByFamilia(Familia familia) {
		return productoRepository.findByFamilia(familia);
	}

	@Override
	public List<Producto> findDescatalogados() {
		return productoRepository.findDescatalogados();
	}

	@Override
	public List<Producto> findByNombreLikeIgnoreCase(String nombre) {
		return productoRepository.findByNombreContainingIgnoreCase(nombre);
	}

	@Override
	@Transactional
	public void incrementarPreciosByFamilia(Familia familia, double incremento) {
		productoRepository.incrementarPrecios(familia, incremento);
	}

	@Override
	public int getNumeroTotalProductos() {
		return (int) productoRepository.count();
	}

	@Override
	public int getNumeroTotalProductosByFamilia(Familia familia) {
		return (int) productoRepository.getNumeroTotalProductosByFamilia(familia);
	}

	@Override
	public Map<Familia, Integer> getEstadisticaNumeroProductosByFamilia() {

		Map<Familia, Integer> estadistica = new HashMap<>();

		Arrays.asList(Familia.values()).stream().forEach(x -> estadistica.put(x, 0));

		List<Object[]> resultados = productoRepository.getEstadisticaNumeroProductosPorFamilia();

		resultados.stream().forEach(x -> {
			Familia familia = (Familia) x[0];
			Integer cuenta = ((Long) x[1]).intValue();
			estadistica.replace(familia, cuenta);
		});

		return estadistica;
	}

	@Override
	public Map<Familia, Double> getEstadisticaPrecioMedioProductosByFamilia() {

		Map<Familia, Double> estadistica = new HashMap<>();

		Arrays.asList(Familia.values()).stream().forEach(x -> estadistica.put(x, null));

		List<Object[]> resultados = productoRepository.getEstadisticaPreciomedioProductosPorFamilia();

		resultados.stream().forEach(x -> {
			Familia familia = (Familia) x[0];
			Double cuenta = (Double) x[1];
			estadistica.replace(familia, cuenta);
		});

		return estadistica;
	}

	@Override
	public List<Familia> getFamilias() {
		return Arrays.asList(Familia.values());
	}

	@Override
	public List<ProductoDTO1> getProductosDTO1() {

		return productoRepository.getProductosDTO1().stream().map(x -> {

			Long codigo = (Long) x[0];
			String strCodigo = "00000000000000000000" + codigo;
			strCodigo = strCodigo.substring(strCodigo.length() - 20);

			return new ProductoDTO1(strCodigo, (String) x[1]);
		}).collect(Collectors.toList());
	}

}
