<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "model.*, controller.*, java.util.Iterator, java.util.List" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.io.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Styles/mainestilo.css" rel="stylesheet" type="text/css" />
<link href="Styles/estilotablas.css" rel="stylesheet" type="text/css" />
<title>grafica</title>
</head>
<body>

<%@ include file="header.jsp" %>

<%
	HttpSession sesion = request.getSession(true); //Obtengo la variable sesión.
%>

<% 
//out.println("ListaData sesion = " + sesion.getAttribute("listaData")); //visualizamos el objeto asociado al atributo listaData
//out.println("clase = " +sesion.getAttribute("listaData").getClass()); //muestro la clase del atributo listaData

List<Data> listilla = (List<Data>)sesion.getAttribute("listaData"); //alamceno el arraylist de la clase data(estadisticas)

System.out.println("Using for loop listilla Data:");
for (int i = 0; i < listilla.size(); i++) {
    System.out.println( "OK!! " + listilla.get(i).toString());
} //Recorro el arraylist para mostrar con el metodo toString cada dato

//out.println("ListaPais sesion = " + sesion.getAttribute("listaPais")); //visualizamos el objeto asociado al atributo listaPais
//out.println("clase = " +sesion.getAttribute("listaPais").getClass()); //muestro la clase del atributo listaPais

List<Country> listilla2 = (List<Country>)sesion.getAttribute("listaPais"); //alamceno el arraylist de la clase country(paises) con la select para obtener solo un unico pais

System.out.println("Using for loop listilla2 Data:");
for (int i = 0; i < listilla2.size(); i++) {
    System.out.println( "OK!! " + listilla2.get(i).toString());
}//Recorro el arraylist para mostrar con el metodo toString el pais(solo se mostrará un pais)

//visualizo el nombre del pais obtenido a partir del code_country
String nombrePais = listilla2.get(0).getCountry_name();
System.out.println("Obtenemos el nombre del pais = " + nombrePais);

HealthIndicator indi = (HealthIndicator)sesion.getAttribute("Indicador");
String nombreIndicador = indi.getIndicator_name();
%>


<h2><c:out value="${Indicador.indicator_name}" /> from
    <c:forEach items="${listaPais}" var="paises">
     	<c:out value="${paises.country_name}" />
  	</c:forEach>
</h2>
    
<script type="text/javascript">
	function mostrar(){
	document.getElementById('oculto').style.display = 'block';}
		
	function ocultar(){
	document.getElementById('oculto').style.display = 'none';}
</script>
    
<input type="button" value="Mostrar tabla" onclick="mostrar()">
<input type="button" value="Ocultar tabla" onclick="ocultar()">
</br></br>

<div id='oculto' style='display:none;'> 
    <table class="login-form" border=1>
        <thead>
            <tr>
                <th>Indicator_code</th>
                <th>Country_code</th>
                <th>Year</th>
                <th>Percentage</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listaData}" var="datas">
  			 	<tr>
            		<td> <c:out value="${datas.indicador_code}" /> </td>
                	<td> <c:out value="${datas.country_code}" /> </td>
                	<td> <c:out value="${datas.year}" /> </td>
                	<td> <c:out value="${datas.percentage}" /> </td>
             	</tr>
  			 </c:forEach>
        </tbody>
    </table>
