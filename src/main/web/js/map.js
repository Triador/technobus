var map, map1;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat:59.818054, lng:30.326640},
        zoom: 16
    });
    var marker = new google.maps.Marker({
        position: {lat:59.818054, lng:30.326640},
        map: map
    });
    map1 = new google.maps.Map(document.getElementById('map1'), {
        center: {lng: 59.818054, lat: 30.326640},
        zoom: 12
    });
}
