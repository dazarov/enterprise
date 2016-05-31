"use strict";

angular.module("photoBlog", ["ngRoute"])
.config(function($routeProvider){
	
	$routeProvider.when("/", {
		templateUrl: "/views/home.html"
	});
	
	//$routeProvider.when("/reset_password", {
	//	templateUrl: "/views/reset_password.html"
	//});
	
	//$routeProvider.when("/answer_questions", {
	//	templateUrl: "/views/answer_questions.html"
	//});
	
	//$routeProvider.when("/restore_userid", {
	//	templateUrl: "/views/restore_userid.html"
	//});
	
	//$routeProvider.when("/congrat", {
	//	templateUrl: "/views/congrat.html"
	//});
	
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
	$scope.uid;
	$scope.email;
	$scope.givenname;
	$scope.questions;
	$scope.answers;
	
	$scope.showFooter = true;
	
	// if false then it is recover User ID
	$scope.restorePassword = true;
	
	$scope.error;
	
	$rootScope.$on( "$routeChangeStart", function(event, next, current) {
		$scope.showFooter = next.templateUrl == "/views/login.html";
	});
	
	$scope.getQuestions = function(uid, email){
		$scope.restorePassword = true;
		$scope.uid = uid;
		$scope.email = email;
		if(!angular.isUndefined($scope.uid) && !angular.isUndefined($scope.email)){
			var req = {
				method: 'POST',
				url: '/idm/mhs2/endpoint/restore?_action=securityQuestionsForUserName',
				headers: {
				   'uid': $scope.uid,
				   'email': $scope.email
				},
				data: { test: 'test' }
			}
			$http(req).then(function successCallback(response){
				$scope.error = '';
				$scope.questions = response.data;
				$location.path('/answer_questions/');
			}, function errorCallback(response){
				$scope.error = 'Request failed';
				$location.path('/error/');
			});
		}
	}
	
	$scope.checkAnswers = function(answers){
		$scope.restorePassword = true;
		$scope.answers = answers;
		if(!angular.isUndefined($scope.uid) && !angular.isUndefined($scope.email)){
			var req = {
				method: 'POST',
				url: '/idm/mhs2/endpoint/restore?_action=checkSecurityAnswersForUserName',
				headers: {
				   'uid': $scope.uid,
				   'email': $scope.email,
				   'answer1': $scope.answers[0],
				   'answer2': $scope.answers[1],
				   'answer3': $scope.answers[2],
				},
				data: { test: 'test' }
			}
			$http(req).then(function successCallback(response){
				$scope.error = '';
				$location.path('/congrat/');
			}, function errorCallback(response){
				$scope.error = 'Request failed';
				$location.path('/error/');
			});
		}
	}
	
	$scope.restoreUserID = function(email, givenname){
		$scope.restorePassword = false;
		$scope.email = email;
		$scope.givenname = givenname;
		if(!angular.isUndefined($scope.givenname) && !angular.isUndefined($scope.email)){
			var req = {
				method: 'POST',
				url: '/idm/mhs2/endpoint/restore?_action=restoreUserId',
				headers: {
				   'email': $scope.email,
				   'givenname': $scope.givenname
				},
				data: { test: 'test' }
			}
			$http(req).then(function successCallback(response){
				$scope.error = '';
				$location.path('/congrat/');
			}, function errorCallback(response){
				$scope.error = 'Request failed';
				$location.path('/error/');
			});
		}
	}
	
	$scope.$back = function() { 
		window.history.back();
	};
  
  
});