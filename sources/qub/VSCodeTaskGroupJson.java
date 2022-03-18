package qub;

/**
 * Defines to which execution group a task belongs to. It supports "build" to add it to the build
 * group and "test" to add it to the test group.
 */
public class VSCodeTaskGroupJson extends JSONObjectWrapperBase
{
    private static final String kindPropertyName = "kind";
    private static final String isDefaultPropertyName = "isDefault";

    private VSCodeTaskGroupJson(JSONObject json)
    {
        super(json);
    }
    
    public static VSCodeTaskGroupJson create()
    {
        return VSCodeTaskGroupJson.create(JSONObject.create());
    }

    public static VSCodeTaskGroupJson create(JSONObject json)
    {
        return new VSCodeTaskGroupJson(json);
    }

    /**
     * Get the task's execution group.
     * "build" marks the task as a build task accessible through the 'Run Build Task' command.
     * "test" marks the task as a test task accessible through the 'Run Test Task' command.
     * "none" assigns the task to no group.
     * @return The task's execution group.
     */
    public String getKind()
    {
        return this.toJson().getString(VSCodeTaskGroupJson.kindPropertyName).catchError().await();
    }

    /**
     * Set the task's execution group.
     * "build" marks the task as a build task accessible through the 'Run Build Task' command.
     * "test" marks the task as a test task accessible through the 'Run Test Task' command.
     * "none" assigns the task to no group.
     * @param kind The task's execution group.
     * @return This object for method chaining.
     */
    public VSCodeTaskGroupJson setKind(String kind)
    {
        PreCondition.assertNotNull(kind, "kind");

        this.toJson().setString(VSCodeTaskGroupJson.kindPropertyName, kind);

        return this;
    }

    /**
     * Get whether this task is the default task in the group.
     * @return Whether this task is the default task in the group.
     */
    public Boolean getIsDefault()
    {
        return this.toJson().getBoolean(VSCodeTaskGroupJson.isDefaultPropertyName).catchError().await();
    }

    /**
     * Set whether this task is the default task in the group.
     * @param isDefault Whether this task is the default task in the group.
     * @return This object for method chaining.
     */
    public VSCodeTaskGroupJson setIsDefault(boolean isDefault)
    {
        this.toJson().setBoolean(VSCodeTaskGroupJson.isDefaultPropertyName, isDefault);

        return this;
    }
}
