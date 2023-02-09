package com.toto.tdng.datachecker.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Delivery {
    private String deliveryId;
    private String user;
    private String company;
    private String miCode;
    private String deliveryType;
    private String validationStatus;
    private String processState;
    private String creationDate;
    private String expirationDate;
    private String comment;

    public Delivery(String deliveryId, String user, String company, String miCode, String deliveryType) {
        this.deliveryId = deliveryId;
        this.user = user;
        this.company = company;
        this.miCode = miCode;
        this.deliveryType = deliveryType;
    }
}
