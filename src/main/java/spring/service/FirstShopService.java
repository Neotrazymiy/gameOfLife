package spring.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring.component.ATB_ProductHtml;
import spring.model.Firm;
import spring.model.Product;
import spring.model.ProductHtml;
import spring.repository.ProductRepository;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Service
@RequiredArgsConstructor
public class FirstShopService {

	private final ProductRepository productRepository;
	private final ATB_ProductHtml config;
	private final List<ProductHtml> products;

	@Value("${flaresolverr.url:http://flaresolverr:8191/v1}")
	private String flareSolverrUrl;

	@PostConstruct
	public void init() {
		launch();
	}

	@Scheduled(cron = "0 0 3 * * *")
	public void launch() {
		productRepository.findByFirm(Firm.ATB).forEach(productRepository::delete);

		System.out.println("Используем FlareSolverr: " + flareSolverrUrl);

		products.forEach(prod -> create(flareSolverrUrl));
	}

	@Transactional
	public void create(String flareSolverrUrl) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			for (ATB_ProductHtml.Item item : config.getItems()) {
				String targetUrl = item.getUrl();
				if (!preparingAndSendingARequest(client, item, targetUrl)) {
					continue;
				}
			}
		} catch (Exception e) {
			System.out.println("Ошибка работы HTTP клиента");
		}
	}

	private boolean preparingAndSendingARequest(CloseableHttpClient client, ATB_ProductHtml.Item item,
			String targetUrl) {
		try {
			HttpPost post = new HttpPost(flareSolverrUrl);
			post.setHeader("Content-Type", "application/json");

			JSONObject jsonRequestBody = new JSONObject();
			jsonRequestBody.put("cmd", "request.get");
			jsonRequestBody.put("url", targetUrl);
			jsonRequestBody.put("maxTimeout", 60000);

			post.setEntity(new StringEntity(jsonRequestBody.toString()));
			String html = bypassing_Cloudflare(post, client, targetUrl, item);
			if (html.isEmpty()) {
				return false;
			}
			return parser(html, targetUrl, item);
		} catch (Exception e) {
			System.out.println("Ошибка парсинга ссылки: " + targetUrl);
			e.printStackTrace();
			return false;
		}
	}

	private String bypassing_Cloudflare(HttpPost post, CloseableHttpClient client, String targetUrl,
			ATB_ProductHtml.Item item) throws IOException, ParseException {
		try (CloseableHttpResponse response = client.execute(post)) {
			String responseString = EntityUtils.toString(response.getEntity());
			JSONObject jsonResponse = new JSONObject(responseString);

			String html = jsonResponse.getJSONObject("solution").getString("response");

			if (html.contains("Cloudflare") || html.contains("Один момент")) {
				System.out.println("Не удалось обойти Cloudflare для: " + targetUrl);
				return "";
			}
			return html;
		}
	}

	private boolean parser(String html, String targetUrl, ATB_ProductHtml.Item item) {
		Document doc = Jsoup.parse(html);
		String name = doc.selectFirst("h1.product-page__title").text();
		String priceText = doc.selectFirst(".product-price__top").text();
		if (name == null || priceText == null) {
			System.out.println("Не найдены данные товара: " + targetUrl);
			return false;
		}
		Integer price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
		saveProduct(name, price, item);
		System.out.println("Успешно импортирован: " + name + " — " + price + " грн.");
		return true;
	}

	private void saveProduct(String name, Integer price, ATB_ProductHtml.Item item) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setFirm(item.getFirm());
		product.setImageUrl(item.getName().getPath());
		product.setAvailable(true);
		product.setLocalDateTime(LocalDateTime.now());
		productRepository.save(product);
	}

}
