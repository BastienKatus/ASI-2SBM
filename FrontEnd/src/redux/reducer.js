import { combineReducers } from "redux"

const initial = {
    price: 0,
    user: '',
    cards: [],
}
export const reducer=(state=initial, action) => {
    const{type, payload} = action
    switch(type){
        case "buy":
            return {
                price: state.price-payload.price,
                user: state.user,
                cards: state.cards,
                selectedCard: state.selectedCard
            }
        case "sell":
            return{
                price: state.price+payload.price,
                user: state.user,
                cards: state.cards,
                selectedCard: state.selectedCard
            }
        case "user":
            return{
                user: payload.username,
                price: payload.price,
                cards: payload.cardList,
                selectedCard: state.selectedCard
            }
        case "selectCard":
            return{
                user: state.user,
                price: state.price,
                cards: state.cards,
                selectedCard: payload.selectedCard
            }
        default:
            return state
    }
}

export default combineReducers({
    reducer,
})