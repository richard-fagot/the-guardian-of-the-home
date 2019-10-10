import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName

class MainTest {

    @Test
    @DisplayName("Mon cas de test")
    fun `testFail devrait faire ça`() {
        Assertions.assertTrue(false)
    }

}