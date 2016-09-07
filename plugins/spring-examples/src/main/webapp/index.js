var app = angular.module("demo", []);

app.controller("dropdownDemo", function($scope) {
	$scope.colours = [{
		name: "Red",
		hex: "#F21B1B"
	}, {
		name: "Blue",
		hex: "#1B66F2"
	}, {
		name: "Green",
		hex: "#07BA16"
	}];
	$scope.colour = "";
});

app.run(function($rootScope) {
	angular.element(document).on("click", function(e) {
		$rootScope.$broadcast("documentClicked", angular.element(e.target));
	});
});

app.directive("countries", function($rootScope, $http) {
	return {
		restrict: "E",
		templateUrl: "templates/countries.html",
		replace: 'true',
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