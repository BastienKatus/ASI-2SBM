package com.example.Auth.Controller;

import com.example.CommonLib.AuthDTO;
import com.example.CommonLib.CardDTO;
import com.example.CommonLib.UserDTO;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthService {

    RestTemplate restTemplate = new RestTemplate();

    String userControllerBaseUrl = "http://localhost:8080/";

    public UserDTO loginUser(AuthDTO auth) {
        try {
            ResponseEntity<UserDTO> response = restTemplate.getForEntity(userControllerBaseUrl + "user/"+auth.getUsername()+"/"+auth.getPassword(), UserDTO.class, "1");

            UserDTO userDTO = response.getBody();

            return userDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String registerUser(UserDTO user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDTO> request = new HttpEntity<>(user, headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(userControllerBaseUrl + "user", request, String.class);

            String userDTO = responseEntity.getBody();
            System.out.println("---" + userDTO);
            return userDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
