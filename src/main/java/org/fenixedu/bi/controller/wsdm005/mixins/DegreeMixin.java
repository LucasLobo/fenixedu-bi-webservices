package org.fenixedu.bi.controller.wsdm005.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.fenixedu.academic.domain.DegreeCurricularPlan;

public interface DegreeMixin {

    @JsonProperty("code")
    String getCode();

    @JsonProperty("ministryCode")
    String getMinistryCode();

    @JsonProperty("presentationName")
    String getPresentationName();

    @JsonProperty("degreeTypeName")
    String getDegreeTypeName();

    @JsonProperty("prevailingScientificArea")
    String getPrevailingScientificArea();

    @JsonProperty
    @JsonUnwrapped(prefix = "mostRecentDegreeCurricularPlan.")
    DegreeCurricularPlan getMostRecentDegreeCurricularPlan();

    @JsonProperty("ectsCredits")
    Double getEctsCredits();
}
