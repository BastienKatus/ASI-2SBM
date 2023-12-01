package com.example.Chat.Controller;

import com.example.Chat.Model.ChatModel;
import com.example.CommonLib.ChatDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChatService {

	private final ChatRepository chatRepository;
	RestTemplate restTemplate = new RestTemplate();

	public ChatService(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}

	public List<ChatModel> getAllChat() {
		List<ChatModel> chatList = new ArrayList<>();
		chatRepository.findAll().forEach(chatList::add);
		return chatList;
	}
	public List<ChatModel> getChatByRoomName(String roomName) {
		List<ChatModel> chatList = new ArrayList<>();
		return chatRepository.findByRoomName(roomName);
	}


	public ChatDTO addChat(ChatModel c) {
		// needed to avoid detached entity passed to persist error
		chatRepository.save(c);
		ChatDTO chatDTO = new ChatDTO(c.getMessage(),c.getIdSender(),c.getIdReceiver(),c.getNumMessage(),c.getRoomName());
		return chatDTO;
	}


}
