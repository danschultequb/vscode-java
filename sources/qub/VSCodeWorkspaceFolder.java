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

    public Folder getVSCodeFolder()
    {
        return this.getFolder(".vscode").await();
    }

    public VSCodeSettingsJsonFile getSettingsJsonFile()
    {
        return VSCodeSettingsJsonFile.get(this.getVSCodeFolder().getFile("settings.json").await());
    }
}