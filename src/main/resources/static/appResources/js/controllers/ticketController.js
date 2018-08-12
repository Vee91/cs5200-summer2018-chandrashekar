define(['jquery', 'angular', 'app', 'homeService', 'ticketService'], function(jquery, angular, app) {
	app.controller('ticketController', [ '$location', 'HomeService', 'TicketService', function($location, HomeService, TicketService) {
		var vm = this;
		
		vm.user          = null;
		vm.admin         = null;
		vm.loggedin      = false;

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
			});

			TicketService.tickets().then(function (response) {
				console.log(response);
			});
		}

		init();

	}]);
	return app;
});