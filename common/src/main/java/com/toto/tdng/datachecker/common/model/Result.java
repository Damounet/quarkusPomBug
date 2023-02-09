package com.toto.tdng.datachecker.common.model;

import java.util.ArrayList;
import java.util.Arrays;

import com.toto.tdng.datachecker.common.utils.Constants;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String deliveryId;
    private String filename;
    private String workerType;
    private String workerName;
    private int status;
    private ArrayList<String> details;
    private ArrayList<String> messages;

    public Result(String deliveryId, String filename, String details) {
        this.deliveryId = deliveryId;
        this.filename = filename;
        this.seetDetailsFromString(details);
        this.status = Status.NULL;
    }

    // Consructor only used for tests
    public Result(String workerType, int status) {
        this.workerType = workerType;
        this.status = status;
    }

    /**
     * Custom getter for details that return a single String containing all the data
     * of the ArrayList<String> details attribute.
     * Name of the function is not a typo, this is due to a Kogito limitation/bug,
     * do not rename it to get.
     * 
     * @return String containing the full result details data concaneted.
     */
    public String geetDetailsAsString() {
        return String.join(", ", this.details);
    }

    /**
     * Custom setter for details that takes a String and split it into an
     * ArrayList<String> so that the entry length are never over RESULT_DETAILS_MAX_SIZE.
     * Name of the function is not a typo, this is due to a Kogito limitation/bug,
     * do not rename it to set.
     * 
     * @param String containing the full details attribute of the Result.
     */
    public void seetDetailsFromString(String details) {
        // Split the details string into an ArrayList of String where each as a size
        // limit
        this.details = new ArrayList<String>(
                Arrays.asList(details.split("(?<=\\G.{" + Constants.RESULT_DETAILS_MAX_SIZE + "})")));
    }
}
