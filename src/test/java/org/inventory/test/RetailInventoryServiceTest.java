package org.inventory.test;

import org.inventory.dto.ProductResponse;
import org.inventory.dto.RequestProduct;
import org.inventory.model.Products;
import org.inventory.model.RetailInventory;
import org.inventory.repositories.ProductsRepo;
import org.inventory.repositories.RetailInventoryRepo;
import org.inventory.services.ProductService;
import org.inventory.services.RetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.BooleanSupplier;

import static com.mongodb.assertions.Assertions.assertNull;
import static org.bson.assertions.Assertions.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
public class RetailInventoryServiceTest {
        @Autowired
        private RetailService retailService;
        @Autowired
        private ProductsRepo productsRepo;
        @Autowired
        private RetailInventoryRepo retailInventoryRepo;


        @BeforeEach
        public void tearDown() {

                retailInventoryRepo.deleteAll();
                productsRepo.deleteAll();
        }

        RequestProduct requestProduct = new RequestProduct();
        ProductResponse productResponse = new ProductResponse();

        @Test
        public void createProductsStore() {
                RetailInventory retailInventory = retailService.create();
                assertNotNull(retailInventory);
                System.out.println(retailInventory);

        }


        @Test
        public void findById() {
                RetailInventory retailInventory = retailService.create();
                assertNotNull(retailInventory);
                System.out.println(retailInventory);
                assertNotNull(retailService.findById(retailInventory.getId()));
                System.out.println(retailService.findById(retailInventory.getId()));
                System.out.println(retailInventory.getId());
                assertThat(retailService.count(), is(1L));


        }

        @Test
        public void canAddProduct() {
                RetailInventory retailInventory = retailService.create();
                requestProduct.setProductName("Rice Bagsss");
                requestProduct.setQuantityOfProduct(10);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                RetailInventory updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                assertThat(retailService.count(), is(1L));
                assertEquals(1, updatedRetailInventory.getProducts().size());
                System.out.println(updatedRetailInventory.getProducts().size());
                Products addedProduct = updatedRetailInventory.getProducts().get(0);
                assertEquals("Rice Bagsss", addedProduct.getProductName());
                System.out.println(addedProduct.getProductName());
                assertEquals(10, addedProduct.getQuantityOfProduct());
                System.out.println(addedProduct.getQuantityOfProduct());

                assertEquals(requestProduct.getProductName(), addedProduct.getProductName());
                System.out.println(addedProduct.getProductName());
                assertEquals(requestProduct.getQuantityOfProduct(), addedProduct.getQuantityOfProduct());
                System.out.println(addedProduct.getQuantityOfProduct());

                requestProduct.setProductName("Rice Bags");
                requestProduct.setQuantityOfProduct(20);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                System.out.println(updatedRetailInventory.toString());
                Products addedProducts = updatedRetailInventory.getProducts().get(1);
                assertEquals("Rice Bags", addedProducts.getProductName());
                System.out.println(addedProducts.getProductName());
                assertEquals(20, addedProducts.getQuantityOfProduct());
                System.out.println(addedProducts.getQuantityOfProduct());
                assertEquals(2, updatedRetailInventory.getProducts().size());

                requestProduct.setProductName("Beans Bags");
                requestProduct.setQuantityOfProduct(5);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                System.out.println(updatedRetailInventory);
                Products addProducts = updatedRetailInventory.getProducts().get(2);
                assertEquals("Beans Bags", addProducts.getProductName());
                System.out.println(addProducts.getProductName());
                assertEquals(20, addedProducts.getQuantityOfProduct());
                System.out.println(addProducts.getQuantityOfProduct());
                assertEquals(3, updatedRetailInventory.getProducts().size());
        }

