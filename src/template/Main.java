package template;

import aeonics.util.*;
import aeonics.data.*;
import aeonics.system.*;
import aeonics.rest.*;
import aeonics.event.Lifecycle;

public class Main extends Module
{
	public List<String> dependency()
	{
		// we shall wait for the http module to be available
		// in order to register our SampleRestEndpoint
		return Arrays.asList("http");
	}
	
	public void register()
	{
		// because of early module injection, delegate the
		// processing to an inner class to avoid dependency issues
		Singleton.get(Lifecycle.class).once(Lifecycle.LOAD, (e) -> { Local.register(); }); 
	}
	
	static class Local
	{
		public static void register()
		{
			Factory.of(Ingress.class).put(template.SampleIngress.class);
			Factory.of(Regress.class).put(template.SampleRegress.class);
			Factory.of(Egress.class).put(template.SampleEgress.class);
			
			Registry.of(Endpoint.class).register(template.SampleRestEndpoint.create());
		}
	}
}
