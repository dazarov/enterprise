angular.module("PhotoBlog")
.controller('PostCtrl', function($scope, $http, $route, $rootScope, $location, $routeParams, $controller) {
	$controller('BaseCtrl', { $scope: $scope });
	
	$scope.blog = {};
	
	$scope.page = $routeParams.page;
	
	console.log('get post...');
	$http({
		method: 'GET',
		url: '/posts/'+$routeParams.postId
	}).then(function successCallback(response) {
		console.log('success');
		$scope.blog.post = response.data;
    }, function errorCallback(response) {
    	console.log('error '+JSON.stringify(response.data));
    	$scope.blog.blogList = null;
    	$rootScope.error = response.data;
    	$rootScope.error.status = response.status;
    	$location.path('/error');
	});
    
    $scope.updateComments = function (){
		console.log('get comment list...');
		$http({
			method: 'GET',
			url: '/posts/'+$routeParams.postId+'/comments?page='+$routeParams.page
		}).then(function successCallback(response) {
			console.log('success, received - '+response.data.length);
			$scope.blog.commentList = response.data;
	    }, function errorCallback(response) {
	    	console.log('error '+JSON.stringify(response.data));
	    	$scope.blog.blogList = null;
	    	$rootScope.error = response.data;
	    	$rootScope.error.status = response.status;
	    	$location.path('/error');
		});
    }
    
    $scope.updateComments();
        
    $scope.addComment = function (comment){
        console.log('add comment...');

        var jsonString = JSON.stringify(comment);
        
        console.log('client addComment string - '+jsonString);
        
        $http({
        	method: 'POST',
        	url: '/posts/'+$routeParams.postId+'/comments',
        	headers: {
        		'Content-Type': 'application/json'
        	},
        	data: jsonString
		}).then(function successCallback(response) {
			console.log('success');
			comment.body = '';
            $scope.addCommentForm.$setPristine();
            $scope.updateComments();
	    }, function errorCallback(response) {
	    	console.log('error '+JSON.stringify(response.data));
	    	$scope.blog.blogList = null;
	    	$rootScope.error = response.data;
	    	$rootScope.error.status = response.status;
	    	$location.path('/error');
		});
    }
    
	$scope.goPrevPage = function (){
		if($scope.page > 1){
			$scope.page--;
			$location.path("/"+$routeParams.blogName+"/posts/"+$routeParams.postId+"/page/"+$scope.page);
		}
	}
	
	$scope.goNextPage = function (){
		$scope.page++;
		$location.path("/"+$routeParams.blogName+"/posts/"+$routeParams.postId+"/page/"+$scope.page);
	}
});