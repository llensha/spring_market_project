angular.module('market', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    $scope.authorized = false;

    $scope.fillTable = function(pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }
            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.productsPage.totalPages) {
                maxPageIndex = $scope.productsPage.totalPages;
            }
            $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        });
    };

    $scope.generatePagesIndexes= function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    // $scope.submitCreateNewProduct = function() {
    //     $http.post(contextPath + '/api/v1/products', $scope.newProduct)
    //         .then(function (response) {
    //             $scope.newProduct = null;
    //             $scope.fillTable();
    //         });
    // };

    // $scope.deleteProductById = function(productId) {
    //     $http.delete(contextPath + '/api/v1/products/' + productId)
    //     .then(function (response) {
    //         $scope.fillTable();
    //     });
    // };

    $scope.showCart = function() {
        $http.get(contextPath + '/api/v1/cart')
            .then(function(response) {
                $scope.cartList = response.data;
            });
    };

    $scope.showOrders = function() {
        $http.get(contextPath + '/api/v1/orders')
            .then(function(response) {
                $scope.ordersList = response.data;
            });
    };

    $scope.tryToAuth = function() {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    // $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.authorized = true;
                    $scope.fillTable();
                    $scope.showCart();
                    $scope.showOrders();
                }
            }, function errorCallback(response) {
                window.alert("Ошибка авторизации");
            });
    };

    $scope.tryToLogout = function() {
        $http.defaults.headers.common.Authorization = '';
        $scope.user.username = null;
        $scope.authorized = false;
        $scope.fillTable();
        $scope.showCart();
        $scope.showOrders();
    };

    $scope.addToCart = function(productId) {
        $http.get(contextPath + '/api/v1/cart/add/' + productId)
            .then(function(response) {
                $scope.showCart();
            });
    };

    $scope.incQuantity = function(orderItemId) {
        $http.get(contextPath + '/api/v1/cart/inc/' + orderItemId)
            .then(function(response) {
                $scope.showCart();
            });
    };

    $scope.decQuantity = function(orderItemId) {
        $http.get(contextPath + '/api/v1/cart/dec/' + orderItemId)
            .then(function(response) {
                $scope.showCart();
            });
    };

    $scope.deleteProductFromCart = function(productId) {
        $http.get(contextPath + '/api/v1/cart/delete/' + productId)
            .then(function(response) {
                $scope.showCart();
            });
    };

    $scope.deleteAllProductsFromCart = function() {
        $http.get(contextPath + '/api/v1/cart/delete')
            .then(function(response) {
                $scope.showCart();
            });
    };

    $scope.checkout = function() {
        $http.get(contextPath + '/api/v1/orders/checkout')
            .then(function(response) {
                $scope.showOrders();
                $scope.showCart();
            });
    };


    $scope.fillTable();
    // $scope.showCart();
    // $scope.showOrders();

});