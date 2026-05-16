package spring.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import spring.dto.ProductGiveDto;
import spring.service.FirstShopService;
import spring.service.ProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ATB_ProductRestController {

	private final ProductService productService;

	@GetMapping
	public Page<ProductGiveDto> getPageProducts(Pageable pageable) {
		return productService.getAllPageProduct(pageable);
	}
}
