package com.eteration.simpleBank.simpleBank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("BillPayment")
public class BillPaymentTransaction extends Transaction {
    private String payee;
}
