package org.acme.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.github.javafaker.Faker;

import org.acme.model.Person;

@ApplicationScoped
public class PersonService {

    public static final int PAGE_SIZE = 10;
    private List<Person> persons = new ArrayList<>();

    public List<Person> getPersons(int pageIndex) {
        if (persons.size() < ((pageIndex + 1) * PAGE_SIZE)) {
            addPeople();
        }
        List<Person> result = new ArrayList<>();
        for (int i = pageIndex * PAGE_SIZE; i < ((pageIndex + 1) * PAGE_SIZE); i++) {
            result.add(persons.get(i));
        }
        return result;
    }

    private void addPeople() {
        Faker faker = new Faker();
        Person person = null;
        for (int i = 0; i < PAGE_SIZE; i++) {
            person = new Person(faker.idNumber().valid(), faker.name().firstName(), faker.name().lastName(),
                    faker.internet().emailAddress(), faker.phoneNumber().cellPhone(), faker.address().streetAddress(),
                    faker.address().city(), faker.address().state(), faker.address().zipCode());
            persons.add(person);
        }
    }

}
