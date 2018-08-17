define([ 'app', 'adminService', 'homeService' ], function(app) {
	app.controller('adminController', [ '$location', '$mdDialog', 'AdminService', 'HomeService', function($location, $mdDialog, AdminService, HomeService) {
		var vm = this;

		vm.user          = null;
		vm.admin         = null;
		vm.loggedin      = false;
		vm.success = null;
		vm.manageUser    = null;
		vm.manageFlight  = null;
		vm.managePassenger = null;
		vm.manageCard = null;
		vm.manageSchedule = null;
		vm.selectedChoice = null;
		vm.card = null;
		vm.handleChange = handleChange;
		vm.logout = logout;
		vm.updateUser = updateUser;
		vm.addNewUser = addNewUser;
		vm.cancel = cancel;
		vm.querySearch = querySearch;
		vm.searchAirline = searchAirline;
		vm.searchAircraft = searchAircraft;
		vm.updateFlight = updateFlight;
		vm.addNewFlight = addNewFlight;
		vm.addNewCard = addNewCard;
		vm.addNewPassenger = addNewPassenger;

		vm.users = null;
		vm.flights = null;
		vm.selectedUser = null;
		vm.selectedFlight = null;
		vm.origin = null;
		vm.originSearch = null;
		vm.destination = null;
		vm.destinationSearch = null;
		vm.airline = null;
		vm.airlineSearch = null;
		vm.aircraft = null;
		vm.aircraftSearch = null;
		vm.bookings = null;
		vm.selectedBooking = null;

		function init() {
			$('.burger, .overlay').click(function(){
				$('.burger').toggleClass('clicked');
				$('.overlay').toggleClass('show');
				$('nav').toggleClass('show');
				$('body').toggleClass('overflow');
			});

			HomeService.startApp()
			.then(function (response) {
				vm.options = ('Manage User, Manage Passenger, Manage Card, Manage Flight').split(',').map(function (option) { return { abbrev: option }; });
				vm.loggedin = response.loggedin;
				var role = response.message;
				if(role == 'ROLE_USER')
					vm.user = true;
				if(role == 'ROLE_ADMIN')
					vm.admin = true;
			});
		}

		function logout() {
			HomeService.logout().then(function (response) {
				if(response == 200) {
					$location.path('/login');
				}
			});
		}

		function handleChange() {
			if(vm.selectedChoice == 0){
				vm.manageUser    = true;
				vm.manageFlight  = null;
				vm.managePassenger = null;
				vm.manageCard = null;
				vm.manageSchedule = null;
				AdminService.getAllUsers()
				.then(function (response) {
					if(response.code == 200) {
						vm.users = response.success.users;
					} else {
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to fetch users at this moment. Please try again later"
				});
			}
			else if(vm.selectedChoice == 1) {
				vm.manageUser    = null;
				vm.manageFlight  = null;
				vm.managePassenger = true;
				vm.manageCard = null;
				vm.manageSchedule = null;
				AdminService.getAllBookings()
				.then(function (response) {
					if(response.code == 200) {
						vm.bookings = response.success.flights;
					} else {
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to fetch bookings at this moment. Please try again later"
				});
			}
			else if(vm.selectedChoice == 2) {
				vm.manageUser    = null;
				vm.manageFlight  = null;
				vm.managePassenger = null;
				vm.manageCard = true;
				vm.manageSchedule = null;
				AdminService.getAllUsers()
				.then(function (response) {
					if(response.code == 200) {
						vm.users = response.success.users;
					} else {
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to fetch users at this moment. Please try again later"
				});
			}
			else if(vm.selectedChoice == 3) {
				vm.manageUser    = null;
				vm.manageFlight  = true;
				vm.managePassenger = null;
				vm.manageCard = null;
				vm.manageSchedule = null;

				AdminService.getAllFlights()
				.then(function (response) {
					if(response.code == 200) {
						vm.flights = response.success.flights;
					} else {
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to fetch flights at this moment. Please try again later"
				});
				
			}
			else if(vm.selectedChoice == 4) {
				vm.manageUser    = null;
				vm.manageFlight  = null;
				vm.managePassenger = null;
				vm.manageCard = null;
				vm.manageSchedule = true;
			}
		}

		function addNewUser(ev) {
			var confirm = $mdDialog.confirm()
			.title('Add new user?')
			.textContent('Are you sure you want to add the new user?')
			.ariaLabel('addadminuser')
			.targetEvent(ev)
			.ok('Add')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				vm.manageUser    = null;
				vm.manageFlight  = null;
				vm.managePassenger = null;
				vm.manageCard = null;
				vm.manageSchedule = null;
				vm.selectedChoice = null;
				AdminService.addNewUser(vm.users.firstname, vm.users.lastname, vm.users.username, vm.users.phone, vm.users.email, vm.users.password, vm.users.role)
				.then(function (response) {
					if(response.code == 200) {
						vm.success = "User added successfully";
						vm.message = null;
					} else {
						vm.success = null;
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to add the user at this moment. Please try again later"
				});
			}, function() {
			});
		}

		function updateUser(ev, u) {
			var confirm = $mdDialog.confirm()
			.title('Update user?')
			.textContent('Are you sure you want to update the user?')
			.ariaLabel('addadminuser')
			.targetEvent(ev)
			.ok('Update')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				vm.manageUser    = null;
				vm.manageFlight  = null;
				vm.managePassenger = null;
				vm.manageCard = null;
				vm.manageSchedule = null;
				vm.selectedChoice = null;
				AdminService.updateUser(u)
				.then(function (response) {
					if(response.code == 200) {
						vm.success = "User updated successfully";
						vm.message = null;
					} else {
						vm.success = null;
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to update the user at this moment. Please try again later"
				});
			}, function() {
			});
		}

		function querySearch(query){
			if(query.length >= 3) {
				return AdminService.autocomplete(query)
				.then(function (response) {
					return response.success.airports;
				});
			} else {
				return [];
			}
		}

		function searchAirline(query){
			if(query.length >= 3) {
				return AdminService.autocompleteAirline(query)
				.then(function (response) {
					return response.success.airlines;
				});
			} else {
				return [];
			}
		}

		function searchAircraft(query) {
			if(query.length >= 3) {
				return AdminService.autocompleteAircraft(query)
				.then(function (response) {
					return response.success.aircrafts;
				});
			} else {
				return [];
			}
		}
		
		function updateFlight(ev, f) {
			var confirm = $mdDialog.confirm()
			.title('Update flight?')
			.textContent('Are you sure you want to update the flight?')
			.ariaLabel('addadminuser')
			.targetEvent(ev)
			.ok('Update')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				vm.manageUser    = null;
				vm.manageFlight  = null;
				vm.managePassenger = null;
				vm.manageCard = null;
				vm.manageSchedule = null;
				vm.selectedChoice = null;
				AdminService.updateFlight(f)
				.then(function (response) {
					if(response.code == 200) {
						vm.success = "Flight updated successfully";
						vm.message = null;
					} else {
						vm.success = null;
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to update the flight at this moment. Please try again later"
				});
			}, function() {
			});
		}
		
		function addNewFlight(ev) {
			var confirm = $mdDialog.confirm()
			.title('Add new flight?')
			.textContent('Are you sure you want to add the new flight?')
			.ariaLabel('addadminflight')
			.targetEvent(ev)
			.ok('Add')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				vm.manageUser    = null;
				vm.manageFlight  = null;
				vm.managePassenger = null;
				vm.manageCard = null;
				vm.manageSchedule = null;
				vm.selectedChoice = null;
				AdminService.addNewFlight(vm.flights.flightNumber, vm.flights.originTerminal, vm.flights.destinationTerminal, vm.origin, vm.destination, vm.airline, vm.aircraft)
				.then(function (response) {
					if(response.code == 200) {
						vm.success = "Flight added successfully";
						vm.message = null;
					} else {
						vm.success = null;
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to add the flight at this moment. Please try again later"
				});
			}, function() {
			});
		}
		
		function addNewCard(ev) {
			var confirm = $mdDialog.confirm()
			.title('Add new card?')
			.textContent('Are you sure you want to add the new card to selected user?')
			.ariaLabel('addadmincard')
			.targetEvent(ev)
			.ok('Add')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				vm.manageUser    = null;
				vm.manageFlight  = null;
				vm.managePassenger = null;
				vm.manageCard = null;
				vm.manageSchedule = null;
				vm.selectedChoice = null;
				AdminService.addNewCard(vm.card)
				.then(function (response) {
					if(response.code == 200) {
						vm.success = "Card added successfully";
						vm.message = null;
					} else {
						vm.success = null;
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to add the card at this moment. Please try again later"
				});
			}, function() {
			});
		}
		
		function addNewPassenger(ev, pass) {
			var confirm = $mdDialog.confirm()
			.title('Add new passenger?')
			.textContent('Are you sure you want to add the passenger to the booking?')
			.ariaLabel('add passenger')
			.targetEvent(ev)
			.ok('Add')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				vm.manageUser    = null;
				vm.manageFlight  = null;
				vm.managePassenger = null;
				vm.manageCard = null;
				vm.manageSchedule = null;
				vm.selectedChoice = null;
				AdminService.addNewPassenger(pass, vm.selectedBooking)
				.then(function (response) {
					if(response.code == 200) {
						vm.success = "Passenger added successfully";
						vm.message = null;
					} else {
						vm.success = null;
						vm.message = response.message;
					}
				}, function(error) {
					vm.message = "We are not able to add the passenger at this moment. Please try again later"
				});
			}, function() {
			});
		}

		function cancel() {
			vm.manageUser    = null;
			vm.manageFlight  = null;
			vm.managePassenger = null;
			vm.manageCard = null;
			vm.manageSchedule = null;
			vm.selectedChoice = null;
		}

		init();

	} ]);
	return app;
});