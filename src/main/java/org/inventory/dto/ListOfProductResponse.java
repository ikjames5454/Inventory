package org.inventory.dto;

import lombok.Data;

@Data
public class ListOfProductResponse {
    private String id;
    private String productName;
    private int quantityOfProduct;
    private int quantityOfProductRemoved;
    private int quantityOfProductAdded;
}
