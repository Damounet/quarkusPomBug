package com.toto.tdng.datachecker.common.model;

import com.google.gson.JsonObject;

import java.util.Set;

import lombok.Data;

@Data
public class MissingIds {
    public Set<String> missingHeaderIds;
    public Set<String> missingContentIds;

    public String toJsonString(String referenceObjectName) {
        JsonObject json = new JsonObject();

        if (this.getMissingContentIds() != null && !this.getMissingContentIds().isEmpty()) {
            json.addProperty("MissingPreliminaryRequirement" + referenceObjectName.substring(0, 1).toUpperCase() + referenceObjectName.substring(1) + "Ids", this.missingContentIds.toString());
        }
        if (this.getMissingHeaderIds() != null && !this.getMissingHeaderIds().isEmpty()) {
            json.addProperty("MissingMainProcedure" + referenceObjectName.substring(0, 1).toUpperCase() + referenceObjectName.substring(1) + "Ids", this.missingHeaderIds.toString());
        }
        return json.toString();
    }
}   

