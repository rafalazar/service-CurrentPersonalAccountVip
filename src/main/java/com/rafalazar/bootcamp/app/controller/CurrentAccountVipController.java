package com.rafalazar.bootcamp.app.controller;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafalazar.bootcamp.app.document.CurrentAccountVip;
import com.rafalazar.bootcamp.app.service.CurrentAccountVipService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/currentAccountVip")
public class CurrentAccountVipController {
	
	@Autowired
	private CurrentAccountVipService service;
	
	@GetMapping("/findAll")
	Flux<CurrentAccountVip> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/findById/{id}")
	Mono<CurrentAccountVip> findById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	@PostMapping("/create")
	public Mono<ResponseEntity<CurrentAccountVip>> create(@RequestBody CurrentAccountVip cav) {
		if(cav.getDepositAmount() == null) {
			cav.setDepositAmount(0.0);
		}
		
		if(cav.getRetiro() == null) {
			cav.setRetiro(0.0);
		}
		
		if(cav.getCreateAt() == null) {
			cav.setCreateAt(new Date());
		}
		
		if(cav.getRetiroDate() == null) {
			cav.setRetiroDate(new Date());
		}
		
		if(cav.getDepositDate() == null) {
			cav.setDepositDate(new Date());
		}
		
		return service.save(cav).map(cv -> ResponseEntity.created(URI.create("/plazoFijoVip/".concat(cv.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(cv));
	}
	
	//Esta es la forma correcta de eliminar un producto.F!
	@DeleteMapping("/deleteById/{id}")
	Mono<ResponseEntity<Void>> deleteById(@PathVariable String id) {
		return service.findById(id)
				.flatMap(cv -> {
					return service.delete(cv)
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
				}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	
	//Esta es la forma correcta de actualizar - F!
	@PutMapping("/update/{id}")
	Mono<ResponseEntity<CurrentAccountVip>> update(@PathVariable String id, @RequestBody CurrentAccountVip cav) {
		return service.update(cav, id)
				.map(cv -> ResponseEntity.created(URI.create("/plazoFijoVip".concat(cv.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(cv))
				.defaultIfEmpty(ResponseEntity.notFound().build());
				
	}
	
}
