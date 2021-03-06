angular.module('market').controller('registrationController', function($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    $scope.registry = function() {
        $http.post(contextPath + '/users', $scope.newUser)
            .then(function successCallback(response) {
                $scope.newUser = null;
                window.alert("Вы успешно зарегистрировались!\nТеперь можете войти");
                $location.path('/');
            }, function errorCallback(response) {
                window.alert("Ошибка регистрации! Попробуйте еще раз");
            });
    };

});