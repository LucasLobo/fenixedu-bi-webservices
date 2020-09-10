package org.fenixedu.bi.controller.wsdm001;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fenixedu.academic.domain.*;
import org.fenixedu.academic.domain.contacts.PhysicalAddress;
import org.fenixedu.academic.domain.contacts.PhysicalAddress_Base;
import org.fenixedu.academic.domain.organizationalStructure.Party;
import org.fenixedu.academic.domain.person.Gender;
import org.fenixedu.academic.domain.person.IDDocumentType;
import org.fenixedu.academic.domain.person.MaritalStatus;
import org.fenixedu.academic.domain.student.PersonalIngressionData_Base;
import org.fenixedu.academic.domain.student.Student;
import org.fenixedu.academic.domain.student.Student_Base;
import org.fenixedu.commons.i18n.LocalizedString;
import org.joda.time.YearMonthDay;
import pt.ist.fenixframework.core.AbstractDomainObject;

import java.util.Optional;

public class PersonWrapper {

	@JsonProperty("number")
	public final Integer number;

	@JsonProperty("name")
	public final String name;

	@JsonProperty("dateOfBirth")
	public final String dateOfBirth;

	@JsonProperty("gender")
	public final String gender;

	@JsonProperty("country.nationality")
	public final String nationality;

	@JsonProperty("defaultPhysicalAddress.parishOfResidence")
	public final String parishOfResidence;

	@JsonProperty("defaultPhysicalAddress.areaOfAreaCode")
	public final String areaOfAreaCode;

	@JsonProperty("personUlisboaSpecifications.disabilityType")
	public final String disabilityType;

	@JsonProperty("defaultPhysicalAddress.address")
	public final String address;

	@JsonProperty("idDocumentType")
	public final String idDocumentType;

	@JsonProperty("documentIdNumber")
	public final String documentIdNumber;

	@JsonProperty("maritalStatus")
	public final String maritalStatus;

	@JsonProperty("defaultPhysicalAddress.postalCode")
	public final String postalCode;

	@JsonProperty("defaultMobilePhoneNumber")
	public final String defaultMobilePhoneNumber;

	@JsonProperty("defaultPhoneNumber")
	public final String defaultPhoneNumber;

	@JsonProperty("defaultEmailAddressValue")
	public final String defaultEmailAddressValue;

	@JsonProperty("student.latestPersonalIngressionData.fatherSchoolLevel")
	public final String fatherSchoolLevel;

	@JsonProperty("student.latestPersonalIngressionData.motherSchoolLevel")
	public final String motherSchoolLevel;

	@JsonProperty("externalId")
	public final String externalId;

	public PersonWrapper(Person person) {

		number = Optional.of(person)
				.map(Person_Base::getStudent)
				.map(Student_Base::getNumber)
				.orElse(null);

		name = Optional.of(person)
				.map(Person::getName)
				.orElse(null);

		dateOfBirth = Optional.of(person)
				.map(Person_Base::getDateOfBirthYearMonthDay)
				.map(YearMonthDay::toString)
				.orElse(null);

		gender = Optional.of(person)
				.map(Person_Base::getGender)
				.map(Gender::toLocalizedString)
				.orElse(null);

		nationality = Optional.of(person)
				.map(Person_Base::getCountryOfBirth)
				.map(Country_Base::getCountryNationality)
				.map(LocalizedString::getContent)
				.orElse(null);

		parishOfResidence = Optional.of(person)
				.map(Party::getDefaultPhysicalAddress)
				.map(PhysicalAddress_Base::getParishOfResidence)
				.orElse(null);

		areaOfAreaCode = Optional.of(person)
				.map(Party::getDefaultPhysicalAddress)
				.map(PhysicalAddress_Base::getAreaOfAreaCode)
				.orElse(null);

		disabilityType = "fixme";

		address = Optional.of(person)
				.map(Party::getDefaultPhysicalAddress)
				.map(PhysicalAddress_Base::getAddress)
				.orElse(null);

		idDocumentType = Optional.of(person)
				.map(Person_Base::getIdDocumentType)
				.map(IDDocumentType::getLocalizedName)
				.orElse(null);

		documentIdNumber = Optional.of(person)
				.map(Person_Base::getDocumentIdNumber)
				.orElse(null);

		maritalStatus = Optional.of(person)
				.map(Person_Base::getMaritalStatus)
				.map(MaritalStatus::getLocalizedName)
				.orElse(null);

		postalCode = Optional.of(person)
				.map(Party::getDefaultPhysicalAddress)
				.map(PhysicalAddress::getPostalCode)
				.orElse(null);

		defaultMobilePhoneNumber = Optional.of(person)
				.map(Party::getDefaultMobilePhoneNumber)
				.orElse(null);

		defaultPhoneNumber = Optional.of(person)
				.map(Party::getDefaultMobilePhoneNumber)
				.orElse(null);

		defaultEmailAddressValue = Optional.of(person)
				.map(Party::getDefaultEmailAddressValue)
				.orElse(null);

		fatherSchoolLevel = Optional.of(person)
				.map(Person_Base::getStudent)
				.map(Student::getLatestPersonalIngressionData)
				.map(PersonalIngressionData_Base::getFatherSchoolLevel)
				.map(SchoolLevelType::getLocalizedName)
				.orElse(null);

		motherSchoolLevel = Optional.of(person)
				.map(Person_Base::getStudent)
				.map(Student::getLatestPersonalIngressionData)
				.map(PersonalIngressionData_Base::getMotherSchoolLevel)
				.map(SchoolLevelType::getLocalizedName)
				.orElse(null);

		externalId = Optional.of(person)
				.map(AbstractDomainObject::getExternalId)
				.orElse(null);
	}
}
