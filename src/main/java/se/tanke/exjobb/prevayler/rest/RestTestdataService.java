package se.tanke.exjobb.prevayler.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * This service is just for test purpuse and is used to add some
 * testdata to the persistent system.
 * @author tobias
 */
@Path("prevayler/testdata")
public class RestTestdataService {

	@GET
	public void addSomeTestdata() {
		// TODO
		throw new UnsupportedOperationException("TODO");
	}
	
}
