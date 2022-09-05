app.controller("userAppCtrl",function($http){
	var vm = this;
			
	var urlUsers = "http://localhost:8080/worldhealthbank/user";
	var urlUser = "http://localhost:8080/worldhealthbank/userSave";
	
	/*
	$http.get(urlUsers)
	.success(function (data){
		//console.log(data);
		vm.user = data;
	});
	*/

	//Enviar el usuario a la BBDD
	vm.enviarUser = function(){
		data = vm.user;
		
		$http.post(urlUser, data)
		   .then(
		       function(response){
		         // success callback
		    	   alert('Se ha enviado un correo a su mail para activar su cuenta.');
		    	   location.href='http://localhost:8080/';
		       }, 
		       function(response){
		         // failure callback
		       }
		    );
	};
});





