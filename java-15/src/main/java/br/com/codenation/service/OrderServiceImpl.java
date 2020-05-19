package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.parallelStream()
				.map(orderItem -> {
					Product product = productRepository.findById(orderItem.getProductId()).get();
                    double totalOrder = product.getValue() * orderItem.getQuantity();
                    return product.getIsSale() ? totalOrder * 0.8 : totalOrder;
				}).mapToDouble(Double::doubleValue).sum();
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.parallelStream()
				.map(id -> productRepository.findById(id)
                .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.parallelStream()
				.map(this::calculateOrderValue)
				.mapToDouble(Double::doubleValue).sum();
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return productIds.parallelStream()
				.map(id -> productRepository.findById(id).get())
				.collect(Collectors.groupingBy(Product::getIsSale));
	}
}