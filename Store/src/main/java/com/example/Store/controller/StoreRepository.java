package com.example.Store.controller;

import com.example.Store.model.StoreTransaction;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<StoreTransaction, Integer> {
	

}
