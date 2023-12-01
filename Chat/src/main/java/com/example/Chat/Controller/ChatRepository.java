package com.example.Chat.Controller;



import com.example.Chat.Model.ChatModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ChatRepository extends CrudRepository<ChatModel, Integer> {

    List<ChatModel> findByRoomName(String roomName);

}
