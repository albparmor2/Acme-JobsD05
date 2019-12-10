
package acme.features.authenticated.requesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestas.Requesta;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedRequestaShowService implements AbstractShowService<Authenticated, Requesta> {

	@Autowired
	AuthenticatedRequestaRepository repository;


	@Override
	public boolean authorise(final Request<Requesta> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Requesta> request, final Requesta entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "moment", "description", "deadline", "reward");
	}

	@Override
	public Requesta findOne(final Request<Requesta> request) {
		assert request != null;

		Requesta result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
