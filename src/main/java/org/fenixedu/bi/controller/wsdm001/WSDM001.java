package org.fenixedu.bi.controller.wsdm001;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.fenixedu.academic.domain.ExecutionYear;
import org.fenixedu.academic.domain.Person;
import org.fenixedu.academic.domain.SchoolLevelType;
import org.fenixedu.academic.domain.candidacy.CandidacySituationType;
import org.fenixedu.academic.domain.student.PersonalIngressionData;
import org.fenixedu.academic.domain.student.Registration;
import org.fenixedu.academic.domain.student.Student;
import org.fenixedu.academic.domain.studentCurriculum.CurriculumModule;
import org.fenixedu.bi.controller.wsdm001.mixins.PersonMixin;
import org.fenixedu.bi.controller.wsdm001.mixins.PersonalIngressionDataMixin;
import org.fenixedu.bi.controller.wsdm001.mixins.SchoolLevelTypeMixin;
import org.fenixedu.bi.controller.wsdm001.mixins.StudentMixin;
import org.fenixedu.bi.utils.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/WS-DM-001")
public class WSDM001 {

	@GetMapping(value = "{executionYearParameter}")
	public ResponseEntity<?> execute(@PathVariable String executionYearParameter) {
		Set<Person> people = new HashSet<>();
		System.out.println(executionYearParameter);

		if (StringUtils.isEmpty(executionYearParameter)) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("ExecutionYear path parameter is required");
		}

		ExecutionYear executionYear = ExecutionYear.readExecutionYearByName(executionYearParameter);
		if (executionYear == null) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND)
					.body("ExecutionYear path parameter " + executionYearParameter + " returned no execution year");
		}

		Set<Registration> collect = executionYear.getExecutionPeriodsSet().stream()
				.flatMap(executionSemester -> executionSemester.getEnrolmentsSet().stream())
				.map(CurriculumModule::getRegistration)
				.collect(Collectors.toSet());

		collect.addAll(
				executionYear.getStudentsSet().stream()
						.filter(registration -> registration.getStudentCandidacy() != null
								&& registration.getStudentCandidacy().getActiveCandidacySituationType() == CandidacySituationType.REGISTERED)
						.collect(Collectors.toSet()));


		ObjectMapper marshaller = getMarshaller();

		for (Registration registration : collect) {
			people.add(registration.getPerson());
		}
		try {
			return ResponseEntity.ok(marshaller.writeValueAsString(people));
		} catch (JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private ObjectMapper getMarshaller() {
		ObjectMapper marshaller = JacksonConfig.getObjectMapper();
		marshaller.addMixIn(Person.class, PersonMixin.class);
		marshaller.addMixIn(Student.class, StudentMixin.class);
		marshaller.addMixIn(PersonalIngressionData.class, PersonalIngressionDataMixin.class);
		marshaller.addMixIn(SchoolLevelType.class, SchoolLevelTypeMixin.class); // fixme might be redundant
		return marshaller;
	}
}
