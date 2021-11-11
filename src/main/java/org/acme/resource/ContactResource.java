package org.acme.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/contact")
public class ContactResource {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getContact() {
        return "Contact";
    }

}
