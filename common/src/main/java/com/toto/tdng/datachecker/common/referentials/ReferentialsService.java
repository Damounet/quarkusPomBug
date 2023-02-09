package com.toto.tdng.datachecker.common.referentials;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.toto.tdng.datachecker.common.referentials.exception.RefServiceCallException;
import com.toto.tdng.datachecker.common.referentials.model.ReferentialsResponse;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class ReferentialsService {

    @Inject
    @RestClient
    ReferentialsCaller referentialsCaller;

    public ReferentialsResponse callReferentialService(String jsonInputString) throws RefServiceCallException {
        try {
            log.info("Calling ReferentialService : " + jsonInputString);
            Response response = referentialsCaller.callReferentialsService(jsonInputString);
            ReferentialsResponse referentialsResponse = response.readEntity(ReferentialsResponse.class);
            return referentialsResponse;
        } catch (ProcessingException e) {
            log.error("Call to Referential Service failed, service wasn't found.");
            throw new RefServiceCallException("Call to Referential Service failed, service wasn't found.");
        } catch (WebApplicationException e) {
            Response response = e.getResponse();
            log.error("Call to Referentials Service failed, input : " + jsonInputString + " | http code : "
                    + response.getStatus() + " | error : " + response.readEntity(String.class));
            throw new RefServiceCallException(
                    "Call to Referentials Service failed");
        }
    }
}
