package ma.digital.digitalbanking.dao.repositories;


import ma.digital.digitalbanking.dao.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}