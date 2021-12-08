package br.org.spcbrasil.microProfileFaultTolerance.resilient;

import java.time.temporal.ChronoUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Retry;

/**
 * 
 * 
 * Voc� pode definir comportamentos muito refinados. 
 * No pr�ximo exemplo, se IOExceptionocorrer um , haver� 2 novas tentativas com um atraso de
 * 200ms menos ou mais um valor aleat�rio entre -100ms e + 100ms, por causa do atributo jitter.
 * Isso ajuda a reduzir os picos de carga.
 * 
 * EX: @Retry(delay = 200, maxRetries = 2, jitter = 100, retryOn = IOException.class)
 */
@Path("/resilience/retry")
@ApplicationScoped
public class FaultToleranceUtilRetry {

	@Retry(
		maxRetries=3,// quantidade m�xima de tentativas. -1 = para sempre
		
		delay=0,//atraso entre cada nova tentativa
		delayUnit=ChronoUnit.MILLIS,//unidade do atraso

		maxDuration=180000,//dura��o m�xima de todas as tentivas, atingindo a dura��o m�xima, nenhuma nova tentativa ser� feita
		durationUnit=ChronoUnit.MILLIS,//unidade da dura��o

		jitter=200,//a varia��o aleat�ria dos atrasos de nova tentativa
		jitterDelayUnit=ChronoUnit.MILLIS,//unidade do jitter

		retryOn= {IndexOutOfBoundsException.class},//Exce��es para tentar novamente
		abortOn={NullPointerException.class}//Exce��es para abortar os retries
	)
	public boolean retry() {
		return true;
	}
}
