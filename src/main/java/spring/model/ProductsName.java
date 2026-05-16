package spring.model;

public enum ProductsName {

	COCA_COLA("/img/products/coca_cola.webp"), OLIVA_OIL_COLD_PRESSED("/img/products/oliva_oil_cold_pressed.webp"),
	PUMPKIN_OIL_COLD_PRESSED("/img/products/pumpkin_oil_cold_pressed.webp"),
	TANGERINES("/img/products/tangerines.webp"), GRAPEFRUIT("/img/products/grapefruit.webp"),
	ORANGE("/img/products/orange.webp"), PINEAPPLE("/img/products/pineapple.webp"), APPLE("/img/products/apple.webp"),
	BANANA("/img/products/bananna.webp"), PEAR("/img/products/pear.webp"), LEMON("/img/products/lemon.webp"),
	WHITE_GRAPES("/img/products/white_grapes.png"), AVOCADO_CHAOS("/img/products/avocado_chaos.webp"),
	MANGO("/img/products/mango.webp"), PINK_GRAPES("/img/products/pink_grapes.webp"), KIWI("/img/products/kiwi.webp"),
	TOMATO("/img/products/tomato.webp"), ZUCCHINI("/img/products/zucchini.webp"),
	LAST_YEARS_CABBAGE("/img/products/cabbage.webp"), BEET("/img/products/beet.webp"),
	CARROT("/img/products/carrot.webp"), CIBULA("/img/products/cibula.webp"),
	LAST_YEARS_POTATOES("/img/products/last_years_potatoes.webp"), CUCUMBERS("/img/products/cucumbers.webp"),
	EARLY_POTATOES("/img/products/early_potatoes.webp"), LAST_YEARS_GARLIC("/img/products/garlic.webp"),
	BROCCOLI_CABBAGE("/img/products/broccoli_cabbage.webp"), GINGER("/img/products/ginger.webp"),
	SWEET_PEPPER("/img/products/sweet_pepper.webp"), CAULIFLOWER("/img/products/cauliflower.webp"),
	EARLY_CABBAGE("/img/products/early_cabbage.webp"), RADISH("/img/products/radish.webp"),
	EARLY_GARLIC("/img/products/early_garlic.webp");

	private final String path;

	ProductsName(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
