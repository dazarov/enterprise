var app = angular.module("demo", []);
app.controller('Controller', ['$scope', function($scope){
	$scope.selectedCountry = 'Not Selected yet';
}]);
//
// countries - directive name
// usage -
// <countries /> 								- node
// <span countries="expression"><span> 			- attribute
// <span class="countries: expression;"></span> - class
// <!-- directive: countries expression -->		- comment
//
app.directive("countries", function($http) {
	return {
		restrict: "E", // E - node, A - attribute, C - class, M - comment
		templateUrl: "templates/countries.html",
		replace: 'true',
		//scope: {
		//	selectedCountry: 'Not Selected yet'
		//},
		link: function(scope){
			$http({
				method: 'GET',
				url: '/hibernate/api/countries'
			}).then(function successCallback(response) {
				console.log('success, received - '+response.data.length);
				scope.list = response.data;
		    }, function errorCallback(response) {
		    	console.log('error '+JSON.stringify(response.data));
			});
			
		}
	}
});