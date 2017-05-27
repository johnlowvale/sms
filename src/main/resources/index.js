
//home controller
angular.module("sms",[]).controller("home",["$scope","$http",function($scope,$http){

    //scope variables
    $scope.currentView  = ""; //"1.1"->"1.8", "2.1"->"2.6", "3.1"->"3.3"
    $scope.productsFile = null;
    $scope.products     = [];

    //load view for menu item
    function loadView(viewIndex) {
        $scope.currentView = viewIndex;
    }

    //handle products file select
    function handleProductsFile(event) {
        var file = event.target.files[0];
        $scope.productsFile = file;
    }

    //upload products
    function uploadProducts() {
        var reader = new FileReader();

        //reading done event
        reader.onload = function(e) {
            try {
                var products = JSON.parse(reader.result);

                //check json data
                if (products.length==0) {
                    alert("Error: Empty products file");
                    return;
                }

                if (products[0].pcode==null || products[0].pcode.length==0 ||
                products[0].pro_name==null || products[0].pro_name.length==0) {
                    alert("Error: Bad products file");
                    return;
                }

                //post to server
                $http.post("/products/upload",{
                    products: products
                }).
                then(
                function success(response){
                    var data = response.data;

                    if (data.error) {
                        alert(data.error);
                        return;
                    }

                    alert("Successfully uploaded "+data.productCount+" products!");
                },
                function error(response) {
                    alert("Failed to upload products!");
                });
            }
            catch (error) {
                alert(error);
            }
        };

        reader.readAsText($scope.productsFile);
    }

    //add product
    function addProduct() {
        var productCode  = $("#product-code-add").val().trim();
        var productName  = $("#product-name-add").val().trim();
        var productQty   = $("#product-qty-add").val().trim();
        var productPrice = $("#product-price-add").val().trim();

        //check value
        if (productCode.length==0 || productName.length==0 || productQty.length==0 || productPrice.length==0) {
            alert("Please enter all fields");
            return;
        }

        productQty   = parseInt(productQty);
        productPrice = parseFloat(productPrice);

        if (isNaN(productQty) || isNaN(productPrice)) {
            alert("Quantity or price is not number");
            return;
        }

        //post to server
        $http.post("/product/add",{
            pcode:    productCode,
            pro_name: productName,
            quantity: productQty,
            price:    productPrice
        }).
        then(
        function success(response){
            var data = response.data;

            if (data.error) {
                alert(data.error);
                return;
            }

            alert(data.message+"\nCurrent number of products: "+data.productCount);
        },
        function error(response) {
            alert("Failed to add product!");
        });
    }

    //get all products
    function getProductList() {

        //post to server
        $http.post("/products/list",{}).
        then(
        function success(response){
            var data = response.data;

            if (data.error) {
                alert(data.error);
                return;
            }

            $scope.products = data.products;
            alert("Current number of products: "+data.products.length);
        },
        function error(response) {
            alert("Failed to get product list!");
        });
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
    $scope.loadView       = loadView;
    $scope.uploadProducts = uploadProducts;
    $scope.addProduct     = addProduct;
    $scope.getProductList = getProductList;
    $scope.init           = init;
}]);

//end of file