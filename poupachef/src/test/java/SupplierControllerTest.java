import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.karls.SupplierRepository;
import dev.karls.application.supplier.SupplierDTO;
import dev.karls.application.supplier.create.SupplierCreateCommand;
import dev.karls.domain.Supplier;
import dev.karls.presentation.http.supplier.SupplierController;

public class SupplierControllerTest {
    @InjectMocks
    private SupplierController supplierController;

    @Mock
    private SupplierRepository supplierRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createSupplierTest() {
        Supplier mock = new Supplier();
        mock.setName("name");

        when(supplierRepository.save(any(Supplier.class))).thenReturn(mock);

        SupplierCreateCommand command = new SupplierCreateCommand();
        SupplierDTO newSupplier = supplierController.create(command);

        assertEquals(newSupplier.name, mock.getName());
    }

    @Test
    public void deleteSupplierTest() {
        Supplier supplier = new Supplier();
        supplier.setName("name");

        Optional<Supplier> mock = Optional.of(supplier);

        when(supplierRepository.findById(anyLong())).thenReturn(mock);

        try {
            SupplierDTO deletedSupplier = supplierController.delete(1L);
            assertEquals(deletedSupplier.name, supplier.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
