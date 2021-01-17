
app
.controller("dataAppCtrl",function($http){
	var vm = this;
	
	var totalPaginas = 0;
	//var urlDatas = "http://www.localhost:8080/worldhealthbank/datasearch/";
	var urlDatas = "http://localhost:8080/worldhealthbank/datas/pag/";
	var urlDelete = "http://localhost:8080/worldhealthbank/data/del/"
	var pag = 0;
	
	 vm.busqueda = " ";
	//Cargar datos
	vm.load = function(){
		if(! vm.busqueda == "")
			$http.get("http://www.localhost:8080/worldhealthbank/datasearch/"+vm.busqueda+"/pag/"+vm.pag)
			.success(function (data){
				console.log(data);
				vm.datos = data.content;
				vm.totalPaginas = data.totalPages;
				vm.pag = data.number;
			});
		else{
			$http.get("http://www.localhost:8080/worldhealthbank/datasearch/%20/pag/"+vm.pag)
			.success(function (data){
				console.log(data);
				vm.datos = data.content;
				vm.totalPaginas = data.totalPages;
				vm.pag = data.number;
			});
		}
	} 

	
	//Borrar un dato
	vm.del = function(id){
		$http.get(urlDelete+id)
		.then(
		       function(response){
		         // success callback
		    	   vm.load();
		       }, 
		       function(response){
		         // failure callback
		       }
		    );
	}
	
	//Cargamos la página por prirmera vez
	vm.pag = 0;
	vm.load();
	
	
	//Página Primera 
	vm.first = function(){
		vm.pag = 0;
		vm.load();
	}
	//Página Anterior
	vm.pre = function(){
		vm.pag--;
		vm.load();
	}
	
	//Página Siguiente 
	vm.next = function(){
		vm.pag = vm.pag+1;
		vm.load();
	}
	//Página Ultima
	vm.last = function(){
		vm.pag = vm.totalPaginas-1;
		vm.load();
	}

});
