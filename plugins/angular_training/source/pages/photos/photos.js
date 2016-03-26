angular.module("MainApp")
.controller("photoCtrl", function ($scope, $filter) {
    $scope.photos = [
        'img/baby_swan.png',
        'img/san_francisco.png',
        'img/bay_bridge.png',
        'img/coyote.png',
        'img/palace.png',
        'img/rabbit.png'
    ];

    $scope.shapes = [
        {label: 'Rounded', value: 'img-rounded'},
        {label: 'Circle', value: 'img-circle'},
        {label: 'Thumbnail', value: 'img-thumbnail'}
    ];

    $scope.sizes = [
        {label:   '50x50 px', value:  '50'},
        {label: '100x100 px', value: '100'},
        {label: '150x150 px', value: '150'},
        {label: '200x200 px', value: '200'},
        {label: '300x300 px', value: '300'}
    ];

    $scope.selectedImage = $scope.photos[0];
    $scope.selectedShape = $scope.shapes[1];
    $scope.selectedSize =  $scope.sizes[1];

    $scope.selectImage = function(newImage){
        $scope.selectedImage = newImage;
    }

    $scope.selectShape = function(newShape){
        $scope.selectedShape = newShape;
    }

});