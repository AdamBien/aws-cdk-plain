package airhacks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.json.bind.JsonbBuilder;
import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.Tags;



public class CDKApp {
    
    static final String CONFIGURATION_FILE = "configuration.json";

    /**
     * Optional additional configuration read from configuration.json
     * Useful to fetch VPC ids and subnet ids
     * @return parsed configuration
     * @throws IOException
     */
    static Configuration configuration() throws IOException {
        var location = Path.of(CONFIGURATION_FILE);
        var reader = Files.newBufferedReader(location);
        return JsonbBuilder.create().fromJson(reader, Configuration.class);
    }

    public static void main(final String[] args) {

            var app = new App();
            var appName = "aws-cdk-plain";
            Tags.of(app).add("project", "airhacks.live");
            Tags.of(app).add("environment","workshops");
            Tags.of(app).add("application", appName);

            var stackProps = StackProps.builder()
                    .build();
            var configuration = Configuration.load();
        new CDKStack(app, appName, stackProps);
        app.synth();
    }
}
