package com.example.Controller;

import com.example.CommonLib.UserDTO;
import com.example.Model.CardModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardModelRepository extends CrudRepository<CardModel, Integer> {
    List<CardModel> findByUser(UserDTO u);
}
