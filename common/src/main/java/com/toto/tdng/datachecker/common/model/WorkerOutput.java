package com.toto.tdng.datachecker.common.model;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerOutput {
    private String workerType;
    private int status;
}
