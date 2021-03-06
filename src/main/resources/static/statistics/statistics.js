angular.module('market').controller('statisticsController', function($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showStatistics = function() {
        $http.get(contextPath + '/api/v1/statistics')
            .then(function(response) {
                $scope.statistics = response.data;
            });
    };

    $scope.showStatistics();

});