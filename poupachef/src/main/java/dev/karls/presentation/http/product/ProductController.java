package dev.karls.presentation.http.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.karls.ProductRepository;
import dev.karls.SupplierRepository;
import dev.karls.application.product.ProductDTO;
import dev.karls.application.product.create.ProductCreate;
import dev.karls.application.product.create.ProductCreateCommand;
import dev.karls.application.product.delete.ProductDelete;
import dev.karls.application.product.list.ProductList;
import dev.karls.application.product.read.ProductRead;
import dev.karls.application.product.read.ProductReadQuery;
import dev.karls.application.product.stock.ProductStockCommand;
import dev.karls.application.product.stock.ProductStockUpdate;
import dev.karls.application.product.update.ProductUpdate;
import dev.karls.application.product.update.ProductUpdateCommand;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ProductDTO create(@RequestBody ProductCreateCommand data) {
        ProductCreate createHandler = new ProductCreate(productRepository, supplierRepository);
        return createHandler.handle(data);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ProductDTO> list() {
        ProductList listHandler = new ProductList(productRepository);
        return listHandler.handle(null);
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public ProductDTO read(ProductReadQuery data) {
        ProductRead readHandler = new ProductRead(productRepository);
        return readHandler.handle(data);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ProductDTO delete(@PathVariable long id) {
        ProductDelete deleteHandler = new ProductDelete(productRepository);
        return deleteHandler.handle(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ProductDTO update(@RequestBody ProductUpdateCommand command) {
        ProductUpdate updateHandler = new ProductUpdate(productRepository, supplierRepository);
        return updateHandler.handle(command);
    }

    @RequestMapping(value = "/stock", method = RequestMethod.PATCH)
    public ProductDTO stock(@RequestBody ProductStockCommand command) {
        ProductStockUpdate updateHandler = new ProductStockUpdate(productRepository);
        return updateHandler.handle(command);
    }
}
