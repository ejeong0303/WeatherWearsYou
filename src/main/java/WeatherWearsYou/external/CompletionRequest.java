package WeatherWearsYou.external;

public record CompletionRequest(String model, String prompt, double temperature, int max_tokens) {

    public static CompletionRequest defaultWith(String[] words, String style) {

        String styleText = style == null ? "" : " " + style + " style";
        String prompt = String.format("Recommend me the trendy south korean style clothing that best fits the weather%s. Give me a word(don't describe with adjectives or colors) for each category " +
                        "(outer, top, bottom, and shoes with 3 examples each) in a comma-separated list for a " +
                        "%s in Seoul on %s with probability of precipitation %s%% , %s weather, lowest temperature %s degree celsius and highest temperature %s degree celsius. " +
                        "Give me only the list, don't tell me additional notes. Also provide each outer, top, bottom, and shoes in separate lines.",
                // Please provide responses except outer, top, bottom, and shoes in korean
                styleText, words[0], words[1], words[2], words[3], words[4], words[5]);

        return new CompletionRequest("text-davinci-003", prompt, 0.7, 500);
    }


}
