package ma.digital.digitalbanking.service.dtos;

import lombok.Data;

@Data
public class TransferDTO {
    private String bankAccountSourceId;
    private double amount;
    private String description;
    private String bankAccountDestinationId;
}
