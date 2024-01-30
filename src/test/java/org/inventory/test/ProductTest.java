package org.inventory.test;

import org.inventory.dto.ProductResponse;
import org.inventory.dto.RequestProduct;
import org.inventory.model.Products;
import org.inventory.repositories.ProductsRepo;
import org.inventory.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private ProductService productService;


    RequestProduct requestProduct = new RequestProduct();
    ProductResponse productResponse = new ProductResponse();
    Products products;
    @BeforeEach
    public void tearDown(){
        productsRepo.deleteAll();
        products = new Products();
    }
    @Test
    public void taskCanBeAdded(){
        requestProduct.setProductName("Rice");
        requestProduct.setQuantityOfProduct(20);
        Products product = productService.addProducts(requestProduct);
        assertEquals(1,productService.count());
        System.out.println(product.getId());
        requestProduct.setProductName("beans");
        requestProduct.setQuantityOfProduct(40);
        Products products = productService.addProducts(requestProduct);
//        assertEquals(2,productService.count());
        assertThat(productService.count(),is(2L));
        System.out.println(products.getId());

    }
}
