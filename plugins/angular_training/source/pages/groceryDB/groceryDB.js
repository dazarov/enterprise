"use strict";

var dbModel = {
    id: "Grocery App Database"
};

angular.module("MainApp")
.run(function ($http) {
    $http.get("/items").success(function (data) {
        dbModel.itemList = data;

        dbModel.pageSizeRange = [];
        if(!angular.isUndefined(dbModel.itemList)){
            for(let i = 1; i <= dbModel.itemList.length; i++){
                dbModel.pageSizeRange.push(i.toString());
            }
        }
    });
})
.controller("groceryDBCtrl", function ($scope, $filter, $http) {
     $scope.db = dbModel;

    $scope.db.selectedCategory = "all";
    $scope.db.selectedStore = "all";
    
    $scope.db.sortType = 'name';
    $scope.db.sortReverse = false;

    $scope.db.selectedPage = 1;
    
    $scope.db.pageSize = 5;

    $scope.db.update = false;

    $scope.db.selectedItem;

    $scope.selectItem = function(item){
        $scope.db.selectedItem = item;
        $scope.db.update = true;
    }

    $scope.updateItems = function(){         
        $http.get("/items").success(function (data) {
        dbModel.itemList = data;

        dbModel.pageSizeRange = [];
        if(!angular.isUndefined(dbModel.itemList)){
            for(let i = 1; i <= dbModel.itemList.length; i++){
                dbModel.pageSizeRange.push(i.toString());
            }
        }
        $scope.db = dbModel;
        //$scope.$apply();
    });
    }
   
     
    $scope.cancelSelection = function(){
        $scope.db.update = false;
    }

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
        if(newSortType == $scope.db.sortType){
            $scope.db.sortReverse = !$scope.db.sortReverse;
        }else{
            $scope.db.sortType = newSortType;
        }
    }

    $scope.selectPage = function (newPage) {
        //console.log('-- Page selected - '+newPage);
        $scope.db.selectedPage = newPage;
        $scope.logAll();
    }

    $scope.getPageClass = function (page){
        return $scope.db.selectedPage == page ? "btn-primary" : "";
    }

    $scope.getDate = function ($scope) {
      return new Date();  
    }

    $scope.itemFilterFn = function (item){
        return ($scope.db.selectedCategory == "all" || item.category == $scope.db.selectedCategory)
            && ($scope.db.selectedStore == "all" || item.store == $scope.db.selectedStore);
    }

    $scope.$watch('db.selectedCategory', function(newVal, oldVal){
        //console.log('-- Selected new category - '+newVal);
        $scope.db.selectedPage = 1;
        //$scope.logAll();
    });

    $scope.$watch('db.selectedStore', function(newVal, oldVal){
        //console.log('-- Selected new store - '+newVal);
        $scope.db.selectedPage = 1;
        //$scope.logAll();
    });

    $scope.$watch('db.pageSize', function(newVal, oldVal){
        //console.log('-- Selected new page size - '+newVal);
        $scope.db.selectedPage = 1;
        $scope.logAll();
    });

    //$scope.$watch('dbModel', function(newVal, oldVal){
        //$scope.$apply();
    //});
    
    $scope.addItem = function (item){
        console.log('add item...');

        var jsonString = JSON.stringify(item);
        console.log('client addGrocery string - '+jsonString);

        $http.post("/items", jsonString).success(function(data){
            // ok
            $scope.updateItems();
            item.name = 
            item.description = 
            item.category = 
            item.store = 
            item.unit = 
            item.price = '';
            $scope.addForm.$setPristine();
        });
    }

    $scope.updateItem = function (item){
        console.log('edit item...');

        var jsonString = JSON.stringify(item);
        console.log('client delete string - '+jsonString);

        $http.put("/items/"+item.id, jsonString).success(function(data){
            $scope.updateItems();
        });
        $scope.db.update = false;
    }

    $scope.deleteItem = function (item){
        console.log('delete item...');

        $http.delete("/items/"+item.id).success(function(data){
            $scope.updateItems();
        });
    }

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

});
//.service('ItemListData', function($http){
//    this.list = function(){
//        return $http.get("/items");
//    }
//});