document.addEventListener('DOMContentLoaded', function (e) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8081/update', true);
    xhr.send();
    xhr.close();
});