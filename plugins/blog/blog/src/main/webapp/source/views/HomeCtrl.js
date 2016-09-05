angular.module("PhotoBlog")
.controller('HomeCtrl', function($scope, $http, $route, $rootScope, $location) {
	$scope.blog = {
    	id: "PhotoBlog"
	};
	
	$scope.updateBlogs = function (){
		console.log('get blog list...');
	
	    $http.get("/blogs")
	    .success(function (data) {
	    	console.log('success');
	    	$scope.blog.blogList = data;
	    })
	    .error(function(){
	    	$scope.blog.blogList = null;
	    });
    }
    
    $scope.updateBlogs();
	
	$scope.addBlog = function (blog){
        console.log('add blog...');

        var jsonString = JSON.stringify(blog);
        console.log('client addBlog string - '+jsonString);

        $http.post("/blogs", jsonString).success(function(data){
            // ok
            blog.name = '';
            $scope.addBlogForm.$setPristine();
            $scope.updateBlogs();
        })
        .error(function(){
        	$location.path('/error');
        });
    }
	
	$scope.goBlog = function (blogName){
		$location.path('/'+blogName+'/posts/page/1');
	}
	
	$scope.$back = function() { 
		window.history.back();
	};
});