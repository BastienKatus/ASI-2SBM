package Controller;

import java.util.*;
import java.util.stream.Collectors;

import Model.UserModel;
import com.example.CommonLib.CardDTO;
import com.example.CommonLib.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class UserService {

	private final UserRepository userRepository;

	RestTemplate restTemplate = new RestTemplate();

	String baseUrl = "http://votre-serveur/api";

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

	public UserDTO addUser(UserDTO user) {
		UserModel u = fromUDtoToUModel(user);
		// needed to avoid detached entity passed to persist error
		userRepository.save(u);

		ResponseEntity<Set> response2 = restTemplate.getForEntity(baseUrl + "/get-random-cards/5", Set.class, "1");
		Set<CardDTO> cardDTORandCard = response2.getBody();

		UserModel uBd = userRepository.save(u);

		uBd.setCardDTOList(cardDTORandCard);

		Set<Integer> uniqueCardIds = uBd.getCardDTOList().stream()
				.map(CardDTO::getId)
				.collect(Collectors.toSet());
		UserDTO userDTO = new UserDTO(uBd.getId(), uBd.getLogin(), uBd.getPwd(), uBd.getAccount(), uBd.getLastName(), uBd.getSurName(), uBd.getEmail(), uniqueCardIds);

		return userDTO;
	}

	public UserDTO updateUser(UserDTO user) {
		UserModel u = fromUDtoToUModel(user);
		return this.updateUser(user);
	}

	public UserDTO updateUser(UserModel user) {
		UserModel uBd = userRepository.save(user);
		Set<Integer> uniqueCardIds = uBd.getCardDTOList().stream()
				.map(CardDTO::getId)
				.collect(Collectors.toSet());
		UserDTO userDTO = new UserDTO(uBd.getId(), uBd.getLogin(), uBd.getPwd(), uBd.getAccount(), uBd.getLastName(), uBd.getSurName(), uBd.getEmail(), uniqueCardIds);
		return userDTO;
	}

	public void deleteUser(String id) {
		userRepository.deleteById(Integer.valueOf(id));
	}

	public List<UserModel> getUserByLoginPwd(String login, String pwd) {
		List<UserModel> ulist = null;
		ulist = userRepository.findByLoginAndPwd(login, pwd);
		return ulist;
	}

	private UserModel fromUDtoToUModel(UserDTO user) {
		UserModel u = new UserModel(user);
		List<CardDTO> cardDTOList = new ArrayList<CardDTO>();
		for (Integer id : user.getCardList()) {
			// Exemple d'appel pour récupérer une carte par ID
			ResponseEntity<CardDTO> response2 = restTemplate.getForEntity(baseUrl + "/card/{id}", CardDTO.class, "1");
			CardDTO cardDTOById = response2.getBody();
			if (cardDTOById != null) {
				cardDTOList.add(cardDTOById);
			}
		}
		return u;
	}
}
