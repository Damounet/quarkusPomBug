package com.toto.tdng.datachecker.service;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.toto.tdng.datachecker.common.minio.MinioService;
import com.toto.tdng.datachecker.common.referentials.model.ReferentialsRequest;
import com.toto.tdng.datachecker.common.referentials.model.ReferentialsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/api/v1/referentials")
public class ReferentialsServiceMock {

    @Inject
    MinioService minioService;

    @POST
    public Response fetchReferential(String json) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode detailsNode = mapper.readTree(json);
            ReferentialsRequest referentialsRequest = mapper.readValue(detailsNode.toString(),
                    ReferentialsRequest.class);
            log.info("Received referentials request : " + referentialsRequest.toString());

            ReferentialsResponse referentialsResponse = minioService.getRefenrentialsObject(
                    referentialsRequest.getMiCode() + "/" + referentialsRequest.getReferential() + ".XML");

            return Response.status(Response.Status.OK).entity(referentialsResponse).build();
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException while reading request json : " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (MinioException e) {
            log.error("MinioException while fetching referential doc : " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
