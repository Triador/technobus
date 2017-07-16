function changeNextBus(direction){
    if (direction === 'to'){
        var b = document.getElementById("fromButton");
        b.style = "background: white; color:#373737";
        b = document.getElementById("toButton");
        b.style =  "background: #373737; color:white";
        b = location.href.split("#");
        b = b[0];
        window.location.href = b + "#" + nextBusTo;
    }
    if (direction === 'from'){
        b = document.getElementById("toButton");
        b.style = "background: white; color:#373737";
        b = document.getElementById("fromButton");
        b.style =  "background: #373737; color:white";
        b = location.href.split("#");
        b = b[0];
        window.location.href = b + "#" + nextBusFrom;
    }
}