angular.module("PhotoBlog")
.controller('ErrorCtrl', function($scope, $rootScope) {
	//$scope.error = $rootScope.error;
	
	$scope.getURL = function (){
		return $rootScope.error.url;
	}
	
	$scope.getMessage = function (){
		return $rootScope.error.message;
	}
	
	$scope.getId = function (){
		return $rootScope.error.id;
	}
});