package dev.karls.application.product.list;

import java.util.List;
import java.util.Optional;

import org.modelmapper.TypeToken;

import dev.karls.ProductRepository;
import dev.karls.domain.Product;
import dev.karls.utils.IHandler;
import dev.karls.utils.ModelMapperUtil;
import dev.karls.application.product.ProductDTO;

public class ProductList implements IHandler<Optional<Product>, List<ProductDTO>> {
    private ProductRepository productRepository;

    private ModelMapperUtil mapper = new ModelMapperUtil();

    public ProductList() {}

    public ProductList(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> handle(Optional<Product> filter) {
        List<Product> data = this.productRepository.findAll();
        return this.mapper.map(
            data,
            new TypeToken<List<ProductDTO>>() {}.getType()
        );
    }
}
