package org.fenixedu.bi.controller.wsdm005;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fenixedu.academic.domain.Degree;
import org.fenixedu.academic.domain.DegreeCurricularPlan;
import org.fenixedu.academic.domain.Degree_Base;
import pt.ist.fenixframework.core.AbstractDomainObject;

import java.util.Optional;

public class DegreeWrapper {

	@JsonProperty("code")
	public final String code;

	@JsonProperty("ministryCode")
	public final String ministryCode;

	@JsonProperty("presentationName")
	public final String presentationName;

	@JsonProperty("degreeTypeName")
	public final String degreeTypeName;

	@JsonProperty("prevailingScientificArea")
	public final String prevailingScientificArea;

	@JsonProperty("durationInYears")
	public final Integer durationInYears;

	@JsonProperty("ectsCredits")
	public final Double ectsCredits;

	@JsonProperty("externalId")
	public final String externalId;

	public DegreeWrapper(Degree degree) {
		code = Optional.of(degree)
				.map(Degree_Base::getCode)
				.orElse(null);

		ministryCode = Optional.of(degree)
				.map(Degree::getMinistryCode)
				.orElse(null);

		presentationName = Optional.of(degree)
				.map(Degree::getPresentationName)
				.orElse(null);

		degreeTypeName = Optional.of(degree)
				.map(Degree::getDegreeTypeName)
				.orElse(null);

		prevailingScientificArea = Optional.of(degree)
				.map(Degree_Base::getPrevailingScientificArea)
				.orElse(null);

		durationInYears = Optional.of(degree)
				.map(Degree::getMostRecentDegreeCurricularPlan)
				.map(DegreeCurricularPlan::getDurationInYears)
				.orElse(null);

		ectsCredits = Optional.of(degree)
				.map(Degree::getEctsCredits)
				.orElse(null);

		externalId = Optional.of(degree)
				.map(AbstractDomainObject::getExternalId)
				.orElse(null);
	}
}
