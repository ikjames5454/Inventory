package org.inventory.services;

import org.inventory.MyException.ProductNotFoundException;
import org.inventory.MyException.QuantityException;
import org.inventory.dto.ListOfProductResponse;
import org.inventory.dto.ProductResponse;
import org.inventory.dto.RequestProduct;
import org.inventory.model.Products;
import org.inventory.model.RetailInventory;
import org.inventory.repositories.RetailInventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RetailServiceImpl implements RetailService{
    @Autowired
    private RetailInventoryRepo retailInventoryRepo;
    @Autowired
    private ProductService productService;
    @Override
    public RetailInventory create() {
        RetailInventory retailInventory = new RetailInventory();
        retailInventory.setProducts(new ArrayList<>());
        retailInventoryRepo.save(retailInventory);
        return retailInventory;
    }

    @Override
    public List<Products> addProducts(RequestProduct requestProduct) {
        Products products = productService.addProducts(requestProduct);
        RetailInventory retailInventory = findById(requestProduct.getId());
        List<Products> listOfProduct = retailInventory.getProducts();
        listOfProduct.add(products);
        retailInventory.setProducts(listOfProduct);
        retailInventoryRepo.save(retailInventory);
        return listOfProduct;
    }

    @Override
    public RetailInventory findById(String id) {
        return retailInventoryRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("RetailInventory not found"));

    }


    @Override
    public List<Products> findByProductName(ProductResponse productResponse) {
        RetailInventory retailInventory = findById(productResponse.getId());
        List<Products> listOfProduct = retailInventory.getProducts();
        List<Products> foundProduct = new ArrayList<>();
        for (Products products: listOfProduct){
            if(products.getProductName().equalsIgnoreCase(productResponse.getProductName())){
                foundProduct.add(products);
            }
        }
         if (!foundProduct.isEmpty()) {
             return foundProduct;
         }else {
             throw new ProductNotFoundException("Product not found with the name: " + productResponse.getProductName());
         }
    }

    @Override
    public void deleteItem(ProductResponse productResponse) {
        RetailInventory retailInventory = findById(productResponse.getId());
        List<Products> listOfProduct = retailInventory.getProducts();
        List<Products> products = findByProductName(productResponse);
        listOfProduct.removeAll(products);
//        retailInventoryRepo.delete((RetailInventory) products);
        retailInventoryRepo.save(retailInventory);

    }

    @Override
    public List<Products> addQuantity(RequestProduct requestProduct) {
            RetailInventory retailInventory = findById(requestProduct.getId());
            List<Products> listOfProduct = retailInventory.getProducts();

            for (Products product : listOfProduct) {
                if (product.getProductName().equalsIgnoreCase(requestProduct.getProductName())) {
                    int currentQuantity = product.getQuantityOfProduct();
                    int addedQuantity = requestProduct.getQuantityOfProductAdded();
                    product.setQuantityOfProduct(currentQuantity + addedQuantity);
                    retailInventory.setProducts(listOfProduct);
                    retailInventoryRepo.save(retailInventory);
                    return retailInventory.getProducts();
                }
            }

            throw new ProductNotFoundException("Product not found with the name: " + requestProduct.getProductName());
        }

    @Override
    public List<Products> listOfProduct(String Id) {
        RetailInventory retailInventory = findById(Id);
        List<Products> listOfProduct;
        listOfProduct = retailInventory.getProducts();
        return listOfProduct;
    }


    @Override
    public List<Products> deleteQuantity(RequestProduct requestProduct) {
        RetailInventory retailInventory = findById(requestProduct.getId());
        List<Products> listOfProduct = retailInventory.getProducts();
        boolean quantityCondition = false;

        for (Products product : listOfProduct) {
            if (product.getProductName().equalsIgnoreCase(requestProduct.getProductName())) {
                int currentQuantity = product.getQuantityOfProduct();
                int removedQuantity = requestProduct.getQuantityOfProductRemoved();
                if (currentQuantity > removedQuantity) {
                    product.setQuantityOfProduct(currentQuantity - removedQuantity);
                    retailInventory.setProducts(listOfProduct);
                    retailInventoryRepo.save(retailInventory);
                    quantityCondition = true;
                }else {
                    throw new QuantityException("Insufficient quantity to remove for product: " + requestProduct.getProductName());
                }

            }

        }
        if(quantityCondition){
            return retailInventory.getProducts();
        }

        throw new ProductNotFoundException("Product not found with the name: " + requestProduct.getProductName());
    }

    @Override
    public long count() {
        return retailInventoryRepo.count();
    }


}
