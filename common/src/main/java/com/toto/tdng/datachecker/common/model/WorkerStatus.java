package com.toto.tdng.datachecker.common.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerStatus {
    private String deliveryId;
    private String filename;
    private String workerType;
    private int status;
}
