package com.rafalazar.bootcamp.app.service;

import com.rafalazar.bootcamp.app.document.CurrentAccountVip;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountVipService {
	
	public Flux<CurrentAccountVip> findAll();
	
	public Mono<CurrentAccountVip> findById(String id);
	
	public Mono<CurrentAccountVip> save(CurrentAccountVip cav);
	
	public Mono<CurrentAccountVip> update(CurrentAccountVip cav, String id);    
	
	public Mono<Void> delete(CurrentAccountVip cav);

}
