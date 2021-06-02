package dev.karls.application.supplier.list;

import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.karls.SupplierRepository;
import dev.karls.application.supplier.SupplierDTO;
import dev.karls.domain.Supplier;
import dev.karls.utils.ModelMapperUtil;

@Service
@Transactional
public class SupplierList {
    private SupplierRepository supplierRepository;

    private ModelMapperUtil mapper = new ModelMapperUtil();

    public SupplierList() {}

    public SupplierList(SupplierRepository repository) {
        this.supplierRepository = repository;
    }

    public List<SupplierDTO> handle() {
        List<Supplier> data = this.supplierRepository.findAll();
        List<SupplierDTO> suppliers = this.mapper.map(data, new TypeToken<List<SupplierDTO>>() {}.getType());

        return suppliers;
    }
}
