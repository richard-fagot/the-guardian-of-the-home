package home.guardian.azure

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import home.guardian.azure.ObjectDetectedResponse
import home.guardian.azure.Rectangle
import home.guardian.azure.ObjectDetected

import javax.json.bind.Jsonb
import javax.json.bind.JsonbBuilder
import javax.json.bind.JsonbConfig

class ObjectDetectedResponseTest {
    @Test
    fun `La configuration de JSON-B sérialize correctement la réponse ObjectDetectedResponse` () {
        val rec = Rectangle(1,2,3,4)
        val objDetected = ObjectDetected(rec, "Type", 0.8F)

        val rec2 = Rectangle(5,6,7,8)
        val objDetected2 = ObjectDetected(rec2, "Type2", 0.1F)
        
        val response = ObjectDetectedResponse(listOf(objDetected, objDetected2))

        val config : JsonbConfig = JsonbConfig().withFormatting(true)
        val jsonb : Jsonb = JsonbBuilder.create(config);
        val result : String = jsonb.toJson(response);

        println(result)

         Assertions.assertEquals("{\"url\":\"http://example.com\"}", result)
    }
}