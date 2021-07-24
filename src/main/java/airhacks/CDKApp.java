package airhacks;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.Tags;



public class CDKApp {
    public static void main(final String[] args) {

            var app = new App();
            var appName = "aws-cdk-plain";
            Tags.of(app).add("project", "airhacks.live");
            Tags.of(app).add("environment","workshops");
            Tags.of(app).add("application", appName);

        var environment = Environment.builder().
                        account(System.getenv("CDK_DEFAULT_ACCOUNT")).
                        region(System.getenv("CDK_DEFAULT_REGION")).
                        build();
                        
        var stackProps = StackProps.builder().
                env(environment).
                build();
        
        new CDKStack(app, appName, stackProps);
        app.synth();
    }
}
