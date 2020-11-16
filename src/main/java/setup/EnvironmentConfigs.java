package setup;

public class EnvironmentConfigs {
    private Environment environment;
    private String target_URL = "http://ezifyautomationlabs.com:6565/";

    public EnvironmentConfigs(Environment environment) {
        this.environment = environment;
    }

    public String getURL() {
        switch (environment) {
            case DEV:
                target_URL = "http://ezifyautomationlabs.com:6565/";
            case TEST:
                target_URL = "http://ezifyautomationlabs.com:6565/";
            case PERFORMANCE:
                target_URL = "http://ezifyautomationlabs.com:6565/";
            case STAGE:
                target_URL = "http://ezifyautomationlabs.com:6565/";
            case PRODUCTION:
                target_URL = "http://ezifyautomationlabs.com:6565/";
        }
        return target_URL;
    }
}
