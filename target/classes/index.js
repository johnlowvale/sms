
//home controller
angular.module("sms",[]).controller("home",["$scope","$http",function($scope,$http){

    //view init
    function init() {
        $scope.test = 999;
    }

    //ALL SCOPE FUNCTIONS=======================================================
    $scope.init = init;
}]);

//end of file