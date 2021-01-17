

var optionB = [];


/*
Se encarga de crear la instancia de XMLHttpRequest()
 */
function getXMLHttpRequest(){
    var xhr;
    if((window.XMLHttpRequest) || (typeof XMLHttpRequest) != undefined){
        xhr = new XMLHttpRequest();
    } else {
        alert("EL navegador no soporta AJAX")
    }
    return xhr;
}

/*
    Inicia la búsqueda basada en el texto que se teclea en el campo
 */
function search(event) {
    var xhr;
    if((event.keyCode >=48 && event.keyCode <= 57) || (event.keyCode>=65 && event.keyCode<=90)){
        xhr = getXMLHttpRequest();
        if (xhr === null){
            return;
        }
        var countryHint = $("#country-hint").val();

        xhr.open("POST",document.forms["form-statistics"].action,true);
        xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        var data;
        data = "operation=country-list&country=".concat(countryHint);
        xhr.onreadystatechange = processCountryHint(xhr);
        xhr.send(data);
    }

}

/*
Procesa los datos devueltos por el servidor y actualiza el autocomplete de jquery
 */
function processCountryHint(xhr){
    return function() {
        if(xhr.readyState == 4) {
            var jsonObject = $.parseJSON(xhr.responseText);
            optionB = [];
            for (var country of jsonObject.entities) {
                optionB.push({label:country.countryName,value:country.countryCode});
            }
            $("#country-hint").autocomplete({source:optionB});
        }
    }
}

function onLoadJs(){
    $("#country-hint").autocomplete({source:optionB});

}