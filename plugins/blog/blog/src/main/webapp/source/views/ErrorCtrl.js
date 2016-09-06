angular.module("PhotoBlog")
.controller('ErrorCtrl', function($scope, $rootScope) {
	$scope.getURL = function (){
		return $rootScope.error.url;
	}

	$scope.getStatus = function (){
		return $rootScope.error.status;
	}
	
	$scope.getMessage = function (){
		return $rootScope.error.message;
	}
	
	$scope.getId = function (){
		return $rootScope.error.id;
	}
});