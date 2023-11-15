import { combineReducers } from "redux"

const initial = {
    price: 0,
    user: [],
    cards: [],
    selectedCard: [],
    socket: []
}
export const reducer=(state=initial, action) => {
    const{type, payload} = action
    switch(type){
        case "buy":
            return {
                ...state,
                price: state.price-payload.price,
            }
        case "cards":
            return {
                ...state,
                cards: payload.cards,
            }
        case "sell":
            return{
                ...state,
                price: state.price+payload.price,
            }
        case "user":
            return{
                ...state,
                user: payload.username,
                price: payload.price,
            }
        case "selectCard":
            return{
                ...state,
                selectedCard: payload.selectedCard
            }
        case "socket":
            return{
                ...state,
                socket: payload.socketio
            }
        default:
            return state
    }
}

export default combineReducers({
    reducer,
})