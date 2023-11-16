
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8085/endpoint'
});
var sessionId = "";

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);

    let url = frame.headers['server'];

    if(url != undefined){
        url = url.replace("ws://localhost:8085/app/endpoint",  "");
        url = url.replace("/websocket", "");
        //url = url.replace(/^[0-9]+\//, "");
        console.log("Your current session is: " + url);
        sessionId = url;
    }

    stompClient.subscribe('/topic/USER', (response) => {
        console.log("I received USER", response.body)
    });

    stompClient.subscribe('/topic/CARD', (response) => {
        console.log("I received CARD", response.body)
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendFromFront() {
    stompClient.publish({
        destination: "/app/USERS",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendFromFront());
});