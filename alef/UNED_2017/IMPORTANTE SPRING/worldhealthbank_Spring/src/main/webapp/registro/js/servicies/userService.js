app.factory('user', ['$http', function($http) { 
  return $http.get('http://localhost:8080/worldhealthbank/users') 
            .success(function(data) { 
              return data; 
            }) 
            .error(function(err) { 
              return err; 
            }); 
}]);