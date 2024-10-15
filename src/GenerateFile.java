import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GenerateFile {
    public void SaveJson(ExchangeRate exchangeRate) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writing = new FileWriter(exchangeRate.base_code() + ".json");
        writing.write(gson.toJson(exchangeRate));
        writing.close();
    }
}
