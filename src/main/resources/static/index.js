angular.module('market', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.fillTable = function(pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageIndex
            }
        }).then(function(response) {
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

    $scope.submitCreateNewProduct = function() {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function(response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function(productId) {
        $http.delete(contextPath + '/products/' + productId)
        .then(function(response) {
            $scope.fillTable();
        });
    };

    $scope.fillCart = function() {
        $http.get(contextPath + '/cart')
            .then(function(response) {
            $scope.cartList = response.data;
        });
    };

    $scope.addProductToCart = function(productId) {
        $http.get(contextPath + '/cart/add/' + productId)
            .then(function(response) {
                $scope.fillCart();
            });
    };

    $scope.reduceNumberOfProduct = function(productId) {
        $http.get(contextPath + '/cart/reduce/' + productId)
            .then(function(response) {
                $scope.fillCart();
            });
    };

    $scope.deleteProductFromCart = function(productId) {
        $http.get(contextPath + '/cart/delete/' + productId)
            .then(function(response) {
                $scope.fillCart();
            });
    };

    $scope.deleteAllProductsFromCart = function() {
        $http.get(contextPath + '/cart/delete')
            .then(function(response) {
                $scope.fillCart();
            });
    };

    $scope.fillTable();
    $scope.fillCart();
});