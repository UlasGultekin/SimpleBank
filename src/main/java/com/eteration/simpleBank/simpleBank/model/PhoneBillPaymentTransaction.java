package com.eteration.simpleBank.simpleBank.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@DiscriminatorValue("PhoneBillPayment")
@Entity
@Getter
@Setter
public class PhoneBillPaymentTransaction extends BillPaymentTransaction {
    @Column(name = "phone_number")
    private String phoneNumber;
}
