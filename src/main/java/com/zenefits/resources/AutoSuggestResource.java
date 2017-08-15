package com.zenefits.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import com.zenefits.Constants;
import com.zenefits.api.IndexRequest;
import com.zenefits.api.autosuggest.AutoSuggestResponse;
import com.zenefits.service.AutoSuggestService;

@Path("/suggest")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class AutoSuggestResource {

	AutoSuggestService autoSuggestService ;
	
	public AutoSuggestResource (AutoSuggestService autoSuggestService){
		this.autoSuggestService = autoSuggestService;
	}
	
	@GET
	public Response suggest(@QueryParam("prefix") String prefix){
		try{
			if(StringUtils.isNotEmpty(prefix)){
				AutoSuggestResponse autoSuggests = autoSuggestService.suggest(prefix);
				return Response.ok(autoSuggests).header("Access-Control-Allow-Origin","*").build();
			}else{
				return Response
	                    .status(Status.BAD_REQUEST)
	                    .entity("Prefix cannot be empty")
	                    .build();
			}
		}
		catch(Exception e){
			return Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
		}
	}
	
	@POST
	@Path("index")
	public Response suggest(IndexRequest request){
		try{
			if(request!=null && !request.getStringsToAdd().isEmpty()){
				String autoSuggests = autoSuggestService.indexStrings(request);
				return Response.ok(autoSuggests).build();
			}else{
				return Response
	                    .status(Status.BAD_REQUEST)
	                    .entity("Prefix cannot be empty")
	                    .build();
			}
		}
		catch(Exception e){
			return Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
		}
		
	}
	
	@GET
	@Path("index")
	public Response suggest(){
		try{
			{
				String autoSuggests = autoSuggestService.indexStringsFromFile(Constants.CSV_FILE);
				return Response.ok(autoSuggests).build();
			}
		}
		catch(Exception e){
			return Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
		}
		
	}

}
