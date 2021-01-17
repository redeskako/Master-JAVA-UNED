app.controller('MainController', ['$scope', 'user', function($scope, user) {
  user.success(function(data) {
    $scope.usuarios = data;
  });
}]);
