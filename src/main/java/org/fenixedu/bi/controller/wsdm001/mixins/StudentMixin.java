package org.fenixedu.bi.controller.wsdm001.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.fenixedu.academic.domain.student.PersonalIngressionData;

public interface StudentMixin {

	@JsonProperty("number")
	Integer getNumber();

	@JsonProperty
	@JsonUnwrapped(prefix = "latestPersonalIngressionData.")
	PersonalIngressionData getLatestPersonalIngressionData();
}
