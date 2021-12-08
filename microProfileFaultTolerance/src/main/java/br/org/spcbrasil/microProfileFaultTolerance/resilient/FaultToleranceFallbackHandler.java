package br.org.spcbrasil.microProfileFaultTolerance.resilient;

/*public class FaultToleranceFallbackHandler implements FallbackHandler<String> {
	
	@Override
	public String handle(ExecutionContext context) {
		System.out.println(this.getFirstParam(context));
		return "alguma coisa ";
	}

	private Long getFirstParam(ExecutionContext context) {
		Object object = context.getParameters()[0];
		return Long.valueOf(object.toString());
	}
	
}*/