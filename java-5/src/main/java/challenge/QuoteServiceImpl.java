package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository repository;

	@Override
	public Quote getQuote() {
		Script script = repository.getRandomScript().orElse(null);
		return convertToQuote(script);
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		Script script = repository.getRandomScriptByActorName(actor).orElse(null);
		return convertToQuote(script);
	}

	private Quote convertToQuote(Script script) {
		if (script != null) {
			return new Quote(
					script.getId(),
					script.getActor(),
					script.getDetail()
			);
		}
		return null;
	}
}
