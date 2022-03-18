package qub;

public interface VSCodeTaskJsonTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(VSCodeTaskJson.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final VSCodeTaskJson taskJson = VSCodeTaskJson.create();
                test.assertNotNull(taskJson);
                test.assertEqual(JSONObject.create(), taskJson.toJson());
                test.assertEqual(Iterable.create(), taskJson.getArgs());
                test.assertNull(taskJson.getCommand());
                test.assertNull(taskJson.getLabel());
                test.assertNull(taskJson.getType());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> VSCodeTaskJson.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JSONObject json = JSONObject.create();
                    final VSCodeTaskJson taskJson = VSCodeTaskJson.create(json);
                    test.assertNotNull(taskJson);
                    test.assertSame(json, taskJson.toJson());
                    test.assertEqual(Iterable.create(), taskJson.getArgs());
                    test.assertNull(taskJson.getCommand());
                    test.assertNull(taskJson.getLabel());
                    test.assertNull(taskJson.getType());
                });
            });

            runner.testGroup("setArgs(Iterable<String>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final VSCodeTaskJson taskJson = VSCodeTaskJson.create();
                    test.assertThrows(() -> taskJson.setArgs(null),
                        new PreConditionFailure("args cannot be null."));
                    test.assertEqual(Iterable.create(), taskJson.getArgs());
                });

                final Action1<Iterable<String>> setArgsTest = (Iterable<String> args) ->
                {
                    runner.test("with " + args, (Test test) ->
                    {
                        final VSCodeTaskJson taskJson = VSCodeTaskJson.create();
                        final VSCodeTaskJson setArgsResult = taskJson.setArgs(args);
                        test.assertSame(taskJson, setArgsResult);
                        test.assertEqual(args, taskJson.getArgs());
                    });
                };

                setArgsTest.run(Iterable.create());
                setArgsTest.run(Iterable.create("a"));
                setArgsTest.run(Iterable.create("a", "b"));
            });

            runner.testGroup("setLabel(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final VSCodeTaskJson taskJson = VSCodeTaskJson.create();
                    test.assertThrows(() -> taskJson.setLabel(null),
                        new PreConditionFailure("label cannot be null."));
                    test.assertNull(taskJson.getLabel());
                });

                final Action1<String> setLabelTest = (String label) ->
                {
                    runner.test("with " + label, (Test test) ->
                    {
                        final VSCodeTaskJson taskJson = VSCodeTaskJson.create();
                        final VSCodeTaskJson setLabelResult = taskJson.setLabel(label);
                        test.assertSame(taskJson, setLabelResult);
                        test.assertEqual(label, taskJson.getLabel());
                    });
                };

                setLabelTest.run("");
                setLabelTest.run("a");
                setLabelTest.run("b");
            });

            runner.testGroup("setType(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final VSCodeTaskJson taskJson = VSCodeTaskJson.create();
                    test.assertThrows(() -> taskJson.setType(null),
                        new PreConditionFailure("type cannot be null."));
                    test.assertNull(taskJson.getType());
                });

                final Action1<String> setTypeTest = (String type) ->
                {
                    runner.test("with " + type, (Test test) ->
                    {
                        final VSCodeTaskJson taskJson = VSCodeTaskJson.create();
                        final VSCodeTaskJson setTypeResult = taskJson.setType(type);
                        test.assertSame(taskJson, setTypeResult);
                        test.assertEqual(type, taskJson.getType());
                    });
                };

                setTypeTest.run("");
                setTypeTest.run("a");
                setTypeTest.run("b");
            });

            runner.testGroup("setCommand(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final VSCodeTaskJson taskJson = VSCodeTaskJson.create();
                    test.assertThrows(() -> taskJson.setCommand(null),
                        new PreConditionFailure("command cannot be null."));
                    test.assertNull(taskJson.getCommand());
                });

                final Action1<String> setCommandTest = (String command) ->
                {
                    runner.test("with " + command, (Test test) ->
                    {
                        final VSCodeTaskJson taskJson = VSCodeTaskJson.create();
                        final VSCodeTaskJson setCommandResult = taskJson.setCommand(command);
                        test.assertSame(taskJson, setCommandResult);
                        test.assertEqual(command, taskJson.getCommand());
                    });
                };

                setCommandTest.run("");
                setCommandTest.run("a");
                setCommandTest.run("b");
            });
        });
    }
}