        @Test
        public void canFindProduct() {
                RetailInventory retailInventory = retailService.create();
                requestProduct.setProductName("Rice Bagsss");
                requestProduct.setQuantityOfProduct(10);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                RetailInventory updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                assertThat(retailService.count(), is(1L));
                assertEquals(1, updatedRetailInventory.getProducts().size());
                System.out.println(updatedRetailInventory.getProducts().size());
                Products addedProduct = updatedRetailInventory.getProducts().get(0);
                assertEquals("Rice Bagsss", addedProduct.getProductName());
                System.out.println(addedProduct.getProductName());
                assertEquals(10, addedProduct.getQuantityOfProduct());
                System.out.println(addedProduct.getQuantityOfProduct());

                requestProduct.setProductName("Rice Bags");
                requestProduct.setQuantityOfProduct(20);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                System.out.println(updatedRetailInventory.toString());
                Products addedProducts = updatedRetailInventory.getProducts().get(1);
                assertEquals("Rice Bags", addedProducts.getProductName());
                System.out.println(addedProducts.getProductName());
                assertEquals(20, addedProducts.getQuantityOfProduct());
                System.out.println(addedProducts.getQuantityOfProduct());
                assertEquals(2, updatedRetailInventory.getProducts().size());

                productResponse.setProductName("Rice Bags");
                productResponse.setId(retailInventory.getId());
                List<Products> service = retailService.findByProductName(productResponse);
                assertNotNull(service);
                System.out.println(service);

                productResponse.setProductName("Rice Bagsss");
                productResponse.setId(retailInventory.getId());
                List<Products> services = retailService.findByProductName(productResponse);
                assertNotNull(services);
                System.out.println(services);

        }

        @Test
        public void productCanBeDeleted() {
                RetailInventory retailInventory = retailService.create();
                requestProduct.setProductName("Rice Bagsss");
                requestProduct.setQuantityOfProduct(10);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                RetailInventory updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                assertThat(retailService.count(), is(1L));
                assertEquals(1, updatedRetailInventory.getProducts().size());
                System.out.println(updatedRetailInventory.getProducts().size());
                Products addedProduct = updatedRetailInventory.getProducts().get(0);
                assertEquals("Rice Bagsss", addedProduct.getProductName());
                System.out.println(addedProduct.getProductName());
                assertEquals(10, addedProduct.getQuantityOfProduct());
                System.out.println(addedProduct.getQuantityOfProduct());

                requestProduct.setProductName("Rice Bags");
                requestProduct.setQuantityOfProduct(20);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                System.out.println(updatedRetailInventory);
                Products addedProducts = updatedRetailInventory.getProducts().get(1);
                assertEquals("Rice Bags", addedProducts.getProductName());
                assertEquals(20, addedProducts.getQuantityOfProduct());
                assertEquals(2, updatedRetailInventory.getProducts().size());


                productResponse.setProductName("Rice Bags");
                productResponse.setId(retailInventory.getId());
                retailService.deleteItem(productResponse);
                updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                assertEquals(1, updatedRetailInventory.getProducts().size());
                System.out.println(updatedRetailInventory);

        }
        @Test
        public void canAddMoreQuantity(){
                RetailInventory retailInventory = retailService.create();
                requestProduct.setProductName("Rice Bagsss");
                requestProduct.setQuantityOfProduct(10);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                RetailInventory updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                assertThat(retailService.count(), is(1L));
                assertEquals(1, updatedRetailInventory.getProducts().size());
                System.out.println(updatedRetailInventory.getProducts().size());
                Products addedProduct = updatedRetailInventory.getProducts().get(0);
                assertEquals("Rice Bagsss", addedProduct.getProductName());
                System.out.println(addedProduct.getProductName());
                assertEquals(10, addedProduct.getQuantityOfProduct());
                System.out.println(addedProduct.getQuantityOfProduct());

                requestProduct.setProductName("Rice Bags");
                requestProduct.setQuantityOfProduct(20);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                System.out.println(updatedRetailInventory);
                Products addedProducts = updatedRetailInventory.getProducts().get(1);
                assertEquals("Rice Bags", addedProducts.getProductName());
                assertEquals(20, addedProducts.getQuantityOfProduct());
                assertEquals(2, updatedRetailInventory.getProducts().size());

                requestProduct.setProductName("Rice Bags");
                requestProduct.setQuantityOfProduct(20);
                requestProduct.setQuantityOfProductAdded(30);
                retailService.addQuantity(requestProduct);
                updatedRetailInventory = retailService.findById(retailInventory.getId());
                Products products = updatedRetailInventory.getProducts().get(1);
                assertEquals(50,products.getQuantityOfProduct());
                assertEquals(0,products.getQuantityOfProductAdded());
                System.out.println(products.getQuantityOfProduct());
                System.out.println(updatedRetailInventory);

        }

