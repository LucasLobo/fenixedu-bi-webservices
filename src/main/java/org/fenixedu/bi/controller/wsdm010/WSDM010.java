package org.fenixedu.bi.controller.wsdm010;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fenixedu.academic.domain.ExecutionSemester;
import org.fenixedu.bi.controller.wsdm010.mixins.ExecutionSemesterMixin;
import org.fenixedu.bi.utils.JacksonConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.FenixFramework;

@RestController
@RequestMapping("/WS-DM-010")
public class WSDM010 {

    @GetMapping(value = "{externalIdParameter}")
    public ResponseEntity<?> execute(@PathVariable String externalIdParameter) {
        DomainObject domainObject = FenixFramework.getDomainObject(externalIdParameter);

        if ( !(domainObject instanceof ExecutionSemester)) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND)
                    .body("ExternalId path parameter " + externalIdParameter + " returned no execution semester");
        }

        ExecutionSemester executionSemester = (ExecutionSemester) domainObject;

        ObjectMapper marshaller = getMarshaller();

        try {
            return ResponseEntity.ok(marshaller.writeValueAsString(executionSemester));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private ObjectMapper getMarshaller() {
        ObjectMapper marshaller = JacksonConfig.getObjectMapper();
        marshaller.addMixIn(ExecutionSemester.class, ExecutionSemesterMixin.class);
        return marshaller;
    }

}
