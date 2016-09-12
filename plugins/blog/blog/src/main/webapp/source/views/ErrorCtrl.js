angular.module("PhotoBlog")
.controller('ErrorCtrl', function($scope, $rootScope) {
	$scope.getURL = function (){
		if (typeof $rootScope.error === "undefined") {
			return 'Undefined';
		}
		return $rootScope.error.url || 'Undefined' ;
	}

	$scope.getStatus = function (){
		if (typeof $rootScope.error === "undefined") {
			return 'Undefined';
		}
		return $rootScope.error.status || 'Undefined';
	}
	
	$scope.getMessage = function (){
		if (typeof $rootScope.error === "undefined") {
			return 'Undefined';
		}
		return $rootScope.error.message || 'Undefined';
	}
	
	$scope.getId = function (){
		if (typeof $rootScope.error === "undefined") {
			return 'Undefined';
		}
		return $rootScope.error.id || 'Undefined';
	}
});