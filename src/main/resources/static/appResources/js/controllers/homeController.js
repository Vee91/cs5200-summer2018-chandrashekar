define(['jquery', 'angular', 'app','homeService'], function(jquery, angular, app, jqueryui) {
	app.controller('homeController', [ '$location', 'HomeService', function($location, HomeService) {
		var vm = this;

		function init() {
            HomeService.startApp()
                .then(function (response) {
                	vm.message = response;
                });
            
            $('.burger, .overlay').click(function(){
      		  $('.burger').toggleClass('clicked');
      		  $('.overlay').toggleClass('show');
      		  $('nav').toggleClass('show');
      		  $('body').toggleClass('overflow');
      		});
        }
		
		init();

	} ]);
	return app;
});