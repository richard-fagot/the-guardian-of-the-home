package home.guardian.azure

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import home.guardian.azure.Input

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

class InputTest {
    
    @Test
    fun `Azure Input is well serialized by JSON-B`() {
        val input = Input("http://example.com")

        val jsonb : Jsonb = JsonbBuilder.create();
        val result : String = jsonb.toJson(input);

        Assertions.assertEquals("{\"url\":\"http://example.com\"}", result)
    }
}