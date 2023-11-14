package com.example.ESB;

import com.example.Model.CardModel;

import java.io.Serializable;

public class BusModel implements Serializable {
    private CardModel cardModel;
    private BusAction action;

    public BusModel( ) {
        this.cardModel = new CardModel();
        this.action = BusAction.DELETE;
    }

    public BusModel(CardModel cardModel, BusAction action) {
        this.cardModel = cardModel;
        this.action = action;
    }

    public void setCardModel(CardModel cardModel) {
        this.cardModel = cardModel;
    }

    public void setAction(BusAction action) {
        this.action = action;
    }

    public CardModel getCardModel() {
        return cardModel;
    }

    public BusAction getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "BusModel{" + cardModel.toString() +
                ", action=" + action.toString() +
                '}';
    }
}
