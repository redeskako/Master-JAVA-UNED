app.controller("editDataCtrl",function($routeParams, $http){
	var vm = this;
	
	
	
	
	//Cargar healths y contries
	vm.loadCountries = function(){
		
		$http.get("http://localhost:8080/worldhealthbank/countries")
		.success(function (data){
			vm.countries = data;
		});
	}
	
	vm.loadHealths = function(){
		$http.get("http://localhost:8080/worldhealthbank/healths")
		.success(function (dataH){
			vm.healths = dataH;
		});
	}
	
	vm.loadData= function(){
		$http.get("http://localhost:8080/worldhealthbank/data/get/"+vm.id.id)
		.success(function (dataD){
			vm.data = dataD;
			console.log(vm.data);
		});
	}
	
	
	vm.loadCountries();
	vm.loadHealths();
	console.log($routeParams);
	if($routeParams.id != "nuevo"){
		vm.id = $routeParams;
		vm.loadData();
	}else{
		console.log("nuevo");
	}
	
	var urlData = "http://localhost:8080/worldhealthbank/data/saveData/";
	//Enviar el usuario a la BBDD
	vm.enviarData = function(){
		data = vm.data;
		
		$http.post(urlData, data)
		   .then(
		       function(response){
		    	   location.href='http://localhost:8080/menu.html#/data/';
		       }, 
		       function(response){
		         // failure callback
		       }
		    );
	};
	
	
});