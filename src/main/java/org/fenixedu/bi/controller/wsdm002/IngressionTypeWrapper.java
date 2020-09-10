package org.fenixedu.bi.controller.wsdm002;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fenixedu.academic.domain.candidacy.IngressionType;
import org.fenixedu.academic.domain.candidacy.IngressionType_Base;
import org.fenixedu.commons.i18n.LocalizedString;
import pt.ist.fenixframework.core.AbstractDomainObject;

import java.util.Optional;

public class IngressionTypeWrapper {

	@JsonProperty
	public final String code;

	@JsonProperty
	public final String description;

	@JsonProperty
	public final String externalId;

	public IngressionTypeWrapper(IngressionType ingressionType) {
		code = Optional.of(ingressionType)
				.map(IngressionType_Base::getCode)
				.orElse(null);

		description = Optional.of(ingressionType)
				.map(IngressionType_Base::getDescription)
				.map(LocalizedString::getContent)
				.orElse(null);

		externalId = Optional.of(ingressionType)
				.map(AbstractDomainObject::getExternalId)
				.orElse(null);

	}
}
