define([], function () {
    var routesConfig = {
        defaultRoutePath: '/',
        routes: {
            '/': {
                templateUrl: 'pages/booking/home.html',
                dependencies: ['homeController'],
                cntrl: 'homeController',
                cntrlAs: 'model'
            },
            '/login': {
                templateUrl: 'pages/booking/login.html',
                dependencies: ['loginController'],
                cntrl: 'loginController',
                cntrlAs: 'model'
            }
        }
    };
    return routesConfig;
});