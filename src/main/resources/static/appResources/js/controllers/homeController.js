define(['jquery', 'angular', 'app', 'homeService'], function(jquery, angular, app) {
	app.controller('homeController', [ '$location', 'HomeService', function($location, HomeService) {
		var self           = this;
		self.loggedin      = false;
		self.from          = null;
	    self.fromSearch    = null;
	    self.querySearch   = querySearch;
	    
		function init() {
            HomeService.startApp()
                .then(function (response) {
                	self.loggedin = response.loggedin;
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

		init();

	} ]);
	return app;
});