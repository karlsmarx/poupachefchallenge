package dev.karls;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.karls.domain.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
