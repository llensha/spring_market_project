angular.module('market').controller('cartController', function($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    // $scope.showCart = function() {
    //     $http.get(contextPath + '/api/v1/cart')
    //         .then(function(response) {
    //             $scope.cartList = response.data;
    //         });
    // };
    //
    // $scope.incQuantity = function(orderItemId) {
    //     $http.get(contextPath + '/api/v1/cart/inc/' + orderItemId)
    //         .then(function(response) {
    //             $scope.showCart();
    //         });
    // };
    //
    // $scope.decQuantity = function(orderItemId) {
    //     $http.get(contextPath + '/api/v1/cart/dec/' + orderItemId)
    //         .then(function(response) {
    //             $scope.showCart();
    //         });
    // };
    //
    // $scope.deleteProductFromCart = function(productId) {
    //     $http.get(contextPath + '/api/v1/cart/delete/' + productId)
    //         .then(function(response) {
    //             $scope.showCart();
    //         });
    // };

    $scope.clearCart = function() {
        $localStorage.myMarketCart.clear();
    }

    $scope.createOrder = function() {
        $http.get(contextPath + '/api/v1/orders/create')
            .then(function(response) {
                $scope.showMyOrders();
                $scope.cartList();
            });
    }

    $scope.goToOrderSubmit = function() {
        $location.path('/order_confirmation');
    }

    $scope.cartList = $localStorage.myMarketCart;

});