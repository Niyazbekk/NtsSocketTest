var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#logsTable").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/logs', function (logs) {
            console.log(logs.body);
            let strLogs = (logs.body).split(" ");
            strLogs.forEach(element => showLogs(element));

        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendCommand() {
    let radioValue = $("input[name='command']:checked").val();
    if(radioValue === "addLog"){
        alert(radioValue)
        stompClient.send("/app/hello", {}, JSON.stringify({'command': radioValue , 'content' : $("#logContent").val()}));
    }
    else{
        alert(radioValue)
        stompClient.send("/app/hello", {}, JSON.stringify({'command': radioValue}));
    }

}

function showLogs(content) {
    $("#logsTable").append("<tr><td>" + content + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendCommand(); });
});