package dev.karls.application.product.stock;

import java.util.Objects;
import java.util.Optional;

import dev.karls.ProductRepository;
import dev.karls.application.product.ProductDTO;
import dev.karls.domain.Product;
import dev.karls.utils.IHandler;
import dev.karls.utils.ModelMapperUtil;

public class ProductStockUpdate implements IHandler<ProductStockCommand, ProductDTO> {
    private ProductRepository productRepository;

    private ModelMapperUtil mapper = new ModelMapperUtil();

    public ProductStockUpdate() {}

    public ProductStockUpdate(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO handle(ProductStockCommand command) throws IllegalArgumentException {
        if(Objects.isNull(command.id))
            throw new IllegalArgumentException("INVALID PRODUCT ID");

        Optional<Product> foundProduct = this.productRepository.findById(command.id);

        if (!foundProduct.isPresent())
            throw new IllegalArgumentException("INVALID PRODUCT ID");

        Product data = foundProduct.get();

        if (command.operation == ProductStockCommand.Operation.INCREASE)
            data.setQuantityStock(data.getQuantityStock() + command.quantity);

        if (command.operation == ProductStockCommand.Operation.DECREASE)
            data.setQuantityStock(data.getQuantityStock() - command.quantity);

        Product updated = this.productRepository.save(data);

        return this.mapper.map(updated, ProductDTO.class);
    }
}
