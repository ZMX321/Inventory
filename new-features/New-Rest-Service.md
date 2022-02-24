# New Rest Service

Implement a new web service class that does the following:
     
## New endpoints


#### Retrieve Product

1. Given a productId for an existing product, return the product object with an HTTP status code of 200. Make sure that we give clients the productId, brand, and price for the product requested.
2. Given a productId for a nonexistent product, return a response with an HTTP status code of 404.

#### Retrieve Products by Brand
1. Add an endpoint for getting all products for a given brand.
2. If no products exist for the brand, return a 404 to the client.

#### Add a New Product
1. Add the ability for a new product to be added to the product repository. 
2. Make sure that we can handle adding 10 new products that are coming soon for a new product line.


*Note:*
* Refactor any code that needs it
* Write appropriate unit tests that verify these criteria
* Correct any bugs you find in the existing code. 
