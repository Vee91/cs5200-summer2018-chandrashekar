define([ 'app', 'jqueryui','homeService'], function(app) {
	app.controller('homeController', [ '$location', 'HomeService', function($location, HomeService) {
		var vm = this;

		function init() {
            HomeService.startApp()
                .then(function (response) {
                	vm.message = response;
                });
        }
		
		init();

	} ]);
	return app;
});