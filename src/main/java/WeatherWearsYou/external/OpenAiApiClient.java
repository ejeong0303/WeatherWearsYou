package WeatherWearsYou.external;

import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class OpenAiApiClient {
    private final HttpClient client = createInsecureHttpClient();

    public enum OpenAiService {
        DALL_E, GPT_3;
    }
    @Value("${openai.api_key}")
    private String openaiApiKey;

    public void setOpenaiApiKey(String openaiApiKey) {
        this.openaiApiKey = openaiApiKey;
    }

    public byte[] postToOpenAiApi(String requestBodyAsJson, OpenAiService service) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder().uri(selectUri(service))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openaiApiKey)
                .POST(BodyPublishers.ofString(requestBodyAsJson)).build();
        return client.send(request, HttpResponse.BodyHandlers.ofByteArray()).body();

    }

    private URI selectUri(OpenAiService service) {
        return URI.create(switch (service) {
            case DALL_E -> "https://api.openai.com/v1/images/generations";
            case GPT_3 -> "https://api.openai.com/v1/completions";
        });
    }

    private HttpClient createInsecureHttpClient() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                }
            } };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            return HttpClient.newBuilder()
                    .sslContext(sc)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error creating insecure HTTP client", e);
        }
    }
}

