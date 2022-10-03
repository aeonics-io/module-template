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
		return super.encode().put("path", path());
	}
	
	public void decode(Data value)
	{
		super.decode(value);
		if( value.containsKey("path") ) path(value.asString("path"));
	}
	
	public void update(Data value)
	{
		super.update(value);
		if( value.containsKey("path") ) path(value.asString("path"));
	}
	
	public Data documentation()
	{
		return super.documentation().encode()
			.put("description", "Writes the message content in a file.")
			.put("name", "File Egress")
			.put("parameters", Data.emptyMap()
				.put("path", "The name of the property to check.")
					);
	}
}
