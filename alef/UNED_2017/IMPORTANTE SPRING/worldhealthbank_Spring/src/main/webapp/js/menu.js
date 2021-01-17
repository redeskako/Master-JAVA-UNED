var app = angular.module('menuAp', ['ngRoute'])

.config(function($routeProvider){
	$routeProvider
	.when('/', {
		templateUrl: 'inicio.html'
	})
	.when('/data/', {
		controller: 'dataAppCtrl',
		controllerAs: "vm",
		templateUrl: '/data/data.html'
	})
	.when('/data/dataEdit/:id', {
		controller: 'editDataCtrl',
		controllerAs: "vm",
		templateUrl: '/data/dataEdit.html'
	})
	.otherwise({
		redirecttTo: '/'
	});

});

