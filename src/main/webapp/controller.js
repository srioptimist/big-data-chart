var analyticsController = angular.module('analyticsController', []);

analyticsController.controller("analyticsCtrl",['$scope','$http',function($scope,$http){
	
	$scope.reasons = [];
	
	$scope.loadData = function(){
		
		var indicatorKey = window.location.search.substring(5);
		
		$http.get("rest/attrition/reasons/"+indicatorKey+"")
	    .success(
	    			function(response) {
	    				$scope.reasons = response;
	    			}
	    );
	};
	 
	 $scope.loadData();
	 
}]);