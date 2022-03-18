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
                
                final File settingsJsonFile = folder.getSettingsJsonFile();
                test.assertNotNull(settingsJsonFile);
                test.assertEqual(folder.getFile(".vscode/settings.json").await(), settingsJsonFile);
            });

            runner.testGroup("getSettingsJson()", () ->
            {
                runner.test("with non-existing .vscode folder",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);

                    test.assertThrows(() -> folder.getSettingsJson().await(),
                        new FileNotFoundException(folder.getFile(".vscode/settings.json").await()));

                    test.assertEqual(Iterable.create(), folder.iterateEntriesRecursively().toList());
                });

                runner.test("with non-existing settings.json file",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);
                    folder.createFolder(".vscode").await();

                    test.assertThrows(() -> folder.getSettingsJson().await(),
                        new FileNotFoundException(folder.getFile(".vscode/settings.json").await()));

                    test.assertEqual(
                        Iterable.create(
                            "/.vscode/"),
                        folder.iterateEntriesRecursively()
                            .map(FileSystemEntry::toString)
                            .toList());
                });

                final Action2<String,Throwable> getSettingsJsonErrorTest = (String contents, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(contents) + " settings.json file contents",
                        (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                        (Test test, FakeDesktopProcess process) ->
                    {
                        final Folder currentFolder = process.getCurrentFolder();
                        final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);
                        folder.setFileContentsAsString(".vscode/settings.json", contents).await();
    
                        test.assertThrows(() -> folder.getSettingsJson().await(),
                            expected);
                    });
                };

                getSettingsJsonErrorTest.run("", new ParseException("Missing object left curly bracket ('{')."));
                getSettingsJsonErrorTest.run("[]", new ParseException("Expected object left curly bracket ('{')."));

                final Action2<String,VSCodeSettingsJson> getSettingsJsonTest = (String contents, VSCodeSettingsJson expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(contents) + " settings.json file contents",
                        (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                        (Test test, FakeDesktopProcess process) ->
                    {
                        final Folder currentFolder = process.getCurrentFolder();
                        final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);
                        folder.setFileContentsAsString(".vscode/settings.json", contents).await();
    
                        final VSCodeSettingsJson settingsJson = folder.getSettingsJson().await();
                        test.assertEqual(expected, settingsJson);
                    });
                };

                getSettingsJsonTest.run("{}", VSCodeSettingsJson.create());
            });

            runner.testGroup("setSettingsJson(VSCodeSettingsJson)", () ->
            {
                runner.test("with null",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);

                    test.assertThrows(() -> folder.setSettingsJson(null),
                        new PreConditionFailure("settingsJson cannot be null."));

                    test.assertEqual(Iterable.create(), folder.iterateEntriesRecursively().toList());
                });

                runner.test("with non-null",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);

                    final VSCodeSettingsJson settingsJson = VSCodeSettingsJson.create();
                    settingsJson.toJson().setString("hello", "there");

                    final Integer setSettingsJsonResult = folder.setSettingsJson(settingsJson).await();
                    test.assertEqual(22, setSettingsJsonResult);

                    test.assertEqual(settingsJson, folder.getSettingsJson().await());
                });
            });

            runner.test("getTasksJsonFile()",
                (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                (Test test, FakeDesktopProcess process) ->
            {
                final Folder currentFolder = process.getCurrentFolder();
                final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);
                
                final File tasksJsonFile = folder.getTasksJsonFile();
                test.assertNotNull(tasksJsonFile);
                test.assertEqual(folder.getFile(".vscode/tasks.json").await(), tasksJsonFile);
            });

            runner.testGroup("getTasksJson()", () ->
            {
                runner.test("with non-existing .vscode folder",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);

                    test.assertThrows(() -> folder.getTasksJson().await(),
                        new FileNotFoundException(folder.getFile(".vscode/tasks.json").await()));

                    test.assertEqual(Iterable.create(), folder.iterateEntriesRecursively().toList());
                });

                runner.test("with non-existing tasks.json file",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);
                    folder.createFolder(".vscode").await();

                    test.assertThrows(() -> folder.getTasksJson().await(),
                        new FileNotFoundException(folder.getFile(".vscode/tasks.json").await()));

                    test.assertEqual(
                        Iterable.create(
                            "/.vscode/"),
                        folder.iterateEntriesRecursively()
                            .map(FileSystemEntry::toString)
                            .toList());
                });

                final Action2<String,Throwable> getTasksJsonErrorTest = (String contents, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(contents) + " tasks.json file contents",
                        (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                        (Test test, FakeDesktopProcess process) ->
                    {
                        final Folder currentFolder = process.getCurrentFolder();
                        final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);
                        folder.setFileContentsAsString(".vscode/tasks.json", contents).await();
    
                        test.assertThrows(() -> folder.getTasksJson().await(),
                            expected);
                    });
                };

                getTasksJsonErrorTest.run("", new ParseException("Missing object left curly bracket ('{')."));
                getTasksJsonErrorTest.run("[]", new ParseException("Expected object left curly bracket ('{')."));

                final Action2<String,VSCodeTasksJson> getTasksJsonTest = (String contents, VSCodeTasksJson expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(contents) + " tasks.json file contents",
                        (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                        (Test test, FakeDesktopProcess process) ->
                    {
                        final Folder currentFolder = process.getCurrentFolder();
                        final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);
                        folder.setFileContentsAsString(".vscode/tasks.json", contents).await();
    
                        final VSCodeTasksJson tasksJson = folder.getTasksJson().await();
                        test.assertEqual(expected, tasksJson);
                    });
                };

                getTasksJsonTest.run("{}", VSCodeTasksJson.create());
            });

            runner.testGroup("setTasksJson(VSCodeTasksJson)", () ->
            {
                runner.test("with null",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);

                    test.assertThrows(() -> folder.setTasksJson(null),
                        new PreConditionFailure("tasksJson cannot be null."));

                    test.assertEqual(Iterable.create(), folder.iterateEntriesRecursively().toList());
                });

                runner.test("with non-null",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final VSCodeWorkspaceFolder folder = VSCodeWorkspaceFolder.get(currentFolder);

                    final VSCodeTasksJson tasksJson = VSCodeTasksJson.create()
                        .setVersion("1.2.3");

                    final Integer setTasksJsonResult = folder.setTasksJson(tasksJson).await();
                    test.assertEqual(24, setTasksJsonResult);

                    test.assertEqual(tasksJson, folder.getTasksJson().await());
                });
            });
        });
    }
}
