package ifam.edu.dra.chatfront.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ifam.edu.dra.chat.model.Contato;

import java.util.List;

@Service
public class FrontContatoService {

	private final String backendUrl = "http://localhost:8080";
	private final RestTemplate restTemplate;

	public FrontContatoService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Contato> getContatos() {
		String url = backendUrl + "/contatos";
		ResponseEntity<List<Contato>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Contato>>() {
				});
		return response.getBody();
	}

	public Contato getContato(Long id) {
		String url = backendUrl + "/contato/" + id;
		return restTemplate.getForObject(url, Contato.class);
	}

	public Contato setContato(Contato contato) {
		String url = backendUrl + "/contato";
		return restTemplate.postForObject(url, contato, Contato.class);
	}

	public Contato updateContato(Long id, Contato contato) {
		String url = backendUrl + "/contato/" + id;
		restTemplate.put(url, contato);
		return contato;
	}

	public void deleteContato(Long id) {
		String url = backendUrl + "/contato/" + id;
		restTemplate.delete(url);
	}
}