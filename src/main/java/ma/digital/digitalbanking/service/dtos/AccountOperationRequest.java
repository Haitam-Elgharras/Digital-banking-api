package ma.digital.digitalbanking.service.dtos;

import lombok.Data;

@Data
public class AccountOperationRequest {
    private String accountId;
    private double amount;
    private String description;
}
