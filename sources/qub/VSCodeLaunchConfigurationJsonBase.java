package qub;

public abstract class VSCodeLaunchConfigurationJsonBase<T extends VSCodeLaunchConfigurationJson> extends VSCodeLaunchConfigurationJson
{
    protected VSCodeLaunchConfigurationJsonBase(JSONObject json)
    {
        super(json);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T setType(String type)
    {
        return (T)super.setType(type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T setName(String name)
    {
        return (T)super.setName(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T setRequest(String request)
    {
        return (T)super.setRequest(request);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T setRequestLaunch()
    {
        return (T)super.setRequestLaunch();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T setCWD(String cwd)
    {
        return (T)super.setCWD(cwd);
    }
}
