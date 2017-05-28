
//home controller
angular.module("sms",[]).controller("home",["$scope","$http",function($scope,$http){

    //scope variables
    $scope.currentView   = ""; //"1.1"->"1.8", "2.1"->"2.6", "3.1"->"3.3"
    $scope.productsFile  = null; //product file select
    $scope.products      = []; //product list
    $scope.foundProducts = []; //product search

    //load view for menu item
    function loadView(viewIndex) {
        $scope.currentView = viewIndex;
    }

    //trigger download
    function triggerDownload(fileName,fileContent,addBom) {
        var textToSave    = fileContent;
        var hiddenElement = document.createElement("a");

        //utf-8 BOM
        if (addBom==true) {
            textToSave = "\xFF\xFE"+textToSave; //utf-16
            //utf-8: "\xEF\xBB\xBF"
        }

        //create file
        var file = new File(
            [textToSave],
            fileName,
            {type:"text/plain;charset=UTF-8"}
        );

        var blobUrl = URL.createObjectURL(file);

        //log
        console.log("Downloading file '"+fileName+"'...");
        console.log("Text size:",fileContent.length,"characters");
        console.log("Blob URL:",blobUrl);

        //link contents
        /*
        //old method using 'data' protocol but content can't exceed 2MB in Chrome
        hiddenElement.href     = "data:attachment/text;charset=utf-8,%EF%BB%BF"+encodeURI(textToSave);
        */
        hiddenElement.href     = blobUrl;
        hiddenElement.target   = "_blank";
        hiddenElement.download = fileName;

        //trigger download
        document.body.appendChild(hiddenElement);
        hiddenElement.click();
        document.body.removeChild(hiddenElement);
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

    //save products to file
    function saveProductsToFile() {

        //post to server
        $http.post("/products/list",{}).
        then(
        function success(response){
            var data = response.data;

            if (data.error) {
                alert(data.error);
                return;
            }

            var jsonStr = JSON.stringify(data.products);
            triggerDownload("products.json",jsonStr);
        },
        function error(response) {
            alert("Failed to get product list!");
        });
    }

    //search for products
    function searchForProducts() {
        var productCode = $("#product-code-search").val().trim();

        //check value
        if (productCode.length==0) {
            alert("Please enter all fields");
            return;
        }

        //post to server
        $http.post("/products/search",{
            pcode: productCode
        }).
        then(
        function success(response){
            var data = response.data;

            if (data.error) {
                alert(data.error);
                return;
            }

            $scope.foundProducts = data.products;
        },
        function error(response) {
            alert("Failed to search for products!");
        });
    }

    //delete a product
    function deleteProduct() {
        var productCode = $("#product-code-delete").val().trim();

        //check value
        if (productCode.length==0) {
            alert("Please enter all fields");
            return;
        }

        //post to server
        $http.post("/product/delete",{
            pcode: productCode
        }).
        then(
        function success(response){
            var data = response.data;

            if (data.error) {
                alert(data.error);
                return;
            }

            alert(data.message);
        },
        function error(response) {
            alert("Failed to delete product!");
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
    $scope.loadView           = loadView;
    $scope.uploadProducts     = uploadProducts;
    $scope.addProduct         = addProduct;
    $scope.getProductList     = getProductList;
    $scope.saveProductsToFile = saveProductsToFile;
    $scope.searchForProducts  = searchForProducts;
    $scope.deleteProduct      = deleteProduct;
    $scope.init               = init;
}]);

//end of file