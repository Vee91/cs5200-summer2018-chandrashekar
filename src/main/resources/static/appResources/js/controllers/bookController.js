define(['jquery', 'angular', 'app', 'homeService', 'searchService', 'bookingService'], function(jquery, angular, app) {
	app.controller('bookController', [ '$location', 'HomeService', 'SearchService', 'BookingService', function($location, HomeService, SearchService, BookingService) {
		var vm = this;
		vm.selectedIndex = 0;
		vm.size = 0;
		vm.passenger1 = null;
		vm.passenger2 = null;
		vm.passenger3 = null;
		vm.card = null;
		vm.confirmBook = confirmBook;
		
		function init() {
			vm.query = SearchService.getQuery();
			$('.burger, .overlay').click(function(){
				$('.burger').toggleClass('clicked');
				$('.overlay').toggleClass('show');
				$('nav').toggleClass('show');
				$('body').toggleClass('overflow');
			});
		}
		
		function confirmBook() {
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
					console.log(response);
				} else {
					
				}
			});
		}
		
		init();

	}]);
	return app;
});