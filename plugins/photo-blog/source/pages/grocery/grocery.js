"use strict";

var jsonModel = {
    id: "Grocery App"
};

angular.module("MainApp")
.run(function ($http) {
    $http.get("pages/grocery/grocery.json").success(function (data) {
        jsonModel.items = data;

        jsonModel.pageSizeRange = [];
        if(!angular.isUndefined(jsonModel.items)){
            for(let i = 1; i <= jsonModel.items.length; i++){
                jsonModel.pageSizeRange.push(i.toString());
            }
        }
    });
})
.controller("groceryCtrl", function ($scope, $filter) {
    $scope.grocery = jsonModel;

    $scope.selectedCategory = "all";
    $scope.selectedStore = "all";
    
    $scope.sortType = 'name';
    $scope.sortReverse = false;

    $scope.selectedPage = 1;
    $scope.pageSize = 5;

    $scope.logAll = function(){
        //console.log('Selected Category - '+$scope.selectedCategory);
        //console.log('Selected Store - '+$scope.selectedStore);
        //console.log('Sort Type - '+$scope.sortType);
        //console.log('Sort Reverse - '+$scope.sortReverse);
        //console.log('Selected Page - '+$scope.selectedPage);
        //console.log('Page Size - '+$scope.pageSize);
        //console.log('Page Size isNumber - '+angular.isNumber($scope.pageSize));
        //console.log('Page Size isString - '+angular.isString($scope.pageSize));
    }

    $scope.selectSortType = function(newSortType){
        if(newSortType == $scope.sortType){
            $scope.sortReverse = !$scope.sortReverse;
        }else{
            $scope.sortType = newSortType;
        }
    }

    $scope.selectPage = function (newPage) {
        //console.log('-- Page selected - '+newPage);
        $scope.selectedPage = newPage;
        $scope.logAll();
    }

    $scope.getPageClass = function (page){
        return $scope.selectedPage == page ? "btn-primary" : "";
    }

    $scope.getDate = function ($scope) {
      return new Date();  
    }

    $scope.itemFilterFn = function (item){
        return ($scope.selectedCategory == "all" || item.category == $scope.selectedCategory)
            && ($scope.selectedStore == "all" || item.store == $scope.selectedStore);
    }

    $scope.$watch('selectedCategory', function(newVal, oldVal){
        //console.log('-- Selected new category - '+newVal);
        $scope.selectedPage = 1;
        //$scope.logAll();
    });

    $scope.$watch('selectedStore', function(newVal, oldVal){
        //console.log('-- Selected new store - '+newVal);
        $scope.selectedPage = 1;
        //$scope.logAll();
    });

    $scope.$watch('pageSize', function(newVal, oldVal){
        //console.log('-- Selected new page size - '+newVal);
        $scope.selectedPage = 1;
        $scope.logAll();
    });

});