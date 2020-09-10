package org.fenixedu.bi.controller.wsdm005.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface DegreeCurricularPlanMixin {

    @JsonProperty("durationInYears")
    int getDurationInYears();
}
