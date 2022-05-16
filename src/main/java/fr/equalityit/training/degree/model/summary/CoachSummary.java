package fr.equalityit.training.degree.model.summary;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the aggregation of the {@link CoachDegreeSummary} of a {@link fr.equalityit.training.degree.model.Coach}
 */
public class CoachSummary {

    private final String coachLastname;

    private final String coachFirstname;

    private final List<CoachDegreeSummary> degrees;

    public CoachSummary(String coachLastname, String coachFirstname) {
        this.coachLastname = coachLastname;
        this.coachFirstname = coachFirstname;
        this.degrees = new ArrayList<>();
    }

    public String getCoachLastname() {
        return coachLastname;
    }

    public String getCoachFirstname() {
        return coachFirstname;
    }

    public List<CoachDegreeSummary> getDegrees() {
        return degrees;
    }

}
