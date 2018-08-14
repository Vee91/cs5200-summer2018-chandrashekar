define(['jquery', 'angular', 'app', 'homeService', 'searchService', 'bookingService'], function(jquery, angular, app) {
	app.controller('bookController', [ '$location', '$mdDialog', 'HomeService', 'SearchService', 'BookingService', function($location, $mdDialog, HomeService, SearchService, BookingService) {
		var vm = this;
		vm.selectedIndex = 0;
		vm.size = 0;
		vm.passenger1 = null;
		vm.passenger2 = null;
		vm.passenger3 = null;
		vm.cards = null;
		vm.card = null;
		vm.selectedCard  = null;
		vm.disabled = false;;
		vm.user          = null;
		vm.admin         = null;
		vm.loggedin      = false;
		vm.confirmBook = confirmBook;
		vm.bookWithCard = bookWithCard;
		vm.logout = logout;

		function init() {
			vm.query = SearchService.getQuery();
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

			BookingService.findAllCards().then(function (response) {
				if(response.code == 200) {
					vm.cards = response.success.cards;
				}
			}, function (error) {
				vm.message = "Something went wrong while fetching your cards. Please try again later"
			});
		}

		function confirmBook(ev) {
			var confirm = $mdDialog.confirm()
			.title('Book Ticket')
			.textContent('Are you sure you want to book the ticket and proceed with payment?')
			.ariaLabel('book with new card')
			.targetEvent(ev)
			.ok('Book')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				vm.disabled = true;
				var passengers = [];
				if(vm.passenger1 != null) {
					passengers.push(vm.passenger1);
				}
				if(vm.passenger2 != null) {
					passengers.push(vm.passenger2);
				}
				if(vm.passenger3 != null) {
					passengers.push(vm.passenger3);
				}
				BookingService.book(passengers, vm.card, vm.query)
				.then(function (response) {
					if(response.code == 200) {
						$location.path('/tickets');
					} else {
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to book that flight at this moment. Please try another one"
				});
			}, function() {
			});

		}

		function bookWithCard(ev, cardId){
			var confirm = $mdDialog.confirm()
			.title('Book Ticket using the selected card?')
			.textContent('Are you sure you want to book the ticket and proceed with payment?')
			.ariaLabel('bookwithcard')
			.targetEvent(ev)
			.ok('Book')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				vm.disabled = true;
				var passengers = [];
				if(vm.passenger1 != null) {
					passengers.push(vm.passenger1);
				}
				if(vm.passenger2 != null) {
					passengers.push(vm.passenger2);
				}
				if(vm.passenger3 != null) {
					passengers.push(vm.passenger3);
				}
				BookingService.bookWithCard(passengers, cardId, vm.query)
				.then(function (response) {
					if(response.code == 200) {
						$location.path('/tickets');
					} else {
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to book that flight at this moment. Please try another one"
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