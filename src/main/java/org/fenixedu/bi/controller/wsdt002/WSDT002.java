package org.fenixedu.bi.controller.wsdt002;

import org.apache.commons.lang.StringUtils;
import org.fenixedu.academic.domain.ExecutionYear;
import org.fenixedu.academic.domain.candidacy.IngressionType;
import org.fenixedu.academic.domain.student.Registration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/WS-DT-002")
public class WSDT002 {

	private static final String DEGREE_CODE = "degreeCode";
	private static final String REGISTRATION_TYPE = "registrationType";
	private static final String INGRESSION_TYPE = "ingressionType";

	@GetMapping(value = "{executionYearParameter}/{curricularYearParameter}")
	public ResponseEntity<?> execute(@PathVariable String executionYearParameter,
									 @PathVariable Integer curricularYearParameter,
									 @RequestParam(value = REGISTRATION_TYPE, required = false) Integer registrationTypeParameter,
									 @RequestParam(value = INGRESSION_TYPE, required = false) String ingressionTypeParameter,
									 @RequestParam(value = DEGREE_CODE, required = false) String degreeCodeParameter) {

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

		final IngressionType ingressionType = IngressionType.findIngressionTypeByCode(ingressionTypeParameter).orElse(null);

		List<RegistrationWrapper> registrations = getRegistrations(executionYear,
															curricularYearParameter,
															registrationTypeParameter,
															ingressionType,
															degreeCodeParameter);

		return ResponseEntity.ok(registrations);
	}

	private List<RegistrationWrapper> getRegistrations(ExecutionYear executionYear,
												Integer curricularYear,
												Integer registrationType,
												IngressionType ingressionType,
												String degreeCode) {

		return executionYear.getStudentsSet().stream()

				// Filter based on Curricular Year if parameter is present
				.filter(registration ->
						curricularYear == null ||
								registration.getCurricularYear(executionYear) == curricularYear
						)

				// Filter based on Ingression Type if parameter is present
				.filter(registration ->
						ingressionType == null ||
								registration.getIngressionType().equals(ingressionType))

				// Filter based on Degree Code if parameter is present
				.filter(registration ->
						degreeCode == null ||
								(registration.getDegree() != null
										&& registration.getDegree().getCode().equals(degreeCode)))

				// Filter based on RegistrationType if parameter is present
				.filter(registration ->
						registrationType == null ||
								matchesRegistrationType(registration, registrationType)
				)

				// Filter to only include registrations which include enrolments
				// fixme enrolments in current executionYear or in any year?
				//.filter(Registration::hasAnyEnrolments)
				.filter(registration -> registration.hasAnyEnroledEnrolments(executionYear))

				.map(registration -> new RegistrationWrapper(registration, executionYear, registrationType))

				.collect(Collectors.toList());
	}

	private Boolean matchesRegistrationType(Registration registration, int registrationType) {
		return registrationType == 1 && registration.isFirstTime()
				|| registrationType == 2 && registration.getIngressionType().isReIngression() //fixme deprecated
				|| registrationType == 3 && !registration.isFirstTime();
	}
}
