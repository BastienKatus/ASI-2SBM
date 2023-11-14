package com.example.User.Controller;

import com.example.User.Model.UserModel;
import com.example.CommonLib.AuthDTO;
//import com.example.CommonLib.CardDTO;
import com.example.CommonLib.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController
public class UserRestController {

	private final UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService=userService;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/users")
	private List<UserDTO> getAllUsers() {
		List<UserDTO> uDTOList=new ArrayList<UserDTO>();
		for(UserModel uM: userService.getAllUsers()){
			Set<Integer> uniqueCardIds = uM.getCardIdsList().stream()
					.collect(Collectors.toSet());
			UserDTO userDTO = new UserDTO(uM.getId(), uM.getLogin(), uM.getPwd(), uM.getAccount(), uM.getLastName(), uM.getSurName(), uM.getEmail(), uniqueCardIds);

			uDTOList.add(userDTO);
		}
		return uDTOList;

	}
	
	@RequestMapping(method=RequestMethod.GET,value="/user/{id}")
	private UserDTO getUser(@PathVariable String id) {
		Optional<UserModel> user;
		user= userService.getUser(id);
		if(user.isPresent()) {
			UserModel uM = user.get();
			Set<Integer> uniqueCardIds = uM.getCardIdsList().stream()
					.collect(Collectors.toSet());
			UserDTO userDTO = new UserDTO(uM.getId(), uM.getLogin(), uM.getPwd(), uM.getAccount(), uM.getLastName(), uM.getSurName(), uM.getEmail(), uniqueCardIds);

			return userDTO;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User id:"+id+", not found",null);

	}

	@RequestMapping(method=RequestMethod.GET,value="/user/{login}/{password}")
	private UserDTO getUser(@PathVariable String login, @PathVariable String password) {
		List<UserModel> users;
		users = userService.getUserByLoginPwd(login, password);
		if(users.size() > 0){
			UserModel user = users.get(0);
			if(user != null) {
				UserModel uM = user;
				Set<Integer> uniqueCardIds = uM.getCardIdsList().stream()
						.collect(Collectors.toSet());
				UserDTO userDTO = new UserDTO(uM.getId(), uM.getLogin(), uM.getPwd(), uM.getAccount(), uM.getLastName(), uM.getSurName(), uM.getEmail(), uniqueCardIds);

				return userDTO;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User login:"+ login + "password: " +  password + ", not found",null);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/user")
	public String addUser(@RequestBody UserDTO user) {
		return userService.addUserESB(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/user/{id}")
	public String updateUser(@RequestBody UserDTO user, @PathVariable String id) {
		user.setId(Integer.valueOf(id));
		return userService.updateUserESB(user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/user/{id}")
	public String deleteUserESB(@PathVariable String id) {
		return userService.deleteUserESB(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/auth")
	private Integer getAllCourses(@RequestBody AuthDTO authDto) {
		 List<UserModel> uList = userService.getUserByLoginPwd(authDto.getUsername(),authDto.getPassword());
		if( uList.size() > 0) {
			return uList.get(0).getId();
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Authentification Failed",null);
	}
}
