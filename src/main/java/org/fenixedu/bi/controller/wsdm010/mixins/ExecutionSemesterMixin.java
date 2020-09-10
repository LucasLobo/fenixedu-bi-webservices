package org.fenixedu.bi.controller.wsdm010.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.YearMonthDay;

public interface ExecutionSemesterMixin {

    @JsonProperty("year")
    String getYear();

    @JsonProperty("semester")
    int getSemester();

    @JsonProperty("beginDateYearMonthDay")
    YearMonthDay getBeginDateYearMonthDay();

    @JsonProperty("endDateYearMonthDay")
    YearMonthDay getEndDateYearMonthDay();
}
