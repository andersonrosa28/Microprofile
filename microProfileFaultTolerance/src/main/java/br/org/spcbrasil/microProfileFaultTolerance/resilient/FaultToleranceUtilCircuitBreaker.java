package br.org.spcbrasil.microProfileFaultTolerance.resilient;

import java.time.temporal.ChronoUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

/**
 * Pode ser definido no método ou na classe.
 * 
 * Pode ser utilizado com @Timeout, @Fallback, @Asynchronous, @Bulkhead e @Retry
 * 
 * Funciona igual um disjuntor, o circuito fica fechado e dependendo das configurações ele pode abrir,
 * o circuto aberto quer dizer que tem algum falha.
 * 
 *  import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
 *
 * 	@CircuitBreaker(requestVolumeThreshold = 4, failureRatio=0.75, delay = 1000, successThreshold = 10)
 * 	public String callSomeService() {...}
 *
 */
@Path("/resilience/circuitBreaker")
@ApplicationScoped
public class FaultToleranceUtilCircuitBreaker {
	
	@CircuitBreaker(
		failOn=Throwable.class,//Considera falha se lançar Throwable ou qualquer subclasse
		delay=5000,//delay aplicado depois que o circuito abre
		delayUnit = ChronoUnit.MILLIS,//unidade do delay
		requestVolumeThreshold = 20,//número de requisições consideradas para o failureRatio
		failureRatio = .50,//limite de falha para abrir o circuito. Valor entre 0 e 1. 
						   //Neste exemplo, 10 falhas das últimas 20 requisições, abre o circuito
		successThreshold = 1,//Quantidade de sucessos, depois do delay, para fechar o circuito novamente
		
		//#MP-3.3 Criado na versão 3.3 de Microprofile
		skipOn = Throwable.class
	)
	public void circuitBreaker() {
		
	}
}
