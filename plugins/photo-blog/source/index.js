"use strict";
angular.module('MainApp', [])
.controller('TabsCtrl', function ($scope) {
    $scope.tabs = [{
            title: 'Introduction',
            url: 'pages/introduction/introduction.html'
        }, {
            title: 'Grocery List',
            url: 'pages/grocery/grocery.html'
        }, {
            title: 'Grocery List Database',
            url: 'pages/groceryDB/groceryDB.html'
        }, {
            title: 'Forms & Validation',
            url: 'pages/forms/forms.html'
        }, {
            title: 'Photos',
            url: 'pages/photos/photos.html'
        }, {
            title: 'Calendar',
            url: 'pages/calendar/calendar.html'
        }, {
            title: 'Something',
            url: 'pages/something/something.html'
    }];

    $scope.currentTab = $scope.tabs[0].url;

    $scope.onClickTab = function (tab) {
        $scope.currentTab = tab.url;
    }
    
    $scope.isActiveTab = function(tabUrl) {
        return tabUrl == $scope.currentTab;
    }

    $scope.getTabClass = function (tabUrl){
        return tabUrl == $scope.currentTab ? "btn-primary" : "";
    }
})
.filter("range", function($filter){
    return function (data, page, size){
        if(angular.isArray(data) && angular.isNumber(page) && angular.isNumber(size)){
            var start_index = (page - 1) * size;
            if(data.length < start_index){
                return [];
            }else{
                return $filter("limitTo")(data.splice(start_index), size);
            }
        }else{
            return data;
        }
    }
})
.filter("pageCount", function(){
    return function (data, size) {
        if(angular.isArray(data)) {
            var result = [];

            for (let i = 0; i < Math.ceil(data.length / size); i++){
                result.push(i);
            }
            return result;
        }else{
            return data;
        }
    }
})
.filter('unique', function () {

  return function (items, filterOn) {

    if (filterOn === false) {
      return items;
    }

    if ((filterOn || angular.isUndefined(filterOn)) && angular.isArray(items)) {
      var hashCheck = {}, newItems = [];

      var extractValueToCompare = function (item) {
        if (angular.isObject(item) && angular.isString(filterOn)) {
          return item[filterOn];
        } else {
          return item;
        }
      };

      angular.forEach(items, function (item) {
        var valueToCheck, isDuplicate = false;

        for (let i = 0; i < newItems.length; i++) {
          if (angular.equals(extractValueToCompare(newItems[i]), extractValueToCompare(item))) {
            isDuplicate = true;
            break;
          }
        }
        if (!isDuplicate) {
          newItems.push(item);
        }

      });
      items = newItems;
    }
    return items;
  };
})
.directive('convertToNumber', function() {
  return {
    require: 'ngModel',
    link: function(scope, element, attrs, ngModel) {
      ngModel.$parsers.push(function(val) {
        return parseInt(val, 10);
      });
      ngModel.$formatters.push(function(val) {
        return '' + val;
      });
    }
  };
})
.directive('confirm', [function () {
     return {
         priority: 100,
         restrict: 'A',
         link: {
             pre: function (scope, element, attrs) {
                 var msg = attrs.confirm || "Are you sure?";

                 element.bind('click', function (event) {
                     if (!confirm(msg)) {
                         event.stopImmediatePropagation();
                         event.preventDefault;
                     }
                 });
             }
         }
     };
}]);
