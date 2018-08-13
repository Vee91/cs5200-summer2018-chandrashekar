define(['jquery', 'angular', 'app', 'homeService', 'ticketService'], function(jquery, angular, app) {
	app.controller('ticketController', [ '$location', '$route', '$mdDialog', 'HomeService', 'TicketService', function($location, $route, $mdDialog, HomeService, TicketService) {
		var vm = this;

		vm.user          = null;
		vm.admin         = null;
		vm.loggedin      = false;
		vm.selectedIndex = null;
		vm.active = null;
		vm.inactive = null;
		vm.cancel = cancel;
		vm.logout = logout;


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
				vm.active = response.success.active;
				vm.inactive = response.success.inactive;
			});
		}

		function cancel(ev, booking) {
			var confirm = $mdDialog.confirm()
			.title('Would you like to cancel the ticket')
			.textContent('Your payment will be refunded in 6-10 business days')
			.ariaLabel('Cancel')
			.targetEvent(ev)
			.ok('Confirm Cancellation')
			.cancel('Do not cancel');

			$mdDialog.show(confirm).then(function() {
				TicketService.cancel(booking).then(function (response) {
					if(response.code == 200) {
						$route.reload();
					} else {
						vm.message = "There was some problem douring cancellation. Please try again later";
					}
				}, function() {
					vm.message = "There was some problem during cancellation. Please try again later";
				});
			}, function() {
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