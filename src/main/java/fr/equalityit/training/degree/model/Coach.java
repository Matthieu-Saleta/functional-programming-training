package fr.equalityit.training.degree.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the coach identified by its lastname and its firstname and who can obtain {@link CoachDegree}.
 */
public class Coach {

    private final String lastname;

    private final String firstname;

    private final List<CoachDegree> degrees;

    public Coach(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.degrees = new ArrayList<>();
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public List<CoachDegree> getDegrees() {
        return this.degrees;
    }

    public void addDegree(CoachDegree degree) {
        this.degrees.add(degree);
    }

}
