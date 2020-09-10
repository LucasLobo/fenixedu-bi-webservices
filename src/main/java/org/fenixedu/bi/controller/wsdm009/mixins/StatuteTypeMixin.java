package org.fenixedu.bi.controller.wsdm009.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fenixedu.commons.i18n.LocalizedString;

public interface StatuteTypeMixin {

    @JsonProperty("active")
    String getActive();

    @JsonProperty("name")
    LocalizedString getName();

    @JsonProperty("code")
    String getCode();
}
