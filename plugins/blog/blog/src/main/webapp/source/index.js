"use strict";

var blogModel = {
	    id: "Photo Blog"
};

angular.module("photoBlog", ["ngRoute"])
.value('blogName', {value: 'Blog1'})
.run(function ($http) {
	//angular.element(document.getElementById('controllerContainer')).scope().updateBlogs();
	//angular.element($('#AppCtrl')).scope().updateBlogs();
	//$scope.updateBlogs();
    $http.get("/blogs").success(function (data) {
        blogModel.blogList = data;
    });
})
.config(function($routeProvider){
	
	$routeProvider.when("/", {
		templateUrl: "/views/home.html"
	});
	
	$routeProvider.when("/posts", {
		templateUrl: "/views/posts/posts.html"
	});
	
	$routeProvider.when("/photos", {
		templateUrl: "/views/photos/photos.html"
	});

	$routeProvider.when("/videos", {
		templateUrl: "/views/videos/videos.html"
	});
	
	$routeProvider.when("/users", {
		templateUrl: "/views/users/users.html"
	});
	
	$routeProvider.when("/error", {
		templateUrl: "/views/error.html"
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
.controller('AppCtrl', function($scope, $http, $route, $rootScope, $location) {
	
	$scope.blog = blogModel;
	
	$scope.blogName = 'Blog1';
	
	$scope.updateBlogs = function(){
		console.log('get blog list...');
        $http.get("/blogs").success(function (data) {
        	console.log('success');
        	blogModel.blogList = data;

        	$scope.blog = blogModel;
        });
    }
	
	$scope.updatePosts = function(){
		console.log('get post list...');
        $http.get("/"+$scope.blogName+"/posts").success(function (data) {
        	console.log('success');
        	blogModel.postList = data;

        	$scope.blog = blogModel;
        });
    }
	
	$scope.addBlog = function (blog){
        console.log('add blog...');

        var jsonString = JSON.stringify(blog);
        console.log('client addGrocery string - '+jsonString);

        $http.post("/blogs", jsonString).success(function(data){
            // ok
            $scope.updateBlogs();
            blog.name = '';
            $scope.addBlogForm.$setPristine();
        });
    }
	
	$scope.goPosts = function (blogName){
		$scope.blogName = blogName;
		$scope.updatePosts();
		$location.path('/posts');
	}
	
	$scope.$back = function() { 
		window.history.back();
	};
  
  
});