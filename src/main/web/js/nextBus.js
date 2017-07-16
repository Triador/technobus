function changeNextBus(direction){
    if (direction === 'to'){
        var b = document.getElementById("fromButton");
        b.style = "background: white; color:#373737";
        b = document.getElementById("toButton");
        b.style =  "background: #373737; color:white";
        b = nextBusTo;
        var element = document.getElementById("fromMetro");
        element.style.display = 'block';
        element = document.getElementById("fromOffice");
        element.style.display = 'none';
        b = document.location.href.split("#");
        document.location.href = b[0] + '#' + nextBusTo;
        //document.getElementById(nextBusTo).scrollIntoView();
        // b = document.location.href.split("#");
        // b = b[0];

    }
    if (direction === 'from'){
        b = document.getElementById("toButton");
        b.style = "background: white; color:#373737";
        b = document.getElementById("fromButton");
        b.style =  "background: #373737; color:white";
        b = nextBusFrom;
        element = document.getElementById("fromMetro");
        element.style.display = 'none';
        element = document.getElementById("fromOffice");
        element.style.display = 'block';
        b = document.location.href.split("#");
        document.location.href = b[0] + '#' + nextBusFrom;
        //document.getElementById(nextBusFrom).scrollIntoView();
        // b = document.location.href.split("#");
        // b = b[0];
        // document.location.href = b + "#" + nextBusFrom;
    }
}