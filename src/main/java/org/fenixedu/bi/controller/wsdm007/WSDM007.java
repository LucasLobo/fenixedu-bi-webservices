package org.fenixedu.bi.controller.wsdm007;

import org.fenixedu.academic.domain.Professorship;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.FenixFramework;

@RestController
@RequestMapping("/WS-DM-007")
public class WSDM007 {

    @GetMapping(value = "{externalIdParameter}")
    public ResponseEntity<?> execute(@PathVariable String externalIdParameter) {
        DomainObject domainObject = FenixFramework.getDomainObject(externalIdParameter);

        if ( !(domainObject instanceof Professorship)) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND)
                    .body("ExternalId path parameter " + externalIdParameter + " returned no professorship");
        }

        Professorship professorship = (Professorship) domainObject;

        return ResponseEntity.ok(new ProfessorshipWrapper(professorship));

    }


}
