angular.module('market').controller('ordersController', function($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showMyOrders = function() {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function(response) {
            $scope.ordersList = response.data;
        });
    };

    $scope.showMyOrders();
});