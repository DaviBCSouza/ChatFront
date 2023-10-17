package ifam.edu.dra.chatfront.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ifam.edu.dra.chatfront.model.Contato;

import java.util.List;

@Service
public class FrontContatoService {

	private final String backendUrl = "http://localhost:8080/contato";
	private final RestTemplate restTemplate;

	public FrontContatoService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Contato> getContatos() {
		String url = backendUrl;
		ResponseEntity<List<Contato>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Contato>>() {
				});
		return response.getBody();
	}

	public Contato getContato(Long id) {
		String url = backendUrl + id;
		return restTemplate.getForObject(url, Contato.class);
	}

	public Contato setContato(Contato contato) {
		String url = backendUrl;
		return restTemplate.postForObject(url, contato, Contato.class);
	}

	public Contato updateContato(Long id, Contato contato) {
		String url = backendUrl + id;
		restTemplate.put(url, contato);
		return contato;
	}

	public void deleteContato(Long id) {
		String url = backendUrl + id;
		restTemplate.delete(url);
	}
}