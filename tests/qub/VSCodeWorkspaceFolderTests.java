package qub;

public interface VSCodeWorkspaceFolderTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(VSCodeWorkspaceFolder.class, () ->
        {
            runner.testGroup("get(Folder)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> VSCodeWorkspaceFolder.get(null),
                        new PreConditionFailure("folder cannot be null."));
                });

                runner.test("with non-null",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);
                    test.assertNotNull(folder);
                    test.assertEqual(currentFolder, folder);
                });
            });

            runner.test("getVSCodeFolder()",
                (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                (Test test, FakeDesktopProcess process) ->
            {
                final Folder currentFolder = process.getCurrentFolder();
                final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);
                
                final Folder vscodeFolder = folder.getVSCodeFolder();
                test.assertNotNull(vscodeFolder);
                test.assertEqual(folder.getFolder(".vscode").await(), vscodeFolder);
            });

            runner.test("getSettingsJsonFile()",
                (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                (Test test, FakeDesktopProcess process) ->
            {
                final Folder currentFolder = process.getCurrentFolder();
                final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);
                
                final VSCodeSettingsJsonFile settingsJsonFile = folder.getSettingsJsonFile();
                test.assertNotNull(settingsJsonFile);
                test.assertEqual(folder.getFile(".vscode/settings.json").await(), settingsJsonFile);
            });
        });
    }
}
