define([ 'app'], function(app) {
	app.factory('AdminService', function($http) {
		var factory = {
				getAllUsers : getAllUsers,
				addNewUser  : addNewUser,
				updateUser  : updateUser,
				getAllFlights : getAllFlights,
				autocomplete  : autocomplete,
				autocompleteAirline : autocompleteAirline,
				autocompleteAircraft : autocompleteAircraft,
				addNewFlight : addNewFlight,
				updateFlight : updateFlight,
				addNewCard : addNewCard,
				getAllBookings : getAllBookings,
				addNewPassenger : addNewPassenger
		};

		function getAllUsers() {
			var url = "api/admin/users";
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		};

		function getAllFlights() {
			var url = "api/admin/flights";
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		};


		function addNewUser(firstname, lastname, username, phone, email, password, role){
			var user = {"firstname" : firstname,
					"lastname" : lastname,
					"username" : username,
					"phone" : phone,
					"email" : email,
					"password" : password,
					"role" : role};

			var url = "api/admin/add/user";
			return $http.post(url, user)
			.then(function (response) {
				return response.data;
			});
		}

		function updateUser(person) {
			var url = "api/admin/update/user";
			return $http.post(url, person)
			.then(function (response) {
				return response.data;
			});
		}

		function autocomplete(query) {
			var url = "api/admin/autocomplete/"+query;
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		}

		function autocompleteAirline(query) {
			var url = "api/admin/autocomplete/airline/"+query;
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		}

		function autocompleteAircraft(query) {
			var url = "api/admin/autocomplete/aircraft/"+query;
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		}

		function addNewFlight(flightNumber, originTerminal, destinationTerminal, origin, destination, airline, aircraft) {
			var flight = { "flightNumber" : flightNumber,
					"originTerminal" : originTerminal,
					"destinationTerminal" : destinationTerminal,
					"origin" : origin,
					"destination" : destination,
					"airline" : airline,
					"aircraft" : aircraft
			}
			var url = "api/admin/add/flight";
			return $http.post(url, flight)
			.then(function (response) {
				return response.data;
			});
		}
		
		function updateFlight(flight) {
			var url = "api/admin/update/flight";
			return $http.post(url, flight)
			.then(function (response) {
				return response.data;
			});
		}
		
		function addNewCard(card) {
			var url = "api/admin/add/card";
			return $http.post(url, card)
			.then(function (response) {
				return response.data;
			});
		}
		
		function getAllBookings() {
			var url = "api/admin/bookings";
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		}
		
		function addNewPassenger (passenger, bookingId) {
			var url = "api/admin/booking/"+bookingId+"/passenger";
			return $http.post(url, passenger)
			.then(function (response) {
				return response.data;
			});
		}

		return factory;
	});
});