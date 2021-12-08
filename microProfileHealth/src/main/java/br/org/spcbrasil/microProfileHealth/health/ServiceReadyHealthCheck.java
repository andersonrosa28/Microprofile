package br.org.spcbrasil.microProfileHealth.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class ServiceReadyHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {

    	//HealthCheckResponseBuilder builder = HealthCheckResponse.named("Validando se banco está pronto");
         HealthCheckResponseBuilder builder = HealthCheckResponse.named(ServiceReadyHealthCheck.class.getSimpleName());
         if(bancoEstaFora()) {
        	 builder.withData("erroBanco", true).down();
         } else {
        	 builder.withData("ready",true).up();
         }

         return builder.build();
    }
    
    public boolean bancoEstaFora() {
    	return false;
    }
}
