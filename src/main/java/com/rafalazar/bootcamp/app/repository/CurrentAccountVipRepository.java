package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.document.CurrentAccountVip;

public interface CurrentAccountVipRepository extends ReactiveMongoRepository<CurrentAccountVip, String>{

}
