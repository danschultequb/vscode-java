package qub;

public interface VSCodeLaunchJsonTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(VSCodeLaunchJson.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final VSCodeLaunchJson launchJson = VSCodeLaunchJson.create();
                test.assertNotNull(launchJson);
                test.assertEqual(JSONObject.create(), launchJson.toJson());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> VSCodeLaunchJson.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JSONObject json = JSONObject.create();
                    final VSCodeLaunchJson launchJson = VSCodeLaunchJson.create(json);
                    test.assertNotNull(launchJson);
                    test.assertSame(json, launchJson.toJson());
                });
            });

            runner.testGroup("parse(File)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> VSCodeLaunchJson.parse(null),
                        new PreConditionFailure("file cannot be null."));
                });

                runner.test("with non-existing file",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final File file = process.getCurrentFolder().getFile("doesntExist").await();
                    test.assertThrows(() -> VSCodeLaunchJson.parse(file).await(),
                        new FileNotFoundException(file));
                });

                runner.test("with existing empty file",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final File file = process.getCurrentFolder().getFile("empty").await();
                    file.create().await();

                    test.assertThrows(() -> VSCodeLaunchJson.parse(file).await(),
                        new ParseException("Missing object left curly bracket ('{')."));
                });

                runner.test("with existing JSON Object file",
                    (TestResources resources) -> Tuple.create(resources.createFakeDesktopProcess()),
                    (Test test, FakeDesktopProcess process) ->
                {
                    final File file = process.getCurrentFolder().getFile("object.json").await();
                    file.setContentsAsString("{}").await();

                    VSCodeLaunchJson launchJson = VSCodeLaunchJson.parse(file).await();
                    test.assertNotNull(launchJson);
                    test.assertEqual(JSONObject.create(), launchJson.toJson());
                });
            });

            runner.testGroup("getVersion()", () ->
            {
                final Action2<VSCodeLaunchJson,String> getVersionTest = (VSCodeLaunchJson launchJson, String expected) ->
                {
                    runner.test("with " + launchJson.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, launchJson.getVersion());
                    });
                };

                getVersionTest.run(VSCodeLaunchJson.create(), null);
                getVersionTest.run(VSCodeLaunchJson.create(JSONObject.create().setBoolean("version", false)), null);
                getVersionTest.run(VSCodeLaunchJson.create().setVersion(""), "");
                getVersionTest.run(VSCodeLaunchJson.create().setVersion("hello"), "hello");
                getVersionTest.run(VSCodeLaunchJson.create().setVersion("0.2.0"), "0.2.0");
            });

            runner.testGroup("setVersion(String)", () ->
            {
                final Action2<String,Throwable> setVersionErrorTest = (String version, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(version), (Test test) ->
                    {
                        final VSCodeLaunchJson launchJson = VSCodeLaunchJson.create();
                        test.assertThrows(() -> launchJson.setVersion(version),
                            expected);
                        test.assertNull(launchJson.getVersion());
                    });
                };

                setVersionErrorTest.run(null, new PreConditionFailure("version cannot be null."));

                final Action1<String> setVersionTest = (String version) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(version), (Test test) ->
                    {
                        final VSCodeLaunchJson launchJson = VSCodeLaunchJson.create();
                        final VSCodeLaunchJson setVersionResult = launchJson.setVersion(version);
                        test.assertSame(launchJson, setVersionResult);
                        test.assertEqual(version, launchJson.getVersion());
                    });
                };

                setVersionTest.run("");
                setVersionTest.run("hello");
                setVersionTest.run("0.2.0");
            });

            runner.testGroup("getConfigurations()", () ->
            {
                final Action2<VSCodeLaunchJson,Iterable<VSCodeLaunchConfigurationJson>> getConfigurationsTest = (VSCodeLaunchJson launchJson, Iterable<VSCodeLaunchConfigurationJson> expected) ->
                {
                    runner.test("with " + launchJson.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, launchJson.getConfigurations());
                    });
                };

                getConfigurationsTest.run(
                    VSCodeLaunchJson.create(),
                    Iterable.create());
                getConfigurationsTest.run(
                    VSCodeLaunchJson.create(JSONObject.create().setString("configurations", "hello")),
                    Iterable.create());
                getConfigurationsTest.run(
                    VSCodeLaunchJson.create(JSONObject.create().setArray("configurations", JSONArray.create())),
                    Iterable.create());
                getConfigurationsTest.run(
                    VSCodeLaunchJson.create(JSONObject.create().setArray("configurations", JSONArray.create(JSONObject.create()))),
                    Iterable.create(VSCodeLaunchConfigurationJson.create()));
            });

            runner.testGroup("setConfigurations(Iterable<VSCodeLaunchConfigurationJson>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final VSCodeLaunchJson launchJson = VSCodeLaunchJson.create();
                    test.assertThrows(() -> launchJson.setConfigurations(null),
                        new PreConditionFailure("configurations cannot be null."));
                    test.assertEqual(Iterable.create(), launchJson.getConfigurations());
                });

                final Action1<Iterable<VSCodeLaunchConfigurationJson>> setConfigurationsTest = (Iterable<VSCodeLaunchConfigurationJson> configurations) ->
                {
                    runner.test("with " + configurations.toString(), (Test test) ->
                    {
                        final VSCodeLaunchJson launchJson = VSCodeLaunchJson.create();
                        final VSCodeLaunchJson setConfigurationsResult = launchJson.setConfigurations(configurations);
                        test.assertSame(launchJson, setConfigurationsResult);
                        test.assertEqual(configurations, launchJson.getConfigurations());
                    });
                };
                
                setConfigurationsTest.run(Iterable.create());
                setConfigurationsTest.run(Iterable.create(VSCodeLaunchConfigurationJson.create()));
            });
        });
    }
}
