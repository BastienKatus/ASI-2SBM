package com.example.Store.controller;

import com.example.Store.model.StoreOrder;
import com.example.Store.model.StoreTransaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController
@RequestMapping(value="/store")
public class StoreRestController {

	private final StoreService storeService;

	public StoreRestController(StoreService storeService) {
		this.storeService = storeService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/buy")
	private boolean buyCard(@RequestBody StoreOrder order) {
		return storeService.buyCard(order.getUser_id(), order.getCard_id());

	}

	@RequestMapping(method = RequestMethod.POST, value = "/sell")
	private boolean sellCard(@RequestBody StoreOrder order) {
		return storeService.sellCard(order.getUser_id(), order.getCard_id());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/transaction")
	private List<StoreTransaction> getCard() {
		return storeService.getAllTransactions();
	}

}
