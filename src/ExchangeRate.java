import com.google.gson.JsonObject;

public record ExchangeRate(String result, String base_code, JsonObject conversion_rates) {
}