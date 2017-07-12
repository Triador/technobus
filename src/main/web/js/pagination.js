document.addEventListener('DOMContentLoaded', function (e) {
    var chedulePage = document.getElementById("chedulePage");
    var placePageMetro = document.getElementById("placePageMetro");
    var placePageTechnopolis = document.getElementById("placePageTechnopolis");
    var fullChedulePage = document.getElementById("fullChedulePage");

    var cheduleLink = document.getElementById("schedule");
    var placeLink1 = document.getElementById("placeMetro");
    var placeLink2 = document.getElementById("placeTechnopolis");
    var fullCheduleLink = document.getElementById("fullChedule");

    cheduleLink.addEventListener('click', function(e) {
        placePageMetro.style.display = 'none';
        placePageTechnopolis.style.display = 'none';
        fullChedulePage.style.display = 'none';
        chedulePage.style.display = 'block';
    });

    placeLink1.addEventListener('click', function(e) {
        placePageMetro.style.display = 'block';
        placePageTechnopolis.style.display = 'none';
        fullChedulePage.style.display = 'none';
        chedulePage.style.display = 'none';
    });

    placeLink2.addEventListener('click', function(e) {
        placePageMetro.style.display = 'none';
        placePageTechnopolis.style.display = 'block';
        fullChedulePage.style.display = 'none';
        chedulePage.style.display = 'none';
    });

    fullCheduleLink.addEventListener('click', function(e) {
        placePageMetro.style.display = 'none';
        placePageTechnopolis.style.display = 'none';
        fullChedulePage.style.display = 'block';
        chedulePage.style.display = 'none';
    });
});

