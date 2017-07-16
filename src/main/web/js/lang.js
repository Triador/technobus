function changeLocale(loc){


    var req = new XMLHttpRequest();
    req.open('GET', "locale/"+loc+"Locale.json", false);
    req.send();
    if (req.status === 200) {
        var json = JSON.parse(req.responseText);
    }

    for(var k in json) {
        var element = document.getElementsByName(k);
        for (var i = 0; i < element.length; i++){
            element[i].innerText = json[k];
        }
    }

    if (loc === 'ru'){
        element = document.getElementById("tw_8_845372483");
        element.style = "display:none";
        element = document.getElementById("tw_8_865927444");
        element.style = "display:block";
    }
    else{
        element = document.getElementById("tw_8_845372483");
        element.style = "display:block";
        element = document.getElementById("tw_8_865927444");
        element.style = "display:none";
    }


}