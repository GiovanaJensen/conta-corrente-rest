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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.fatecrl.conta.model.PessoaFisica;
import com.br.fatecrl.conta.service.PessoaFisicaService;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/pessoasFisica")
public class PessoaFisicaController implements IController<PessoaFisica>{
    @Autowired
	private PessoaFisicaService service;
	
	@Override
	@GetMapping(produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"
					   , description = "Resultado com sucesso"
			),
			@ApiResponse(responseCode = "500"
			           , description = "Erro interno do servidor"
					   )
	})
	@Operation(summary = "Retorna a lista de pessoas fisicas")
	public ResponseEntity<List<PessoaFisica>> getAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@Override
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<PessoaFisica> get(@PathVariable("id") Long id) {
		PessoaFisica pessoa = service.findById(id);
		if (pessoa != null) {
			return ResponseEntity.ok(pessoa);
		}
		return ResponseEntity.notFound().build();
	}	
	
	@Override
	@PostMapping
	@Operation(summary = "Cria uma Pessoa Fisica")
	public ResponseEntity<PessoaFisica> post(@RequestBody PessoaFisica pessoa){
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
	public ResponseEntity<PessoaFisica> put(@RequestBody PessoaFisica pessoa){
		if (service.update(pessoa)) {
			return ResponseEntity.ok(pessoa);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	@PatchMapping
	@Operation(summary = "Atualiza uma Pessoa Fisica")
	public ResponseEntity<PessoaFisica> patch(@RequestBody PessoaFisica pessoa){
		if (service.update(pessoa)) {
			return ResponseEntity.ok(pessoa);
		}
		return ResponseEntity.notFound().build();
	}	
	
	@Override
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Exclui uma Pessoa Fisica")
	public ResponseEntity<PessoaFisica> delete(@PathVariable("id") Long id){
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}	
}
