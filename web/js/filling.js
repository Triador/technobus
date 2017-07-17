var nextBusTo;
var nextBusFrom;

document.addEventListener('DOMContentLoaded', function (e) {

    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8081/schedule', false);
    xhr.send();
    if (xhr.status === 200) {
        var json = JSON.parse(xhr.responseText);
    }
    var date = new Date();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var day = date.getDay();
    day = getDayMask(day);

    var from = json['toOffice'];
    var firstList = document.getElementsByClassName('curTimetable')[0];

    //для полного расписания
    var fullList = document.getElementsByClassName('column')[0];
    var hourCounter = 6;
    var row = document.createElement("div");
    row.className = "row timeLine";
    var oldTime = true;
    for (var i = 0; i < from.length; i++) {
        var time = from[i]["time"];
        var mask = parseInt(from[i]["mask"]);
        var mask1 = mask & day;
        if (mask1 === day){
            var element1 = document.createElement("a");
            element1.id = "to" + time;
            element = document.createElement("li");
             // parsing time from json
            var arr = time.split(':');
            if (parseInt(arr[0]) < hours ||
                (parseInt(arr[0]) === hours && (arr[1] < minutes))) {
                    element.classList.add('older');
            }
            else{
                if (oldTime){
                    nextBusTo = "to" + time;
                    oldTime = false;
                }
            }
            element.appendChild(document.createTextNode(time));
            firstList.appendChild(element1);
            firstList.appendChild(element);
        }
        //а вот тут в полное
        var funcReturn = toFullPage(fullList,time,mask,hourCounter,row);
        hourCounter = funcReturn[0];
        row = funcReturn[1];
    }
    if (oldTime){
        element = document.createElement("li");
        element.id = "noBusTo";
        nextBusTo = "noBusTo";
        element.appendChild(document.createTextNode("На сегодня рейсов больше нет :("));
        element.setAttribute("name", "noBus");
        firstList.appendChild(element);
    }
    if ((day === 6) || (day === 7)){
        var txt = document.createElement("p");
        txt.innerText = "Автобус ходит только по будним дням!";
        txt.setAttribute("name",'onlyWeekDays');
        txt.style = "margin-top: 20px; font-size: 20px; text-align: center";
        fullList.appendChild(txt);
    }


    fullList = document.getElementsByClassName('column')[1];
    hourCounter = 8;
    row = document.createElement("div");
    row.className = "row timeLine";

    var to = json["fromOffice"];
    var secondList = document.getElementsByClassName('curTimetable')[1];
	
	oldTime = true;

    for (i = 0; i < to.length; i++) {
        time = to[i]["time"];
        mask = to[i]["mask"];
		mask1 = mask & day;
		if (mask1 === day){
			element = document.createElement("li");
            element1 = document.createElement("a");
            element1.id = "from" + time;
			arr = time.split(':');
			if (parseInt(arr[0]) < hours ||
				(parseInt(arr[0]) === hours && (arr[1] < minutes))) {
				element.classList.add('older');
			}
            else{
                if (oldTime){
                    nextBusFrom = "from" + time;
                    oldTime = false;
                }
            }
			element.appendChild(document.createTextNode(time));
            secondList.appendChild(element1);
			secondList.appendChild(element);
		}
		
		funcReturn = toFullPage(fullList,time,mask,hourCounter,row);
        hourCounter = funcReturn[0];
        row = funcReturn[1];
	}
    if (oldTime){
	    var element = document.createElement("li");
        element.id = "noBusFrom";
        nextBusFrom = "noBusFrom";
		element.appendChild(document.createTextNode("На сегодня рейсов больше нет :("));
        element.setAttribute("name","noBus");
		secondList.appendChild(element);
    }
});


function toFullPage(list, time, mask, hourCounter, row){
    if (parseInt(time[0]+time[1]) > hourCounter){
        row = document.createElement("div");
        row.className = "row timeLine";
        var row1 = document.createElement("div");
        row1.className = "row timeLine1";
        row1.style = "";
        list.appendChild(row1);
        hourCounter++;
    }
    var box = document.createElement("div");
    box.className = "timeBox";
    if (mask == 31){
        box.appendChild(document.createTextNode(time));
    }
    else{
    var first = true;
        var days = "(";
        for (var i = 1; i <= 7; i++){
            var day = getDayMask(i);
            var mask1 = day & mask;
            if (mask1 == day){
                if (!first) days += ", ";
                days += getDay(day);
                first = false;
            }
        }
        days += ")";
        box.appendChild(document.createTextNode(time + days));
    }
    box.setAttribute("name","box");
    row.appendChild(box);
    list.appendChild(row);
    return [hourCounter,row];
}

function getDay(mask){
    var str;
    switch (mask) {
        case 1:
            str = "пн";
            break;
        case 2:
            str = "вт";
            break;
        case 4:
            str = "ср";
            break;
        case 8:
            str = "чт";
            break;
        case 16:
            str = "пт";
            break;
        case 32:
            str = "сб";
            break;
        case 64:
            str = "вс";
            break;
    }
    return str;
}

function getDayMask(day) {
    var day1;
    switch (day) {
        case 1:
            day1 = 1;
            break;
        case 2:
            day1 = 2;
            break;
        case 3:
            day1 = 4;
            break;
        case 4:
            day1 = 8;
            break;
        case 5:
            day1 = 16;
            break;
        case 6:
            day1 = 32;
            break;
        case 7:
            day1 = 64;
    }
    return day1;
}