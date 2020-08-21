package org.fenixedu.bi.controller.wsdm001.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface SchoolLevelTypeMixin {

	// fixme might be redundant
	@JsonProperty("SchoolLevel")
	String getName();
}
