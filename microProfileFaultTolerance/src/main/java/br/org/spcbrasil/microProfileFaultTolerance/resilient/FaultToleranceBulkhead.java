package br.org.spcbrasil.microProfileFaultTolerance.resilient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Bulkhead;

/**
 * Pode ser definido no m�todo ou na classe.
 * 
 * Pode ser utilizado com @Timeout, @Fallback, @Asynchronous, @CircuitBreaker e @Retry
 * 
 *
 */
@Path("/resilience/bulkhead")
@ApplicationScoped
public class FaultToleranceBulkhead {
	
	@Bulkhead(
		value = 10, // n�mero m�ximo de chamadas concorrentes
		waitingTaskQueue = 10 // N�mero m�ximo de elementos em fila aguardando
					 	      // S� pode ser utilizado em m�todos anotados com @Asynchronous
	)
	public void bulkhead() {
		
	}
}
