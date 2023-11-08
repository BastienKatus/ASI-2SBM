const buy = () => {
    return (dispatch) => (
        dispatch({
            type: "buy",
            payload: 1,
        })
    )
}
export default buy;