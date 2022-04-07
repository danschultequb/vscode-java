package qub;

/**
 * A {@link Folder} type that contains helper methods for accessing VS Code Workspace files.
 */
public class VSCodeWorkspaceFolder extends Folder
{
    private VSCodeWorkspaceFolder(Folder folder)
    {
        super(folder.getFileSystem(), folder.getPath());
    }

    public static VSCodeWorkspaceFolder get(Folder folder)
    {
        PreCondition.assertNotNull(folder, "folder");

        return new VSCodeWorkspaceFolder(folder);
    }

    private static <T> Result<T> readParsedFile(File file, Function1<JSONObject,T> creator)
    {
        PreCondition.assertNotNull(file, "file");
        PreCondition.assertNotNull(creator, "creator");

        return Result.create(() ->
        {
            return creator.run(JSON.parseObject(file).await());
        });
    }

    private static Result<Integer> writeParsedFile(File file, JSONObjectWrapper json)
    {
        PreCondition.assertNotNull(file, "file");
        PreCondition.assertNotNull(json, "json");

        return Result.create(() ->
        {
            int result = 0;

            try (final CharacterWriteStream writeStream = file.getContentsCharacterWriteStream().await())
            {
                result += json.toString(writeStream, JSONFormat.pretty).await();
            }

            PostCondition.assertGreaterThanOrEqualTo(result, 0, "result");

            return result;
        });
    }

    /**
     * Get a reference to the ".vscode" folder in this workspace folder.
     * @return A reference to the ".vscode" folder in this workspace folder.
     */
    public Folder getVSCodeFolder()
    {
        return this.getFolder(".vscode").await();
    }

    /**
     * Get a reference to the ".vscode/settings.json" file in this workspace folder.
     * @return A reference to the ".vscode/settings.json" file in this workspace folder.
     */
    public File getSettingsJsonFile()
    {
        return this.getVSCodeFolder().getFile("settings.json").await();
    }

    /**
     * Get the parsed contents of the ".vscode/settings.json" file in this workspace folder.
     * @return The parsed contents of the ".vscode/settings.json" file in this workspace folder.
     */
    public Result<VSCodeSettingsJson> getSettingsJson()
    {
        return VSCodeWorkspaceFolder.readParsedFile(this.getSettingsJsonFile(), VSCodeSettingsJson::create);
    }

    /**
     * Set the contents of the ".vscode/settings.json" file in this workspace folder.
     * @param settingsJson The contents of the ".vscode/settings.json" file.
     * @return The number of characters that were written.
     */
    public Result<Integer> setSettingsJson(VSCodeSettingsJson settingsJson)
    {
        PreCondition.assertNotNull(settingsJson, "settingsJson");

        return VSCodeWorkspaceFolder.writeParsedFile(this.getSettingsJsonFile(), settingsJson);
    }

    /**
     * Get a reference to the ".vscode/tasks.json" file in this workspace folder.
     * @return A reference to the ".vscode/tasks.json" file in this workspace folder.
     */
    public File getTasksJsonFile()
    {
        return this.getVSCodeFolder().getFile("tasks.json").await();
    }

    /**
     * Get the parsed contents of the ".vscode/tasks.json" file in this workspace folder.
     * @return The parsed contents of the ".vscode/tasks.json" file in this workspace folder.
     */
    public Result<VSCodeTasksJson> getTasksJson()
    {
        return VSCodeWorkspaceFolder.readParsedFile(this.getTasksJsonFile(), VSCodeTasksJson::create);
    }

    /**
     * Set the contents of the ".vscode/tasks.json" file in this workspace folder.
     * @param tasksJson The contents of the ".vscode/tasks.json" file.
     * @return The number of characters that were written.
     */
    public Result<Integer> setTasksJson(VSCodeTasksJson tasksJson)
    {
        PreCondition.assertNotNull(tasksJson, "tasksJson");

        return VSCodeWorkspaceFolder.writeParsedFile(this.getTasksJsonFile(), tasksJson);
    }

    /**
     * Get a reference to the ".vscode/launch.json" file in this workspace folder.
     * @return A reference to the ".vscode/launch.json" file in this workspace folder.
     */
    public File getLaunchJsonFile()
    {
        return this.getVSCodeFolder().getFile("launch.json").await();
    }

    /**
     * Get the parsed contents of the ".vscode/launch.json" file in this workspace folder.
     * @return The parsed contents of the ".vscode/launch.json" file in this workspace folder.
     */
    public Result<VSCodeLaunchJson> getLaunchJson()
    {
        return VSCodeWorkspaceFolder.readParsedFile(this.getLaunchJsonFile(), VSCodeLaunchJson::create);
    }

    /**
     * Set the contents of the ".vscode/launch.json" file in this workspace folder.
     * @param launchJson The contents of the ".vscode/launch.json" file.
     * @return The number of characters that were written.
     */
    public Result<Integer> setLaunchJson(VSCodeLaunchJson launchJson)
    {
        PreCondition.assertNotNull(launchJson, "launchJson");

        return VSCodeWorkspaceFolder.writeParsedFile(this.getLaunchJsonFile(), launchJson);
    }
}