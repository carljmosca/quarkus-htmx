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

@Path("/person")
public class PersonResource {

    @Inject
    PersonService personService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPersons(@QueryParam("page") @DefaultValue("0") int pageIndex) {

        StringBuilder sb = new StringBuilder();
        String triggerAttributes = String
                .format("hx-get=\"/person/?page=%d\" hx-trigger=\"revealed\" hx-swap=\"afterend\"", pageIndex + 1);
        List<Person> persons = personService.getPersons(pageIndex);
        for (int i = 0; i < PersonService.PAGE_SIZE; i++) {
            sb.append(String.format("<div class=\"columns\" %s>", i == (PersonService.PAGE_SIZE - 1) ? triggerAttributes : ""));
            sb.append(String.format("<div class=\"column\"><p><strong>%s %s</strong></p></div>", persons.get(i).getFirstName(),
                    persons.get(i).getLastName()));
            sb.append(getColumn(persons.get(i).getPhone()));
            sb.append(getColumn(persons.get(i).getEmail()));
            sb.append(getColumn(persons.get(i).getAddress()));
            sb.append(getColumn(persons.get(i).getCity() + ", " +
                persons.get(i).getState() + " " + persons.get(i).getZip()));
            sb.append("</div>");
        }
        return sb.toString();
    }

    private String getColumn(String value) {
        return String.format("<div class=\"column\"><p>%s</p></div>", value);
    }

}