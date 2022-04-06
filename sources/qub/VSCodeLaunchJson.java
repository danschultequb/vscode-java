package qub;

public class VSCodeLaunchJson extends JSONObjectWrapperBase
{
    private static final String versionPropertyName = "version";
    private static final String configurationsPropertyName = "configurations";

    protected VSCodeLaunchJson(JSONObject json)
    {
        super(json);
    }

    /**
     * Create a new {@link VSCodeLaunchJson} object.
     * @return The new {@link VSCodeLaunchJson} object.
     */
    public static VSCodeLaunchJson create()
    {
        return VSCodeLaunchJson.create(JSONObject.create());
    }

    /**
     * Create a new {@link VSCodeLaunchJson} object that wraps around the provided
     * {@link JSONObject}.
     * @param json The {@link JSONObject} to wrap.
     * @return The new {@link VSCodeLaunchJson} object.
     */
    public static VSCodeLaunchJson create(JSONObject json)
    {
        return new VSCodeLaunchJson(json);
    }

    /**
     * Parse a {@link VSCodeLaunchJson} from the provided file's contents.
     * @param file The {@link File} to parse.
     * @return The parsed {@link VSCodeLaunchJson} from the provided file's contents.
     */
    public static Result<VSCodeLaunchJson> parse(File file)
    {
        PreCondition.assertNotNull(file, "file");

        return Result.create(() ->
        {
            final JSONObject json = JSON.parseObject(file).await();
            return VSCodeLaunchJson.create(json);
        });
    }

    /**
     * Get the version of the launch.json's file format.
     */
    public String getVersion()
    {
        return this.toJson().getString(VSCodeLaunchJson.versionPropertyName).catchError().await();
    }

    /**
     * Set the version of the launch.json's file format.
     * @param version The version of the launch.json's file format.
     * @return This object for method chaining.
     */
    public VSCodeLaunchJson setVersion(String version)
    {
        PreCondition.assertNotNull(version, "version");

        this.toJson().setString(VSCodeLaunchJson.versionPropertyName, version);

        return this;
    }

    /**
     * Get the configurations specified in this launch.json file.
     */
    public Iterable<VSCodeLaunchConfigurationJson> getConfigurations()
    {
        return this.toJson().getArray(VSCodeLaunchJson.configurationsPropertyName)
            .catchError(() -> JSONArray.create())
            .await()
            .instanceOf(JSONObject.class)
            .map(VSCodeLaunchConfigurationJson::create);
    }

    /**
     * Set the configurations specified in this launch.json file.
     * @param configurations The configurations specified in this launch.json file.
     * @return This object for method chaining.
     */
    public VSCodeLaunchJson setConfigurations(Iterable<VSCodeLaunchConfigurationJson> configurations)
    {
        PreCondition.assertNotNull(configurations, "configurations");

        this.toJson().setArray(VSCodeLaunchJson.configurationsPropertyName, JSONArray.create(configurations.map(VSCodeLaunchConfigurationJson::toJson)));

        return this;
    }
}