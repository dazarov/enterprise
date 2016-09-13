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
})
.directive("compareTo", function() {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function(scope, element, attributes, ngModel) {
             
            ngModel.$validators.compareTo = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };
 
            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    }
})
.controller('BaseCtrl', function($scope) {
	$scope.$back = function() { 
		window.history.back();
	};
	
	$scope.getValidationClass = function(valid, dirty){
        var cls;

        if(dirty){
            if(valid){
                cls = 'has-success has-feedback';
            }else{
                cls = 'has-error has-feedback';
            }
        }
        return cls;
    }

     $scope.getStatusIcon = function(valid){
        if(valid){
            return '(success)';
        }else{
            return '(warning)';
        }
    }
    
    $scope.getDate = function(dateTime){
    	return new Date(dateTime).toLocaleDateString();
    }
});