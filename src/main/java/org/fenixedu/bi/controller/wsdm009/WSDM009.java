package org.fenixedu.bi.controller.wsdm009;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fenixedu.academic.domain.student.StatuteType;
import org.fenixedu.academic.domain.student.StudentStatute;
import org.fenixedu.bi.controller.wsdm009.mixins.StatuteTypeMixin;
import org.fenixedu.bi.controller.wsdm009.mixins.StudentStatuteMixin;
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
@RequestMapping("/WS-DM-009")
public class WSDM009 {

    @GetMapping(value = "{externalIdParameter}")
    public ResponseEntity<?> execute(@PathVariable String externalIdParameter) {
        DomainObject domainObject = FenixFramework.getDomainObject(externalIdParameter);

        if ( !(domainObject instanceof StudentStatute)) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND)
                    .body("ExternalId path parameter " + externalIdParameter + " returned no student statute");
        }

        StudentStatute studentStatute = (StudentStatute) domainObject;

        ObjectMapper marshaller = getMarshaller();

        try {
            return ResponseEntity.ok(marshaller.writeValueAsString(studentStatute));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private ObjectMapper getMarshaller() {
        ObjectMapper marshaller = JacksonConfig.getObjectMapper();
        marshaller.addMixIn(StudentStatute.class, StudentStatuteMixin.class);
        marshaller.addMixIn(StatuteType.class, StatuteTypeMixin.class);
        return marshaller;
    }

}