package com.example.Controller;

import com.example.Model.CardReference;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CardReferenceService {
	
	private final CardRefRepository cardRefRepository;
	
	public CardReferenceService(CardRefRepository cardRefRepository) {
		this.cardRefRepository=cardRefRepository;
	}

	public List<CardReference> getAllCardRef() {
		List<CardReference> cardRefList = new ArrayList<>();
		cardRefRepository.findAll().forEach(cardRefList::add);
		return cardRefList;
	}

	public void addCardRef(CardReference cardRef) {
		cardRefRepository.save(cardRef);
	}

	public void updateCardRef(CardReference cardRef) {
		cardRefRepository.save(cardRef);

	}

	public CardReference getRandCardRef() {
		List<CardReference> cardRefList=getAllCardRef();
		if( cardRefList.size()>0) {
			Random rand=new Random();
			int rindex=rand.nextInt(cardRefList.size()-1);
			return cardRefList.get(rindex);
		}
		return null;
	}

	/**
	 * Executed after application start
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void doInitAfterStartup() {
		List<CardReference> cardRefList=getAllCardRef();
		if( cardRefList.size()==0) {
			cardRefRepository.addPokemonStart();
		}
	}
	
}
