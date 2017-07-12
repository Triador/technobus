document.addEventListener('DOMContentLoaded', function (e) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8081/schedule', false);
    xhr.send();
    if (xhr.status === 200) {
        var json = JSON.parse(xhr.responseText);
    }

    var from = json['toOffice'];
    var firstList = document.getElementsByClassName('curTimetable')[0];
    for (var i = 0; i < from.length; i++) {
        var time = from[i]["time"];
        var mask = from[i]["mask"];
        var element = document.createElement("li");

        element.appendChild(document.createTextNode(time));
        firstList.appendChild(element);
    }

    var to = json["fromOffice"];
    var secondList = document.getElementsByClassName('curTimetable')[1];
    for (i = 0; i < from.length; i++) {
        time = to[i]["time"];
        mask = to[i]["mask"];
        element = document.createElement("li");

        element.appendChild(document.createTextNode(time));
        secondList.appendChild(element);
    }
});