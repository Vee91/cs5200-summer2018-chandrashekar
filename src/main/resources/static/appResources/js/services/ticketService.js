define([ 'app'], function(app) {
	app.factory('TicketService', function($http) {
		var factory = {
				tickets : tickets,
		};

		
		function tickets() {
			var url = "api/itinerary";
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		};
		

		return factory;
	});
});