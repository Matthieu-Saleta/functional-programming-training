package fr.equalityit.training.degree.service;

import fr.equalityit.training.degree.model.Coach;
import fr.equalityit.training.degree.model.CoachDegree;
import fr.equalityit.training.degree.model.Degree;
import fr.equalityit.training.degree.model.DegreeType;
import fr.equalityit.training.degree.model.summary.CoachDegreeSummary;
import fr.equalityit.training.degree.model.summary.CoachSummary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service that can compute the {@link CoachSummary}.
 */
public class DegreeService {

	public List<CoachSummary> computeCoachSummaryImperative(List<Coach> coaches) {

		List<CoachSummary> coachSummaries = new ArrayList<>();

		for (Coach coach : coaches) {

			CoachSummary coachSummary = new CoachSummary(coach.getLastname(), coach.getFirstname());

			Map<Degree, CoachDegreeSummary> coachDegreeSummaryMap = new LinkedHashMap<>();
			for (CoachDegree coachDegree : coach.getDegrees()) {
				CoachDegreeSummary coachDegreeSummary = coachDegreeSummaryMap.get(coachDegree.degree());
				if (coachDegreeSummary == null) {
					coachDegreeSummary = new CoachDegreeSummary();
					coachDegreeSummary.setDegree(coachDegree.degree());
				}

				if (coachDegree.degreeType() == DegreeType.SUCCESS) {
					coachDegreeSummary.setSuccessDate(coachDegree.obtainingDate());
				} else {
					LocalDate revisionDate = coachDegreeSummary.getRevisionDate();

					if (revisionDate == null || revisionDate.isBefore(coachDegree.obtainingDate())) {
						coachDegreeSummary.setRevisionDate(coachDegree.obtainingDate());
					}
				}

				coachDegreeSummaryMap.put(coachDegree.degree(), coachDegreeSummary);
			}

			coachSummary.getDegrees()
						.addAll(coachDegreeSummaryMap.values());
			coachSummaries.add(coachSummary);
		}

		return coachSummaries;
	}

	public List<CoachSummary> computeCoachSummaryFunctional(List<Coach> coaches) {

		return coaches.stream()
					  .map(c -> {
						  CoachSummary coachSummary = new CoachSummary(c.getLastname(), c.getFirstname());
						  coachSummary.getDegrees()
									  .addAll(this.computeCoachDegreeSummaries(c.getDegrees()));
						  return coachSummary;
					  })
					  .toList();
	}

	private List<CoachDegreeSummary> computeCoachDegreeSummaries(List<CoachDegree> coachDegrees) {

		return Arrays.stream(Degree.values())
					 .map(d -> coachDegrees.stream()
										   .filter(de -> de.degree() == d)
										   .map(this::mapCoachDegreeToCoachDegreeSummary)
										   .reduce(new CoachDegreeSummary(), this::reduceCoachDegreeSummary))
					 .toList();

	}

	private CoachDegreeSummary mapCoachDegreeToCoachDegreeSummary(CoachDegree coachDegree) {

		CoachDegreeSummary coachDegreeSummary = new CoachDegreeSummary();
		coachDegreeSummary.setDegree(coachDegree.degree());

		if (coachDegree.degreeType() == DegreeType.SUCCESS) {
			coachDegreeSummary.setSuccessDate(coachDegree.obtainingDate());
		} else {
			coachDegreeSummary.setRevisionDate(coachDegree.obtainingDate());
		}

		return coachDegreeSummary;
	}

	private CoachDegreeSummary reduceCoachDegreeSummary(CoachDegreeSummary r1, CoachDegreeSummary r2) {

		CoachDegreeSummary coachDegreeSummary = new CoachDegreeSummary();
		coachDegreeSummary.setDegree(Optional.ofNullable(r1.getDegree())
											 .orElse(r2.getDegree()));
		coachDegreeSummary.setSuccessDate(Optional.ofNullable(r1.getSuccessDate())
												  .orElse(r2.getSuccessDate()));
		coachDegreeSummary.setRevisionDate(Stream.of(r1.getRevisionDate(), r2.getRevisionDate())
												 .filter(Objects::nonNull)
												 .max(LocalDate::compareTo)
												 .orElse(null));

		return coachDegreeSummary;
	}

	public List<CoachSummary> computeCoachSummaryFunctionalCollector(List<Coach> coaches) {

		return coaches.stream()
					  .map(c -> {
						  CoachSummary coachSummary = new CoachSummary(c.getLastname(), c.getFirstname());
						  coachSummary.getDegrees()
									  .addAll(this.computeCoachDegreeSummaryCollector(c.getDegrees()));
						  return coachSummary;
					  })
					  .collect(Collectors.toList());
	}

	private Collection<CoachDegreeSummary> computeCoachDegreeSummaryCollector(List<CoachDegree> coachDegrees) {

		return coachDegrees.stream()
						   .collect(Collectors.groupingBy(CoachDegree::degree,
							   LinkedHashMap::new,
							   Collectors.reducing(new CoachDegreeSummary(),
								   this::mapCoachDegreeToCoachDegreeSummary,
								   this::reduceCoachDegreeSummary)))
						   .values();

	}

}
