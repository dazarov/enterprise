angular.module("MainApp")
.controller("formCtrl", function ($scope, $filter) {
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

    $scope.addUser = function (userDetails){
        // To do
    }
}).directive("compareTo", function() {
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
});
 
