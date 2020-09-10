package org.fenixedu.bi.controller.wsdm007.mixins;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.codehaus.jackson.annotate.JsonProperty;
import org.fenixedu.academic.domain.Person;

public interface ProfessorshipMixin {

    @JsonProperty
    @JsonUnwrapped
    Person getPerson();
}
