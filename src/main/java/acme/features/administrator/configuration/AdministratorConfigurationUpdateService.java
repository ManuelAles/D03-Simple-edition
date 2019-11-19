
package acme.features.administrator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configurations.Configuration;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorConfigurationUpdateService implements AbstractUpdateService<Administrator, Configuration> {

	//	INTERNAL STATES ----------------------------------------

	@Autowired
	AdministratorConfigurationRepository repository;


	//	AbstractUpdateService<Administrator, Configuration> interface -----

	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWordsEN", "spamWordsES", "threshold");
	}

	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;

		Configuration result;

		result = this.repository.findOne();

		return result;
	}

	@Override
	public void validate(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Configuration> request, final Configuration entity) {
		assert request != null;
		assert entity != null;

		entity.setSpamWordsEN(request.getModel().getString("spamWordsEN"));
		entity.setSpamWordsES(request.getModel().getString("spamWordsES"));
		entity.setThreshold(request.getModel().getDouble("threshold"));

		this.repository.save(entity);
	}

}
