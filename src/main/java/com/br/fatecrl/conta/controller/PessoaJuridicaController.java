package com.br.fatecrl.conta.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.fatecrl.conta.model.PessoaJuridica;
import com.br.fatecrl.conta.service.PessoaJuridicaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class PessoaJuridicaController implements IController<PessoaJuridica>{
    @Autowired
	private PessoaJuridicaService service;
	
	@Override
	@GetMapping(produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"
					   , description = "Resultado com sucesso"
			//		   , content = {@Content(mediaType = "application/json")}
			),
			@ApiResponse(responseCode = "500"
			           , description = "Erro interno do servidor"
			//           , content = {@Content(mediaType = "application/json")} 
			)
	})
	@Operation(summary = "Retorna a lista de pessoas fisicas")
	public ResponseEntity<List<PessoaJuridica>> getAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@Override
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<PessoaJuridica> get(@PathVariable("id") Long id) {
		PessoaJuridica pessoa = service.findById(id);
		if (pessoa != null) {
			return ResponseEntity.ok(pessoa);
			//HTTP 200 OK
		}
		return ResponseEntity.notFound().build();
	}	
	
	@Override
	@PostMapping
	@Operation(summary = "Cria uma Pessoa Fisica")
	public ResponseEntity<PessoaJuridica> post(@RequestBody PessoaJuridica pessoa){
		service.create(pessoa);

		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(pessoa.getId())
						.toUri();
		return ResponseEntity.created(location).body(pessoa);
	}
	
	@Override
	@PutMapping
	@Operation(summary = "Atualiza uma Pessoa Fisica")
	public ResponseEntity<PessoaJuridica> put(@RequestBody PessoaJuridica pessoa){
		if (service.update(pessoa)) {
			return ResponseEntity.ok(pessoa);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	@PatchMapping
	@Operation(summary = "Atualiza uma Pessoa Fisica")
	public ResponseEntity<PessoaJuridica> patch(@RequestBody PessoaJuridica pessoa){
		if (service.update(pessoa)) {
			return ResponseEntity.ok(pessoa);
		}
		return ResponseEntity.notFound().build();
	}	
	
	@Override
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Exclui uma Pessoa Fisica")
	public ResponseEntity<PessoaJuridica> delete(@PathVariable("id") Long id){
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}	
}
