package fr.equalityit.training.degree.model;

import java.time.LocalDate;

/**
 * Represents the {@link Degree} that can be obtained by a {@link Coach}.
 * It is composed of the {@link Degree} that can be a {@link DegreeType#SUCCESS} or a {@link DegreeType#REVISION} {@link Degree} and the date the coach has obtained it.
 */
public record CoachDegree(Degree degree, DegreeType degreeType, LocalDate obtainingDate) {

}
