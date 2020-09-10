package org.fenixedu.bi.controller.wsdm002.mixins;

import org.codehaus.jackson.annotate.JsonProperty;
import org.fenixedu.commons.i18n.LocalizedString;

public interface IngressionTypeMixin {

    @JsonProperty("code")
    String getCode();

    @JsonProperty("description")
    LocalizedString getDescription();

    @JsonProperty("externalId")
    String getExternalId();
}
