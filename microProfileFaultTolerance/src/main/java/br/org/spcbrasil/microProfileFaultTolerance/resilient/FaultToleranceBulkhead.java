package br.org.spcbrasil.microProfileFaultTolerance.resilient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Bulkhead;

/**
 * Pode ser definido no método ou na classe.
 * 
 * Pode ser utilizado com @Timeout, @Fallback, @Asynchronous, @CircuitBreaker e @Retry
 * 
 *
 */
@Path("/resilience/bulkhead")
@ApplicationScoped
public class FaultToleranceBulkhead {
	
	@Bulkhead(
		value = 10, // número máximo de chamadas concorrentes
		waitingTaskQueue = 10 // Número máximo de elementos em fila aguardando
					 	      // Só pode ser utilizado em métodos anotados com @Asynchronous
	)
	public void bulkhead() {
		
	}
}
