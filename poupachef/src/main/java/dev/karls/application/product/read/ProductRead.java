package dev.karls.application.product.read;

import java.util.Optional;

import dev.karls.ProductRepository;
import dev.karls.application.product.ProductDTO;
import dev.karls.domain.Product;
import dev.karls.utils.IHandler;
import dev.karls.utils.ModelMapperUtil;

public class ProductRead implements IHandler<ProductReadQuery, ProductDTO>{
    private ProductRepository productRepository;

    private ModelMapperUtil mapper = new ModelMapperUtil();

    public ProductRead() {}

    public ProductRead(ProductRepository repository) {
        this.productRepository = repository;
    }

    public ProductDTO handle(ProductReadQuery query) throws IllegalArgumentException {
        Optional<Product> foundProduct = this.productRepository.findById(query.id);

        if (!foundProduct.isPresent())
            throw new IllegalArgumentException("INVALID PRODUCT ID");

        return this.mapper.map(foundProduct.get(), ProductDTO.class);
    }
}
