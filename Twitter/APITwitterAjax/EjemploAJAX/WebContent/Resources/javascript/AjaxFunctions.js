
function mostrarTweets()
{
	var misTitulos=["ID","Texto","Usuario","URL"];
	var miTabla=document.getElementById("miTabla");
	for(j=0;j<4;++j){
		var titulo=document.createElement("th");
		miTabla.appendChild(titulo);
		titulo.setAttribute("id","titulo"+j);
		titulo.innerHTML=misTitulos[j];
	}
	GetTweets.cargarTweets("diabetes",10,5,
			function (data){
				for(i=0;i<5;++i){
					var fila=document.createElement("tr");
					miTabla.appendChild(fila);
					fila.setAttribute("id","fila"+i);
					for(j=0;j<4;++j){
						var celda=document.createElement("td");
						fila.appendChild(celda);
						celda.setAttribute("id","celda"+i+j);
						celda.innerHTML=data[i][j];
					}
				}
		});
}