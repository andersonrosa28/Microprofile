package br.org.spcbrasil.microProfileFaultTolerance.resilient.RequestUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Asynchronous;

@ApplicationScoped
public class RequestUtil {
	
	@Asynchronous
    public Future<Response> createFailingRequestToClientAsynch() {
        Response response = ClientBuilder
        		.newClient()
        		.target("http://localhost:8082/microProfileOpenTracing")
        		.path("/data/hello")
        		.request()
        		.get();
        return CompletableFuture.completedFuture(response);
    }

}
