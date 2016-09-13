angular.module("PhotoBlog")
.controller('BlogCtrl', function($scope, $http, $route, $rootScope, $location, $routeParams, $controller) {
	$controller('BaseCtrl', { $scope: $scope });
	
	$scope.blog = {};
	$scope.newPost = {};
	
	$scope.initPost = function (post){
    	post.commentAllowance = $scope.blog.commentAllowance;
		post.time = new Date();
		post.status = 'ENTRY_PUBLIC';
    }
	
	$scope.page = $routeParams.page;
	
	$scope.updateBlog = function (){
		console.log('get blog...');
		
		$http({
			method: 'GET',
			url: '/blogs/'+$routeParams.blogName
		}).then(function successCallback(response) {
			console.log('success');
			$scope.blog = response.data;
			
			$scope.initPost($scope.newPost);
	    }, function errorCallback(response) {
	    	console.log('error '+JSON.stringify(response.data));
	    	$scope.blog.blogList = null;
	    	$rootScope.error = response.data;
	    	$rootScope.error.status = response.status;
	    	$location.path('/error');
		});
		
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
        post.dateTime = new Date(post.time).getUTCMilliseconds();

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
			
			$scope.initPost(post);
			
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