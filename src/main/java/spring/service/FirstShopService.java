package spring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import spring.model.Firm;
import spring.model.Product;
import spring.model.ProductHtml;
import spring.model.ProductsName;
import spring.repository.ProductRepository;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Service
@RequiredArgsConstructor
public class FirstShopService {

	private final ProductRepository productRepository;
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

		products.forEach(prod -> create(flareSolverrUrl, prod.getUrlHtml(), prod.getFirm()));
	}

	@Transactional
	public void create(String flareSolverrUrl, Map<ProductsName, String> strings, Firm firm) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {

			for (Entry<ProductsName, String> url : strings.entrySet()) {
				String targetUrl = url.getValue();

				try {
					HttpPost post = new HttpPost(flareSolverrUrl);
					post.setHeader("Content-Type", "application/json");

					JSONObject jsonRequestBody = new JSONObject();
					jsonRequestBody.put("cmd", "request.get");
					jsonRequestBody.put("url", targetUrl);
					jsonRequestBody.put("maxTimeout", 60000);

					post.setEntity(new StringEntity(jsonRequestBody.toString()));

					try (CloseableHttpResponse response = client.execute(post)) {
						String responseString = EntityUtils.toString(response.getEntity());
						JSONObject jsonResponse = new JSONObject(responseString);

						String html = jsonResponse.getJSONObject("solution").getString("response");

						if (html.contains("Cloudflare") || html.contains("Один момент")) {
							System.out.println("Не удалось обойти Cloudflare для: " + targetUrl);
							continue;
						}

						Document doc = Jsoup.parse(html);

						String name = doc.selectFirst("h1.product-page__title").text();
						String priceText = doc.selectFirst(".product-price__top").text();
						if (name == null || priceText == null) {
							System.out.println("Не найдены данные товара: " + targetUrl);
							continue;
						}
						Integer price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));

						Product product = new Product();
						product.setName(name);
						product.setPrice(price);
						product.setFirm(firm);
						product.setImageUrl(url.getKey().getPath());
						product.setAvailable(true);
						product.setLocalDateTime(LocalDateTime.now());

						productRepository.save(product);
						System.out.println("Успешно импортирован: " + name + " — " + price + " грн.");
					}
				} catch (Exception e) {
					System.out.println("Ошибка парсинга ссылки: " + targetUrl);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("Ошибка работы HTTP клиента");
		}
	}

}
