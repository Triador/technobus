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

    element = document.getElementsByName("box");
    for (var i = 0; i < element.length; i++){
        if (element[i].innerText.substring(5,6) == '('){
            var comma = false;
            var str = element[i].innerText.substring(0,6);
            if (loc === 'en'){
                var index = element[i].innerText.indexOf("пн");
                var index1 = element[i].innerText.indexOf("Mon");
                if ((index != -1) || (index1 != -1)){
                    str += 'Mon';
                    comma = true;
                }
                index = element[i].innerText.indexOf("вт");
                index1 = element[i].innerText.indexOf("Tue");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'Tue';
                }
                index = element[i].innerText.indexOf("ср");
                index1 = element[i].innerText.indexOf("Wed");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'Wed';
                }
                index = element[i].innerText.indexOf("чт");
                index1 = element[i].innerText.indexOf("Thu");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'Thu';
                }
                index = element[i].innerText.indexOf("пт");
                index1 = element[i].innerText.indexOf("Fri");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'Fri';
                }
                index = element[i].innerText.indexOf("сб")
                index1 = element[i].innerText.indexOf("Sat");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'Sat';
                }
                index = element[i].innerText.indexOf("вс");
                index1 = element[i].innerText.indexOf("Sun");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'Sun';
                }
            }
            if (loc === 'ru'){
                index = element[i].innerText.indexOf("Mon");
                index1 = element[i].innerText.indexOf("пн");
                if ((index != -1) || (index1 != -1)){
                    str += 'пн';
                    comma = true;
                }
                index = element[i].innerText.indexOf("Tue");
                index1 = element[i].innerText.indexOf("вт");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'вт';
                }
                index = element[i].innerText.indexOf("Wed");
                index1 = element[i].innerText.indexOf("ср");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'ср';
                }
                index = element[i].innerText.indexOf("Thu");
                index1 = element[i].innerText.indexOf("чт");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'чт';
                }
                index = element[i].innerText.indexOf("Fri");
                index1 = element[i].innerText.indexOf("пт");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'пт';
                }
                index = element[i].innerText.indexOf("Sat");
                index1 = element[i].innerText.indexOf("сб");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'сб';
                }
                index = element[i].innerText.indexOf("Sun");
                index1 = element[i].innerText.indexOf("вс");
                if ((index != -1) || (index1 != -1)){
                    if (comma) str += ',';
                    else comma = true;
                    str += 'вс';
                }
            }
            str += ')';
            element[i].innerText = str;
        }
    }

}