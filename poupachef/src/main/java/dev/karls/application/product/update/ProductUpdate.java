package dev.karls.application.product.update;

import java.util.Objects;
import java.util.Optional;

import org.modelmapper.PropertyMap;

import dev.karls.ProductRepository;
import dev.karls.SupplierRepository;
import dev.karls.application.product.ProductDTO;
import dev.karls.domain.Product;
import dev.karls.domain.Supplier;
import dev.karls.utils.IHandler;
import dev.karls.utils.ModelMapperUtil;

public class ProductUpdate implements IHandler<ProductUpdateCommand, ProductDTO> {
    private ProductRepository productRepository;

    private SupplierRepository supplierRepository;

    private ModelMapperUtil mapper = new ModelMapperUtil();

    public ProductUpdate() {}

    public ProductUpdate(
        ProductRepository productRepository,
        SupplierRepository supplierRepository
    ) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public ProductDTO handle(ProductUpdateCommand command) throws IllegalArgumentException {
        if (Objects.isNull(command.id))
            throw new IllegalArgumentException("INVALID PRODUCT ID");

        Optional<Product> foundProduct = this.productRepository.findById(command.id);

        if (!foundProduct.isPresent())
            throw new IllegalArgumentException("INVALID PRODUCT ID");

        Product data = foundProduct.get();

        this.mapper.getConfiguration().setSkipNullEnabled(true);
        this.mapper.addMappings(new PropertyMap<ProductUpdateCommand, Product>() {
           @Override
           protected void configure() {
               skip(destination.getSupplier());
           } 
        });

        mapper.map(command, data);

        if (command.supplierId != 0) {
            Supplier newSupplier = this.supplierRepository.getOne(command.supplierId);
            data.setSupplier(newSupplier);
        }

        Product updated = this.productRepository.save(data);

        return this.mapper.map(updated, ProductDTO.class);
    }
}
