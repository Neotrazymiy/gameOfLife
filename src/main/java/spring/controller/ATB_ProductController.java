package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ATB_ProductController {

	@GetMapping
	public String getProducts() {
		return "products";
	}

}
