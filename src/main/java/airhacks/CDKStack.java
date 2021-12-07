package airhacks;

import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class CDKStack extends Stack {

    public CDKStack(Construct scope, String id, StackProps props) {
        super(scope, id, props);
    }
}
