angular.module('market').controller('orderResultController', function($scope, $http, $location, $routeParams) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showOrder = function() {
        $http({
            url: contextPath + '/api/v1/orders/' + $routeParams.orderId,
            method: 'GET'
        }).then(function(response) {
            $scope.orderInfo = response.data;
        });
    };

    $scope.showOrder();
});