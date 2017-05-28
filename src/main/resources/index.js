
//home controller
angular.module("sms",[]).controller("home",["$scope","$http",function($scope,$http){

    //scope variables
    $scope.currentView    = ""; //"1.1"->"1.8", "2.1"->"2.6", "3.1"->"3.3"

    //product
    $scope.productsFile   = null; //product file select
    $scope.products       = []; //product list
    $scope.foundProducts  = []; //product search
    $scope.sortedProducts = []; //product sort

    //customer
    $scope.customersFile  = null; //customer file select
    $scope.customers      = []; //customer list
    $scope.foundCustomers = []; //customer search

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

    //handle customers file select
    function handleCustomersFile(event) {
        var file = event.target.files[0];
        $scope.customersFile = file;
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

    //upload customers
    function uploadCustomers() {
        var reader = new FileReader();

        //reading done event
        reader.onload = function(e) {
            try {
                var customers = JSON.parse(reader.result);

                //check json data
                if (customers.length==0) {
                    alert("Error: Empty customers file");
                    return;
                }

                if (customers[0].ccode==null || customers[0].ccode.length==0 ||
                customers[0].cus_name==null || customers[0].cus_name.length==0) {
                    alert("Error: Bad customers file");
                    return;
                }

                //post to server
                $http.post("/customers/upload",{
                    customers: customers
                }).
                then(
                function success(response){
                    var data = response.data;

                    if (data.error) {
                        alert(data.error);
                        return;
                    }

                    alert("Successfully uploaded "+data.customerCount+" customers!");
                },
                function error(response) {
                    alert("Failed to upload customers!");
                });
            }
            catch (error) {
                alert(error);
            }
        };

        reader.readAsText($scope.customersFile);
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

    //add customer
    function addCustomer() {
        var customerCode  = $("#customer-code-add").val().trim();
        var customerName  = $("#customer-name-add").val().trim();
        var customerPhone = $("#customer-phone-add").val().trim();

        //check value
        if (customerCode.length==0 || customerName.length==0 || customerPhone.length==0) {
            alert("Please enter all fields");
            return;
        }

        //post to server
        $http.post("/customer/add",{
            ccode:    customerCode,
            cus_name: customerName,
            phone:    customerPhone
        }).
        then(
        function success(response){
            var data = response.data;

            if (data.error) {
                alert(data.error);
                return;
            }

            alert(data.message+"\nCurrent number of customers: "+data.customerCount);
        },
        function error(response) {
            alert("Failed to add customer!");
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

    //get all customers
    function getCustomerList() {

        //post to server
        $http.post("/customers/list",{}).
        then(
        function success(response){
            var data = response.data;

            if (data.error) {
                alert(data.error);
                return;
            }

            $scope.customers = data.customers;
            alert("Current number of customers: "+data.customers.length);
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
            triggerDownload("products-all.json",jsonStr);
        },
        function error(response) {
            alert("Failed to get product list!");
        });
    }

    //save customers to file
    function saveCustomersToFile() {

        //post to server
        $http.post("/customers/list",{}).
        then(
        function success(response){
            var data = response.data;

            if (data.error) {
                alert(data.error);
                return;
            }

            var jsonStr = JSON.stringify(data.customers);
            triggerDownload("customers-all.json",jsonStr);
        },
        function error(response) {
            alert("Failed to get customer list!");
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

    //search for customers
    function searchForCustomers() {
        var customerCode = $("#customer-code-search").val().trim();

        //check value
        if (customerCode.length==0) {
            alert("Please enter all fields");
            return;
        }

        //post to server
        $http.post("/customers/search",{
            ccode: customerCode
        }).
        then(
        function success(response){
            var data = response.data;

            if (data.error) {
                alert(data.error);
                return;
            }

            $scope.customerProducts = data.customers;
        },
        function error(response) {
            alert("Failed to search for customers!");
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

    //delete a customer
    function deleteCustomer() {
        var customerCode = $("#customer-code-delete").val().trim();

        //check value
        if (customerCode.length==0) {
            alert("Please enter all fields");
            return;
        }

        //post to server
        $http.post("/customer/delete",{
            ccode: customerCode
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
            alert("Failed to delete customer!");
        });
    }

    //sort all products
    function sortProductList() {

        //post to server
        $http.post("/products/sort",{}).
        then(
        function success(response){
            var data = response.data;

            if (data.error) {
                alert(data.error);
                return;
            }

            $scope.sortedProducts = data.products;
            alert("Sorted "+data.products.length+" products!");
        },
        function error(response) {
            alert("Failed to sort product list!");
        });
    }

    //view init
    function init() {
        if ($("#products-file").length==0 || $("#customers-file").length==0) {
            setTimeout(init,100);
            return;
        }

        document.getElementById("products-file").addEventListener("change",handleProductsFile,false);
        document.getElementById("customers-file").addEventListener("change",handleCustomersFile,false);
    }

    //ALL SCOPE FUNCTIONS=======================================================
    $scope.loadView = loadView;

    //products
    $scope.uploadProducts     = uploadProducts;
    $scope.addProduct         = addProduct;
    $scope.getProductList     = getProductList;
    $scope.saveProductsToFile = saveProductsToFile;
    $scope.searchForProducts  = searchForProducts;
    $scope.deleteProduct      = deleteProduct;
    $scope.sortProductList    = sortProductList;

    //customers
    $scope.uploadCustomers     = uploadCustomers;
    $scope.addCustomer         = addCustomer;
    $scope.getCustomerList     = getCustomerList;
    $scope.saveCustomersToFile = saveCustomersToFile;
    $scope.searchForCustomers  = searchForCustomers;
    $scope.deleteCustomer      = deleteCustomer;

    //view init
    $scope.init = init;
}]);

//end of file