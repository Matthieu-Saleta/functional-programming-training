package fr.equalityit.training.degree.model.summary;

import fr.equalityit.training.degree.model.Degree;

import java.time.LocalDate;

/**
 * Represents the summary of a {@link Degree} for a {@link fr.equalityit.training.degree.model.Coach}.
 * It is composed of the {@link Degree}, its success date (i.e. The first time the coach has obtained this degree) and its last revision date.
 */
public class CoachDegreeSummary {

    private Degree degree;

    private LocalDate successDate;

    private LocalDate revisionDate;

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public LocalDate getSuccessDate() {
        return successDate;
    }

    public void setSuccessDate(LocalDate successDate) {
        this.successDate = successDate;
    }

    public LocalDate getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(LocalDate revisionDate) {
        this.revisionDate = revisionDate;
    }

}
