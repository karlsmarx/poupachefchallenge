package dev.karls.application.product.delete;

import java.util.Optional;

import dev.karls.ProductRepository;
import dev.karls.application.product.ProductDTO;
import dev.karls.domain.Product;
import dev.karls.utils.IHandler;
import dev.karls.utils.ModelMapperUtil;

public class ProductDelete implements IHandler<Long, ProductDTO> {
    private ProductRepository productRepository;

    private ModelMapperUtil mapper = new ModelMapperUtil();

    public ProductDelete() {}

    public ProductDelete(ProductRepository repository) {
        this.productRepository = repository;
    }

    public ProductDTO handle(Long id) throws IllegalArgumentException {
        Optional<Product> foundProduct = this.productRepository.findById(id);

        if (!foundProduct.isPresent())
            throw new IllegalArgumentException("INVALID PRODUCT ID");

        this.productRepository.deleteById(id);

        return this.mapper.map(foundProduct.get(), ProductDTO.class);
    }
}
