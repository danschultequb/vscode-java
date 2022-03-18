package qub;

/**
 * A {@link JSONObject} type that provides helper methods for interacting with VS Code settings.json
 * files.
 */
public class VSCodeSettingsJson extends JSONObjectWrapperBase
{
    private VSCodeSettingsJson(JSONObject json)
    {
        super(json);
    }

    public static VSCodeSettingsJson create()
    {
        return VSCodeSettingsJson.create(JSONObject.create());
    }

    public static VSCodeSettingsJson create(JSONObject json)
    {
        return new VSCodeSettingsJson(json);
    }
}
