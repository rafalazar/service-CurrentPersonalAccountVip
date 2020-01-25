package com.rafalazar.bootcamp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.bootcamp.app.document.CurrentAccountVip;
import com.rafalazar.bootcamp.app.repository.CurrentAccountVipRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentAccountVipServiceImpl implements CurrentAccountVipService{

	@Autowired
	private CurrentAccountVipRepository repo;
	
	@Override
	public Flux<CurrentAccountVip> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<CurrentAccountVip> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Mono<CurrentAccountVip> save(CurrentAccountVip cav) {
		return repo.save(cav);
	}

	@Override
	public Mono<CurrentAccountVip> update(CurrentAccountVip cav, String id) {
		return repo.findById(id)
				.flatMap(cv -> {
					cv.setNumAccount(cav.getNumAccount());
					cv.setNameOwner(cav.getNameOwner());
					cv.setDniOwner(cav.getDniOwner());
					cv.setDepositAmount(cav.getDepositAmount());
					cv.setRetiro(cav.getRetiro());
					cv.setTotalAvailable(cav.getTotalAvailable());
					
					return repo.save(cv);
				});
	}

	@Override
	public Mono<Void> delete(CurrentAccountVip cav) {
		return repo.delete(cav);
	}

}
