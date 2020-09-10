package org.fenixedu.bi.controller.wsdt001;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fenixedu.academic.domain.*;
import org.fenixedu.academic.domain.candidacy.*;

import java.util.Optional;

public class StudentCandidacyWrapper {

	@JsonProperty("executionYear.name")
	public final String executionYearName;

	@JsonProperty("ingressionType.code")
	public final String ingressionTypeCode;

	@JsonProperty("name")
	public final String name;

	@JsonProperty("placingOption")
	public final Integer placingOption;

	@JsonProperty("degree.code")
	public final String degreeCode;

	@JsonProperty("entryGrade")
	public final Double entryGrade;

	@JsonProperty("contigent")
	public final String contigent;

	@JsonProperty("registered")
	public final String registered;

	@JsonProperty("registration.studentCandidacy.entryPhase")
	public final String entryPhase;

	public StudentCandidacyWrapper(StudentCandidacy candidacy) {

		executionYearName = Optional.of(candidacy)
				.map(StudentCandidacy::getExecutionYear)
				.map(ExecutionInterval_Base::getName)
				.orElse(null);

		ingressionTypeCode = Optional.of(candidacy)
				.map(StudentCandidacy_Base::getIngressionType)
				.map(IngressionType_Base::getCode)
				.orElse(null);

		name = Optional.of(candidacy)
				.map(Candidacy_Base::getPerson)
				.map(Person::getName)
				.orElse(null);

		placingOption = Optional.of(candidacy)
				.map(StudentCandidacy_Base::getPlacingOption)
				.orElse(null);

		degreeCode = Optional.of(candidacy)
				.map(StudentCandidacy_Base::getExecutionDegree)
				.map(ExecutionDegree::getDegree)
				.map(Degree_Base::getCode)
				.orElse(null);

		entryGrade = Optional.of(candidacy)
				.map(StudentCandidacy_Base::getEntryGrade)
				.orElse(null);

		contigent = Optional.of(candidacy)
				.map(StudentCandidacy_Base::getContigent)
				.orElse(null);

		registered = Optional.of(candidacy)
				.map(Candidacy::getActiveCandidacySituationType)
				.map(CandidacySituationType::getFullyQualifiedName)
				.orElse(null); //fixme probably wrong

		entryPhase = Optional.of(candidacy)
				.map(StudentCandidacy_Base::getEntryPhase)
				.map(EntryPhase::getFullyQualifiedName)
				.orElse(null);
	}
}
