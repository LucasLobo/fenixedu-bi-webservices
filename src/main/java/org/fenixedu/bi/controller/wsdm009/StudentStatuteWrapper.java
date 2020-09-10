package org.fenixedu.bi.controller.wsdm009;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fenixedu.academic.domain.student.StatuteType_Base;
import org.fenixedu.academic.domain.student.StudentStatute;
import org.fenixedu.academic.domain.student.StudentStatute_Base;
import org.fenixedu.commons.i18n.LocalizedString;
import pt.ist.fenixframework.core.AbstractDomainObject;

import java.util.Optional;

public class StudentStatuteWrapper {
	
	@JsonProperty("active")
	public final Boolean active;

	@JsonProperty("name")
	public final String name;

	@JsonProperty("code")
	public final String code;

	@JsonProperty("externalId")
	public final String externalId;

	public StudentStatuteWrapper(StudentStatute studentStatute) {
		active = Optional.of(studentStatute)
				.map(StudentStatute_Base::getType)
				.map(StatuteType_Base::getActive)
				.orElse(null);

		name = Optional.of(studentStatute)
				.map(StudentStatute_Base::getType)
				.map(StatuteType_Base::getName)
				.map(LocalizedString::getContent)
				.orElse(null);

		code = Optional.of(studentStatute)
				.map(StudentStatute_Base::getType)
				.map(StatuteType_Base::getCode)
				.orElse(null);

		externalId = Optional.of(studentStatute)
				.map(AbstractDomainObject::getExternalId)
				.orElse(null);
	}
}
