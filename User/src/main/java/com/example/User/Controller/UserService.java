package com.example.User.Controller;

import java.util.*;
import java.util.stream.Collectors;

import com.example.User.ESB.BusAction;
import com.example.User.ESB.BusEmitter;
import com.example.User.ESB.BusModel;
import com.example.User.Model.UserModel;
import com.example.CommonLib.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

	private final UserRepository userRepository;
	@Autowired()
	private BusEmitter busEmitter;

	RestTemplate restTemplate = new RestTemplate();

	String cardControllerBaseUrl = "http://localhost:8082/";

	public UserService(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	public List<UserModel> getAllUsers() {
		List<UserModel> userList = new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		return userList;
	}

	public Optional<UserModel> getUser(String id) {
		return userRepository.findById(Integer.valueOf(id));
	}

	public Optional<UserModel> getUser(Integer id) {
		return userRepository.findById(id);
	}

	public String addUserESB(UserDTO user) {
		BusModel busModel = new BusModel(fromUDtoToUModel(user), BusAction.CREATE);
		busEmitter.sendMsg(busModel,"USER");
		return "En cours de création";
	}

	public UserDTO addUser(UserModel u) {
		// needed to avoid detached entity passed to persist error
		userRepository.save(u);
		Set<Integer> cardDTORandCard = new HashSet<>();
		try{
			ResponseEntity<Set> response2 = restTemplate.getForEntity(cardControllerBaseUrl + "get-random-cards/5", Set.class, "1");
			cardDTORandCard = response2.getBody();
		}catch (Exception e){
			System.out.println("Error while getting random cards");
			cardDTORandCard.add(1);
			cardDTORandCard.add(2);
			cardDTORandCard.add(3);
		}

		UserModel uBd = userRepository.save(u);

		uBd.setCardIdsList(cardDTORandCard);

		Set<Integer> uniqueCardIds = uBd.getCardIdsList().stream()
				.collect(Collectors.toSet());
		UserDTO userDTO = new UserDTO(uBd.getId(), uBd.getLogin(), uBd.getPwd(), uBd.getAccount(), uBd.getLastName(), uBd.getSurName(), uBd.getEmail(), uniqueCardIds);

		return userDTO;
	}

	public String updateUserESB(UserDTO user) {
		BusModel busModel = new BusModel(fromUDtoToUModel(user), BusAction.UPDATE);
		busEmitter.sendMsg(busModel,"USER");
		return "En cours de modification";
	}

	public UserDTO updateUser(UserDTO user) {
		UserModel u = fromUDtoToUModel(user);
		UserModel uBd = userRepository.save(u);
		Set<Integer> uniqueCardIds = uBd.getCardIdsList().stream()
				.collect(Collectors.toSet());
		UserDTO userDTO = new UserDTO(uBd.getId(), uBd.getLogin(), uBd.getPwd(), uBd.getAccount(), uBd.getLastName(), uBd.getSurName(), uBd.getEmail(), uniqueCardIds);
		return userDTO;
	}

	public UserDTO updateUser(UserModel user) {
		UserModel uBd = userRepository.save(user);
		Set<Integer> uniqueCardIds = uBd.getCardIdsList().stream()
				.collect(Collectors.toSet());
		UserDTO userDTO = new UserDTO(uBd.getId(), uBd.getLogin(), uBd.getPwd(), uBd.getAccount(), uBd.getLastName(), uBd.getSurName(), uBd.getEmail(), uniqueCardIds);
		return userDTO;
	}

	public String deleteUserESB(String id) {
		BusModel busModel = new BusModel();
		busModel.getUserModel().setId(Integer.valueOf(id));
		busEmitter.sendMsg(busModel,"USER");
		return "En cours de suppression";
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	public List<UserModel> getUserByLoginPwd(String login, String pwd) {
		System.out.println("getUserLoginPwd");
		List<UserModel> ulist = null;
		ulist = userRepository.findByLoginAndPwd(login, pwd);
		return ulist;
	}

	private UserModel fromUDtoToUModel(UserDTO user) {
		UserModel u = new UserModel(user);
		return u;
	}
}
