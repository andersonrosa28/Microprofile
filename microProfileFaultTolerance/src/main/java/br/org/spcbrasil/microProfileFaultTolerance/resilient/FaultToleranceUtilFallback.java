package br.org.spcbrasil.microProfileFaultTolerance.resilient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Fallback;

/**
 * 
 * A @Fallbackanotação fornece um caminho de execução alternativo em caso de falha, aumentando assim a taxa de sucesso das solicitações.
 *
 */
@Path("/resilience/fallback")
@ApplicationScoped
public class FaultToleranceUtilFallback {

	/**
	 * O metodo chamado no "fallbackMethod" precisa ter os mesmos parametros, 
	 * dos mesmos tipos e ordem que metodo original
	 * 
	 * Só pode ser definido no metodo e não na classe
	 * 
	 * Você pode declarar o value ou o fallbackMethod
	 * EX: 
	 * value = MeuHandler class,
	 * fallbackMethod =  "metodoFallback"
	 */
	@Fallback(
		fallbackMethod =  "metodoFallback",
		applyOn = Throwable.class,
		skipOn = Throwable.class
	)
	public Boolean fallBack(Long id) {
		return true;
	}
	
	public String metodoFallback(Long id) {
	   return "Fallback answer due to timeout";
	}
}
