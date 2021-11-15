package com.fatec.scc.servico;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.model.Cliente;
import com.fatec.scc.model.ClienteRepository;
import com.fatec.scc.model.Endereco;
import com.fatec.scc.model.EnderecoRepository;

@Service
public class ClienteServicoI implements ClienteServico {
	Logger logger = LogManager.getLogger(ClienteServicoI.class);
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Iterable<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findByCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}

	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
		logger.info(">>>>>> 2. comando exclusao executado para o id => " + id);
	}

	public Cliente findById(Long id) {
		return clienteRepository.findById(id).get();
	}

	
    public Cliente save (Cliente cliente) {
    	
    		Endereco endereco = obtemEndereco(cliente.getCep());
			if (endereco != null) {
				cliente.setDataCadastro(new DateTime());
				endereco.setCpf(cliente.getCpf());
				enderecoRepository.save(endereco);
				cliente.setEndereco(endereco);
				clienteRepository.save(cliente);
				logger.info(">>>>>> 4. servico comando save executado ");
				
			}
		
    	return null;
    }
	public Endereco obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		//Endereco endereco = template.getForObject(url, Endereco.class, cep);
		ResponseEntity<Endereco> response = template.getForEntity(url, Endereco.class, cep);
		Endereco endereco = response.getBody();
		logger.info(">>>>>> 3. obtem endereco ==> " + response.getStatusCode().toString());
		logger.info(">>>>>> 3. obtem endereco ==> " + endereco.toString());
		return endereco;
	}
	
}