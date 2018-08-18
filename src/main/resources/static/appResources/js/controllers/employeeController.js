define(['jquery', 'app', 'homeService', 'employeeService'], function(jquery, app) {
	app.controller('employeeController', ['$location', '$route', '$mdDialog', '$routeParams', 'HomeService', 'EmployeeService', function($location, $route, $mdDialog, $routeParams, HomeService, EmployeeService) {
		var vm = this;
	
		vm.user          = null;
		vm.admin         = null;
		vm.employee      = null;
		vm.loggedin      = false;
		vm.logout = logout;
		vm.flights = null;
		
		function init() {

			$('.burger, .overlay').click(function(){
				$('.burger').toggleClass('clicked');
				$('.overlay').toggleClass('show');
				$('nav').toggleClass('show');
				$('body').toggleClass('overflow');
			});

			HomeService.startApp()
			.then(function (response) {
				vm.loggedin = response.loggedin;
				var role = response.message;
				if(role == 'ROLE_USER')
					vm.user = true;
				if(role == 'ROLE_ADMIN')
					vm.admin = true;
				if(role == 'ROLE_EMPLOYEE')
					vm.employee = true;
			});
			
			EmployeeService.getCrewFlights().then(function (response) {
				if(response.code == 200)
					vm.flights = response.success.flights; 
			});
		}
		
		function logout() {
			HomeService.logout().then(function (response) {
				if(response == 200) {
					$location.path('/login');
				}
			});
		}

		init();
	}]);
	return app;
});