package dev.karls.application.product.stock;

public class ProductStockCommand {
    public enum Operation {
        INCREASE("increase"),
        DECREASE("decrease");
    
        private String operation;
    
        private Operation(String operation) {
            this.operation = operation;
        }
    }

    public long id;

    public Operation operation;

    public int quantity;
}
