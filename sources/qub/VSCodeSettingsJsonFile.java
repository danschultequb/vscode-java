package qub;

/**
 * A {@link File} type that provides helper methods for interacting with VS Code settings.json
 * files.
 */
public class VSCodeSettingsJsonFile extends File
{
    private VSCodeSettingsJsonFile(File file)
    {
        super(file.getFileSystem(), file.getPath());
    }

    public static VSCodeSettingsJsonFile get(File file)
    {
        PreCondition.assertNotNull(file, "file");

        return new VSCodeSettingsJsonFile(file);
    }

    /**
     * Get the contents of this {@link VSCodeSettingsJsonFile} parsed as a {@link JSONObject}.
     */
    public Result<JSONObject> getContentsAsJSONObject()
    {
        return Result.create(() ->
        {
            JSONObject result;
            try (final CharacterReadStream readStream = this.getContentsReadStream().await())
            {
                result = JSON.parseObject(readStream).await();
            }
            return result;
        });
    }
}
