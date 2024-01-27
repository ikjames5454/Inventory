package org.inventory.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
@Data
public class RetailInventory {
    @Id
    private String id;
    private List<Products>products;
}
