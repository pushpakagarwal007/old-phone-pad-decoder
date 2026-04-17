package org.example.sevice.test;

import org.example.sevice.OldPhonePadService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OldPhonePadTest {
    private OldPhonePadService service;

    @BeforeMethod
    public void setUp() {
        service = new OldPhonePadService();
    }

    // ================= CORE FUNCTIONAL =================
    @DataProvider(name = "coreData")
    public Object[][] coreData() {
        return new Object[][]{
                {"2#", "A"},
                {"22#", "B"},
                {"222#", "C"},
                {"3#", "D"},
                {"33#", "E"},
                {"7777#", "S"},
                {"9999#", "Z"},
                {"23#", "AD"},
                {"234#", "ADG"}
        };
    }

    @Test(dataProvider = "coreData", groups = {"smoke", "core"})
    public void testCoreFunctionality(String input, String expected) {
        String actual = service.convert(input);
        Assert.assertEquals(actual, expected, "Failed for input: " + input);
    }

    // ================= CYCLING =================
    @DataProvider(name = "cyclingData")
    public Object[][] cyclingData() {
        return new Object[][]{
                {"2222#", "A"},
                {"77777#", "P"},
                {"99999#", "W"}
        };
    }

    @Test(dataProvider = "cyclingData", groups = {"regression"})
    public void testCyclingBehavior(String input, String expected) {
        Assert.assertEquals(service.convert(input), expected);
    }

    // ================= SPACE / PAUSE =================
    @DataProvider(name = "spaceData")
    public Object[][] spaceData() {
        return new Object[][]{
                {"2 2#", "AA"},
                {"22 2#", "BA"},
                {"4433555 555666#", "HELLO"}
        };
    }

    @Test(dataProvider = "spaceData", groups = {"smoke", "regression"})
    public void testPauseHandling(String input, String expected) {
        Assert.assertEquals(service.convert(input), expected);
    }

    // ================= BACKSPACE =================
    @DataProvider(name = "backspaceData")
    public Object[][] backspaceData() {
        return new Object[][]{
                {"2*#", ""},
                {"22*#", ""},
                {"4433555 555666*#", "HELL"},
                {"4433555 555666**#", "HEL"},
                {"4433555 555666***#", "HE"}
        };
    }

    @Test(dataProvider = "backspaceData", groups = {"regression"})
    public void testBackspace(String input, String expected) {
        Assert.assertEquals(service.convert(input), expected);
    }

    // ================= BACKSPACE EDGE =================
    @DataProvider(name = "backspaceEdgeData")
    public Object[][] backspaceEdgeData() {
        return new Object[][]{
                {"*#", ""},
                {"*#", ""},
                {"**2#", "A"}
        };
    }

    @Test(dataProvider = "backspaceEdgeData", groups = {"edge"})
    public void testBackspaceEdgeCases(String input, String expected) {
        Assert.assertEquals(service.convert(input), expected);
    }

    // ================= ZERO HANDLING =================
    @DataProvider(name = "zeroData")
    public Object[][] zeroData() {
        return new Object[][]{
                {"0#", " "},
                {"00#", " "},
                {"0 0#", "  "}
        };
    }

    @Test(dataProvider = "zeroData", groups = {"regression"})
    public void testZeroHandling(String input, String expected) {
        Assert.assertEquals(service.convert(input), expected);
    }

    // ================= MIXED INPUT =================
    @DataProvider(name = "mixedData")
    public Object[][] mixedData() {
        return new Object[][]{
                {"2 22*#", "A"},
                {"33 3*#", "E"},
                {"7777 0 666*#", "S "}
        };
    }

    @Test(dataProvider = "mixedData", groups = {"regression"})
    public void testMixedScenarios(String input, String expected) {
        Assert.assertEquals(service.convert(input), expected);
    }

    // ================= END CONDITIONS =================
    @DataProvider(name = "endData")
    public Object[][] endData() {
        return new Object[][]{
                {"23", "AD"},
                {"#", ""},
                {"2#23#", "A"}
        };
    }

    @Test(dataProvider = "endData", groups = {"edge"})
    public void testTerminationBehavior(String input, String expected) {
        Assert.assertEquals(service.convert(input), expected);
    }

    // ================= INVALID INPUT =================
    @DataProvider(name = "invalidData")
    public Object[][] invalidData() {
        return new Object[][]{
                {"1#", ""},
                {"10#", " "},
                {"2a3#", "AD"}
        };
    }

    @Test(dataProvider = "invalidData", groups = {"edge"})
    public void testInvalidInputs(String input, String expected) {
        Assert.assertEquals(service.convert(input), expected);
    }

    // ================= STRESS =================
    @DataProvider(name = "stressData")
    public Object[][] stressData() {
        return new Object[][]{
                {"222222222222#", "C"},
                {"4433555 555666096667775553#", "HELLO WORLD"}
        };
    }

    @Test(dataProvider = "stressData", groups = {"performance"})
    public void testStressCases(String input, String expected) {
        Assert.assertEquals(service.convert(input), expected);
    }

    // ================= NEGATIVE TEST =================
    @Test(expectedExceptions = RuntimeException.class, groups = {"edge"})
    public void testNullInput() {
        service.convert(null);
    }

    @Test(expectedExceptions = RuntimeException.class, groups = {"edge"})
    public void testEmptyInput() {
        service.convert("");
    }
}