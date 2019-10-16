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
    fun `Le modèle ObjectDetectedResponse peut être désérializé de sa propre sérialisation` () {
        val rec = Rectangle(1,2,3,4)
        val objDetected = ObjectDetected(rec, "Type", 0.8F)

        val rec2 = Rectangle(5,6,7,8)
        val objDetected2 = ObjectDetected(rec2, "Type2", 0.1F)
        
        val objList = listOf(objDetected, objDetected2)
        val response = ObjectDetectedResponse(objList)

        val jsonb : Jsonb = JsonbBuilder.create();
        val result : String = jsonb.toJson(response);
        val resultDeserialized = jsonb.fromJson(result, ObjectDetectedResponse::class.java)

        Assertions.assertEquals(response, resultDeserialized)
    }

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

         Assertions.assertEquals("{\"objects\":[{\"rectangle\":{\"y\":2,\"x\":1,\"w\":3,\"h\":4},\"object\":\"Type\",\"confidence\":0.8},{\"rectangle\":{\"y\":6,\"x\":5,\"w\":7,\"h\":8},\"object\":\"Type2\",\"confidence\":0.1}]}", result)
    }

    @Test
    fun `Une chaine json de retour de l'API DetectObject est correctement désérialisée` () {
        val objectDetectResponse = "{\"objects\": [{\"rectangle\": {\"x\": 0,\"y\": 0,\"w\": 50,\"h\": 50},\"object\": \"tree\",\"confidence\": 0.9,\"parent\": {\"object\": \"plant\",\"confidence\": 0.95}}],\"requestId\": \"1ad0e45e-b7b4-4be3-8042-53be96103337\",\"metadata\": {\"width\": 100,\"height\": 100,\"format\": \" Jpeg\"}}"

        val jsonb = JsonbBuilder.create()
 
        val objDetected = jsonb.fromJson(objectDetectResponse, ObjectDetectedResponse::class.java)

        val expectedRect = Rectangle(0, 0, 50, 50)
        val expectedObjDetected = ObjectDetected(expectedRect, "tree", 0.9F)
        val expectedResponse = ObjectDetectedResponse(listOf(expectedObjDetected))

        Assertions.assertEquals(expectedResponse, objDetected)
        
    }

}