define(['angular', 'routes', 'angularRoute', 'angularSanitize', 'angularAnimate','angularAria','angularMessage','angularMaterial'],
    function (angular, routesConfig) {
        'use strict';
        var app = angular.module('Travel', ['ngRoute', 'ngSanitize', 'ngAnimate', 'ngAria', 'ngMessages', 'ngMaterial']);

        app.factory('httpInterceptor', function ($q, $log, $location) {
            return {};
        });

        app.config(['$routeProvider', '$locationProvider', '$controllerProvider', '$compileProvider', '$filterProvider', '$provide', '$httpProvider', function ($routeProvider, $locationProvider, $controllerProvider, $compileProvider, $filterProvider, $provide, $httpProvider) {
            $locationProvider.html5Mode({
                enabled: true,
                requireBase: true
            });
            app.controller = $controllerProvider.register;
            app.directive = $compileProvider.directive;
            app.filter = $filterProvider.register;
            app.factory = $provide.factory;
            app.service = $provide.service
            function resolver(dependencies) {
                var definition = {
                    resolver: ['$q', '$rootScope', function ($q, $rootScope) {
                        var deferred = $q.defer();
                        require(dependencies, function () {
                            $rootScope.$apply(function () {
                                deferred.resolve();
                            });
                        });

                        return deferred.promise;
                    }]
                }

                return definition;
            }

            if (!(routesConfig.routes == undefined)) {
                angular.forEach(routesConfig.routes, function (route, path) {
                        
                            $routeProvider.when(path, {
                                templateUrl: route.templateUrl + '?cd=' + (new Date()).getTime(),
                                resolve: resolver(route.dependencies),
                                controller: route.cntrl,
                                controllerAs: route.cntrlAs,
                                css: route.css
                            });
                        
                    }
                );
            }

            if (!(routesConfig.defaultRoutePaths == undefined)) {
                $routeProvider.otherwise({
                    redirectTo: config.defaultRoutePaths
                });
            }

            $httpProvider.interceptors.push('httpInterceptor');

        }])
        ;


        app.run(['$rootScope', '$location', function ($rootScope, $location) {

            $rootScope.$on('$routeChangeSuccess', function (ev, data) {
                if (data.$$route) {
                    $rootScope.route = data.$$route.originalPath.replace("/", "");
                }
                ;
            });

        }]);

      //Directive to implement css loading
        app.directive('head', ['$rootScope','$compile',
			function($rootScope, $compile){
			return {
				restrict: 'E',
				link: function(scope, elem){
					var html = '<link rel="stylesheet" ng-repeat="(routeCtrl, cssUrl) in routeStyles" ng-href="{{cssUrl}}" >';
					elem.append($compile(html)(scope));

                    scope.routeStyles = {};

					$rootScope.$on('$routeChangeStart', function (e, next, current) {

						if(current && current.$$route && current.$$route.css){
							if(!Array.isArray(current.$$route.css)){
								current.$$route.css = [current.$$route.css];
							}
							angular.forEach(current.$$route.css, function(sheet){
								scope.routeStyles[sheet] = undefined;
							});
						}

						if(next && next.$$route && next.$$route.css){
							if(!Array.isArray(next.$$route.css)){
								next.$$route.css = [next.$$route.css];
							}
							angular.forEach(next.$$route.css, function(sheet){
								scope.routeStyles[sheet] = sheet;
							});
						}

					});

				}
			};
		}
	]);

        return app;
    });