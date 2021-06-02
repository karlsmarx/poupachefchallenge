package dev.karls.presentation.http.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.karls.SupplierRepository;
import dev.karls.application.supplier.SupplierDTO;
import dev.karls.application.supplier.create.SupplierCreate;
import dev.karls.application.supplier.create.SupplierCreateCommand;
import dev.karls.application.supplier.delete.SupplierDelete;
import dev.karls.application.supplier.list.SupplierList;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SupplierDTO create(@RequestBody SupplierCreateCommand data) {
        SupplierCreate createHandler = new SupplierCreate(supplierRepository);
        return createHandler.handle(data.name);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<SupplierDTO> list() {
        SupplierList listHandler = new SupplierList(supplierRepository);
        return listHandler.handle();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public SupplierDTO delete(@PathVariable long id) throws Exception {
        SupplierDelete deleteHandler = new SupplierDelete(supplierRepository);
        return deleteHandler.handle(id);
    }
}
