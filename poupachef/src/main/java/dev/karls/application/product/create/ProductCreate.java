package dev.karls.application.product.create;

import dev.karls.ProductRepository;
import dev.karls.SupplierRepository;
import dev.karls.application.product.ProductDTO;
import dev.karls.domain.Product;
import dev.karls.domain.Supplier;
import dev.karls.utils.IHandler;
import dev.karls.utils.ModelMapperUtil;

public class ProductCreate implements IHandler<ProductCreateCommand, ProductDTO> {
    private ProductRepository productRepository;

    private SupplierRepository supplierRepository;

    private ModelMapperUtil mapper = new ModelMapperUtil();

    public ProductCreate() {}

    public ProductCreate(
        ProductRepository productRepository,
        SupplierRepository supplierRepository
    ) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public ProductDTO handle(ProductCreateCommand command) {
        Product newProduct = new Product();

        newProduct.setName(command.name);
        newProduct.setQuantityStock(command.quantityStock);
        newProduct.setUnitPrice(command.unitPrice);

        Supplier supplier = this.supplierRepository
            .getOne(Long.valueOf(command.supplierId));
        newProduct.setSupplier(supplier);

        Product savedProduct = this.productRepository.save(newProduct);

        return this.mapper.map(savedProduct, ProductDTO.class);
    }
}
