define(['jquery', 'angular', 'app', 'homeService', 'searchService'], function(jquery, angular, app) {
	app.controller('searchController', [ '$window', 'HomeService', 'SearchService', function($window, HomeService, SearchService) {
		var vm = this;
		vm.message = null;
		vm.tiles = null;
		vm.results = null;
		vm.info = null;
		vm.loading = true;
		vm.user          = null;
		vm.admin         = null;
		vm.loggedin      = false;
		vm.book = book;
		vm.logout = logout;

		function init() {
			vm.query = HomeService.getSearchQuery();
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

			SearchService.searchFlights(vm.query).then(function (response) {
				if(response.code == 200) {
					vm.results = response.success;
					vm.info = response.infoMap;
					vm.loading = false;
				} else {
					vm.message = response.message;
					vm.loading = false;
				}
			});

		}

		function book(flight) {
			SearchService.savequery(flight).then(function (response) {
				var url = "http://" + $window.location.host + "/travelbooking/book";
				$window.location.href = url;
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