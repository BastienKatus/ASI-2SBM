package com.example.Chat.Controller;

import com.example.Chat.Model.ChatModel;
import com.example.CommonLib.ChatDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController
public class ChatRestController {

	private final ChatService chatService;
	
	public ChatRestController(ChatService chatService) {
		this.chatService=chatService;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/chat")
	private List<ChatDTO> getAllChat() {
		List<ChatDTO> cDTOList=new ArrayList<ChatDTO>();
		for(ChatModel cM: chatService.getAllChat()){
			ChatDTO chatDTO = new ChatDTO(cM.getMessage(), cM.getIdSender(), cM.getIdReceiver(), cM.getNumMessage(), cM.getRoomName());
			cDTOList.add(chatDTO);
		}
		return cDTOList;

	}
	
	@RequestMapping(method=RequestMethod.GET,value="/chat/{roomName}")
	private List<ChatDTO> getChatbyIdRoom(@PathVariable String roomName) {
		List<ChatDTO> cDTOList=new ArrayList<ChatDTO>();
		for(ChatModel cM: chatService.getChatByRoomName(roomName)){
			ChatDTO chatDTO = new ChatDTO(cM.getMessage(), cM.getIdSender(), cM.getIdReceiver(), cM.getNumMessage(), cM.getRoomName());
			cDTOList.add(chatDTO);
		}
		return cDTOList;

	}

	
	@RequestMapping(method=RequestMethod.POST,value="/chat")
	public ChatDTO addUser(@RequestBody ChatDTO chat) {
		return chatService.addChat(new ChatModel(chat));
	}

}
