package spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import spring.dto.ProductGiveDto;
import spring.mapper.ProductGiveMapper;
import spring.mapper.ProductRequestMapper;
import spring.repository.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductRequestMapper productRequestMapper;
	private final ProductGiveMapper productGiveMapper;

	public Page<ProductGiveDto> getAllPageProduct(Pageable pageable) {
		return productRepository.findAll(pageable).map(productGiveMapper::toDto);
	}
}
