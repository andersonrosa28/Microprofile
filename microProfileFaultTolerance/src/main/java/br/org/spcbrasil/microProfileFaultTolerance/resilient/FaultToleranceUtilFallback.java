package br.org.spcbrasil.microProfileFaultTolerance.resilient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Fallback;

/**
 * 
 * A @Fallbackanota��o fornece um caminho de execu��o alternativo em caso de falha, aumentando assim a taxa de sucesso das solicita��es.
 *
 */
@Path("/resilience/fallback")
@ApplicationScoped
public class FaultToleranceUtilFallback {

	/**
	 * O metodo chamado no "fallbackMethod" precisa ter os mesmos parametros, 
	 * dos mesmos tipos e ordem que metodo original
	 * 
	 * S� pode ser definido no metodo e n�o na classe
	 * 
	 * Voc� pode declarar o value ou o fallbackMethod
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
