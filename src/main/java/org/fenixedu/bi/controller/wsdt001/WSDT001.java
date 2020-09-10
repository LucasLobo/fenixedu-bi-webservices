package org.fenixedu.bi.controller.wsdt001;

import org.apache.commons.lang.StringUtils;
import org.fenixedu.academic.domain.*;
import org.fenixedu.academic.domain.candidacy.IngressionType;
import org.fenixedu.academic.domain.candidacy.StudentCandidacy_Base;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/WS-DT-001")
public class WSDT001 {

	private static final String DEGREE_CODE = "degreeCode";
	private static final String PLACING_OPTION = "placingOption";
	private static final String INGRESSION_TYPE = "ingressionType";

	@GetMapping(value = "{executionYearParameter}")
	public ResponseEntity<?> execute(@PathVariable String executionYearParameter,
									 @RequestParam(value = PLACING_OPTION, required = false) Integer placingOptionParameter,
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

		final List<StudentCandidacyWrapper> candidacies = getCandidacies(executionYear,
																		 placingOptionParameter,
																		 ingressionType,
																		 degreeCodeParameter);

		return ResponseEntity.ok(candidacies);

	}

	private List<StudentCandidacyWrapper> getCandidacies(ExecutionYear executionYear,
												  Integer placingOption,
												  IngressionType ingressionType,
												  String degreeCode) {

		return executionYear.getStudentCandidacies().stream()

				// Only keep first time candidacies
				.filter(StudentCandidacy_Base::getFirstTimeCandidacy)

				// Filter based on Placing Option if parameter is present
				.filter(candidacy ->
						placingOption == null ||
								candidacy.getPlacingOption().equals(placingOption))

				// Filter based on Ingression Type if parameter is present
				.filter(candidacy ->
						ingressionType == null ||
								candidacy.getIngressionType().equals(ingressionType))

				// Filter based on Degree Code if parameter is present
				.filter(candidacy ->
						degreeCode == null ||
								(candidacy.getExecutionDegree().getDegree() != null
										&& candidacy.getExecutionDegree().getDegree().getCode().equals(degreeCode)))

				.map(StudentCandidacyWrapper::new)

				.collect(Collectors.toList());
	}
}
