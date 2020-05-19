package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController
public class QuoteController {

	@Autowired
	private QuoteService service;

	@GetMapping(value = "/quote")
	public Quote getQuote() {
		return service.getQuote();
	}

	@GetMapping(value = "/quote/{actor}")
	public Quote getQuoteByActor(@PathVariable("actor") String actor) {
		return service.getQuoteByActor(actor);
	}

}
