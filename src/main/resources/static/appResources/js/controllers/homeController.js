define(['jquery', 'angular', 'app', 'homeService'], function(jquery, angular, app) {
	app.controller('homeController', [ '$location', 'HomeService', function($location, HomeService) {
		var self           = this;
		self.loggedin      = false;
		self.from          = null;
		self.fromSearch    = null;
		self.to            = null;
		self.toSearch      = null;
		self.minDepDate    = new Date();
		self.dDate         = null;
		self.rDate         = null;
		self.nonstop       = false;
		self.user          = null;
		self.admin         = null;
		self.querySearch   = querySearch;
		self.searchFlight  = searchFlight;
		

		function init() {
			self.minDepDate.setDate(self.minDepDate.getDate() + 3);
			HomeService.startApp()
			.then(function (response) {
				self.loggedin = response.loggedin;
				var role = response.message;
				if(role == 'ROLE_USER')
					self.user = true;
				if(role == 'ROLE_ADMIN')
					self.admin = true;
			});

			$('.burger, .overlay').click(function(){
				$('.burger').toggleClass('clicked');
				$('.overlay').toggleClass('show');
				$('nav').toggleClass('show');
				$('body').toggleClass('overflow');
			});
			
		}

		// ******************************
		// Internal methods
		// ******************************

		/**
		 * Search for airports...
		 */
		function querySearch (query) {
			if(query.length >= 3) {
				return HomeService.autocomplete(query)
				.then(function (response) {
					return response.success.airports;
				});
			} else {
				return [];
			}
		}
		
		function searchFlight(){
			HomeService.saveSearch(self.from.value, self.to.value, self.dDate, self.rDate, self.nonstop);
			$location.path('/search/results');
		}

		init();

	} ]);
	return app;
});