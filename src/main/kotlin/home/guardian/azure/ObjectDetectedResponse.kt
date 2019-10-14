package home.guardian.azure

import javax.json.bind.annotation.JsonbProperty

data class Rectangle (val x : Int, val y : Int, val w : Int, val h : Int)

data class ObjectDetected constructor (val rect : Rectangle, val objType : String, val conf : Float) {
    @JsonbProperty("object")
    val objectType : String

    val rectangle = rect

    init {

        this.objectType = objType
    }
    
    val confidence = conf
}

class ObjectDetectedResponse constructor(objDetected : List<ObjectDetected>) {
    @JsonbProperty("objects") 
    val objectsDetected = objDetected
} 