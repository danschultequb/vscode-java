package qub;

public class VSCodeLaunchConfigurationJson extends JSONObjectWrapperBase
{
    private static final String typePropertyName = "type";
    private static final String namePropertyName = "name";
    private static final String requestPropertyName = "request";
    private static final String launchPropertyValue = "launch";
    private static final String cwdPropertyValue = "cwd";

    protected VSCodeLaunchConfigurationJson(JSONObject json)
    {
        super(json);
    }

    public static VSCodeLaunchConfigurationJson create()
    {
        return VSCodeLaunchConfigurationJson.create(JSONObject.create());
    }

    public static VSCodeLaunchConfigurationJson create(JSONObject json)
    {
        return new VSCodeLaunchConfigurationJson(json);
    }

    /**
     * Get the type of this configuration.
     */
    public String getType()
    {
        return this.toJson().getString(VSCodeLaunchConfigurationJson.typePropertyName).catchError().await();
    }

    /**
     * Set the type of this configuration.
     * @param type The type of this configuration.
     * @return This object for method chaining.
     */
    public VSCodeLaunchConfigurationJson setType(String type)
    {
        PreCondition.assertNotNull(type, "type");

        this.toJson().setString(VSCodeLaunchConfigurationJson.typePropertyName, type);

        return this;
    }

    /**
     * Get the name of this configuration. This value appears in the launch configuration dropdown
     * menu.
     */
    public String getName()
    {
        return this.toJson().getString(VSCodeLaunchConfigurationJson.namePropertyName).catchError().await();
    }

    /**
     * Set the name of this configuration. This value appears in the launch configuration dropdown
     * menu.
     * @param name The name of this configuration.
     * @return This object for method chaining.
     */
    public VSCodeLaunchConfigurationJson setName(String name)
    {
        PreCondition.assertNotNull(name, "name");

        this.toJson().setString(VSCodeLaunchConfigurationJson.namePropertyName, name);

        return this;
    }

    /**
     * Get the request type of this configuration. Should be either "launch" or "attach".
     */
    public String getRequest()
    {
        return this.toJson().getString(VSCodeLaunchConfigurationJson.requestPropertyName).catchError().await();
    }

    /**
     * Set the request type of this configuration. Should be either "launch" or "attach".
     * @param request The request type of this configuration. Should be either "launch" or "attach".
     * @return This object for method chaining.
     */
    public VSCodeLaunchConfigurationJson setRequest(String request)
    {
        PreCondition.assertNotNull(request, "request");

        this.toJson().setString(VSCodeLaunchConfigurationJson.requestPropertyName, request);

        return this;
    }

    /**
     * Set the request type of this configuration to be "launch".
     * @return This object for method chaining.
     */
    public VSCodeLaunchConfigurationJson setRequestLaunch()
    {
        return this.setRequest(VSCodeLaunchConfigurationJson.launchPropertyValue);
    }

    /**
     * Get the cwd (current working directory) that this configuration should execute in.
     */
    public String getCWD()
    {
        return this.toJson().getString(VSCodeLaunchConfigurationJson.cwdPropertyValue).catchError().await();
    }

    /**
     * Set the cwd (current working directory) that this configuration should execute in.
     * @param cwd The cwd (current working directory) that this configuration should execute in.
     * @return This object for method chaining.
     */
    public VSCodeLaunchConfigurationJson setCWD(String cwd)
    {
        PreCondition.assertNotNull(cwd, "cwd");

        this.toJson().setString(VSCodeLaunchConfigurationJson.cwdPropertyValue, cwd);

        return this;
    }
}