        @Test
        public void canRemoveQuantity(){
                RetailInventory retailInventory = retailService.create();
                requestProduct.setProductName("Rice Bagsss");
                requestProduct.setQuantityOfProduct(10);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                RetailInventory updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                assertThat(retailService.count(), is(1L));
                assertEquals(1, updatedRetailInventory.getProducts().size());
                System.out.println(updatedRetailInventory.getProducts().size());
                Products addedProduct = updatedRetailInventory.getProducts().get(0);
                assertEquals("Rice Bagsss", addedProduct.getProductName());
                System.out.println(addedProduct.getProductName());
                assertEquals(10, addedProduct.getQuantityOfProduct());
                System.out.println(addedProduct.getQuantityOfProduct());

                requestProduct.setProductName("Rice Bags");
                requestProduct.setQuantityOfProduct(70);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                System.out.println(updatedRetailInventory);
                Products addedProducts = updatedRetailInventory.getProducts().get(1);
                assertEquals("Rice Bags", addedProducts.getProductName());
                assertEquals(70, addedProducts.getQuantityOfProduct());
                assertEquals(2, updatedRetailInventory.getProducts().size());

                requestProduct.setProductName("Rice Bags");
                requestProduct.setQuantityOfProductRemoved(30);
                retailService.deleteQuantity(requestProduct);
                updatedRetailInventory = retailService.findById(retailInventory.getId());
                Products products = updatedRetailInventory.getProducts().get(1);
                assertEquals(40,products.getQuantityOfProduct());
                System.out.println(products.getQuantityOfProduct());
                System.out.println(updatedRetailInventory);


        }

        @Test
        public void canReturnListOfProductAdd(){
                RetailInventory retailInventory = retailService.create();
                requestProduct.setProductName("Rice Bagsss");
                requestProduct.setQuantityOfProduct(10);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                RetailInventory updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                assertThat(retailService.count(), is(1L));
                assertEquals(1, updatedRetailInventory.getProducts().size());
                System.out.println(updatedRetailInventory.getProducts().size());
                Products addedProduct = updatedRetailInventory.getProducts().get(0);
                assertEquals("Rice Bagsss", addedProduct.getProductName());
                System.out.println(addedProduct.getProductName());
                assertEquals(10, addedProduct.getQuantityOfProduct());
                System.out.println(addedProduct.getQuantityOfProduct());

                requestProduct.setProductName("Rice Bags");
                requestProduct.setQuantityOfProduct(70);
                requestProduct.setQuantityOfProductRemoved(0);
                requestProduct.setQuantityOfProductAdded(0);
                requestProduct.setId(retailInventory.getId());
                retailService.addProducts(requestProduct);
                updatedRetailInventory = retailService.findById(retailInventory.getId());
                assertNotNull(updatedRetailInventory);
                System.out.println(updatedRetailInventory);
                Products addedProducts = updatedRetailInventory.getProducts().get(1);
                assertEquals("Rice Bags", addedProducts.getProductName());
                assertEquals(70, addedProducts.getQuantityOfProduct());
                assertEquals(2, updatedRetailInventory.getProducts().size());


                List<Products> listOfAllProducts = retailService.listOfProduct(retailInventory.getId());
                assertNotNull(listOfAllProducts);
                assertEquals(2, listOfAllProducts.size());
                System.out.println(listOfAllProducts);



        }

}


