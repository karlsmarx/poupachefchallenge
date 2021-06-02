package dev.karls.application.supplier.delete;

import java.util.Optional;

import dev.karls.SupplierRepository;
import dev.karls.application.supplier.SupplierDTO;
import dev.karls.domain.Supplier;
import dev.karls.utils.IHandler;
import dev.karls.utils.ModelMapperUtil;

public class SupplierDelete implements IHandler<Long, SupplierDTO> {
    private ModelMapperUtil mapper = new ModelMapperUtil();

    private SupplierRepository supplierRepository;

    public SupplierDelete() {}

    public SupplierDelete(SupplierRepository repository) {
        this.supplierRepository = repository;
    }

    public SupplierDTO handle(Long id) throws IllegalArgumentException {
        Optional<Supplier> deleted = this.supplierRepository.findById(id);
        if (!deleted.isPresent())
            throw new IllegalArgumentException("INVALID SUPPLIER ID");

        this.supplierRepository.deleteById(id);

        return this.mapper.map(deleted.get(), SupplierDTO.class);
    }
}
