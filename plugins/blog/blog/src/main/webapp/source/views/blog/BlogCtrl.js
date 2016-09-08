angular.module("PhotoBlog")
.controller('BlogCtrl', function($scope, $http, $route, $rootScope, $location, $routeParams, $controller) {
	$controller('BaseCtrl', { $scope: $scope });
	
	$scope.blog = {};
	
	$scope.blogName = $routeParams.blogName;
	
	$scope.page = $routeParams.page;
	
	$scope.updateBlog = function (){
		console.log('get post list...');
		
		$http({
			method: 'GET',
			url: '/'+$routeParams.blogName+'/posts?page='+$routeParams.page
		}).then(function successCallback(response) {
			console.log('success, received - '+response.data.length);
			$scope.blog.postList = response.data;
	    }, function errorCallback(response) {
	    	console.log('error '+JSON.stringify(response.data));
	    	$scope.blog.blogList = null;
	    	$rootScope.error = response.data;
	    	$rootScope.error.status = response.status;
	    	$location.path('/error');
		});
    }
    
    $scope.updateBlog();
    
    $scope.addPost = function (post){
        console.log('add post...');

        var jsonString = JSON.stringify(post);
        
        console.log('client addPost string - '+jsonString);
        
        $http({
        	method: 'POST',
        	url: '/'+$routeParams.blogName+'/posts',
        	headers: {
        		'Content-Type': 'application/json'
        	},
        	data: jsonString
		}).then(function successCallback(response) {
			console.log('success');
			post.subject = 
	        post.description = 
	        post.body = '';
	        $scope.addPostForm.$setPristine();
	        $scope.updateBlog();
	    }, function errorCallback(response) {
	    	console.log('error '+JSON.stringify(response.data));
	    	$scope.blog.blogList = null;
	    	$rootScope.error = response.data;
	    	$rootScope.error.status = response.status;
	    	$location.path('/error');
		});
    }
    
    $scope.goPost = function (postId){
		$location.path("/"+$routeParams.blogName+"/posts/"+postId+"/page/1");
	}
	
	$scope.goPrevPage = function (){
		if($scope.page > 1){
			$scope.page--;
			$location.path('/'+$routeParams.blogName+'/posts/page/'+$scope.page);
		}
	}
	
	$scope.goNextPage = function (){
		$scope.page++;
		$location.path('/'+$routeParams.blogName+'/posts/page/'+$scope.page);
	}
});