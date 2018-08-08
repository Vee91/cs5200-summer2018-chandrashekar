define([ 'app', 'adminService' ], function(app) {
	app.controller('adminController', [ '$location', 'AdminService', function($location, AdminService) {
		var vm = this;
		
		function init() {
			AdminService.startApp()
                .then(function (response) {
                	vm.message = response.message;
                });
        }
		
		init();

	} ]);
	return app;
});