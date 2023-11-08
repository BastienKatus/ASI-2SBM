package com.example.Controller;

import com.example.CommonLib.CardDTO;
import com.example.Model.CardModel;
import com.example.common.tools.DTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController
@RequestMapping("/cards")
public class CardRestController {

	private final CardModelService cardModelService;
	
	public CardRestController(CardModelService cardModelService) {
		this.cardModelService=cardModelService;
	}

	@GetMapping()
	private List<CardDTO> getAllCards() {
		List<CardDTO> cLightList=new ArrayList<>();
		for(CardModel c:cardModelService.getAllCardModel()){
			cLightList.add(DTOMapper.fromCardModelToCardDTO(c));
		}
		return cLightList;

	}

	@GetMapping("/{id}")
	private CardDTO getCard(@PathVariable String id) {
		Optional<CardModel> rcard;
		rcard= cardModelService.getCard(Integer.valueOf(id));
		if(rcard.isPresent()) {
			return DTOMapper.fromCardModelToCardDTO(rcard.get());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card id:"+id+", not found",null);

	}

	@PostMapping()
	public CardDTO addCard(@RequestBody CardDTO card) {
		return cardModelService.addCard(DTOMapper.fromCardDtoToCardModel(card));
	}

	@PutMapping("/{id}")
	public CardDTO updateCard(@RequestBody CardDTO card,@PathVariable String id) {
		card.setId(Integer.valueOf(id));
		return cardModelService.updateCard(DTOMapper.fromCardDtoToCardModel(card));
	}

	@DeleteMapping("/{id}")
	public void deleteCard(@PathVariable String id) {
		cardModelService.deleteCardModel(Integer.valueOf(id));
	}

	@GetMapping("/cards_to_sell")
	private List<CardDTO> getCardsToSell() {
		List<CardDTO> list=new ArrayList<>();
		for( CardModel c : cardModelService.getAllCardToSell()){
			CardDTO cLight=DTOMapper.fromCardModelToCardDTO(c);
			list.add(cLight);
		}
		return list;

	}

	/*@RequestMapping(method=RequestMethod.GET, value="/get-random-cards/{nbr}")
	private Set<CardDTO> getRandCard(@PathVariable int nbr) {
		return cardModelService.getRandCard(nbr);;

	}*/
	
}
