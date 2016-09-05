angular.module("PhotoBlog")
.controller('PostCtrl', function($scope, $http, $route, $rootScope, $location, $routeParams) {
	$scope.blog = {
    	id: "PhotoBlog"
	};
	
	$scope.page = $routeParams.page;
	
	console.log('get post...');
	
    $http.get("/posts/"+$routeParams.postId)
    .success(function (data) {
    	console.log('success');
    	$scope.blog.post = data;
    })
    .error(function(){
    	$scope.blog.post = null;
    });
    
    $scope.updateComments = function (){
		console.log('get comment list...');
	    $http.get("/posts/"+$routeParams.postId+"/comments?page="+$routeParams.page)
	    .success(function (data) {
	    	console.log('success');
	    	$scope.blog.commentList = data;
	    })
	    .error(function(){
	    	$scope.blog.commentList = null;
	    });
    }
    
    $scope.updateComments();
        
    $scope.addComment = function (comment){
        console.log('add comment...');

        var jsonString = JSON.stringify(comment);
        console.log('client addComment string - '+jsonString);

        $http.post("/posts/"+$routeParams.postId+"/comments", jsonString).success(function(data){
            comment.body = '';
            $scope.addCommentForm.$setPristine();
            $scope.updateComments();
        })
        .error(function(){
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