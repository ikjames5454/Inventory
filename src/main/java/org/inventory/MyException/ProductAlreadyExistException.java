package org.inventory.MyException;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException(String message){
        super(message);
    }
}
