package org.inventory.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Products {
    @Id
    private String id;
    private String productName;
    private int quantityOfProduct;
    private int quantityOfProductRemoved;
    private int quantityOfProductAdded;


}
