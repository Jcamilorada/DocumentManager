var AngularApp = {};

var App = angular.module('AngularApp', ['ngRoute']);
App.config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/', {
        templateUrl: 'templates/home.html',
        controller: homeController
    });

    $routeProvider.otherwise({redirectTo: '/'});
}]);
