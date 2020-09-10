package org.fenixedu.bi.controller.wsdm007.mixins;

import org.codehaus.jackson.annotate.JsonProperty;
import org.fenixedu.academic.domain.person.IDDocumentType;

public interface PersonMixin {

    @JsonProperty("documentIdNumber")
    String getDocumentIdNumber();

    @JsonProperty("idDocumentType")
    IDDocumentType getIDDocumentType();

    @JsonProperty("name")
    String getName();

    @JsonProperty("externalId")
    String getExternalId();
}
