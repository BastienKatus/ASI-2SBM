package com.example.Controller;

import com.example.CommonLib.BusNotifModel;
import com.example.CommonLib.CardDTO;
import com.example.ESB.BusAction;
import com.example.ESB.BusEmitter;
import com.example.ESB.BusModel;
import com.example.Model.CardReference;
import com.example.common.tools.DTOMapper;
import com.example.Model.CardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardModelService {
	private final CardModelRepository cardRepository;
	private final CardReferenceService cardRefService;
	private Random rand;
	private int idTransaction;

	@Autowired()
	private BusEmitter busEmitter;

	public CardModelService(CardModelRepository cardRepository,CardReferenceService cardRefService) {
		this.rand=new Random();
		// Dependencies injection by constructor
		this.cardRepository=cardRepository;
		this.cardRefService=cardRefService;
		this.idTransaction = 0;
	}
	
	public List<CardModel> getAllCardModel() {
		List<CardModel> cardList = new ArrayList<>();
		cardRepository.findAll().forEach(cardList::add);
		return cardList;
	}

	public String createCardESB(CardModel cardModel) {
		idTransaction ++;
		BusModel busModel = new BusModel(cardModel, BusAction.CREATE,idTransaction);
		busEmitter.sendMsg(busModel,"CARD");

		return String.valueOf(idTransaction) + "/CARD";
	}

	public CardDTO createCard(CardModel cardModel,Integer idTransaction) {
		CardModel cDb = cardRepository.save(cardModel);
		if (idTransaction != 0){
			BusNotifModel busNotifModel = new BusNotifModel(idTransaction,"CARD");
			busEmitter.sendMsg(busNotifModel,"NOTIFS");
		}
		return DTOMapper.fromCardModelToCardDTO(cDb);
	}


	public String updateCardESB(CardModel cardModel) {
		idTransaction ++;
		BusModel busModel = new BusModel(cardModel, BusAction.UPDATE,idTransaction);
		busEmitter.sendMsg(busModel,"CARD");
		return String.valueOf(idTransaction) + "/CARD";
	}
	public CardDTO updateCard(CardModel cardModel,Integer idTransaction) {
		CardModel cDb=cardRepository.save(cardModel);
		BusNotifModel busNotifModel = new BusNotifModel(idTransaction,"CARD");
		busEmitter.sendMsg(busNotifModel,"NOTIFS");
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
		idTransaction ++;
		return String.valueOf(idTransaction) + "/CARD";
	}

	public void deleteCard(Integer id,Integer idTransaction) {
		cardRepository.deleteById(id);
		BusNotifModel busNotifModel = new BusNotifModel(idTransaction,"CARD");
		busEmitter.sendMsg(busNotifModel,"NOTIFS");
	}
	
	public List<Integer> getRandCard(int nbr){
		List<Integer> cardList=new ArrayList<>();
		for(int i=0;i<nbr;i++) {
			CardReference currentCardRef=cardRefService.getRandCardRef();
			CardModel currentCard=new CardModel(currentCardRef);
			currentCard.setAttack(rand.nextFloat()*100);
			currentCard.setDefence(rand.nextFloat()*100);
			currentCard.setEnergy(100);
			currentCard.setHp(rand.nextFloat()*100);
			currentCard.setPrice(currentCard.computePrice());
			currentCard.isSell(true);
			//save new card before sending for user creation
			this.createCard(currentCard,0);
			cardList.add(currentCard.getId());
		}
		return cardList;
	}


	public List<CardModel> getAllCardToSell(){
		return this.cardRepository.findAllToSell();}
}

