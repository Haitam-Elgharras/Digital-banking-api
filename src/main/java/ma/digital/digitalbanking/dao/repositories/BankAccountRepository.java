package ma.digital.digitalbanking.dao.repositories;

import ma.digital.digitalbanking.dao.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}