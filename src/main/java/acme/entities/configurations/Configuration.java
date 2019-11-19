
package acme.entities.configurations;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				spamWordsEN;

	@NotBlank
	private String				spamWordsES;

	//No puede ser negativo
	@NotNull
	private Double				threshold;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
