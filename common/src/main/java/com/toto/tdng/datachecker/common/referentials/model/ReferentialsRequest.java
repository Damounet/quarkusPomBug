package com.toto.tdng.datachecker.common.referentials.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferentialsRequest {
    private String deliveryId;
    private String miCode;
    private Referential referential;
}
