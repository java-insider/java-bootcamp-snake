package snake.config.loader;

import com.google.gson.Gson;
import snake.config.GameConfig;

import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Objects.requireNonNull;

public final class ConfigLoader {
    private static final String CONFIG_FILE = "/config.json";

    private ConfigLoader() {
        throw new UnsupportedOperationException();
    }

    public static GameConfig load() {
        Gson gson = new Gson();

        try (InputStreamReader in = new InputStreamReader(
            requireNonNull(ConfigLoader.class.getResourceAsStream(CONFIG_FILE)))
        ) {
            return gson.fromJson(in, GameConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
