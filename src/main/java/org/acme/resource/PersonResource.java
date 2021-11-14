package org.acme.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.acme.model.Person;
import org.acme.service.PersonService;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

@Path("person")
public class PersonResource {

    @Inject
    PersonService personService;
    
    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance personTable(List<Person> persons, int nextPage);  
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(@QueryParam("page") @DefaultValue("0") int pageIndex) {

        List<Person> persons = personService.getPersons(pageIndex);
        return Templates.personTable(persons, pageIndex + 1);
    }



}