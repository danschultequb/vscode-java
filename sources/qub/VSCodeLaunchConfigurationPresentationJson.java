package qub;

public class VSCodeLaunchConfigurationPresentationJson extends JSONObjectWrapperBase
{
    private static final String hiddenPropertyName = "hidden";
    private static final String groupPropertyName = "group";
    private static final String orderPropertyName = "order";

    private VSCodeLaunchConfigurationPresentationJson(JSONObject json)
    {
        super(json);
    }

    public static VSCodeLaunchConfigurationPresentationJson create()
    {
        return VSCodeLaunchConfigurationPresentationJson.create(JSONObject.create());
    }

    public static VSCodeLaunchConfigurationPresentationJson create(JSONObject json)
    {
        return new VSCodeLaunchConfigurationPresentationJson(json);
    }

    /**
     * Get whether this configuration should be shown in the configuration dropdown and the command
     * palette.
     */
    public Boolean getHidden()
    {
        return this.toJson().getBoolean(VSCodeLaunchConfigurationPresentationJson.hiddenPropertyName).catchError().await();
    }

    /**
     * Set whether this configuration should be shown in the configuration dropdown and the command
     * palette.
     * @param hidden Whether this configuration should be shown in the configuration dropdown and
     * the command palette.
     * @return This object for method chaining.
     */
    public VSCodeLaunchConfigurationPresentationJson setHidden(boolean hidden)
    {
        this.toJson().setBoolean(VSCodeLaunchConfigurationPresentationJson.hiddenPropertyName, hidden);

        return this;
    }

    /**
     * Get the group that this configuration belongs to. This is used for grouping and sorting in
     * the configuration dropdown and the command palette.
     */
    public String getGroup()
    {
        return this.toJson().getString(VSCodeLaunchConfigurationPresentationJson.groupPropertyName).catchError().await();
    }

    /**
     * Set the group that this configuration belongs to. This is used for grouping and sorting in
     * the configuration dropdown and the command palette.
     * @param group The group that this configuration belongs to.
     * @return This object for method chaining.
     */
    public VSCodeLaunchConfigurationPresentationJson setGroup(String group)
    {
        PreCondition.assertNotNull(group, "group");

        this.toJson().setString(VSCodeLaunchConfigurationPresentationJson.groupPropertyName, group);

        return this;
    }

    /**
     * Get the order of this configuration within a group. Used for grouping and sorting in the
     * configuration dropdown and the command palette.
     */
    public Integer getOrder()
    {
        return this.toJson().getInteger(VSCodeLaunchConfigurationPresentationJson.orderPropertyName).catchError().await();
    }

    /**
     * Set the order of this configuration within a group. Used for grouping and sorting in the
     * configuration dropdown and the command palette.
     * @param order The order of this configuration within a group.
     * @return This object for method chaining.
     */
    public VSCodeLaunchConfigurationPresentationJson setOrder(int order)
    {
        this.toJson().setNumber(VSCodeLaunchConfigurationPresentationJson.orderPropertyName, order);

        return this;
    }
}
