package airhacks;

import airhacks.placeholder.configuration.control.ZCfg;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public record Configuration(String appName) {

        public StackProps stackProperties() {
        ZCfg.load(appName);
        var region = ZCfg.string("stack.props.region");
        var env = Environment
                .builder()
                .region(region)
                .build();
        return StackProps
                .builder()
                .env(env)
                .build();
    }
    
}
