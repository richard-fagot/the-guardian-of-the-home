package home.guardian.azure

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import home.guardian.azure.ObjectDetectedResponse
import home.guardian.azure.Rectangle
import home.guardian.azure.ObjectDetected

import javax.json.bind.Jsonb
import javax.json.bind.JsonbBuilder
import javax.json.bind.JsonbConfig
import javax.json.bind.config.PropertyOrderStrategy

class ObjectDetectedResponseTest {
    @Test
    fun `La configuration de JSON-B sérialize correctement la réponse ObjectDetectedResponse` () {
        val rec = Rectangle(1,2,3,4)
        val objDetected = ObjectDetected(rec, "Type", 0.8F)

        val rec2 = Rectangle(5,6,7,8)
        val objDetected2 = ObjectDetected(rec2, "Type2", 0.1F)
        
        val response = ObjectDetectedResponse(listOf(objDetected, objDetected2))

        val config : JsonbConfig = JsonbConfig().withFormatting(false)
                                                .withPropertyOrderStrategy(PropertyOrderStrategy.REVERSE)

        val jsonb : Jsonb = JsonbBuilder.create(config);
        val result : String = jsonb.toJson(response);

        println(result)

         Assertions.assertEquals("{\"objects\":[{\"rectangle\":{\"y\":2,\"x\":1,\"w\":3,\"h\":4},\"object\":\"Type\",\"confidence\":0.8},{\"rectangle\":{\"y\":6,\"x\":5,\"w\":7,\"h\":8},\"object\":\"Type2\",\"confidence\":0.1}]}", result)
    }
}