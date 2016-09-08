angular.module("PhotoBlog")
.controller('HomeCtrl', function($scope, $http, $route, $rootScope, $location, $controller) {
	$controller('BaseCtrl', { $scope: $scope });
	
	$scope.blog = {};
	
	$scope.updateBlogs = function (){
		console.log('get blog list...');
		
		$http({
			method: 'GET',
			url: '/blogs'
		}).then(function successCallback(response) {
			console.log('success, received - '+response.data.length);
	    	$scope.blog.blogList = response.data;
	    }, function errorCallback(response) {
	    	console.log('error '+JSON.stringify(response.data));
	    	$scope.blog.blogList = null;
	    	$rootScope.error = response.data;
	    	$rootScope.error.status = response.status;
	    	$location.path('/error');
		});
    }
    
    $scope.updateBlogs();
	
	$scope.addBlog = function (blog){
        console.log('add blog...');

        var jsonString = JSON.stringify(blog);
        
        console.log('client addBlog string - '+jsonString);
        
        $http({
        	method: 'POST',
  		  	url: '/blogs',
  		  	headers: {
  		  		'Content-Type': 'application/json'
  		  	},
  		  	data: jsonString
  		}).then(function successCallback(response) {
  			console.log('success');
  			blog.name = '';
            $scope.addBlogForm.$setPristine();
            $scope.updateBlogs();
  	    }, function errorCallback(response) {
  	    	console.log('error '+JSON.stringify(response.data));
  	    	$scope.blog.blogList = null;
  	    	$rootScope.error = response.data;
  	    	$rootScope.error.status = response.status;
  	    	$location.path('/error');
  		});
    }
	
	$scope.goBlog = function (blogName){
		$location.path('/'+blogName+'/posts/page/1');
	}
});