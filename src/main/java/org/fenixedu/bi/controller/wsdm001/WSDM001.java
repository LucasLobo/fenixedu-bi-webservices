package org.fenixedu.bi.controller.wsdm001;

import org.apache.commons.lang.StringUtils;
import org.fenixedu.academic.domain.ExecutionYear;
import org.fenixedu.academic.domain.candidacy.CandidacySituationType;
import org.fenixedu.academic.domain.student.Registration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/WS-DM-001")
public class WSDM001 {

	@GetMapping(value = "{executionYearParameter}")
	public ResponseEntity<?> execute(@PathVariable String executionYearParameter) {
		System.out.println(executionYearParameter);

		if (StringUtils.isEmpty(executionYearParameter)) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("ExecutionYear path parameter is required");
		}

		final ExecutionYear executionYear = ExecutionYear.readExecutionYearByName(executionYearParameter);
		if (executionYear == null) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND)
					.body("ExecutionYear path parameter " + executionYearParameter + " returned no execution year");
		}

		final Set<PersonWrapper> personWrapperSet = getPersons(executionYear);

		return ResponseEntity.ok(personWrapperSet);
	}

	private Set<PersonWrapper> getPersons(ExecutionYear executionYear) {
		return executionYear.getStudentsSet().stream()

				.filter(registration ->
						registration.getStudentCandidacy() != null
								&& registration.getStudentCandidacy().getActiveCandidacySituationType().equals(CandidacySituationType.REGISTERED))

				.map(Registration::getPerson)
				.map(PersonWrapper::new)

				.collect(Collectors.toSet());
	}
}
