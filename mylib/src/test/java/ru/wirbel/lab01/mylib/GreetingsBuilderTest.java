package ru.wirbel.lab01.mylib;

import org.junit.*;

/**
 * Unit test for GreetingsBuilder class.
 */
public class GreetingsBuilderTest {

    // Выполняется один раз до выполнения тест-методов, например, для соединения с БД, инициализации пула соединений
    @BeforeClass
    public static void runOnceBeforeClass() {
        System.out.println("@BeforeClass - runOnceBeforeClass");
    }

    // Выполняется один раз уже после выполнения всех тест-методов, например, для закрытия соединения
    // и освобождения других ресурсов
    @AfterClass
    public static void runOnceAfterClass() {
        System.out.println("@AfterClass - runOnceAfterClass");
    }

    // Выполняется перед каждым тест-методом
    // Позволяет, например, создать новый экземпляр объекта, необходимого тест-методу
    @Before
    public void runBeforeTestMethod() {
        System.out.println("@Before - runBeforeTestMethod");
    }

    // Выполняется после каждого тест-метода
    @After
    public void runAfterTestMethod() {
        System.out.println("@After - runAfterTestMethod");
    }

    @Test
    public void testGreetingsBuilder1() {
        System.out.println("@Test - run testGreetingsBuilder1");

        String expectedValue = "Hello, World!";

        GreetingsBuilder greetings = new GreetingsBuilder();
        String value = greetings.to("World")
                .withLanguage(Greetings.LANGUAGE_EN)
                .withStyle(Greetings.STYLE_FORMAL)
                .build();

        Assert.assertEquals("English formal greetings check", expectedValue, value);
    }

    @Test
    public void testGreetingsBuilder2() {
        System.out.println("@Test - run testGreetingsBuilder2");
        Assert.assertTrue(true);
    }
}
