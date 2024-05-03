package ma.digital.digitalbanking.service.dtos;

import lombok.Data;

@Data
public class SavingAccountRequest {
    private double initialBalance;
    private Long customerId;
    private double rate;

}
