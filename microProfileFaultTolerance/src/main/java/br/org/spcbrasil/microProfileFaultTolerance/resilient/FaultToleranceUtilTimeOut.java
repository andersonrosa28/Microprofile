package br.org.spcbrasil.microProfileFaultTolerance.resilient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;
import br.org.spcbrasil.microProfileFaultTolerance.resilient.RequestUtil.RequestUtil;

/**
 * 
 * Se você anotar um método com isso @Timeout, a execução será abortada após o
 * limite definido. Para este exemplo, esse limite é 500 ms.
 *
 */
@Path("/resilience/timeout")
@ApplicationScoped
public class FaultToleranceUtilTimeOut {

	private static final Logger LOGGER = Logger.getLogger(FaultToleranceUtilTimeOut.class);
	
	@Inject
	private RequestUtil requestUtil;

	private AtomicLong counter = new AtomicLong(0);

	@Fallback(fallbackMethod = "fallback") // better use FallbackHandler
	@Timeout(5)
	@GET
	public Response checkTimeout() {
		long started = System.currentTimeMillis();
		final Long invocationNumber = counter.getAndIncrement();

		try {
			Future<Response> response = requestUtil.createFailingRequestToClientAsynch();
			Response responseResult = response.get();		
			
			LOGGER.infof("FaultToleranceUtilTimeOut#recommendations() invocation #%d returning successfully", invocationNumber);
			return responseResult;
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.errorf("FaultToleranceUtilTimeOut#recommendations() invocation #%d timed out after %d ms", invocationNumber,
					System.currentTimeMillis() - started);
			return null;
		}		
	}

	public String fallback() {
		return "Fallback answer due to timeout";
	}

}
