package airhacks;

import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class PlaceHolderStack
 extends Stack {

    public PlaceHolderStack(Construct scope, String appName,StackProps stackProps) {
        super(scope, ConventionalDefaults.stackName(appName, "placeholder"),stackProps);
    }
}
