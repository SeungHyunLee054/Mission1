function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(savePosition);

    } else {
        alert("Geolocation is not supported by this browser.");
    }
}

function savePosition(position) {
    const latitude = position.coords.latitude.toFixed(7);
    const longitude = position.coords.longitude.toFixed(7);

    document.getElementById("lat").value = latitude;
    document.getElementById("lnt").value = longitude;
}