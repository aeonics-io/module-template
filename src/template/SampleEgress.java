package template;

import java.io.*;
import aeonics.data.*;
import aeonics.util.*;

/**
 * This Egress example forwards the message to a file
 */
public class SampleEgress extends Item.Abstract implements Egress
{
	private String path = null;
	public String path() { return path; }
	public void path(String value) { path = value; }
	
	public void accept(Message message)
	{
		try( FileOutputStream file = new FileOutputStream(path()) )
		{
			file.write(message.content().asMemoryView().toByteArray());
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
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
