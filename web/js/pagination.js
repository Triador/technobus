document.addEventListener('DOMContentLoaded', function (e) {
    var agent = window.navigator.userAgent;
    agent = agent.toUpperCase();

    var chedulePage = document.getElementById("chedulePage");
    var placePageMetro = document.getElementById("placePageMetro");
    var placePageTechnopolis = document.getElementById("placePageTechnopolis");
    var fullChedulePage = document.getElementById("fullChedulePage");

    var cheduleLink = document.getElementById("schedule");
    var placeLink1 = document.getElementById("placeMetro");
    var placeLink2 = document.getElementById("placeTechnopolis");
    var fullCheduleLink = document.getElementById("fullChedule");

    if (agent.indexOf('LINUX') !== -1 ||
        agent.indexOf('WINDOWS') !== -1 ||
        agent.indexOf('MACINTOSH') !== -1) {
        // desktop!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        placePageMetro.style.display = 'none';
        placePageTechnopolis.style.display = 'none';
        fullChedulePage.style.display = 'block';
        chedulePage.style.display = 'none';
    } else {
        // mobile
        placePageMetro.style.display = 'none';
        placePageTechnopolis.style.display = 'none';
        fullChedulePage.style.display = 'none';
        chedulePage.style.display = 'block';
    }

    cheduleLink.addEventListener('click', function(e) {
        placePageMetro.style.display = 'none';
        placePageTechnopolis.style.display = 'none';
        fullChedulePage.style.display = 'none';
        chedulePage.style.display = 'block';
        if (nextBusTo == undefined){
            nextBusTo = "noBusTo";
        }
        var element = document.getElementById("fromMetro");
        element.style.display = 'block';
        element = document.getElementById("fromOffice");
        element.style.display = 'none';
        var b = document.location.href.split("#");
        document.location.href = b[0] + '#' + nextBusTo;
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

    var scheduleNav = document.getElementById('schedule');
    var placeMetroNav = document.getElementById('placeMetro');
    var placeTechnopolisNav = document.getElementById('placeTechnopolis');
    var fullScheduleNav = document.getElementById('fullChedule');

    scheduleNav.addEventListener('click',function () {
        $('.collapse').collapse('hide');
    });
    placeMetroNav.addEventListener('click',function () {
        $('.collapse').collapse('hide');
    });
    placeTechnopolisNav.addEventListener('click',function () {
        $('.collapse').collapse('hide');
    });
    fullScheduleNav.addEventListener('click',function () {
        $('.collapse').collapse('hide');
    });

});




