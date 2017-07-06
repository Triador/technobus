document.addEventListener('DOMContentLoaded', function (e) {
    var chedulePage = document.getElementById("chedulePage");
    var placePage = document.getElementById("placePage");
    var fullChedulePage = document.getElementById("fullChedulePage");

    var cheduleLink = document.getElementById("schedule");
    var placeLink = document.getElementById("place");
    var fullCheduleLink = document.getElementById("fullChedule");

    cheduleLink.addEventListener('click', function(e) {
        placePage.style.display = 'none';
        fullChedulePage.style.display = 'none';
        chedulePage.style.display = 'block';
    });

    placeLink.addEventListener('click', function(e) {
        placePage.style.display = 'block';
        fullChedulePage.style.display = 'none';
        chedulePage.style.display = 'none';
    });

    fullCheduleLink.addEventListener('click', function(e) {
        placePage.style.display = 'none';
        fullChedulePage.style.display = 'block';
        chedulePage.style.display = 'none';
    });
});

