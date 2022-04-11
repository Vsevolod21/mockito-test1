import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

class LocalizationServiceImplTest {
    LocalizationService sut;

    @BeforeEach
    public void setUp() {
        sut = new LocalizationServiceImpl();
    }

    @AfterEach
    public void tearDown() {
        sut = null;
    }

    @ParameterizedTest
    @MethodSource("expectedFactory")
    public void testLocale(Country country, String expectedMessage) {

        final String resultMessage = sut.locale(country);

        Assertions.assertEquals(expectedMessage, resultMessage);
    }

    private static Stream<Arguments> expectedFactory() {
        return Stream.of(Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(USA, "Welcome"));
    }
}