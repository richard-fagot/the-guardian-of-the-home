package home.guardian

import java.net.URI
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import org.json.JSONArray
import org.json.JSONObject

class Main {
    
    val subscriptionKey = System.getenv("SUBSCRIPTION_KEY") ?: ""

    val uriBase =
        "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect"

    val imageWithFaces =
        "{\"url\":\"https://upload.wikimedia.org/wikipedia/commons/9/9a/Congestion_caused_by_a_road_accident%2C_Algarve%2C_Portugal.jpg\"}"

    val faceAttributes =
        "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise"

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var application = Main()
            application.detectObjects()
        }
    }


    fun detectObjects() {
        val httpclient : HttpClient = HttpClients.createDefault();

        try
        {
            val builder : URIBuilder = URIBuilder("https://testvisionkotlin.cognitiveservices.azure.com/vision/v2.0/detect");

            //builder.setParameter("visualFeatures", "Categories");
            //builder.setParameter("details", "{string}");
            //builder.setParameter("language", "en");

            val uri : URI = builder.build();
            val request : HttpPost = HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);


            // Request body
            val reqEntity : StringEntity = StringEntity(imageWithFaces);
            request.setEntity(reqEntity);

            val response : HttpResponse = httpclient.execute(request);
            val entityNullable : HttpEntity? = response.getEntity();
            val entity = entityNullable ?: return

            System.out.println(EntityUtils.toString(entity));
        }
        catch (e : Exception)
        {
            System.out.println(e.message);
        }
    }



    fun faceDetection() {
        var httpclient : HttpClient = HttpClientBuilder.create().build()

        try {
            var builder : URIBuilder = URIBuilder(uriBase)

            // Request parameters. All of them are optional.
            builder.setParameter("returnFaceId", "true")
            builder.setParameter("returnFaceLandmarks", "false")
            builder.setParameter("returnFaceAttributes", faceAttributes)

            // Prepare the URI for the REST API call.
            var uri : URI = builder.build()
            var request : HttpPost = HttpPost(uri)

            // Request headers.
            request.setHeader("Content-Type", "application/json")
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey)

            // Request body.
            var reqEntity : StringEntity = StringEntity(imageWithFaces)
            request.setEntity(reqEntity)

            // Execute the REST API call and get the response entity.
            var response : HttpResponse = httpclient.execute(request)
            
            var entityNullable : HttpEntity? = response.getEntity()
            var entity = entityNullable ?: return

            
            // Format and display the JSON response.
            System.out.println("REST Response:\n")

            var jsonString : String = EntityUtils.toString(entity).trim()
            if (jsonString.get(0) == '[') {
                var jsonArray : JSONArray = JSONArray(jsonString)
                System.out.println(jsonArray.toString(2))
            }
            else if (jsonString.get(0) == '{') {
                var jsonObject : JSONObject = JSONObject(jsonString)
                System.out.println(jsonObject.toString(2))
            } else {
                System.out.println(jsonString)
            }
            

        } catch (e : Exception) {
            // Display error message.
            System.out.println(e.message)
        }
    }
}