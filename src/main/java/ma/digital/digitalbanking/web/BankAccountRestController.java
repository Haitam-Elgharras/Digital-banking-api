package ma.digital.digitalbanking.web;

import lombok.AllArgsConstructor;
import ma.digital.digitalbanking.service.dtos.*;
import ma.digital.digitalbanking.service.services.interfaces.BankAccountService;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bankAccounts")
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    @GetMapping()
    public List<BankAccountDTO> getAllBankAccounts(){
        return bankAccountService.bankAccountDTOList();
    }

    @GetMapping("/{id}")
    public BankAccountDTO getBankAccount(@PathVariable String id){
        return bankAccountService.getBankAccount(id);
    }

    @PostMapping("/savingAccount")
    public SavingBankAccountDTO saveSavingAccount(@RequestBody SavingAccountRequest request){
        return bankAccountService.saveSavingAccount(request.getInitialBalance(), request.getCustomerId(), request.getRate());
    }

    @PostMapping("/currentAccount")
    public CurrentBankAccountDTO saveCurrentAccount(@RequestBody CurrentAccountRequest request){
        return bankAccountService.saveCurrentAccount(request.getInitialBalance(), request.getCustomerId(), request.getOverdraft());
    }

    @GetMapping("/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/{accountId}/pageOperation")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                        @RequestParam(name = "page",defaultValue = "0")int page,
                                        @RequestParam(name = "size",defaultValue = "4")int size){
        return bankAccountService.getAccountHistory(accountId,page,size);
    }

    @PostMapping("/debit")
    public AccountOperationRequest debit(@RequestBody AccountOperationRequest request){
       return bankAccountService.debit(request.getAccountId(),request.getAmount(),request.getDescription());
    }

    @PostMapping("/credit")
    public AccountOperationRequest credit(@RequestBody AccountOperationRequest request){
        return bankAccountService.credit(request.getAccountId(),request.getAmount(),request.getDescription());
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferDTO transferDTO){
        bankAccountService.transfer(transferDTO.getBankAccountSourceId(),transferDTO.getBankAccountDestinationId(),transferDTO.getAmount());
    }
}
