package hiro.kitchenpos.product.infrastructure;

import hiro.kitchenpos.product.domain.PurgomalumClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class DefaultProductPurgomalumClient implements PurgomalumClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean containsProfanity(final String text) {
        final URI url = UriComponentsBuilder.fromUriString("https://www.purgomalum.com/service/containsprofanity")
                .queryParam("text", text)
                .build()
                .toUri();

        return Boolean.parseBoolean(restTemplate.getForObject(url, String.class));
    }

}
