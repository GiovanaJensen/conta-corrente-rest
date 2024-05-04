package com.br.fatecrl.conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fatecrl.conta.model.PessoaFisica;

public interface PessoaFisicaRepository extends
                       JpaRepository<PessoaFisica, Long> {
}