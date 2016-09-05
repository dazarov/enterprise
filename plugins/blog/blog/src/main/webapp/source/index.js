"use strict";

angular.module("PhotoBlog", ["ngRoute"])
.config(function($routeProvider){
	
	$routeProvider.when("/", {
		templateUrl: "/views/home.html",
		controller: 'HomeCtrl'
	})
	
	.when("/:blogName/posts/page/:page", {
		templateUrl: "/views/blog/blog.html",
		controller: 'BlogCtrl'
	})

	.when("/:blogName/posts/:postId/page/:page", {
		templateUrl: "/views/post/post.html",
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
		templateUrl: "/views/error.html",
		controller: 'ErrorCtrl'
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
});