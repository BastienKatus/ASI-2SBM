package com.example.ESB;

import com.example.Model.CardModel;

import java.io.Serializable;

public class BusModel implements Serializable {
    private CardModel cardModel;
    private BusAction action;
    private Integer idTransaction;

    public BusModel( ) {
        this.cardModel = new CardModel();
        this.action = BusAction.DELETE;
        this.idTransaction = 0;
    }

    public BusModel(CardModel cardModel, BusAction action , Integer idTransaction) {
        this.cardModel = cardModel;
        this.action = action;
        this.idTransaction = idTransaction;
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

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    @Override
    public String toString() {
        return "BusModel{" +
                "cardModel=" + cardModel +
                ", action=" + action +
                ", idTransaction=" + idTransaction +
                '}';
    }
}
