package com.br.fatecrl.conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fatecrl.conta.model.PessoaJuridica;

public interface PessoaJuridicaRepository extends
                       JpaRepository<PessoaJuridica, Long> {
}