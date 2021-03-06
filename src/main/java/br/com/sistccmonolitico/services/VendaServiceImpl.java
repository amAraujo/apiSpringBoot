package br.com.sistccmonolitico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistccmonolitico.enuns.MensagemEnum;
import br.com.sistccmonolitico.exception.NegocioException;
import br.com.sistccmonolitico.model.Usuario;
import br.com.sistccmonolitico.model.Venda;
import br.com.sistccmonolitico.repositories.UsuarioRepository;
import br.com.sistccmonolitico.repositories.VendaRepository;

@Service
public class VendaServiceImpl implements VendaService {

	@Autowired
	private VendaRepository repository;
	
	@Autowired
	private UsuarioRepository repositoryUser;
	
	@Override
	public Venda salvar(Venda venda) {
		verificarObjetoVenda(venda);
		Usuario usuario = repositoryUser.findById(venda.getIdUsuario()).get();
		if (usuario == null) {
			throw new NegocioException(MensagemEnum.USUARIO_NAO_CADASTRADO);
		
		}
		if(venda.getProdutos().isEmpty()) {
			throw new NegocioException(MensagemEnum.NENHUM_PRODUTO_ADICIONADO);
		}
		
		return repository.save(venda);

	}

	private void verificarObjetoVenda(Venda venda) {
		if(venda == null) {
			throw new NegocioException(MensagemEnum.VENDA_NULL);
		}
	}

	@Override
	public List<Venda> listar() {
		return repository.findAll();
		
	}
}
