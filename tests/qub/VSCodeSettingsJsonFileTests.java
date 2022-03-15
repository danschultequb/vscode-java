package qub;

public interface VSCodeSettingsJsonFileTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(VSCodeSettingsJsonFile.class, () ->
        {
            runner.testGroup("get(File)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> VSCodeSettingsJsonFile.get(null),
                        new PreConditionFailure("file cannot be null."));
                });

                runner.test("with non-null",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final File file = currentFolder.getFile("file").await();
                    final VSCodeSettingsJsonFile settingsJsonFile = VSCodeSettingsJsonFile.get(file);
                    test.assertNotNull(settingsJsonFile);
                    test.assertEqual(file, settingsJsonFile);
                });
            });

            runner.testGroup("getContentsAsJSONObject()", () ->
            {
                runner.test("with file that doesn't exist",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final Folder currentFolder = process.getCurrentFolder();
                    final File file = currentFolder.getFile("file").await();
                    final VSCodeSettingsJsonFile settingsJsonFile = VSCodeSettingsJsonFile.get(file);

                    test.assertThrows(() -> settingsJsonFile.getContentsAsJSONObject().await(),
                        new FileNotFoundException(settingsJsonFile));
                });

                final Action2<String,Throwable> getContentsAsJSONObjectErrorTest = (String contents, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(contents),
                        (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                        (Test test, FakeDesktopProcess process) ->
                    {
                        final Folder currentFolder = process.getCurrentFolder();
                        final File file = currentFolder.getFile("file").await();
                        final VSCodeSettingsJsonFile settingsJsonFile = VSCodeSettingsJsonFile.get(file);
                        settingsJsonFile.setContentsAsString(contents).await();

                        test.assertThrows(() -> settingsJsonFile.getContentsAsJSONObject().await(),
                            expected);

                        test.assertEqual(contents, settingsJsonFile.getContentsAsString().await());
                    });
                };

                getContentsAsJSONObjectErrorTest.run(
                    "",
                    new ParseException("Missing object left curly bracket ('{')."));
                getContentsAsJSONObjectErrorTest.run(
                    "[]",
                    new ParseException("Expected object left curly bracket ('{')."));

                final Action2<String,JSONObject> getContentsAsJSONObjectTest = (String contents, JSONObject expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(contents),
                        (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                        (Test test, FakeDesktopProcess process) ->
                    {
                        final Folder currentFolder = process.getCurrentFolder();
                        final File file = currentFolder.getFile("file").await();
                        final VSCodeSettingsJsonFile settingsJsonFile = VSCodeSettingsJsonFile.get(file);
                        settingsJsonFile.setContentsAsString(contents).await();

                        final JSONObject settingsJson = settingsJsonFile.getContentsAsJSONObject().await();
                        test.assertEqual(expected, settingsJson);
                    });
                };

                getContentsAsJSONObjectTest.run(
                    "{}",
                    JSONObject.create());
                getContentsAsJSONObjectTest.run(
                    Strings.join("\n", Iterable.create(
                        "{",
                            "\"java.project.sourcePaths\": [",
                                "\"tests\",",
                                "\"sources\"",
                            "],",
                            "\"java.project.referencedLibraries\": [",
                                "\"C:/qub/qub/lib-java/versions/178/lib-java.jar\",",
                                "\"C:/qub/qub/lib-java/versions/178/lib-java.tests.jar\"",
                            "],",
                            "\"java.foldingRange.enabled\": false",
                        "}")),
                    JSONObject.create()
                        .setArray("java.project.sourcePaths", JSONArray.create(
                            JSONString.get("tests"),
                            JSONString.get("sources")))
                        .setArray("java.project.referencedLibraries", JSONArray.create(
                            JSONString.get("C:/qub/qub/lib-java/versions/178/lib-java.jar"),
                            JSONString.get("C:/qub/qub/lib-java/versions/178/lib-java.tests.jar")))
                        .setBoolean("java.foldingRange.enabled", false));
            });
        });
    }
}
