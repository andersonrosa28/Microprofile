package br.org.spcbrasil.microProfileFaultTolerance.resilient;

import java.util.concurrent.CompletableFuture;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Asynchronous;

/**
 * Pode ser definido no método.
 * 
 * Executa em uma Thread separada
 * 
 * Pode ser utilizado com @Timeout, @Fallback, @Bulkhead, @CircuitBreaker e @Retry
 * 
 */
@Path("/resilience/asynchronous")
@ApplicationScoped
public class FaultToleranceAsynchronous {
	
	@Asynchronous
	public java.util.concurrent.CompletionStage<Integer> asynchronous1() {
		return CompletableFuture.completedFuture(1);
	}
	
	@Asynchronous
	public java.util.concurrent.Future<Integer> asynchronous2() {
		return CompletableFuture.completedFuture(1);
	}
}
