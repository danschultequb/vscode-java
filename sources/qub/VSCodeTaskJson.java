package qub;

public class VSCodeTaskJson extends JSONObjectWrapperBase
{
    private static final String labelPropertyName = "label";
    private static final String typePropertyName = "type";
    private static final String commandPropertyName = "command";
    private static final String isBackgroundPropertyName = "isBackground";
    private static final String argsPropertyName = "args";
    private static final String groupPropertyName = "group";

    private VSCodeTaskJson(JSONObject json)
    {
        super(json);
    }

    public static VSCodeTaskJson create()
    {
        return VSCodeTaskJson.create(JSONObject.create());
    }

    public static VSCodeTaskJson create(JSONObject json)
    {
        return new VSCodeTaskJson(json);
    }

    /**
     * Get the arguments passed to the command when this task is invoked.
     * @return The arguments passed to the command when this task is invoked.
     */
    public Iterable<String> getArgs()
    {
        final JSONArray argsJson = this.toJson().getArray(VSCodeTaskJson.argsPropertyName)
            .catchError(() -> { return JSONArray.create(); })
            .await();
        final Iterable<String> result = argsJson.instanceOf(JSONString.class)
            .map(JSONString::getValue)
            .toList();

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    /**
     * Set the arguments passed to the command when this task is invoked.
     * @param args The arguments passed to the command when this task is invoked.
     * @return This object for method chaining.
     */
    public VSCodeTaskJson setArgs(Iterable<String> args)
    {
        PreCondition.assertNotNull(args, "args");

        this.toJson().setArray(VSCodeTaskJson.argsPropertyName, JSONArray.create(args.map(JSONString::get)));

        return this;
    }

    /**
     * Get the task's user interface label.
     * @return The task's user interface label.
     */
    public String getLabel()
    {
        return this.toJson().getString(VSCodeTaskJson.labelPropertyName).catchError().await();
    }

    /**
     * Set the task's user interface label.
     * @param label The task's user interface label.
     * @return This object for method chaining.
     */
    public VSCodeTaskJson setLabel(String label)
    {
        PreCondition.assertNotNull(label, "label");

        this.toJson().setString(VSCodeTaskJson.labelPropertyName, label);

        return this;
    }

    /**
     * Get whether the task is run as a process or as a command inside a shell.
     * @return Whether the task is run as a process or as a command inside a shell.
     */
    public String getType()
    {
        return this.toJson().getString(VSCodeTaskJson.typePropertyName).catchError().await();
    }

    /**
     * Set whether the task is run as a process or as a command inside a shell.
     * @param type Whether the task is run as a process or as a command inside a shell.
     * @return This object for method chaining.
     */
    public VSCodeTaskJson setType(String type)
    {
        PreCondition.assertNotNull(type, "type");

        this.toJson().setString(VSCodeTaskJson.typePropertyName, type);

        return this;
    }

    /**
     * Get the command to execute. Can be an external program or a shell command.
     * @return The command to execute.
     */
    public String getCommand()
    {
        return this.toJson().getString(VSCodeTaskJson.commandPropertyName).catchError().await();
    }

    /**
     * Set the command to execute. Can be an external program or a shell command.
     * @param command The command to execute.
     * @return This object for method chaining.
     */
    public VSCodeTaskJson setCommand(String command)
    {
        PreCondition.assertNotNull(command, "command");

        this.toJson().setString(VSCodeTaskJson.commandPropertyName, command);

        return this;
    }

    
}
