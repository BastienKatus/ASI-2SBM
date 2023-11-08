package com.example.common.tools;
import com.example.CommonLib.CardDTO;
import com.example.Model.CardModel;


public class DTOMapper {
	
	public static CardDTO fromCardModelToCardDTO(CardModel cM) {
		CardDTO cDto =new CardDTO(cM.getId(), cM.getName(), cM.getDescription(), cM.getFamily(),cM.getAffinity(), cM.getImgUrl(), cM.getSmallImgUrl(),cM.getEnergy(), cM.getHp(), cM.getDefence(), cM.getAttack(), cM.getPrice(),0);
		return cDto;
	}
	
	public static CardModel fromCardDtoToCardModel(CardDTO cD) {
		CardModel cm=new CardModel(cD.getName(), cD.getDescription(), cD.getFamily(), cD.getAffinity(), cD.getEnergy(), cD.getHp(), cD.getDefence(), cD.getAttack(), cD.getImgUrl(), cD.getSmallImgUrl(),cD.getPrice());
		cm.setEnergy(cD.getEnergy());
		cm.setHp(cD.getHp());
		cm.setDefence(cD.getDefence());
		cm.setAttack(cD.getAttack());
		cm.setPrice(cD.getPrice());
		cm.setId(cD.getId());
		return cm;
	}
	
	

	
}
