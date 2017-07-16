setInterval(function() {
    var textNode = document.getElementById("timer");
    var currentTime = new Date();
    var options ={
        weekday: 'long',
        hour: 'numeric',
        minute: 'numeric'
    };
    textNode.innerHTML = "Сейчас: " +
        currentTime.toLocaleString('ru',options);
}, 1000);
