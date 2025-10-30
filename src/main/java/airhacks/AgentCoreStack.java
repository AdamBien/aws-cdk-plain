package airhacks;

import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class AgentCoreStack extends Stack {

    public AgentCoreStack(Construct scope, String appName,StackProps stackProps) {
        super(scope, ConventionalDefaults.stackName(appName, "agent-core"),stackProps);
    }
}
