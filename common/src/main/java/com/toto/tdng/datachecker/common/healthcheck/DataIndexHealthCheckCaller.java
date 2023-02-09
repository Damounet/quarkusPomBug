package com.toto.tdng.datachecker.common.healthcheck;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "dataindex")
@ApplicationScoped
public interface DataIndexHealthCheckCaller {

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    Response dataIndexLiveCheck();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    Response dataIndexReadyCheck();

}
