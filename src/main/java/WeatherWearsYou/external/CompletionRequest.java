package WeatherWearsYou.external;

public record CompletionRequest(String model, String prompt,
                                double temperature, int max_tokens) {

    public static CompletionRequest defaultWith(String prompt) {

        String words[] = prompt.split(",");

        prompt = String.format("Give me a word(don't describe with adjectives or colors) for each category " +
                "(outer, top, bottom, and shoes with 10 examples each) in a comma-separated list for a " +
                "%s in Seoul on %s with probability of precipitation %s%% , %s weather, lowest temperature %s degree celsius and highest temperature %s degree celsius. " +
                "Give me only the list, don't tell me additional notes.", words[0], words[1], words[2], words[3], words[4], words[5]);

        return new CompletionRequest("text-davinci-003", prompt, 0.7, 500);
    }

}
