
//home controller
angular.module("sms",[]).controller("home",["$scope","$http",function($scope,$http){

    //scope variables
    $scope.currentView = ""; //"1.1"->"1.8", "2.1"->"2.6", "3.1"->"3.3"

    //load view for menu item
    function loadView(viewIndex) {
        $scope.currentView = viewIndex;
    }

    //handle products file select
    function handleProductsFile(event) {
        var file   = event.target.files[0];
        var reader = new FileReader();

        //reading done event
        reader.onload = function(e) {
            $http.post("/products/upload",{
                products: JSON.parse(reader.result)
            }).
            then(
            function success(response){
                alert("Products uploaded successfully!");
            },
            function error(response) {
                alert("Failed to upload products!");
            });
        };

        reader.readAsText(file);
    }

    //view init
    function init() {
        if ($("#products-file").length==0) {
            setTimeout(init,100);
            return;
        }

        document.getElementById("products-file").addEventListener("change",handleProductsFile,false);
    }

    //ALL SCOPE FUNCTIONS=======================================================
    $scope.loadView = loadView;
    $scope.init     = init;
}]);

//end of file