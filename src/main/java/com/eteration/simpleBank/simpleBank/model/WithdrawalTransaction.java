package com.eteration.simpleBank.simpleBank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Withdrawal")
public class WithdrawalTransaction extends Transaction{
}
