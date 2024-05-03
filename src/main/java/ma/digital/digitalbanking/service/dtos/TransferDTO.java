package ma.digital.digitalbanking.service.dtos;

import lombok.Data;

@Data
public class TransferDTO {
    private String bankAccountSourceId;
    private String bankAccountDestinationId;
    private double amount;
}
