package airhacks;

public class ConventionalDefaults {

    static String stackName(String appName, String stackName) {
        return "%s-%s-stack".formatted(appName, stackName);
    }
}
