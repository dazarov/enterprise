"use strict";

angular.module("photoBlog", ["ngRoute"])
.config(function($routeProvider){
	
	$routeProvider.when("/", {
		templateUrl: "/views/home.html",
		controller: 'HomeCtrl'
	})
	
	.when("/:blogName/posts", {
		templateUrl: "/views/posts/blog.html",
		controller: 'BlogCtrl'
	})

	.when("/:blogName/posts/:postId", {
		templateUrl: "/views/posts/post.html",
		controller: 'PostCtrl'
	})
	
	.when("/photos", {
		templateUrl: "/views/photos/photos.html"
	})

	.when("/videos", {
		templateUrl: "/views/videos/videos.html"
	})
	
	.when("/users", {
		templateUrl: "/views/users/users.html"
	})
	
	.when("/error", {
		templateUrl: "/views/error.html"
	})
	
	.otherwise({
		redirectTo: "/error"
	});
})
.directive( 'goClick', function ( $location ) {
  return function ( scope, element, attrs ) {
    var path;

    attrs.$observe( 'goClick', function (val) {
      path = val;
    });

    element.bind( 'click', function () {
      scope.$apply( function () {
        $location.path( path );
      });
    });
  };
})
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
		$location.path('/'+blogName+'/posts');
	}
	
	$scope.$back = function() { 
		window.history.back();
	};
})
.controller('BlogCtrl', function($scope, $http, $route, $rootScope, $location, $routeParams) {
	$scope.blog = {
    	id: "PhotoBlog"
	};
	
	$scope.updateBlog = function (){
		console.log('get post list...');
		
	    $http.get("/"+$routeParams.blogName+"/posts")
	    .success(function (data) {
	    	console.log('success');
	    	$scope.blog.postList = data;
	    })
	    .error(function(){
	    	$scope.blog.postList = null;
	    });
    }
    
    $scope.updateBlog();
    
    $scope.addPost = function (post){
        console.log('add post...');

        var jsonString = JSON.stringify(post);
        console.log('client addPost string - '+jsonString);

        $http.post("/"+$routeParams.blogName+"/posts", jsonString).success(function(data){
            // ok
            post.subject = 
            post.description = 
            post.body = '';
            $scope.addPostForm.$setPristine();
            $scope.updateBlog();
        })
        .error(function(){
    		$scope.blog.postList = null;
    	});
    }
    
    $scope.goPost = function (postId){
		$location.path("/"+$routeParams.blogName+"/posts/"+postId);
	}
})
.controller('PostCtrl', function($scope, $http, $route, $rootScope, $location, $routeParams) {
	$scope.blog = {
    	id: "PhotoBlog"
	};
	
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
	    $http.get("/posts/"+$routeParams.postId+"/comments")
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
});