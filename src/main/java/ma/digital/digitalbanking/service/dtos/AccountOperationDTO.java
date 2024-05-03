package ma.digital.digitalbanking.service.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.digital.digitalbanking.enumerations.OperationType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private Double amount;
    private OperationType operationType;
    private String description;
}
