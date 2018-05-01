package com.getDeals.service;



import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.getDeals.dao.GetDealsDao;
import com.getDeals.data.GetOrderResponse;

@Path("/api")
public class GetDealsService {
	
	@GET
	@Path("/v1")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<GetOrderResponse> getDeals(@QueryParam("pincode") String pincode,
			@QueryParam("shopCategory") String shopCategory) {
		ArrayList<GetOrderResponse> response= new ArrayList<GetOrderResponse>();

			GetDealsDao getDeals = new GetDealsDao();
			response = getDeals.getShops(pincode,shopCategory);

        return response;
	}

}
