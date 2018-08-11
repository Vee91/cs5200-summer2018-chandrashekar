define([ 'app'], function(app) {
	app.factory('BookingService', function($http) {
		var factory = {
				book : book,
		};

		
		function book(p, c, q) {
			var url = "api/book/itinerary";
			var body = { "passengers" : p, 
					"card" : c,
					"itinerary" : q};
			console.log(body);
			return $http.post(url, body)
			.then(function (response) {
				return response.data;
			});
		};
		

		return factory;
	});
});