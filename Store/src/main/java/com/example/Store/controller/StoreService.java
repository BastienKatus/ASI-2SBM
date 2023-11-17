package com.example.Store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.CommonLib.CardDTO;
import com.example.CommonLib.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Store.model.StoreAction;
import com.example.Store.model.StoreTransaction;
import org.springframework.web.client.RestTemplate;

@Service
public class StoreService {

	//private final CardModelService cardService;
	//private final UserService userService;
	private final StoreRepository storeRepository;
	private final RestTemplate restTemplate = new RestTemplate();
	private final String userUrl;
	private final String cardUrl;

	public StoreService(StoreRepository storeRepository) {
		//this.cardService = cardService;
		//this.userService = userService;
		this.storeRepository = storeRepository;
		userUrl = "http://localhost:8080/";
		cardUrl = "http://localhost:8082/";

	}

	public boolean buyCard(Integer user_id, Integer card_id) {
		ResponseEntity<UserDTO> userResponse = restTemplate.exchange(userUrl + "user/" + user_id, HttpMethod.GET, null, UserDTO.class);
		UserDTO userDTO = userResponse.getBody();
		ResponseEntity<CardDTO> cardResponse = restTemplate.exchange( cardUrl + "cards/" + card_id, HttpMethod.GET, null, CardDTO.class);
		CardDTO cardDTO = cardResponse.getBody();

		if (userDTO == null || cardDTO == null) {
			return false;
		}

		if (userDTO.getAccount() > cardDTO.getPrice() && cardDTO.getIsSell() == false) {
			userDTO.getCardList().add(card_id);
			userDTO.setAccount(userDTO.getAccount() - cardDTO.getPrice());

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			HttpEntity<UserDTO> requestUpdate = new HttpEntity<>(userDTO, headers);
			ResponseEntity<String> userUpdate = restTemplate.exchange(userUrl + "user/" + user_id, HttpMethod.PUT, requestUpdate, String.class);
			StoreTransaction sT = new StoreTransaction(user_id, card_id, StoreAction.BUY);
			storeRepository.save(sT);
			return true;
		} else {
			return false;
		}
	}

	public boolean sellCard(Integer user_id, Integer card_id) {
		ResponseEntity<UserDTO> userResponse = restTemplate.exchange(userUrl + "user/" + user_id, HttpMethod.GET, null, UserDTO.class);
		UserDTO userDTO = userResponse.getBody();
		ResponseEntity<CardDTO> cardResponse = restTemplate.exchange( cardUrl + "cards/" + card_id, HttpMethod.GET, null, CardDTO.class);
		CardDTO cardDTO = cardResponse.getBody();

		if (userDTO == null || cardDTO == null) {
			return false;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		cardDTO.setId(null);

		HttpEntity<CardDTO> requestUpdateCard = new HttpEntity<>(cardDTO, headers);
		ResponseEntity<String> cardUpdate = restTemplate.exchange( cardUrl + "cards/" + card_id, HttpMethod.PUT, requestUpdateCard, String.class);
		Set<Integer> listTmp = userDTO.getCardList();
		listTmp.remove(card_id);
		userDTO.setCardList(listTmp);
		userDTO.setAccount(userDTO.getAccount() + cardDTO.getPrice());
		HttpEntity<UserDTO> requestUpdateUser = new HttpEntity<>(userDTO, headers);
		ResponseEntity<String> userUpdate = restTemplate.exchange(userUrl + "user/" + user_id, HttpMethod.PUT, requestUpdateUser, String.class);

		StoreTransaction sT = new StoreTransaction(user_id, card_id, StoreAction.SELL);
		storeRepository.save(sT);
		return true;
	}

	public List<StoreTransaction> getAllTransactions() {
		List<StoreTransaction> sTList = new ArrayList<>();
		this.storeRepository.findAll().forEach(sTList::add);
		return sTList;

	}

}
