
package acme.features.consumer.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	ConsumerOfferRepository repository;


	// AbstractCreateService<Consumer, Offer> interface ---------------

	@Override
	public boolean authorise(final acme.framework.components.Request<Offer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final acme.framework.components.Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final acme.framework.components.Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "text", "minMoney", "maxMoney", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Offer instantiate(final acme.framework.components.Request<Offer> request) {
		Offer result;

		result = new Offer();

		return result;
	}

	@Override
	public void validate(final acme.framework.components.Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted;

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "consumer.offer.error.must-accept");

	}

	@Override
	public void create(final acme.framework.components.Request<Offer> request, final Offer entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
