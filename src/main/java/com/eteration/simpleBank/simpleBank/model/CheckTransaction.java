package com.eteration.simpleBank.simpleBank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CheckTransactionService")
@Getter
@Setter
public class CheckTransaction extends Transaction {
    private String checkNumber;
    private String payee;
}
