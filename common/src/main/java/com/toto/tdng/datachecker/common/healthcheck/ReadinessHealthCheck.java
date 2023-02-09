package com.toto.tdng.datachecker.common.healthcheck;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {

    @Inject
    @RestClient
    DataIndexHealthCheckCaller dataIndexHealthCheckCaller;

    @Override
    public HealthCheckResponse call() {
        try {
            Response response = dataIndexHealthCheckCaller.dataIndexLiveCheck();
            if (response.getStatus() < 400) {
                return HealthCheckResponse.up("Kogito Data-Index");
            }
            return HealthCheckResponse.down("Kogito Data-Index");
        } catch (Exception e) {
            return HealthCheckResponse.down("Kogito Data-Index");
        }
    }
}