define([ 'app'], function(app) {
	app.factory('EmployeeService', function($http) {
		
		var factory = {
				getCrewFlights : getCrewFlights
		};

		function getCrewFlights() {
			var url = "api/crew/flights";
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		};

		return factory;
	});
});