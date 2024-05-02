package ma.digital.digitalbanking.dao.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Data
@EqualsAndHashCode(callSuper = true) // tell lombok to generate equals and hashcode methods from parent class
@AllArgsConstructor
@DiscriminatorValue("CA")
@NoArgsConstructor
public class CurrentAccount extends BankAccount {
    private double overDraft;
}