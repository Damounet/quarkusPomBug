package com.toto.tdng.datachecker.common.model;

import java.util.ArrayList;

import lombok.*;

@Data
@ToString(includeFieldNames = true)
@AllArgsConstructor
@NoArgsConstructor
public class Scenario {
    private String deliveryId;
    private ArrayList<String> tasks;
    private String minioPath;
    private ArrayList<CheckDM> filenameList;
    private User creator;
}
