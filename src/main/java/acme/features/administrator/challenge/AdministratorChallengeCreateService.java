
package acme.features.administrator.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	//	INTERNAL STATES ----------------------------------------

	@Autowired
	AdministratorChallengeRepository repository;


	//	AbstractUpdateService<Administrator, Announcement> interface -----

	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goldReward", "silverReward", "bronzeReward", "goldGoal", "silverGoal", "bronzeGoal");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;

		result = new Challenge();

		return result;
	}

	//	TODO: El atributo deadline debe ser futuro, no puede ser un día anterior al actual!!

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//		Date now = new Date(System.currentTimeMillis() - 1);
		//
		//		assert entity.getDeadline().compareTo(now) > 0;
	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {

		this.repository.save(entity);
	}

}
