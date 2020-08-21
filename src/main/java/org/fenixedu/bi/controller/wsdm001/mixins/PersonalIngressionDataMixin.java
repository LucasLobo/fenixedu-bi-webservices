package org.fenixedu.bi.controller.wsdm001.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.fenixedu.academic.domain.SchoolLevelType;

public interface PersonalIngressionDataMixin {

	@JsonProperty
	@JsonUnwrapped(prefix = "father")
	SchoolLevelType getFatherSchoolLevel();

	@JsonProperty
	@JsonUnwrapped(prefix = "mother")
	SchoolLevelType getMotherSchoolLevel();
}
