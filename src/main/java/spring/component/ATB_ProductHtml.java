package spring.component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.Data;
import spring.model.Firm;
import spring.model.ProductHtml;

@Component
@Data
public class ATB_ProductHtml implements ProductHtml {

	private static final String COCA_COLA = "https://www.atbmarket.com/product/napij-250-ml-coca-cola-bezalkogolnij-silnogazovanij";
	private static final List<String> PRODUCT = new ArrayList<String>();

	@PostConstruct
	public void init() {
		PRODUCT.add(COCA_COLA);
	}

	@Override
	public List<String> getUrlHtml() {
		return PRODUCT;
	}

	@Override
	public Firm getFirm() {
		return Firm.ATB;
	}

}
