package qub;

public interface VSCodeLaunchConfigurationPresentationJsonTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(VSCodeLaunchConfigurationPresentationJson.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final VSCodeLaunchConfigurationPresentationJson configuration = VSCodeLaunchConfigurationPresentationJson.create();
                test.assertNotNull(configuration);
                test.assertEqual(JSONObject.create(), configuration.toJson());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> VSCodeLaunchConfigurationPresentationJson.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JSONObject json = JSONObject.create();
                    final VSCodeLaunchConfigurationPresentationJson configuration = VSCodeLaunchConfigurationPresentationJson.create(json);
                    test.assertNotNull(configuration);
                    test.assertSame(json, configuration.toJson());
                });
            });

            runner.testGroup("getHidden()", () ->
            {
                final Action2<VSCodeLaunchConfigurationPresentationJson,Boolean> getHiddenTest = (VSCodeLaunchConfigurationPresentationJson configuration, Boolean expected) ->
                {
                    runner.test("with " + configuration.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, configuration.getHidden());
                    });
                };

                getHiddenTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(),
                    null);
                getHiddenTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(JSONObject.create()
                        .setNumber("hidden", 5)),
                    null);
                getHiddenTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(JSONObject.create()
                        .setString("hidden", "")),
                    null);
                getHiddenTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(JSONObject.create()
                        .setBoolean("hidden", false)),
                    false);
                getHiddenTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(JSONObject.create()
                        .setBoolean("hidden", true)),
                    true);
            });

            runner.testGroup("setHidden(Boolean)", () ->
            {
                final Action1<Boolean> setHiddenTest = (Boolean hidden) ->
                {
                    runner.test("with " + hidden, (Test test) ->
                    {
                        final VSCodeLaunchConfigurationPresentationJson configuration = VSCodeLaunchConfigurationPresentationJson.create();
                        final VSCodeLaunchConfigurationPresentationJson setHiddenResult = configuration.setHidden(hidden);
                        test.assertSame(configuration, setHiddenResult);
                        test.assertEqual(hidden, configuration.getHidden());
                    });
                };

                setHiddenTest.run(false);
                setHiddenTest.run(true);
            });

            runner.testGroup("getGroup()", () ->
            {
                final Action2<VSCodeLaunchConfigurationPresentationJson,String> getGroupTest = (VSCodeLaunchConfigurationPresentationJson configuration, String expected) ->
                {
                    runner.test("with " + configuration.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, configuration.getGroup());
                    });
                };

                getGroupTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(),
                    null);
                getGroupTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(JSONObject.create()
                        .setNumber("group", 5)),
                    null);
                getGroupTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(JSONObject.create()
                        .setString("group", "")),
                    "");
                getGroupTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(JSONObject.create()
                        .setString("group", "java")),
                    "java");
                getGroupTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create()
                        .setGroup(""),
                    "");
                getGroupTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create()
                        .setGroup("java"),
                    "java");
            });

            runner.testGroup("setGroup(String)", () ->
            {
                final Action2<String,Throwable> setGroupErrorTest = (String group, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(group), (Test test) ->
                    {
                        final VSCodeLaunchConfigurationPresentationJson configuration = VSCodeLaunchConfigurationPresentationJson.create();
                        test.assertThrows(() -> configuration.setGroup(group),
                            expected);
                        test.assertNull(configuration.getGroup());
                    });
                };

                setGroupErrorTest.run(null, new PreConditionFailure("group cannot be null."));

                final Action1<String> setGroupTest = (String group) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(group), (Test test) ->
                    {
                        final VSCodeLaunchConfigurationPresentationJson configuration = VSCodeLaunchConfigurationPresentationJson.create();
                        final VSCodeLaunchConfigurationPresentationJson setGroupResult = configuration.setGroup(group);
                        test.assertSame(configuration, setGroupResult);
                        test.assertEqual(group, configuration.getGroup());
                    });
                };

                setGroupTest.run("hello");
                setGroupTest.run("java");
            });

            runner.testGroup("getOrder()", () ->
            {
                final Action2<VSCodeLaunchConfigurationPresentationJson,Integer> getOrderTest = (VSCodeLaunchConfigurationPresentationJson configuration, Integer expected) ->
                {
                    runner.test("with " + configuration.toString(), (Test test) ->
                    {
                        test.assertEqual(expected, configuration.getOrder());
                    });
                };

                getOrderTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(),
                    null);
                getOrderTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(JSONObject.create()
                        .setString("order", "")),
                    null);
                getOrderTest.run(
                    VSCodeLaunchConfigurationPresentationJson.create(JSONObject.create()
                        .setNumber("order", 5)),
                    5);
            });

            runner.testGroup("setOrder(Integer)", () ->
            {
                final Action1<Integer> setOrderTest = (Integer order) ->
                {
                    runner.test("with " + order, (Test test) ->
                    {
                        final VSCodeLaunchConfigurationPresentationJson configuration = VSCodeLaunchConfigurationPresentationJson.create();
                        final VSCodeLaunchConfigurationPresentationJson setOrderResult = configuration.setOrder(order);
                        test.assertSame(configuration, setOrderResult);
                        test.assertEqual(order, configuration.getOrder());
                    });
                };

                setOrderTest.run(-1);
                setOrderTest.run(0);
                setOrderTest.run(1);
                setOrderTest.run(2);
                setOrderTest.run(100);
            });
        });
    }
}
