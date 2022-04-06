package qub;

public interface VSCodeLaunchConfigurationJsonTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(VSCodeLaunchConfigurationJson.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create();
                test.assertNotNull(configuration);
                test.assertEqual(JSONObject.create(), configuration.toJson());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> VSCodeLaunchConfigurationJson.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JSONObject json = JSONObject.create();
                    final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create(json);
                    test.assertNotNull(configuration);
                    test.assertSame(json, configuration.toJson());
                });
            });

            runner.testGroup("getType()", () ->
            {
                final Action2<VSCodeLaunchConfigurationJson,String> getTypeTest = (VSCodeLaunchConfigurationJson configuration, String expected) ->
                {
                    runner.test("with " + configuration.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, configuration.getType());
                    });
                };

                getTypeTest.run(
                    VSCodeLaunchConfigurationJson.create(),
                    null);
                getTypeTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setNumber("type", 5)),
                    null);
                getTypeTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setString("type", "")),
                    "");
                getTypeTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setString("type", "java")),
                    "java");
                getTypeTest.run(
                    VSCodeLaunchConfigurationJson.create()
                        .setType(""),
                    "");
                getTypeTest.run(
                    VSCodeLaunchConfigurationJson.create()
                        .setType("java"),
                    "java");
            });

            runner.testGroup("setType(String)", () ->
            {
                final Action2<String,Throwable> setTypeErrorTest = (String type, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(type), (Test test) ->
                    {
                        final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create();
                        test.assertThrows(() -> configuration.setType(type),
                            expected);
                        test.assertNull(configuration.getType());
                    });
                };

                setTypeErrorTest.run(null, new PreConditionFailure("type cannot be null."));

                final Action1<String> setTypeTest = (String type) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(type), (Test test) ->
                    {
                        final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create();
                        final VSCodeLaunchConfigurationJson setTypeResult = configuration.setType(type);
                        test.assertSame(configuration, setTypeResult);
                        test.assertEqual(type, configuration.getType());
                    });
                };

                setTypeTest.run("hello");
                setTypeTest.run("java");
            });

            runner.testGroup("getName()", () ->
            {
                final Action2<VSCodeLaunchConfigurationJson,String> getNameTest = (VSCodeLaunchConfigurationJson configuration, String expected) ->
                {
                    runner.test("with " + configuration.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, configuration.getName());
                    });
                };

                getNameTest.run(
                    VSCodeLaunchConfigurationJson.create(),
                    null);
                getNameTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setNumber("name", 5)),
                    null);
                getNameTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setString("name", "")),
                    "");
                getNameTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setString("name", "java")),
                    "java");
                getNameTest.run(
                    VSCodeLaunchConfigurationJson.create()
                        .setName(""),
                    "");
                getNameTest.run(
                    VSCodeLaunchConfigurationJson.create()
                        .setName("java"),
                    "java");
            });

            runner.testGroup("setName(String)", () ->
            {
                final Action2<String,Throwable> setNameErrorTest = (String name, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(name), (Test test) ->
                    {
                        final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create();
                        test.assertThrows(() -> configuration.setName(name),
                            expected);
                        test.assertNull(configuration.getName());
                    });
                };

                setNameErrorTest.run(null, new PreConditionFailure("name cannot be null."));

                final Action1<String> setNameTest = (String name) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(name), (Test test) ->
                    {
                        final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create();
                        final VSCodeLaunchConfigurationJson setNameResult = configuration.setName(name);
                        test.assertSame(configuration, setNameResult);
                        test.assertEqual(name, configuration.getName());
                    });
                };

                setNameTest.run("hello");
                setNameTest.run("java");
            });

            runner.testGroup("getRequest()", () ->
            {
                final Action2<VSCodeLaunchConfigurationJson,String> getRequestTest = (VSCodeLaunchConfigurationJson configuration, String expected) ->
                {
                    runner.test("with " + configuration.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, configuration.getRequest());
                    });
                };

                getRequestTest.run(
                    VSCodeLaunchConfigurationJson.create(),
                    null);
                getRequestTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setNumber("request", 5)),
                    null);
                getRequestTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setString("request", "")),
                    "");
                getRequestTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setString("request", "java")),
                    "java");
                getRequestTest.run(
                    VSCodeLaunchConfigurationJson.create()
                        .setRequest(""),
                    "");
                getRequestTest.run(
                    VSCodeLaunchConfigurationJson.create()
                        .setRequest("java"),
                    "java");
            });

            runner.testGroup("setRequest(String)", () ->
            {
                final Action2<String,Throwable> setRequestErrorTest = (String request, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(request), (Test test) ->
                    {
                        final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create();
                        test.assertThrows(() -> configuration.setRequest(request),
                            expected);
                        test.assertNull(configuration.getRequest());
                    });
                };

                setRequestErrorTest.run(null, new PreConditionFailure("request cannot be null."));

                final Action1<String> setRequestTest = (String request) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(request), (Test test) ->
                    {
                        final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create();
                        final VSCodeLaunchConfigurationJson setRequestResult = configuration.setRequest(request);
                        test.assertSame(configuration, setRequestResult);
                        test.assertEqual(request, configuration.getRequest());
                    });
                };

                setRequestTest.run("hello");
                setRequestTest.run("java");
                setRequestTest.run("launch");
                setRequestTest.run("attach");
            });

            runner.test("setRequestLaunch()", (Test test) ->
            {
                final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create();
                final VSCodeLaunchConfigurationJson setRequestLaunchResult = configuration.setRequestLaunch();
                test.assertSame(configuration, setRequestLaunchResult);
                test.assertEqual("launch", configuration.getRequest());
            });

            runner.testGroup("getCWD()", () ->
            {
                final Action2<VSCodeLaunchConfigurationJson,String> getCWDTest = (VSCodeLaunchConfigurationJson configuration, String expected) ->
                {
                    runner.test("with " + configuration.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, configuration.getCWD());
                    });
                };

                getCWDTest.run(
                    VSCodeLaunchConfigurationJson.create(),
                    null);
                getCWDTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setNumber("cwd", 5)),
                    null);
                getCWDTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setString("cwd", "")),
                    "");
                getCWDTest.run(
                    VSCodeLaunchConfigurationJson.create(JSONObject.create()
                        .setString("cwd", "java")),
                    "java");
                getCWDTest.run(
                    VSCodeLaunchConfigurationJson.create()
                        .setCWD(""),
                    "");
                getCWDTest.run(
                    VSCodeLaunchConfigurationJson.create()
                        .setCWD("java"),
                    "java");
            });

            runner.testGroup("setCWD(String)", () ->
            {
                final Action2<String,Throwable> setCWDErrorTest = (String cwd, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(cwd), (Test test) ->
                    {
                        final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create();
                        test.assertThrows(() -> configuration.setCWD(cwd),
                            expected);
                        test.assertNull(configuration.getCWD());
                    });
                };

                setCWDErrorTest.run(null, new PreConditionFailure("cwd cannot be null."));

                final Action1<String> setCWDTest = (String cwd) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(cwd), (Test test) ->
                    {
                        final VSCodeLaunchConfigurationJson configuration = VSCodeLaunchConfigurationJson.create();
                        final VSCodeLaunchConfigurationJson setCWDResult = configuration.setCWD(cwd);
                        test.assertSame(configuration, setCWDResult);
                        test.assertEqual(cwd, configuration.getCWD());
                    });
                };

                setCWDTest.run("hello");
                setCWDTest.run("${workspaceFolder}");
            });
        });
    }
}
