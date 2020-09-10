package org.fenixedu.bi.controller.wsdm003;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fenixedu.academic.domain.student.RegistrationProtocol;
import org.fenixedu.bi.controller.wsdm003.mixins.RegistrationProtocolMixin;
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
@RequestMapping("/WS-DM-003")
public class WSDM003 {

    @GetMapping(value = "{externalIdParameter}")
    public ResponseEntity<?> execute(@PathVariable String externalIdParameter) {
        DomainObject domainObject = FenixFramework.getDomainObject(externalIdParameter);

        if ( !(domainObject instanceof RegistrationProtocol)) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND)
                    .body("ExternalId path parameter " + externalIdParameter + " returned no registration protocol");
        }

        RegistrationProtocol registrationProtocol = (RegistrationProtocol) domainObject;

        ObjectMapper marshaller = getMarshaller();

        try {
            return ResponseEntity.ok(marshaller.writeValueAsString(registrationProtocol));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private ObjectMapper getMarshaller() {
        ObjectMapper marshaller = JacksonConfig.getObjectMapper();
        marshaller.addMixIn(RegistrationProtocol.class, RegistrationProtocolMixin.class);
        return marshaller;
    }

}