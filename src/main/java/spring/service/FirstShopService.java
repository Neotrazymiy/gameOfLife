package spring.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import spring.model.Firm;
import spring.model.Product;
import spring.model.ProductHtml;
import spring.repository.ProductRepository;

@Service
@AllArgsConstructor
public class FirstShopService {

	private final ProductRepository productRepository;
	private final List<ProductHtml> products;

	@PostConstruct
	public void init() {
		launch();
	}

	@Scheduled(cron = "0 0 3 * * *")
	public void launch() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		try {
			products.forEach(prod -> create(driver, prod.getUrlHtml(), prod.getFirm()));
		} finally {
			driver.quit();
		}
	}

	@Transactional
	public void create(WebDriver driver, List<String> strings, Firm firm) {
		for (String url : strings) {
			driver.get(url);

			String name = driver.findElement(By.cssSelector("h1.product-page__title")).getText();

			String priceText = driver.findElement(By.cssSelector(".product-price__top")).getText().replace("грн/шт", "")
					.replace("грн", "").replace(".", "").replace(",", "").trim();
			Integer price = Integer.parseInt(priceText);

			Product product = new Product();
			product.setName(name);
			product.setPrice(price);
			product.setFirm(firm);
			product.setAvailable(true);
			product.setLocalDateTime(LocalDateTime.now());

			productRepository.save(product);
		}
	}

}
