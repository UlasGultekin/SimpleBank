package com.eteration.simpleBank.simpleBank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
@DiscriminatorValue("Deposit")
public class DepositTransaction extends Transaction{





}
