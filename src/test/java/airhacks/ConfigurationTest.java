package airhacks;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ConfigurationTest {
    
    @Test
    public void load() {
        var configuration = Configuration.load();
        assertNotNull(configuration);
    }
}
