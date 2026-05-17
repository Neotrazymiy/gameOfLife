package spring.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import spring.model.Firm;
import spring.model.ProductHtml;
import spring.model.ProductsName;

@Component
@Data
@ConfigurationProperties(prefix = "products")
public class ATB_ProductHtml implements ProductHtml {

	private final List<Item> items = new ArrayList<>();

	@Data
	public static class Item {
		private Firm firm;
		private ProductsName name;
		private String url;
	}

	@Override
	public List<Item> getItems() {
		return items;
	}

}
