package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

@Configuration
public class ProxyConfig {

	@Value("${proxy.host}")
	private String proxyHost;

	@Value("${proxy.port}")
	private int proxyPort;

	@Value("${proxy.username}")
	private String username;

	@Value("${proxy.password}")
	private String password;

	@Bean
	public WebClient webClient() {

		HttpClient httpClient = HttpClient.create().proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP).host(proxyHost)
				.port(proxyPort).username(username).password(p -> password));

		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
	}
}