package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Complain;

@Path("/Complain")
public class ComplainService {
	
	Complain comObj = new Complain();
	
	//insert Complain details
	@POST
	@Path("/form1")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertComplain( 
			 @FormParam("Description") String Description) 
			
	{
		String output = comObj.insertComplain(Description);
		System.out.println(output);
		return output;
		
	
	}
	


	//Read Complain details
		@GET
		@Path("/readDetails")
		@Produces(MediaType.TEXT_HTML)
		public String readComplain() 
		{
			return comObj.readComplain(); 
		}

		
		//Update Complain 
		@PUT
		@Path("/updateCom")
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN)
		public String updateComplain(String form2Data)
		{
			//Convert the input string to a JSON object 
			JsonObject comObj2 = new JsonParser().parse(form2Data).getAsJsonObject(); 
			
			//Read the values from the JSON object
			String Description = comObj2.get("Description").getAsString();
			String Complain_id  = comObj2.get("Complain_id").getAsString(); 

			String output = comObj.updateComplain(Description, Complain_id );
			
			
			return output;
		}
		
		
	
	
}
