document.addEventListener('DOMContentLoaded', function (e) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8081/schedule', false);
    xhr.send();
    if (xhr.status === 200) {
        var json = JSON.parse(xhr.responseText);
    }

    var from = json['toOffice'];
    var firstList = document.getElementsByClassName('curTimetable')[0];

    //для полного расписания
    var fullFromMetroList = document.getElementsByClassName('column')[0];
    var hourCounter = 7;
    var row = document.createElement("div");
    row.className = "row timeLine";

    for (var i = 0; i < from.length; i++) {
        var time = from[i]["time"];
        var mask = from[i]["mask"];

        //здесь добавляем элементы на текущее расписание
        var element = document.createElement("li");

        element.appendChild(document.createTextNode(time));
        firstList.appendChild(element);

        //а вот тут в полное
        if (parseInt(time[0]+time[1]) > hourCounter){
            row = document.createElement("div");
            row.className = "row timeLine";
            hourCounter++;
            var row1 = document.createElement("div");
            row1.className = "row timeLine";
            row1.style = "height:4px; width = 100%; background-color: #ccc;padding-right: 20px;";
            fullFromMetroList.appendChild(row1);
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
        fullFromMetroList.appendChild(row);
    }

    var fullFromTechList = document.getElementsByClassName('column')[1];
    hourCounter = 9;
    row = document.createElement("div");
    row.className = "row timeLine";

    var to = json["fromOffice"];
    var secondList = document.getElementsByClassName('curTimetable')[1];
    for (i = 0; i < from.length; i++) {
        time = to[i]["time"];
        mask = to[i]["mask"];
        element = document.createElement("li");

        element.appendChild(document.createTextNode(time));
        secondList.appendChild(element);

        if (parseInt(time[0]+time[1]) > hourCounter){
            row = document.createElement("div");
            row.className = "row timeLine";
            hourCounter++;
            row1 = document.createElement("div");
            row1.className = "row timeLine";
            row1.style = "height:4px; width = 100%; background-color: #ccc;padding-right: 20px;";
            fullFromTechList.appendChild(row1);
        }
        box = document.createElement("div");
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
        fullFromTechList.appendChild(row);
    }
});