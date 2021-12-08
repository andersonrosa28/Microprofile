package br.org.spcbrasil.microProfileFaultTolerance.resilient;

import java.time.temporal.ChronoUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Retry;

/**
 * 
 * 
 * Você pode definir comportamentos muito refinados. 
 * No próximo exemplo, se IOExceptionocorrer um , haverá 2 novas tentativas com um atraso de
 * 200ms menos ou mais um valor aleatório entre -100ms e + 100ms, por causa do atributo jitter.
 * Isso ajuda a reduzir os picos de carga.
 * 
 * EX: @Retry(delay = 200, maxRetries = 2, jitter = 100, retryOn = IOException.class)
 */
@Path("/resilience/retry")
@ApplicationScoped
public class FaultToleranceUtilRetry {

	@Retry(
		maxRetries=3,// quantidade máxima de tentativas. -1 = para sempre
		
		delay=0,//atraso entre cada nova tentativa
		delayUnit=ChronoUnit.MILLIS,//unidade do atraso

		maxDuration=180000,//duração máxima de todas as tentivas, atingindo a duração máxima, nenhuma nova tentativa será feita
		durationUnit=ChronoUnit.MILLIS,//unidade da duração

		jitter=200,//a variação aleatória dos atrasos de nova tentativa
		jitterDelayUnit=ChronoUnit.MILLIS,//unidade do jitter

		retryOn= {IndexOutOfBoundsException.class},//Exceções para tentar novamente
		abortOn={NullPointerException.class}//Exceções para abortar os retries
	)
	public boolean retry() {
		return true;
	}
}
