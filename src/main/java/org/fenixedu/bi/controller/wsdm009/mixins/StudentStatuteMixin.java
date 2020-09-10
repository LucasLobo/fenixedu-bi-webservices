package org.fenixedu.bi.controller.wsdm009.mixins;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.codehaus.jackson.annotate.JsonProperty;
import org.fenixedu.academic.domain.student.StatuteType;

public interface StudentStatuteMixin {

    @JsonProperty("externalId")
    String getExternalId();

    @JsonProperty
    @JsonUnwrapped
    StatuteType getType();
}
