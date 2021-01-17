/**
 * Created by javierbelogarcia on 28/04/2017.
 */
var map;

function getXMLHttpRequest(){
    var xhr;
    if((window.XMLHttpRequest) || (typeof XMLHttpRequest) != undefined){
        xhr = new XMLHttpRequest();
    } else {
        alert("EL navegador no soporta AJAX")
    }
    return xhr;
}

function processCountriesPage(xhr){
    return function() {
        if (xhr.readyState == 4) {
            console.log(xhr.responseText);
            var jsonObject = $.parseJSON(xhr.responseText);
            $("#select-country").empty();

            for (var country of jsonObject.entities) {
                var option = $('<option></option>').attr("value", country.countryCode).text(country.countryName);
                $("#select-country").append(option);
            }
            $("#country-offset").val(jsonObject.offset);
        } else {
            console.log(xhr.readyState);
        }
    };
}

function processIndicatorPage(xhr){
    return function() {
        if (xhr.readyState == 4) {
            console.log(xhr.responseText);
            var jsonObject = $.parseJSON(xhr.responseText);
            $("#select-indicator").empty();

            for (var indicator of jsonObject.entities) {
                var option = $('<option></option>').attr("value", indicator.indicatorCode).text(indicator.indicatorName);
                $("#select-indicator").append(option);
            }
            $("#indicator-offset").val(jsonObject.offset);
        } else {
            console.log(xhr.readyState);
        }
    };
}

function pagination(selector, action) {
    var xhr = getXMLHttpRequest();

    if (xhr === null){
        return;
    }
    var  offset;
    if(selector === "select-country"){
        offset = $("#country-offset").val();
    } else if (selector === "select-indicator"){
        offset = $("#indicator-offset").val();
    }

    xhr.open("POST",document.forms["form-statistics"].action,true);
    xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    var data;
    data = "operation=pagination&selector=".concat(selector).concat("&action=").concat(action);
    if(selector === "select-country"){
        xhr.onreadystatechange = processCountriesPage(xhr);
    } else if(selector === "select-indicator"){
        xhr.onreadystatechange = processIndicatorPage(xhr);
    }
    xhr.send(data);
}

function validateForm() {
    var country = document.forms[0]["country"].value;
    var indicator = document.forms[0]["indicator"].value;
    if(country == "" || indicator == ""){
        alert("Se deben seleccionar los dos valores: país e indicador");
        return false;
    }

}

function getGeoChart(){
    var year = $("#select-year").val();
    var indicator = $("#select-indicator").val();
    if (year == null || indicator == null){
        alert("Se deben seleccionar los dos valores: año e indicador");
        return;
    }
    var xhr = getXMLHttpRequest();

    if (xhr === null){
        return;
    }

    xhr.open("POST",document.forms["form-statistics"].action,true);
    xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    var data;
    data = "operation=geo-statistic&year=".concat(year).concat("&indicator=").concat(indicator);
    xhr.onreadystatechange = processGeoChart(xhr);
    xhr.send(data);
}

function processGeoChart(xhr){
    return function(){
        if(xhr.readyState == 4){
            console.log(xhr.responseText);
            var jsonObject = $.parseJSON(xhr.responseText);

            var dataArray = [['Country','Percentage']];
            for (var data of jsonObject){
                var a = [data.country,data.percentage];
                dataArray.push(a);
            }

            var gData = google.visualization.arrayToDataTable(dataArray);
            var gOptions = {};
            var chart = new google.visualization.GeoChart(document.getElementById('geo-chart'));
            chart.draw(gData, gOptions);


        } else {
            console.log(xhr.readyState);
        }
    };
}

function logOut(){
    $.post("/healthworldbank-web/main/logout",function(data){
        alert("has hecho logout")
    });

}

/*
* Load the googl map Api, we are not using this in this project
function loadAPI()
{
    var script = document.createElement("script");
    script.src = "http://www.google.com/jsapi?key=PUT HERE YOUR API KEY&callback=loadMaps";
    script.type = "text/javascript";
    document.getElementsByTagName("head")[0].appendChild(script);
}*/

/*
* Called whe the Google Map API has been loaded
function loadMaps()
{
    //AJAX API is loaded successfully. Now lets load the maps api
    google.load("maps", "2", {"callback" : mapLoaded});
} */

/*
* Called to move the map to a position
function mapLoaded()
{
    //here you can be sure that maps api has loaded
    //and you can now proceed to render the map on page
    if (GBrowserIsCompatible())
    {
        map = new GMap2(document.getElementById("google-map"));
        map.setCenter(new GLatLng(0.0, 0.0), 0);
    }
} */

/*
* Method to move the map to the country selected by the user
function onClickSelect(el){
    if(el.selectedIndex == -1){
        return;
    }
    var country = el.options[el.selectedIndex].text;
    $("#country-name").val(country);
    mapPosition(country);
}*/

/*
* Method to move the map to a country using Google Geocoder API
function mapPosition(country){
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode( { 'address': country}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location,4);
            map.fitBounds(results[0].geometry.bounds);
        } else {
            alert("Geocode was not successful for the following reason: " + status);
        }
    });
}
*/

function onLoaded(){
    //loadAPI();

}