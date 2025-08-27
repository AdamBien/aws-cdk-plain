package airhacks;

import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.Tags;

public interface CDKApp {
    String appName = "aws-cdk-plain";

    static void main(String... args) {

        var app = new App();
        Tags.of(app).add("project", "airhacks.live");
        Tags.of(app).add("environment", "workshops");
        Tags.of(app).add("application", appName);

        var stackProps = StackProps
                .builder()
                .build();
        new CDKStack(app, appName, stackProps);
        app.synth();
    }
}
