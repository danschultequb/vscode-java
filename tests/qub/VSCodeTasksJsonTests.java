package qub;

public interface VSCodeTasksJsonTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(VSCodeTasksJson.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final VSCodeTasksJson tasksJson = VSCodeTasksJson.create();
                test.assertNotNull(tasksJson);
                test.assertEqual(JSONObject.create(), tasksJson.toJson());
                test.assertEqual(Iterable.create(), tasksJson.getTasks());
                test.assertNull(tasksJson.getVersion());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> VSCodeTasksJson.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JSONObject json = JSONObject.create();
                    final VSCodeTasksJson tasksJson = VSCodeTasksJson.create(json);
                    test.assertNotNull(tasksJson);
                    test.assertSame(json, tasksJson.toJson());
                    test.assertEqual(Iterable.create(), tasksJson.getTasks());
                    test.assertNull(tasksJson.getVersion());
                });
            });

            runner.testGroup("setVersion(String)", () ->
            {
                final Action2<String,Throwable> setVersionErrorTest = (String version, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(version), (Test test) ->
                    {
                        final VSCodeTasksJson tasksJson = VSCodeTasksJson.create();
                        test.assertThrows(() -> tasksJson.setVersion(version),
                            expected);
                        test.assertNull(tasksJson.getVersion());
                    });
                };

                setVersionErrorTest.run(null, new PreConditionFailure("version cannot be null."));
                
                final Action1<String> setVersionTest = (String version) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(version), (Test test) ->
                    {
                        final VSCodeTasksJson tasksJson = VSCodeTasksJson.create();
                        final VSCodeTasksJson setVersionResult = tasksJson.setVersion(version);
                        test.assertSame(tasksJson, setVersionResult);
                        test.assertEqual(version, tasksJson.getVersion());
                    });
                };

                setVersionTest.run("");
                setVersionTest.run("a");
                setVersionTest.run("2.0.0");
            });

            runner.testGroup("setTasks(Iterable<VSCodeTaskJson>)", () ->
            {
                final Action2<Iterable<VSCodeTaskJson>,Throwable> setTasksErrorTest = (Iterable<VSCodeTaskJson> tasks, Throwable expected) ->
                {
                    runner.test("with " + tasks, (Test test) ->
                    {
                        final VSCodeTasksJson tasksJson = VSCodeTasksJson.create();
                        test.assertThrows(() -> tasksJson.setTasks(tasks),
                            expected);
                        test.assertEqual(Iterable.create(), tasksJson.getTasks());
                    });
                };

                setTasksErrorTest.run(null, new PreConditionFailure("tasks cannot be null."));

                final Action1<Iterable<VSCodeTaskJson>> setTasksTest = (Iterable<VSCodeTaskJson> tasks) ->
                {
                    runner.test("with " + tasks, (Test test) ->
                    {
                        final VSCodeTasksJson tasksJson = VSCodeTasksJson.create();
                        final VSCodeTasksJson setTasksResult = tasksJson.setTasks(tasks);
                        test.assertSame(tasksJson, setTasksResult);
                        test.assertEqual(tasks, tasksJson.getTasks());
                    });
                };

                setTasksTest.run(Iterable.create());
                setTasksTest.run(Iterable.create(VSCodeTaskJson.create()));
                setTasksTest.run(Iterable.create(VSCodeTaskJson.create().setLabel("1"), VSCodeTaskJson.create().setLabel("2")));
            });
        });
    }
}
