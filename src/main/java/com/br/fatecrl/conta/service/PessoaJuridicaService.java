package com.br.fatecrl.conta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.fatecrl.conta.model.PessoaJuridica;
import com.br.fatecrl.conta.repository.PessoaJuridicaRepository;

public class PessoaJuridicaService implements IService<PessoaJuridica>{

    @Autowired
	private PessoaJuridicaRepository repository;

	public PessoaJuridicaService() {
	}

	@Override
	public PessoaJuridica findById(Long id) {
		Optional<PessoaJuridica> obj = repository.findById(id);
		return obj.orElse(null);
	}

	@Override
	public List<PessoaJuridica> findAll() {
		return repository.findAll();
	}

	@Override
	public PessoaJuridica create(PessoaJuridica pessoa) {
		repository.save(pessoa);
		return pessoa;
	}

	@Override
	public boolean update(PessoaJuridica pessoa) {
		if (repository.existsById(pessoa.getId())) {
			repository.save(pessoa);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}