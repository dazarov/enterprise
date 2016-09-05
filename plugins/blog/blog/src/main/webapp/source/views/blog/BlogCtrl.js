angular.module("PhotoBlog")
.controller('BlogCtrl', function($scope, $http, $route, $rootScope, $location, $routeParams) {
	$scope.blog = {
    	id: "PhotoBlog"
	};
	
	$scope.blogName = $routeParams.blogName;
	
	$scope.page = $routeParams.page;
	
	$scope.updateBlog = function (){
		console.log('get post list...');
		
	    $http.get("/"+$routeParams.blogName+"/posts?page="+$routeParams.page)
	    .success(function (data) {
	    	console.log('success');
	    	$scope.blog.postList = data;
	    })
	    .error(function(error){
	    	console.log('error '+JSON.stringify(error));
	    	$scope.blog.postList = null;
	    	$rootScope.error = error;
        	$location.path('/error');
	    });
    }
    
    $scope.updateBlog();
    
    $scope.addPost = function (post){
        console.log('add post...');

        var jsonString = JSON.stringify(post);
        
        console.log('client addPost string - '+jsonString);

        $http.post("/"+$routeParams.blogName+"/posts", jsonString).success(function(data){
            console.log('success');
            post.subject = 
            post.description = 
            post.body = '';
            $scope.addPostForm.$setPristine();
            $scope.updateBlog();
        })
        .error(function(error){
        	console.log('error '+JSON.stringify(error));
    		$scope.blog.postList = null;
    		$rootScope.error = error;
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