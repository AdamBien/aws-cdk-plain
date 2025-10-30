package airhacks.placeholder.configuration.control;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * Configuration loader that reads properties in order:
 * 1. ~/.[appName]/app.properties (global)
 * 2. ./app.properties (local, overwrites global)
 * 3. System properties (highest priority)
 * 
 * <pre>
 * // Initialize once at application startup
 * ZCfg.load("myapp");
 * 
 * // Access configuration values
 * var port = ZCfg.integer("server.port", 8080);
 * var debug = ZCfg.bool("debug.enabled", false);
 * var dbUrl = ZCfg.string("db.url", "localhost:5432");
 * </pre>
 */
public class ZCfg {

    static final String PROPERTIES_FILE = "app.properties";
    static Properties CACHE;

    public static void load(String appName) {
        CACHE = loadProperties(appName);
    }

    static Properties loadProperties(String appName) {
        var properties = new Properties();

        // Load global properties from ~/.[appName]/app.properties
        var userHome = System.getProperty("user.home");
        var globalConfig = Path.of(userHome, "." + appName, PROPERTIES_FILE);
        if (Files.exists(globalConfig)) {
            loadFromFile(globalConfig, properties);
        }

        // Load local properties from ./app.properties (overwrites global)
        var localConfig = Path.of(PROPERTIES_FILE);
        if (Files.exists(localConfig)) {
            loadFromFile(localConfig, properties);
        }

        // System properties have highest priority
        properties.putAll(System.getProperties());

        return properties;
    }

    static void loadFromFile(Path file, Properties properties) {
        try (var is = Files.newBufferedReader(file)) {
            properties.load(is);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load properties from: " + file, e);
        }
    }

    public static String string(String key) {
        if (CACHE == null)
            throw new IllegalStateException("Call ZCfg.load(appName) first");
        return CACHE.getProperty(key);
    }

    public static String string(String key, String defaultValue) {
        if (CACHE == null)
            throw new IllegalStateException("Call ZCfg.load(appName) first");
        return CACHE.getProperty(key, defaultValue);
    }

    public static int integer(String key, int defaultValue) {
        if (CACHE == null)
            throw new IllegalStateException("Call ZCfg.load(appName) first");
        var value = CACHE.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    public static boolean bool(String key, boolean defaultValue) {
        if (CACHE == null)
            throw new IllegalStateException("Call ZCfg.load(appName) first");
        var value = CACHE.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    /**
     * Retrieves comma-separated values as a list, trimming whitespace from each element.
     *
     * @param key the configuration key
     * @return list of trimmed values, empty list if key doesn't exist
     */
    public static List<String> strings(String key) {
        if (CACHE == null)
            throw new IllegalStateException("Call ZCfg.load(appName) first");
        var value = CACHE.getProperty(key);
        if (value == null)
            return List.of();
        return split(value);
    }

    static List<String> split(String value) {
        var values = value.split(",");
        return Stream.of(values)
                .map(String::trim)
                .toList();
    }
}