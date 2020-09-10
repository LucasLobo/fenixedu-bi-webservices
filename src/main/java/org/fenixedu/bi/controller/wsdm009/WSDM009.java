package org.fenixedu.bi.controller.wsdm009;

import org.fenixedu.academic.domain.student.StudentStatute;
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

        final StudentStatute studentStatute = (StudentStatute) domainObject;

        return ResponseEntity.ok(new StudentStatuteWrapper(studentStatute));
    }

}