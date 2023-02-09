package com.toto.tdng.datachecker.common.referentials;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(configKey = "referentialsservice")
public interface ReferentialsCaller {

    static final String ENDPOINT_REFERENTIAL_SERVICE = "/api/v1/referentials";

    @POST
    @Path(ENDPOINT_REFERENTIAL_SERVICE)
    @Produces(MediaType.APPLICATION_JSON)
    Response callReferentialsService(@RequestBody String json);

}
