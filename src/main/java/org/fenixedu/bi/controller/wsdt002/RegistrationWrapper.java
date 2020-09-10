package org.fenixedu.bi.controller.wsdt002;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fenixedu.academic.domain.*;
import org.fenixedu.academic.domain.candidacy.IngressionType;
import org.fenixedu.academic.domain.candidacy.IngressionType_Base;
import org.fenixedu.academic.domain.candidacy.StudentCandidacy_Base;
import org.fenixedu.academic.domain.student.Registration;
import org.fenixedu.academic.domain.student.RegistrationProtocol_Base;
import org.fenixedu.academic.domain.student.RegistrationRegimeType;
import org.fenixedu.academic.domain.student.Registration_Base;
import org.fenixedu.commons.i18n.LocalizedString;
import org.joda.time.YearMonthDay;

import java.util.Optional;

public class RegistrationWrapper {

	@JsonProperty("executionYear.name")
	public final String executionYearName;

	@JsonProperty("curricularYear")
	public final Integer curricularYear;

	@JsonProperty("enrollmentDate")
	public final String enrollmentDate;

	@JsonProperty("ingressionType.code")
	public final String ingressionTypeCode;

	@JsonProperty("ingressionType.localizedName")
	public final String ingressionTypeLocalizedName;

	@JsonProperty("registration.registrationProtocol.code")
	public final String registrationProtocolCode;

	@JsonProperty("registration.registrationProtocol.description")
	public final String registrationProtocolDescription;

	// todo statuteTypes name

	// todo statuteTypes code

	@JsonProperty("regimeType")
	public final String regimeType;

	@JsonProperty("registration.studentCandidacy.entryPhase")
	public final String entryPhase;

	@JsonProperty("registrationType")
	public final Integer registrationType;

	@JsonProperty("registrationNumber")
	public final Integer registrationNumber;

	@JsonProperty("name")
	public final String name;

	@JsonProperty("degree.code")
	public final String degreeCode;

	@JsonProperty("degree.presentationName")
	public final String degreePresentationName;

	public RegistrationWrapper(Registration registration, ExecutionYear executionYear, Integer registrationTypeParameter) {

		executionYearName = Optional.of(executionYear)
				.map(ExecutionInterval_Base::getName)
				.orElse(null);

		curricularYear = Optional.of(registration)
				.map(r -> r.getCurricularYear(executionYear))
				.orElse(null);

		enrollmentDate = Optional.of(registration)
				.map(Registration::getStartDate)
				.map(YearMonthDay::toString)
				.map(date -> date.replace("-", ""))
				.orElse(null);

		ingressionTypeCode = Optional.of(registration)
				.map(Registration_Base::getIngressionType)
				.map(IngressionType_Base::getCode).orElse(null);

		ingressionTypeLocalizedName= Optional.of(registration)
				.map(Registration_Base::getIngressionType)
				.map(IngressionType::getLocalizedName).orElse(null);

		registrationProtocolCode = Optional.of(registration)
				.map(Registration_Base::getRegistrationProtocol)
				.map(RegistrationProtocol_Base::getCode)
				.orElse(null);

		registrationProtocolDescription = Optional.of(registration)
				.map(Registration_Base::getRegistrationProtocol)
				.map(RegistrationProtocol_Base::getDescription)
				.map(LocalizedString::getContent)
				.orElse(null);

		regimeType = Optional.of(registration)
				.map(r -> r.getRegimeType(executionYear))
				.map(RegistrationRegimeType::getLocalizedName)
				.orElse(null);

		entryPhase = Optional.of(registration)
				.map(Registration_Base::getStudentCandidacy)
				.map(StudentCandidacy_Base::getEntryPhase)
				.map(EntryPhase::getLocalizedName)
				.orElse(null);

		registrationType = registrationTypeParameter;

		registrationNumber = Optional.of(registration)
				.map(Registration::getNumber)
				.orElse(null);

		name = Optional.of(registration)
				.map(Registration::getName)
				.orElse(null);

		degreeCode = Optional.of(registration)
				.map(Registration::getDegree)
				.map(Degree_Base::getCode)
				.orElse(null);

		degreePresentationName = Optional.of(registration)
				.map(Registration::getDegree)
				.map(Degree::getPresentationName)
				.orElse(null);

	}
}
