package org.fenixedu.bi.controller.wsdm003;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fenixedu.academic.domain.student.RegistrationProtocol;
import org.fenixedu.academic.domain.student.RegistrationProtocol_Base;
import org.fenixedu.commons.i18n.LocalizedString;
import pt.ist.fenixframework.core.AbstractDomainObject;

import java.util.Optional;

public class RegistrationProtocolWrapper {

	@JsonProperty
	public final String code;

	@JsonProperty
	public final String description;

	@JsonProperty
	public final String externalId;

	public RegistrationProtocolWrapper(RegistrationProtocol registrationProtocol) {
		code = Optional.of(registrationProtocol)
				.map(RegistrationProtocol_Base::getCode)
				.orElse(null);

		description = Optional.of(registrationProtocol)
				.map(RegistrationProtocol_Base::getDescription)
				.map(LocalizedString::getContent)
				.orElse(null);

		externalId = Optional.of(registrationProtocol)
				.map(AbstractDomainObject::getExternalId)
				.orElse(null);
	}
}
