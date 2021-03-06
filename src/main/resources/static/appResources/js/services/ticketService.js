define([ 'app'], function(app) {
	app.factory('TicketService', function($http) {
		var factory = {
				tickets : tickets,
				cancel : cancel
		};

		
		function tickets() {
			var url = "api/itinerary";
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		};
		
		function cancel(id) {
			var url = "api/ticket/cancel";
			return $http.post(url, id)
			.then(function (response) {
				return response.data;
			});
		};
		

		return factory;
	});
});