document.addEventListener('load', function (e) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8081/update', false);
    xhr.send();
});