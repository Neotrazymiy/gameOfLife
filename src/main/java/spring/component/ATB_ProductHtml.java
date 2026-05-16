package spring.component;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.Data;
import spring.model.Firm;
import spring.model.ProductHtml;
import spring.model.ProductsName;

@Component
@Data
public class ATB_ProductHtml implements ProductHtml {

	private static final String COCA_COLA = "https://www.atbmarket.com/product/napij-250-ml-coca-cola-bezalkogolnij-silnogazovanij";
	private static final String OLIVA_OIL_COLD_PRESSED = "https://www.atbmarket.com/product/olia-250-ml-de-luxe-foodsgoods-selected-olivkova-extra-virgin";
	private static final String PUMPKIN_OIL_COLD_PRESSED = "https://www.atbmarket.com/product/olia-230g-de-luxe-foodsgoods-selected-garbuzova-persij-holodnij-vidzimu";
	private static final String TANGERINES = "https://www.atbmarket.com/product/mandarini-1gat";
	private static final String GRAPEFRUIT = "https://www.atbmarket.com/product/grejpfrut-1gat";
	private static final String ORANGE = "https://www.atbmarket.com/product/apelsin-vag-1-gat";
	private static final String PINEAPPLE = "https://www.atbmarket.com/product/ananas-vag-1-gat";
	private static final String APPLE = "https://www.atbmarket.com/product/abluko-ukrainske";
	private static final String BANANA = "https://www.atbmarket.com/product/banan-1-gat";
	private static final String PEAR = "https://www.atbmarket.com/product/grusa-1-gat";
	private static final String LEMON = "https://www.atbmarket.com/product/limon-1-gat";
	private static final String WHITE_GRAPES = "https://www.atbmarket.com/product/vinograd-bilij-500-g-fas-1-gat";
	private static final String AVOCADO_CHAOS = "https://www.atbmarket.com/product/avokado-haas-vagove-1-gat";
	private static final String MANGO = "https://www.atbmarket.com/product/mango-vag-1-gat";
	private static final String PINK_GRAPES = "https://www.atbmarket.com/product/vinograd-rozevij-1-gat";
	private static final String KIWI = "https://www.atbmarket.com/product/kivi-vagove-1gat";
	private static final String TOMATO = "https://www.atbmarket.com/product/tomati-teplicni-1-gatunok";
	private static final String ZUCCHINI = "https://www.atbmarket.com/product/kabacki-1-gat";
	private static final String LAST_YEARS_CABBAGE = "https://www.atbmarket.com/product/kapusta-1-gat";
	private static final String BEET = "https://www.atbmarket.com/product/burak-1-gat";
	private static final String CARROT = "https://www.atbmarket.com/product/morkva-1gat";
	private static final String CIBULA = "https://www.atbmarket.com/product/cibula-ripcasta-1-gat";
	private static final String LAST_YEARS_POTATOES = "https://www.atbmarket.com/product/kartopla-1-gat";
	private static final String CUCUMBERS = "https://www.atbmarket.com/product/ogirki-teplicni-1gat";
	private static final String EARLY_POTATOES = "https://www.atbmarket.com/product/kartopla-ranna-egipet-1gat";
	private static final String LAST_YEARS_GARLIC = "https://www.atbmarket.com/product/casnik-import-1-gat";
	private static final String BROCCOLI_CABBAGE = "https://www.atbmarket.com/product/kapusta-brokkoli-1-gat";
	private static final String GINGER = "https://www.atbmarket.com/product/korin-imbira-1-gat";
	private static final String SWEET_PEPPER = "https://www.atbmarket.com/product/perec-solodkij-cervonij-1-gat";
	private static final String CAULIFLOWER = "https://www.atbmarket.com/product/kapusta-cvitna-1gat";
	private static final String EARLY_CABBAGE = "https://www.atbmarket.com/product/kapusta-ranna-1-gat";
	private static final String RADISH = "https://www.atbmarket.com/product/redis-1-gat";
	private static final String EARLY_GARLIC = "https://www.atbmarket.com/product/casnik-rannij-1-gat";
	private final Map<ProductsName, String> IMAGES = new HashMap<>();

	@PostConstruct
	public void init() {
		IMAGES.put(ProductsName.COCA_COLA, COCA_COLA);
		IMAGES.put(ProductsName.OLIVA_OIL_COLD_PRESSED, OLIVA_OIL_COLD_PRESSED);
		IMAGES.put(ProductsName.PUMPKIN_OIL_COLD_PRESSED, PUMPKIN_OIL_COLD_PRESSED);
		IMAGES.put(ProductsName.TANGERINES, TANGERINES);
		IMAGES.put(ProductsName.GRAPEFRUIT, GRAPEFRUIT);
		IMAGES.put(ProductsName.ORANGE, ORANGE);
		IMAGES.put(ProductsName.PINEAPPLE, PINEAPPLE);
		IMAGES.put(ProductsName.APPLE, APPLE);
		IMAGES.put(ProductsName.BANANA, BANANA);
		IMAGES.put(ProductsName.PEAR, PEAR);
		IMAGES.put(ProductsName.LEMON, LEMON);
		IMAGES.put(ProductsName.WHITE_GRAPES, WHITE_GRAPES);
		IMAGES.put(ProductsName.AVOCADO_CHAOS, AVOCADO_CHAOS);
		IMAGES.put(ProductsName.MANGO, MANGO);
		IMAGES.put(ProductsName.PINK_GRAPES, PINK_GRAPES);
		IMAGES.put(ProductsName.KIWI, KIWI);
		IMAGES.put(ProductsName.TOMATO, TOMATO);
		IMAGES.put(ProductsName.ZUCCHINI, ZUCCHINI);
		IMAGES.put(ProductsName.LAST_YEARS_CABBAGE, LAST_YEARS_CABBAGE);
		IMAGES.put(ProductsName.BEET, BEET);
		IMAGES.put(ProductsName.CARROT, CARROT);
		IMAGES.put(ProductsName.CIBULA, CIBULA);
		IMAGES.put(ProductsName.LAST_YEARS_POTATOES, LAST_YEARS_POTATOES);
		IMAGES.put(ProductsName.CUCUMBERS, CUCUMBERS);
		IMAGES.put(ProductsName.EARLY_POTATOES, EARLY_POTATOES);
		IMAGES.put(ProductsName.LAST_YEARS_GARLIC, LAST_YEARS_GARLIC);
		IMAGES.put(ProductsName.BROCCOLI_CABBAGE, BROCCOLI_CABBAGE);
		IMAGES.put(ProductsName.GINGER, GINGER);
		IMAGES.put(ProductsName.SWEET_PEPPER, SWEET_PEPPER);
		IMAGES.put(ProductsName.CAULIFLOWER, CAULIFLOWER);
		IMAGES.put(ProductsName.EARLY_CABBAGE, EARLY_CABBAGE);
		IMAGES.put(ProductsName.RADISH, RADISH);
		IMAGES.put(ProductsName.EARLY_GARLIC, EARLY_GARLIC);
	}

	@Override
	public Map<ProductsName, String> getUrlHtml() {
		return IMAGES;
	}

	@Override
	public Firm getFirm() {
		return Firm.ATB;
	}

}
