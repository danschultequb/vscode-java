package qub;

public interface VSCodeSettingsJsonTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(VSCodeSettingsJson.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final VSCodeSettingsJson settingsJson = VSCodeSettingsJson.create();
                test.assertNotNull(settingsJson);
                test.assertEqual(JSONObject.create(), settingsJson.toJson());
            });

            runner.testGroup("create(JSONObject)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> VSCodeSettingsJson.create(null),
                        new PreConditionFailure("json cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final JSONObject json = JSONObject.create();
                    final VSCodeSettingsJson settingsJson = VSCodeSettingsJson.create(json);
                    test.assertNotNull(settingsJson);
                    test.assertSame(json, settingsJson.toJson());
                });
            });
        });
    }
}
