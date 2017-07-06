var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 12
});

var marker = new google.maps.Marker({
    position: {
        lat: 59.8545755,
        lng: 30.3211391
    },
    map: map
});