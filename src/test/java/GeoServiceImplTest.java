import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

class GeoServiceImplTest {
    GeoService geoService;

    @BeforeEach
    public void setUp() {
        geoService = new GeoServiceImpl();
    }

    @AfterEach
    public void tearDown() {
        geoService = null;
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testByIp(String ip, Country expectedCountry) {

        final Country resultCountry = geoService.byIp(ip).getCountry();

        Assertions.assertEquals(expectedCountry, resultCountry);
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("172.0.32.11", RUSSIA),
                Arguments.of("96.44.183.149", USA),
                Arguments.of("172.", RUSSIA),
                Arguments.of("96.", USA),
                Arguments.of("127.0.0.1", null));
    }

    @ParameterizedTest
    @MethodSource("expectedFactory")
    public void testByCoordinates(double latitude, double longitude) {
        Assertions.assertThrows(RuntimeException.class,
                () -> geoService.byCoordinates(latitude, longitude));
    }

    private static Stream<Arguments> expectedFactory() {
        return Stream.of(Arguments.of(250.2, 16.3),
                Arguments.of(13.5, 84.8));
    }
}