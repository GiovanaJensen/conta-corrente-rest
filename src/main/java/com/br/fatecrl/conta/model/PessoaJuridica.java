package com.br.fatecrl.conta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_pessoaJuridica")
public class PessoaJuridica extends Cliente {
    private static final long serialVersionUID = 1L;

    @Column(name="cd_cnpj", length=14)
    private String cnpj;
    @Column(name="nm_ramo_atividade", length=20)
    private String ramoAtividade;

    public PessoaJuridica(String nome, String endereco, String cnpj, String ramoAtividade) {
        super(nome, endereco);
        this.cnpj = cnpj;
        this.ramoAtividade = ramoAtividade;
    }

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
}
