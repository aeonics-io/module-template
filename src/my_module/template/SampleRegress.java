package template;

import aeonics.data.*;
import aeonics.util.*;

/**
 * This Regress example filters out (drop) all messages that do not contain a populated property 
 */
public class SampleRegress extends Item.Abstract implements Regress
{
	private String key = null;
	public String key() { return key; }
	public void key(String value) { key = value; }
	
	public Message apply(Message message)
	{
		if( !message.contains(key()) )
			throw new Drop("The message did not contain the property " + key());
		if( message.content().isEmpty(key()) )
			throw new Drop("The message property " + key() + " is null or empty");
		
		// let other regress handlers process this message
		return null;
	}
	
	public Data encode()
	{
		return super.encode().put("key", key());
	}
	
	public void decode(Data value)
	{
		super.decode(value);
		if( value.containsKey("key") ) key(value.asString("key"));
	}
	
	public void update(Data value)
	{
		super.update(value);
		if( value.containsKey("key") ) key(value.asString("key"));
	}
	
	public Data documentation()
	{
		return super.documentation().encode()
			.put("description", "Drops a message if the target property is not populated.")
			.put("name", "Property Filter")
			.put("parameters", Data.emptyMap()
				.put("key", "The name of the property to check.")
					);
	}
}
