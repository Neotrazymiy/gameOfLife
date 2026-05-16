package spring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.model.Firm;
import spring.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

	List<Product> findByFirm(Firm firm);

}
