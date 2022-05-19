package template;

import aeonics.data.*;
import aeonics.util.*;
import aeonics.rest.*;

/**
 * This REST endpoint example accepts a single parameter "name" either in GET or POST
 * and outputs a greetings JSON response.
 */
public class SampleRestEndpoint
{
	public static Endpoint create()
	{
		return new RestEndpoint("/hello/world", "GET", "POST")
		{
			public Data handle(Data params)
			{
				return Data.emptyMap()
					.put("hello", params.asString("name"));
			}
		}
		.add(new Parameter("name").min(1).max(50).optional(false));
	}
}
