import { combineReducers } from "redux"

const initial = {
    price: 0,
    user: ''
}
export const reducer=(state=initial, action) => {
    const{type, payload} = action
    switch(type){
        case "buy":
            return {
                price: state.price+payload.price
            }
        case "sell":
            return{
                price: state.price-payload.price
            }
        case "user":
            return{
                user: payload.username,
                price: payload.price
            }
        default:
            return state
    }
}

export default combineReducers({
    reducer,
})