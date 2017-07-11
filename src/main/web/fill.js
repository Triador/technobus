var jsonStr = '{"fromOffice":[{"time":"09:30","mask":31},{"time":"10:10","mask":31},{"time":"10:50","mask":31},{"time":"11:30","mask":31},{"time":"12:10","mask":31},{"time":"12:50","mask":31},{"time":"13:30","mask":31},{"time":"14:10","mask":31},{"time":"14:50","mask":31},{"time":"15:10","mask":16},{"time":"15:30","mask":31},{"time":"15:50","mask":31},{"time":"16:00","mask":16},{"time":"16:30","mask":31},{"time":"16:50","mask":31},{"time":"17:00","mask":31},{"time":"17:10","mask":31},{"time":"17:30","mask":31},{"time":"17:40","mask":31},{"time":"17:50","mask":31},{"time":"18:00","mask":31},{"time":"18:10","mask":31},{"time":"18:20","mask":31},{"time":"18:30","mask":31},{"time":"18:40","mask":31},{"time":"18:50","mask":31},{"time":"19:10","mask":31},{"time":"19:20","mask":31},{"time":"19:30","mask":31},{"time":"19:50","mask":31},{"time":"20:10","mask":31},{"time":"20:45","mask":31},{"time":"21:20","mask":31}],"toOffice":[{"time":"07:45","mask":31},{"time":"08:00","mask":31},{"time":"08:10","mask":31},{"time":"08:20","mask":31},{"time":"08:30","mask":31},{"time":"08:35","mask":31},{"time":"08:40","mask":31},{"time":"08:50","mask":31},{"time":"09:00","mask":31},{"time":"09:10","mask":31},{"time":"09:15","mask":31},{"time":"09:20","mask":31},{"time":"09:30","mask":31},{"time":"09:40","mask":31},{"time":"09:50","mask":31},{"time":"09:55","mask":31},{"time":"10:00","mask":31},{"time":"10:10","mask":31},{"time":"10:20","mask":31},{"time":"10:30","mask":31},{"time":"10:35","mask":31},{"time":"10:40","mask":31},{"time":"10:50","mask":31},{"time":"11:00","mask":31},{"time":"11:10","mask":31},{"time":"11:20","mask":31},{"time":"11:30","mask":31},{"time":"11:50","mask":31},{"time":"12:10","mask":31},{"time":"12:30","mask":31},{"time":"13:10","mask":31},{"time":"13:50","mask":31},{"time":"14:30","mask":31},{"time":"15:10","mask":31},{"time":"15:30","mask":31},{"time":"16:10","mask":31},{"time":"16:50","mask":31},{"time":"17:20","mask":31}]}';
var json = JSON.parse(jsonStr);


document.addEventListener('DOMContentLoaded', function (e) {
    var from = json['fromOffice'];
    var firstList = document.getElementsByClassName('curTimetable')[0];
    for (var i = 0; i < from.length; i++) {
        var time = from[i]["time"];
        var mask = from[i]["mask"];
        var element = document.createElement("li");

        element.appendChild(document.createTextNode(time));
        firstList.appendChild(element);
    }

    var to = json["toOffice"];
    var secondList = document.getElementsByClassName('curTimetable')[1];
    for (i = 0; i < from.length; i++) {
        time = to[i]["time"];
        mask = to[i]["mask"];
        element = document.createElement("li");

        element.appendChild(document.createTextNode(time));
        secondList.appendChild(element);
    }
});

/*
var xhr = new XMLHttpRequest();
xhr.open('GET', '0.0.0.0:8081/schedule', false);
xhr.send();

if (xhr.status != 200) {
  // обработать ошибку
  alert( xhr.status + ': ' + xhr.statusText );
} else {
  // вывести результат
  alert( xhr.responseText );
}
*/
