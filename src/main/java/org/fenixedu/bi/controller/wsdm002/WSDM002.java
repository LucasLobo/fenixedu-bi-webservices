package org.fenixedu.bi.controller.wsdm002;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fenixedu.academic.domain.candidacy.IngressionType;
import org.fenixedu.bi.controller.wsdm002.mixins.IngressionTypeMixin;
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
@RequestMapping("/WS-DM-002")
public class WSDM002 {

    @GetMapping(value = "{externalIdParameter}")
    public ResponseEntity<?> execute(@PathVariable String externalIdParameter) {
        DomainObject domainObject = FenixFramework.getDomainObject(externalIdParameter);

        if ( !(domainObject instanceof IngressionType)) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND)
                    .body("ExternalId path parameter " + externalIdParameter + " returned no ingression type");
        }

        IngressionType ingressionType = (IngressionType) domainObject;

        ObjectMapper marshaller = getMarshaller();

        try {
            return ResponseEntity.ok(marshaller.writeValueAsString(ingressionType));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private ObjectMapper getMarshaller() {
        ObjectMapper marshaller = JacksonConfig.getObjectMapper();
        marshaller.addMixIn(IngressionType.class, IngressionTypeMixin.class);
        return marshaller;
    }

}