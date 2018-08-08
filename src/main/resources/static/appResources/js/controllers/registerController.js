define(['app','registerService'], function(app) {
	app.controller('registerController', ['$location','RegisterService', function($location, RegisterService) {
		var vm = this;
		
		vm.register = register;
		vm.roles = [
            {name: 'EMPLOYEE'},
            {name: 'USER'}
        ];
		
		function register(user) {
			RegisterService.register(user)
             .then(function (response) {
             	if(response.code == 200) {
             		$location.url("/profile/"+response.message);
             	}
             });
		}

	}]);
	return app;
});