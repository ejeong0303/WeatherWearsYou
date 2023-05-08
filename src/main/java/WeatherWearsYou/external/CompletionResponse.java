package WeatherWearsYou.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Optional;
@JsonIgnoreProperties(ignoreUnknown = true)
public record CompletionResponse(Usage usage, List<Choice> choices) {

    public Optional<String> firstAnswer() {
        if (choices == null || choices.isEmpty())
            return Optional.empty();
        return Optional.of(choices.get(0).text);
    }

    record Usage(int total_tokens, int prompt_tokens, int completion_tokens) {}
    @JsonIgnoreProperties(ignoreUnknown = true)
    record Choice(String text) {}
}
