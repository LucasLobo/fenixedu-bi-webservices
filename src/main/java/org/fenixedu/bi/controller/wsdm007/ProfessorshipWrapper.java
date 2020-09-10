package org.fenixedu.bi.controller.wsdm007;

import org.codehaus.jackson.annotate.JsonProperty;
import org.fenixedu.academic.domain.*;
import org.fenixedu.academic.domain.person.IDDocumentType;
import pt.ist.fenixframework.core.AbstractDomainObject;

import java.util.Optional;

public class ProfessorshipWrapper {

	@JsonProperty("teacherId")
	public final String teacherId;

	@JsonProperty("documentIdNumber")
	public final String documentIdNumber;

	@JsonProperty("idDocumentType")
	public final String idDocumentType;

	@JsonProperty("name")
	public final String name;

	@JsonProperty("externalId")
	public final String externalId;

	public ProfessorshipWrapper(Professorship professorship) {

		teacherId = Optional.of(professorship)
				.map(Professorship::getTeacher)
				.map(Teacher::getTeacherId)
				.orElse(null);

		documentIdNumber = Optional.of(professorship)
				.map(Professorship_Base::getPerson)
				.map(Person_Base::getDocumentIdNumber)
				.orElse(null);

		idDocumentType = Optional.of(professorship)
				.map(Professorship_Base::getPerson)
				.map(Person_Base::getIdDocumentType)
				.map(IDDocumentType::getLocalizedName)
				.orElse(null);

		name = Optional.of(professorship)
				.map(Professorship_Base::getPerson)
				.map(Person::getName)
				.orElse(null);

		externalId = Optional.of(professorship)
				.map(AbstractDomainObject::getExternalId)
				.orElse(null);


	}
}
