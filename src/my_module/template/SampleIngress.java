package template;

import java.security.SecureRandom;

import aeonics.data.*;
import aeonics.system.*;
import aeonics.util.*;
import aeonics.data.ingress.BasicIngress;

/**
 * This Ingress example produces a message with a property "random" containing a random number at a given interval
 */
public class SampleIngress extends BasicIngress
{
	private int delay = 1;
	public int delay() { return delay; }
	public void delay(int value) { delay = value; }
	
	public Message get()
	{
		try
		{
			Data config = Data.emptyMap().put("random", SecureRandom.getInstanceStrong().nextInt());
			Message message = Factory.of(Message.class).produce(Message.class, config);
			Thread.sleep(delay() * 1000);
			return message;
		}
		catch(Exception e)
		{
			Logger.log(Logger.WARNING, getClass(), e);
			return null;
		}
	}
	
	public Data documentation()
	{
		return super.documentation().encode()
			.put("description", "Sample data collector that produces random numbers at the specified time interval.")
			.put("name", "Random")
			.put("parameters", Data.emptyMap()
				.put("delay", "The delay in seconds.")
					);
	}
	
	public Data encode()
	{
		return super.encode().put("delay", delay());
	}
	
	public void decode(Data value)
	{
		super.decode(value);
		if( value.containsKey("delay") ) delay(value.asInt("delay"));
	}
	
	public void update(Data value)
	{
		super.update(value);
		if( value.containsKey("delay") ) delay(value.asInt("delay"));
	}
}
