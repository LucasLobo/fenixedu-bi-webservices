package org.fenixedu.bi.controller.wsdm006.mixins;

import org.codehaus.jackson.annotate.JsonProperty;
import org.fenixedu.commons.i18n.LocalizedString;

public interface CompetenceCourseMixin {

    @JsonProperty("code")
    String getCode();

    @JsonProperty("name")
    LocalizedString getName();

    @JsonProperty("ectsCredits")
    double getEctsCredits();

    @JsonProperty("externalId")
    String getExternalId();
}
