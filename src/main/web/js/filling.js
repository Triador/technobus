document.addEventListener('DOMContentLoaded', function (e) {
    var date = new Date();
    var hours = date.getHours();
    var minutes = date.getMinutes();

    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8081/schedule', false);
    xhr.send();
    if (xhr.status === 200) {
        var json = JSON.parse(xhr.responseText);
    }

    var from = json['toOffice'];
    var firstList = document.getElementsByClassName('curTimetable')[0];

    //для полного расписания
    var fullList = document.getElementsByClassName('column')[0];
    var hourCounter = 6;
    var row = document.createElement("div");
    row.className = "row timeLine";

    var date = new Date();
    var options ={
        hour: 'numeric',
        minute: 'numeric'
    };
    var curTime = date.toLocaleString('ru',options);

    var options1 = {
        weekday: 'short'
    };
    var day = date.toLocaleString('ru',options1);

    // var nextBus = from[0]["time"];

    for (var i = 0; i < from.length; i++) {
        var time = from[i]["time"];
        var mask = parseInt(from[i]["mask"]);
        var element = document.createElement("li");

        // parsing time from json
        var arr = time.split(':');

        if (parseInt(arr[0]) < hours ||
            (parseInt(arr[0]) === hours && (arr[1] < minutes))) {
            element.classList.add('older');
        }

        if (mask === 16 && date.getDay() !== 5) {
            continue
        }

        element.appendChild(document.createTextNode(time));

        firstList.appendChild(element);

        //а вот тут в полное
        var funcReturn = toFullPage(fullList,time,mask,hourCounter,row);
        hourCounter = funcReturn[0];
        row = funcReturn[1];
    }

    fullList = document.getElementsByClassName('column')[1];
    hourCounter = 8;
    row = document.createElement("div");
    row.className = "row timeLine";

    var to = json["fromOffice"];
    var secondList = document.getElementsByClassName('curTimetable')[1];
    for (i = 0; i < from.length; i++) {
        time = to[i]["time"];
        mask = to[i]["mask"];
        element = document.createElement("li");

        arr = time.split(':');
        if (parseInt(arr[0]) < hours ||
            (parseInt(arr[0]) === hours && (arr[1] < minutes))) {
            element.classList.add('older');
        }

        if (mask === 16 && date.getDay() !== 5) {
            continue;
        }

        element.appendChild(document.createTextNode(time));

        secondList.appendChild(element);

        funcReturn = toFullPage(fullList,time,mask,hourCounter,row);
        hourCounter = funcReturn[0];
        row = funcReturn[1];

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
    switch (parseInt(mask)){
        case 31:
            box.appendChild(document.createTextNode(time));
            break;
        case 16:
            box.appendChild(document.createTextNode(time + "(пт)"));
            break;
    }

    row.appendChild(box);
    list.appendChild(row);
    return [hourCounter,row];
}
