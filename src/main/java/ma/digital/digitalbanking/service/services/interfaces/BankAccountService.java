package ma.digital.digitalbanking.service.services.interfaces;

import ma.digital.digitalbanking.service.dtos.*;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customer);
    CurrentBankAccountDTO saveCurrentAccount(double initialBalance, Long customerId, double overdraft);
    SavingBankAccountDTO saveSavingAccount(double initialBalance, Long customerId, double rate);
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId);
    AccountOperationRequest debit(String accountId, double amount, String description);
    AccountOperationRequest credit(String accountId, double amount, String description);
    void transfer(String accountIdSource,String accountIdDestination,double amount);

    CustomerDTO getCustomer(Long customerId);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long id);

    List<BankAccountDTO> bankAccountDTOList();

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId,int page,int size);

    List<CustomerDTO> searchCustomer(String keyword);
}
