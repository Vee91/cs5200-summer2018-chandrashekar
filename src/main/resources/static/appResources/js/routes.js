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
					cntrlAs: 'model',
					css: ['extResources/css/materialize.min.css','appResources/css/style.css']
				},
				'/register': {
					templateUrl: 'pages/booking/register.html',
					dependencies: ['registerController'],
					cntrl: 'registerController',
					cntrlAs: 'model',
					css: ['extResources/css/materialize.min.css','appResources/css/style.css']
				},
				'/profile': {
					templateUrl: 'pages/booking/profile.html',
					dependencies: ['profileController'],
					cntrl: 'profileController',
					cntrlAs: 'model'
				},
				'/admin': {
					templateUrl: 'pages/booking/admin.html',
					dependencies: ['adminController'],
					cntrl: 'adminController',
					cntrlAs: 'model'
				},
				'/profile/:username': {
					templateUrl: 'pages/booking/profile.html',
					dependencies: ['profileController'],
					cntrl: 'profileController',
					cntrlAs: 'model'
				},
				'/search/results' : {
					templateUrl: 'pages/booking/search_result.html',
					dependencies: ['searchController'],
					cntrl: 'searchController',
					cntrlAs: 'model'
				}
			}
	};
	return routesConfig;
});