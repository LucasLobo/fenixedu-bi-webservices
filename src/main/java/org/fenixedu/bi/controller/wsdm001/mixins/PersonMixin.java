package org.fenixedu.bi.controller.wsdm001.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.fenixedu.academic.domain.Country;
import org.fenixedu.academic.domain.person.Gender;
import org.fenixedu.academic.domain.person.IDDocumentType;
import org.fenixedu.academic.domain.person.MaritalStatus;
import org.fenixedu.academic.domain.student.Student;
import org.joda.time.YearMonthDay;

public interface PersonMixin {

	@JsonProperty("name")
	String getName();

	@JsonProperty("dateOfBirth")
	YearMonthDay getDateOfBirthYearMonthDay();

	@JsonProperty("gender")
	Gender getGender();

	@JsonProperty("country.nationality")
	Country getCountryOfBirth();

	@JsonProperty("defaultPhysicalAddress.parishOfResidence")
	String getParishOfResidence();

	@JsonProperty("defaultPhysicalAddress.areaOfAreaCode")
	String getAreaOfAreaCode();

	// personUlisboaSpecifications.disabilityType
	//todo

	@JsonProperty("defaultPhysicalAddress.address")
	String getAddress();

	@JsonProperty("idDocumentType")
	IDDocumentType getIdDocumentType();

	@JsonProperty("documentIdNumber")
	String getDocumentIdNumber();

	@JsonProperty("maritalStatus")
	MaritalStatus getMaritalStatus();

	@JsonProperty("defaultPhysicalAddress.postalCode")
	String getPostalCode();

	@JsonProperty("defaultMobilePhoneNumber")
	String getDefaultMobilePhoneNumber();

	@JsonProperty("defaultPhoneNumber")
	String getDefaultPhoneNumber();

	@JsonProperty("defaultEmailAddressValue")
	String getDefaultEmailAddressValue();

	@JsonProperty("externalId")
	String getExternalId();

	@JsonProperty()
	@JsonUnwrapped(prefix = "student.")
	Student getStudent();

}
