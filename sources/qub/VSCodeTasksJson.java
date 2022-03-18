package qub;

public class VSCodeTasksJson extends JSONObjectWrapperBase
{
    private static final String versionPropertyName = "version";
    private static final String tasksPropertyName = "tasks";

    protected VSCodeTasksJson(JSONObject json)
    {
        super(json);
    }
    
    public static VSCodeTasksJson create()
    {
        return VSCodeTasksJson.create(JSONObject.create());
    }

    public static VSCodeTasksJson create(JSONObject json)
    {
        return new VSCodeTasksJson(json);
    }

    /**
     * Get the configuration's version number.
     */
    public String getVersion()
    {
        return this.toJson().getString(VSCodeTasksJson.versionPropertyName).catchError().await();
    }

    /**
     * Set the configuration's version number.
     * @param version The configuration's version number.
     * @return This object for method chaining.
     */
    public VSCodeTasksJson setVersion(String version)
    {
        PreCondition.assertNotNull(version, "version");
        
        this.toJson().setString(VSCodeTasksJson.versionPropertyName, version);

        return this;
    }

    /**
     * Get the configuration of the available tasks. A tasks.json file can either contain a global
     * problemMatcher property or a tasks property, but not both.
     * @return
     */
    public Iterable<VSCodeTaskJson> getTasks()
    {
        final JSONArray tasksJson = this.toJson().getArray(VSCodeTasksJson.tasksPropertyName).catchError().await();
        final Iterable<VSCodeTaskJson> result = tasksJson == null
            ? Iterable.create()
            : tasksJson
                .instanceOf(JSONObject.class)
                .map(VSCodeTaskJson::create)
                .toList();

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    /**
     * Set the configuration of the available tasks. A tasks.json file can either contain a global
     * problemMatcher property or a tasks property, but not both.
     * @param tasks The configuration of available tasks.
     * @return This object for method chaining.
     */
    public VSCodeTasksJson setTasks(Iterable<VSCodeTaskJson> tasks)
    {
        PreCondition.assertNotNull(tasks, "tasks");

        this.toJson().setArray(VSCodeTasksJson.tasksPropertyName, JSONArray.create(tasks.map(VSCodeTaskJson::toJson)));

        return this;
    }
}