</br>
</div>

    
<div id="map" style="width:100%;height:500px"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      //google.charts.load('current', {'packages':['corechart']});
      //google.charts.setOnLoadCallback(drawChart);

      google.charts.load('current', {packages: ['corechart', 'line']});
      //google.charts.load('current', {packages: ['corechart']});
      //google.charts.setOnLoadCallback(drawBasic);

      function drawBasic(marker) {

            var data = new google.visualization.DataTable();
            //data.addColumn('number', 'x');
            //data.addColumn('number', 'y');

            data.addColumn('number', 'Year');
  		    data.addColumn('number', <% out.print("'" + listilla.get(0).getIndicador_code() + "'");%>);

  data.addRows(<% out.print(listilla.size()); %>);
  
  <% for (int i = 0; i < listilla.size(); i++) { %>
  
  data.addRow([<% out.print(listilla.get(i).getYear());%>,<% out.print(listilla.get(i).getPercentage()); %>]);
   
<%} %>

  //for (int i = 0; i < listilla.size(); i++) {
  //data.setCell(0, 0, 'John');
  //data.setCell(0, 1, 10000, '$10,000');

  //data.setCell(1, 0, 'Mary');
  //data.setCell(1, 1, 25000, '$25,000');

  //data.setCell(2, 0, 'Steve');
  //data.setCell(2, 1, 8000, '$8,000');

  //data.setCell(3, 0, 'Ellen');
  //data.setCell(3, 1, 20000, '$20,000');
 
  //data.setCell(4, 0, 'Mike');
  //data.setCell(4, 1, 12000, '$12,000');


            //data.addRows([
            //  [0, 0],   [1, 10],  [2, 23],  [3, 17],  [4, 18],  [5, 9],
            //  [6, 11],  [7, 27],  [8, 33],  [9, 40],  [10, 32], [11, 35],
            //  [12, 30], [13, 40], [14, 42], [15, 47], [16, 44], [17, 48],
            //  [18, 52], [19, 54], [20, 42], [21, 55], [22, 56], [23, 57],
            //  [24, 60], [25, 50], [26, 52], [27, 51], [28, 49], [29, 53],
            //  [30, 55], [31, 60], [32, 61], [33, 59], [34, 62], [35, 65],
            //  [36, 62], [37, 58], [38, 55], [39, 61], [40, 64], [41, 65],
            //  [42, 63], [43, 66], [44, 67], [45, 69], [46, 69], [47, 70],
            //  [48, 72], [49, 68], [50, 66], [51, 65], [52, 67], [53, 70],
            //  [54, 71], [55, 72], [56, 73], [57, 75], [58, 70], [59, 68],
            //  [60, 64], [61, 60], [62, 65], [63, 67], [64, 68], [65, 69],
            //  [66, 70], [67, 72], [68, 75], [69, 80]
            //]);

            var options = {
            		width: 400,
            		  height: 200,
              hAxis: {
                title: 'Year'
              },
              vAxis: {
                title: 'Percentage'
              }
            };

            
            var node = document.createElement('div');
            var chart = new google.visualization.LineChart(node);
            var infoWindow  = new google.maps.InfoWindow();
            
            chart.draw(data, options);
            infoWindow.setContent(node);
            infoWindow.open(marker.getMap(),marker);
          }      
      




function myMap() {
	  var mapCanvas = document.getElementById("map");
	  
	//Defino el nombre del pais para pasarselo como adress al geocoder
	  <% String nombrePais2 = listilla2.get(0).getCountry_name(); %>
	  var address = "<%=nombrePais2%>";
	  var geocoder = new google.maps.Geocoder();
	  
	//Defino una ubicaciòn por defecto antes de pasarle el pais
	  var myCenter = new google.maps.LatLng(51.508742,-0.120850); 
	  var mapOptions = {center: myCenter, zoom: 5};
	  
	//Defino el mapa inicializado con una ubicacion por defecto
	  var map = new google.maps.Map(mapCanvas,mapOptions);
	  
	//Defino el maker para que se mueva la señal en el mapa
	  var marker = new google.maps.Marker({
		    position: myCenter,
		    animation: google.maps.Animation.BOUNCE
		  });
		  
	  //Se centra el mapa segun el nombre del pais y no la latitud y longitud
	  geocoder.geocode( { 'address': address}, function(results, status) {
		//si el estado de la llamado es OK
		  if (status == google.maps.GeocoderStatus.OK) {
			//centro el mapa en las coordenadas obtenidas
	          map.setCenter(results[0].geometry.location);
	        //coloco el marcador en dichas coordenadas
	          marker.setPosition(results[0].geometry.location);        
	    } else {
			alert("No podemos encontrar la direcci&oacute;n, error: " + status);
	    }
	  });
	  
	  marker.setMap(map);
	  

	    google.maps.event.addListener(marker, 'click', function() {
	      drawBasic(marker);
	    });
	}

</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBfQVYJWe7h4S25277syzU8j43OVD8Nvso&callback=myMap"></script>


</body>
</html>