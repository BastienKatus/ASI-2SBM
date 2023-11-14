package com.example.Controller;

import com.example.CommonLib.CardDTO;
import com.example.CommonLib.UserDTO;
import com.example.ESB.BusAction;
import com.example.ESB.BusEmitter;
import com.example.ESB.BusModel;
import com.example.common.tools.DTOMapper;
import com.example.Model.CardModel;
import com.example.Model.CardReference;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardModelService {
	private final CardModelRepository cardRepository;
	private final CardReferenceService cardRefService;
	private Random rand;

	@Autowired()
	private BusEmitter busEmitter;

	public CardModelService(CardModelRepository cardRepository,CardReferenceService cardRefService) {
		this.rand=new Random();
		// Dependencies injection by constructor
		this.cardRepository=cardRepository;
		this.cardRefService=cardRefService;
	}
	
	public List<CardModel> getAllCardModel() {
		List<CardModel> cardList = new ArrayList<>();
		cardRepository.findAll().forEach(cardList::add);
		return cardList;
	}

	public String createCardESB(CardModel cardModel) {
		BusModel busModel = new BusModel(cardModel, BusAction.CREATE);
		busEmitter.sendMsg(busModel,"CARD");
		return "En cour de création";
	}

	public CardDTO createCard(CardModel cardModel) {
		CardModel cDb = cardRepository.save(cardModel);
		return DTOMapper.fromCardModelToCardDTO(cDb);
	}


	public String updateCardESB(CardModel cardModel) {
		BusModel busModel = new BusModel(cardModel, BusAction.UPDATE);
		busEmitter.sendMsg(busModel,"CARD");
		return "En cour de mise à jour";
	}
	public CardDTO updateCard(CardModel cardModel) {
		CardModel cDb=cardRepository.save(cardModel);
		return DTOMapper.fromCardModelToCardDTO(cDb);
	}
	public Optional<CardModel> getCard(Integer id) {
		return cardRepository.findById(id);
	}

	public String deleteCardESB(Integer id) {

		BusModel busModel = new BusModel();
		busModel.getCardModel().setId(id);
		busModel.setAction(BusAction.DELETE);
		busEmitter.sendMsg(busModel,"CARD");
		return "En cour de suppression";
	}

	public void deleteCard(Integer id) {
		cardRepository.deleteById(id);
	}
	
	/*public Set<CardModel> getRandCard(int nbr){
		List<CardModel> cardList=new ArrayList<>();
		for(int i=0;i<nbr;i++) {
			CardReference currentCardRef=cardRefService.getRandCardRef();
			CardModel currentCard=new CardModel(currentCardRef);
			currentCard.setAttack(rand.nextFloat()*100);
			currentCard.setDefence(rand.nextFloat()*100);
			currentCard.setEnergy(100);
			currentCard.setHp(rand.nextFloat()*100);
			currentCard.setPrice(currentCard.computePrice());
			//save new card before sending for user creation
			//this.addCard(currentCard);
			cardList.add(currentCard);
		}
		return cardList;
	}
*/

	public List<CardModel> getAllCardToSell(){
		return this.cardRepository.findAllToSell();}
}

