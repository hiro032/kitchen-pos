package hiro.kitchenpos.product.domain;

import java.util.Arrays;
import java.util.List;

public class InmemoryPurgomalumClient implements PurgomalumClient {

    private static final List<String> PROFANITIES = Arrays.asList("욕설", "비속어");

    @Override
    public boolean containsProfanity(String text) {
        return PROFANITIES.contains(text);
    }
}
