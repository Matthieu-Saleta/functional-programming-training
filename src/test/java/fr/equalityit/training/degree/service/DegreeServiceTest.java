package fr.equalityit.training.degree.service;

import fr.equalityit.training.degree.model.Degree;
import fr.equalityit.training.degree.model.CoachDegree;
import fr.equalityit.training.degree.model.Coach;
import fr.equalityit.training.degree.model.DegreeType;
import fr.equalityit.training.degree.model.summary.CoachDegreeSummary;
import fr.equalityit.training.degree.model.summary.CoachSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DegreeServiceTest {

    private DegreeService degreeService;

    private final List<Coach> coaches = new ArrayList<>();

    @BeforeEach
    void setUp() {

        this.degreeService = new DegreeService();

        final Coach coach1 = new Coach("Mirounho", "Jose");
        coach1.addDegree(new CoachDegree(Degree.DEGREE_1, DegreeType.SUCCESS, LocalDate.of(1995, 1, 25)));
        coach1.addDegree(new CoachDegree(Degree.DEGREE_2, DegreeType.SUCCESS, LocalDate.of(1996, 9, 13)));
        coach1.addDegree(new CoachDegree(Degree.DEGREE_3, DegreeType.SUCCESS, LocalDate.of(2000, 6, 5)));
        coach1.addDegree(new CoachDegree(Degree.DEGREE_1, DegreeType.REVISION, LocalDate.of(1998, 2, 18)));
        coach1.addDegree(new CoachDegree(Degree.DEGREE_2, DegreeType.REVISION, LocalDate.of(1999, 12, 12)));
        coach1.addDegree(new CoachDegree(Degree.DEGREE_2, DegreeType.REVISION, LocalDate.of(2002, 4, 10)));
        coach1.addDegree(new CoachDegree(Degree.DEGREE_3, DegreeType.REVISION, LocalDate.of(2005, 3, 22)));

        final Coach coach2 = new Coach("Naoh", "Yannick");
        coach2.addDegree(new CoachDegree(Degree.DEGREE_1, DegreeType.SUCCESS, LocalDate.of(1987, 10, 14)));
        coach2.addDegree(new CoachDegree(Degree.DEGREE_2, DegreeType.SUCCESS, LocalDate.of(1988, 11, 2)));
        coach2.addDegree(new CoachDegree(Degree.DEGREE_3, DegreeType.SUCCESS, LocalDate.of(1990, 7, 31)));
        coach2.addDegree(new CoachDegree(Degree.DEGREE_1, DegreeType.REVISION, LocalDate.of(1991, 3, 15)));
        coach2.addDegree(new CoachDegree(Degree.DEGREE_1, DegreeType.REVISION, LocalDate.of(1993, 8, 26)));
        coach2.addDegree(new CoachDegree(Degree.DEGREE_2, DegreeType.REVISION, LocalDate.of(1992, 5, 17)));
        coach2.addDegree(new CoachDegree(Degree.DEGREE_2, DegreeType.REVISION, LocalDate.of(1995, 3, 12)));
        coach2.addDegree(new CoachDegree(Degree.DEGREE_3, DegreeType.REVISION, LocalDate.of(1996, 10, 16)));
        coach2.addDegree(new CoachDegree(Degree.DEGREE_3, DegreeType.REVISION, LocalDate.of(2001, 12, 4)));

        this.coaches.add(coach1);
        this.coaches.add(coach2);
    }

    @Test
    void shouldComputeCoachSummary_ImperativeProgramming() {

        final List<CoachSummary> coachSummaries = this.degreeService.computeCoachSummaryImperative(this.coaches);

        this.verifySummary(coachSummaries);
    }

    @Test
    void shouldComputeCoachSummary_Functional() {

        final List<CoachSummary> coachSummaries = this.degreeService.computeCoachSummaryFunctional(this.coaches);

        this.verifySummary(coachSummaries);
    }

    @Test
    void shouldComputeCoachSummary_FunctionalCollectors() {

        final List<CoachSummary> coachSummaries = this.degreeService.computeCoachSummaryFunctionalCollector(this.coaches);

        this.verifySummary(coachSummaries);
    }

    private void verifySummary(List<CoachSummary> coachSummaries) {

        assertThat(coachSummaries).isNotNull();
        assertThat(coachSummaries.size()).isEqualTo(2);

        final CoachSummary coachSummary1 = coachSummaries.get(0);
        assertThat(coachSummary1).isNotNull();
        assertThat(coachSummary1.getCoachLastname()).isEqualTo("Mirounho");
        assertThat(coachSummary1.getCoachFirstname()).isEqualTo("Jose");

        final List<CoachDegreeSummary> coachDegreeSummaries1 = coachSummary1.getDegrees();
        assertThat(coachDegreeSummaries1).hasSize(3);
        assertThat(coachDegreeSummaries1.get(0).getDegree()).isEqualTo(Degree.DEGREE_1);
        assertThat(coachDegreeSummaries1.get(0).getSuccessDate()).isEqualTo(LocalDate.of(1995, 1, 25));
        assertThat(coachDegreeSummaries1.get(0).getRevisionDate()).isEqualTo(LocalDate.of(1998, 2, 18));

        assertThat(coachDegreeSummaries1.get(1).getDegree()).isEqualTo(Degree.DEGREE_2);
        assertThat(coachDegreeSummaries1.get(1).getSuccessDate()).isEqualTo(LocalDate.of(1996, 9, 13));
        assertThat(coachDegreeSummaries1.get(1).getRevisionDate()).isEqualTo(LocalDate.of(2002, 4, 10));

        assertThat(coachDegreeSummaries1.get(2).getDegree()).isEqualTo(Degree.DEGREE_3);
        assertThat(coachDegreeSummaries1.get(2).getSuccessDate()).isEqualTo(LocalDate.of(2000, 6, 5));
        assertThat(coachDegreeSummaries1.get(2).getRevisionDate()).isEqualTo(LocalDate.of(2005, 3, 22));

        final CoachSummary coachSummary2 = coachSummaries.get(1);
        assertThat(coachSummary2).isNotNull();
        assertThat(coachSummary2.getCoachLastname()).isEqualTo("Naoh");
        assertThat(coachSummary2.getCoachFirstname()).isEqualTo("Yannick");

        final List<CoachDegreeSummary> coachDegreeSummaries2 = coachSummary2.getDegrees();
        assertThat(coachDegreeSummaries2).hasSize(3);
        assertThat(coachDegreeSummaries2.get(0).getDegree()).isEqualTo(Degree.DEGREE_1);
        assertThat(coachDegreeSummaries2.get(0).getSuccessDate()).isEqualTo(LocalDate.of(1987, 10, 14));
        assertThat(coachDegreeSummaries2.get(0).getRevisionDate()).isEqualTo(LocalDate.of(1993, 8, 26));

        assertThat(coachDegreeSummaries2.get(1).getDegree()).isEqualTo(Degree.DEGREE_2);
        assertThat(coachDegreeSummaries2.get(1).getSuccessDate()).isEqualTo(LocalDate.of(1988, 11, 2));
        assertThat(coachDegreeSummaries2.get(1).getRevisionDate()).isEqualTo(LocalDate.of(1995, 3, 12));

        assertThat(coachDegreeSummaries2.get(2).getDegree()).isEqualTo(Degree.DEGREE_3);
        assertThat(coachDegreeSummaries2.get(2).getSuccessDate()).isEqualTo(LocalDate.of(1990, 7, 31));
        assertThat(coachDegreeSummaries2.get(2).getRevisionDate()).isEqualTo(LocalDate.of(2001, 12, 4));
    }

}
