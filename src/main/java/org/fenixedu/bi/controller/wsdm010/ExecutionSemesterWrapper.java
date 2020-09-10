package org.fenixedu.bi.controller.wsdm010;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fenixedu.academic.domain.ExecutionInterval_Base;
import org.fenixedu.academic.domain.ExecutionSemester;
import org.joda.time.YearMonthDay;

import java.util.Optional;

public class ExecutionSemesterWrapper {

	@JsonProperty("year")
	public final String year;

	@JsonProperty("semester")
	public final Integer semester;

	@JsonProperty("beginDateYearMonthDay")
	public final String beginDateYearMonthDay;

	@JsonProperty("endDateYearMonthDay")
	public final String endDateYearMonthDay;

	public ExecutionSemesterWrapper(ExecutionSemester executionSemester) {
		year = Optional.of(executionSemester)
				.map(ExecutionSemester::getYear)
				.orElse(null);

		semester = Optional.of(executionSemester)
				.map(ExecutionSemester::getSemester)
				.orElse(null);

		beginDateYearMonthDay = Optional.of(executionSemester)
				.map(ExecutionInterval_Base::getBeginDateYearMonthDay)
				.map(YearMonthDay::toString)
				.orElse(null);

		endDateYearMonthDay = Optional.of(executionSemester)
				.map(ExecutionInterval_Base::getEndDateYearMonthDay)
				.map(YearMonthDay::toString)
				.orElse(null);
	}
}
