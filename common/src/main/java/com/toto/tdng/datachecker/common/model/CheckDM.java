package com.toto.tdng.datachecker.common.model;

import java.util.ArrayList;

import lombok.*;

@Data
@NoArgsConstructor
public class CheckDM {
    private String deliveryId;
    private String filename;
    private String minioPath;
    private String processId;
    private ArrayList<String> tasks;
    private ArrayList<WorkerOutput> workerOutputs;
    private User creator;

    public CheckDM(String deliveryId, String filename, String minioPath, ArrayList<String> tasks, User creator) {
        this.deliveryId = deliveryId;
        this.filename = filename;
        this.minioPath = minioPath;
        this.tasks = tasks;
        this.workerOutputs = new ArrayList<>();
        this.creator = creator;
    }

    public CheckDM(String deliveryId, String filename, String minioPath, ArrayList<String> tasks) {
        this.deliveryId = deliveryId;
        this.filename = filename;
        this.minioPath = minioPath;
        this.tasks = tasks;
        this.workerOutputs = new ArrayList<>();
        this.creator = new User();
    }
}
