package qub;

public interface VSCodeTaskGroupJsonTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(VSCodeTaskGroupJson.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final VSCodeTaskGroupJson json = VSCodeTaskGroupJson.create();
                test.assertNotNull(json);
                test.assertEqual(JSONObject.create(), json.toJson());
                test.assertNull(json.getKind());
                test.assertNull(json.getIsDefault());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> VSCodeTaskGroupJson.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JSONObject json = JSONObject.create();
                    final VSCodeTaskGroupJson taskGroupJson = VSCodeTaskGroupJson.create(json);
                    test.assertNotNull(taskGroupJson);
                    test.assertSame(json, taskGroupJson.toJson());
                    test.assertNull(taskGroupJson.getKind());
                    test.assertNull(taskGroupJson.getIsDefault());
                });
            });

            runner.testGroup("setKind(String)", () ->
            {
                final Action2<String,Throwable> setKindErrorTest = (String kind, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(kind), (Test test) ->
                    {
                        final VSCodeTaskGroupJson json = VSCodeTaskGroupJson.create();
                        test.assertThrows(() -> json.setKind(kind),
                            expected);
                        test.assertEqual(JSONObject.create(), json.toJson());
                    });
                };

                setKindErrorTest.run(null, new PreConditionFailure("kind cannot be null."));

                final Action1<String> setKindTest = (String kind) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(kind), (Test test) ->
                    {
                        final VSCodeTaskGroupJson json = VSCodeTaskGroupJson.create();
                        final VSCodeTaskGroupJson setKindResult = json.setKind(kind);
                        test.assertSame(json, setKindResult);
                        test.assertEqual(kind, json.getKind());
                        test.assertEqual(
                            JSONObject.create().setString("kind", kind),
                            json.toJson());
                    });
                };

                setKindTest.run("build");
                setKindTest.run("test");
                setKindTest.run("none");
                setKindTest.run("spam");
            });

            runner.testGroup("setIsDefault(String)", () ->
            {
                final Action1<Boolean> setIsDefaultTest = (Boolean isDefault) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(isDefault), (Test test) ->
                    {
                        final VSCodeTaskGroupJson json = VSCodeTaskGroupJson.create();
                        final VSCodeTaskGroupJson setIsDefaultResult = json.setIsDefault(isDefault);
                        test.assertSame(json, setIsDefaultResult);
                        test.assertEqual(isDefault, json.getIsDefault());
                        test.assertEqual(
                            JSONObject.create().setBoolean("isDefault", isDefault),
                            json.toJson());
                    });
                };

                setIsDefaultTest.run(false);
                setIsDefaultTest.run(true);
            });
        });
    }
}
