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
    }

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
    }
});