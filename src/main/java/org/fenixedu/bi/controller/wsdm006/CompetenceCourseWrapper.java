package org.fenixedu.bi.controller.wsdm006;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fenixedu.academic.domain.CompetenceCourse;
import org.fenixedu.academic.domain.CompetenceCourse_Base;
import pt.ist.fenixframework.core.AbstractDomainObject;

import java.util.Optional;

public class CompetenceCourseWrapper {

	@JsonProperty("code")
	public final String code;

	@JsonProperty("name")
	public final String name;

	@JsonProperty("ectsCredits")
	public final Double ectsCredits;

	@JsonProperty("externalId")
	public final String externalId;

	public CompetenceCourseWrapper(CompetenceCourse competenceCourse) {
		code = Optional.of(competenceCourse)
				.map(CompetenceCourse_Base::getCode)
				.orElse(null);

		name = Optional.of(competenceCourse)
				.map(CompetenceCourse::getName)
				.orElse(null);

		ectsCredits = Optional.of(competenceCourse)
				.map(CompetenceCourse::getEctsCredits)
				.orElse(null);

		externalId = Optional.of(competenceCourse)
				.map(AbstractDomainObject::getExternalId)
				.orElse(null);
	}
}
