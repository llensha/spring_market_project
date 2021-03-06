angular.module('market').controller('authorizationController', function($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8189/market';

    $scope.tryToAuth = function() {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = {username: $scope.user.username, token: response.data.token};
                    $scope.currentUserName = $scope.user.username;
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $location.path('/');
                }
            }, function errorCallback(response) {
                window.alert("Ошибка авторизации");
            });
    };

});