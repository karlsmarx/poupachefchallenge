package dev.karls.application.supplier.create;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.karls.SupplierRepository;
import dev.karls.application.supplier.SupplierDTO;
import dev.karls.domain.Supplier;
import dev.karls.utils.IHandler;
import dev.karls.utils.ModelMapperUtil;

@Service
@Transactional
public class SupplierCreate implements IHandler<String, SupplierDTO> {
    private SupplierRepository supplierRepository;

    private ModelMapperUtil mapper = new ModelMapperUtil();

    public SupplierCreate() {}

    public SupplierCreate(SupplierRepository repository) {
        this.supplierRepository = repository;
    }

    public SupplierDTO handle(String name) {
        Supplier supplier = new Supplier();
        supplier.setName(name);

        Supplier data = this.supplierRepository.save(supplier);

        return this.mapper.map(data, SupplierDTO.class);
    }
}
