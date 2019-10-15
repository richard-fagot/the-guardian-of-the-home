package home.guardian.azure

import javax.json.bind.annotation.JsonbProperty
import javax.json.bind.annotation.JsonbCreator 
import javax.json.bind.annotation.JsonbTransient

data class Rectangle (val x : Int, val y : Int, val w : Int, val h : Int)

class ObjectDetected @JsonbCreator constructor (
    val rectangle : Rectangle,    // le modificateur val provoque la création d'un attribut de la classe qui sera sérialisé par JSON-B.
    objType : String,             // l'annotation @JsonbProperty ne fonctionne  pas ici (et on en a besoin parce qu'on veut le terme "object" qui est un mot clé Kotlin), 
                                  //il faut passer par une variable (pas de val ni de var dans le constructeur et l'initialiser dans un attibut plus tard).
    val confidence : Float
    )
{
    @JsonbProperty("object") // c'est ici qu'on peut personnaliser le nom de l'attribut à prendre pour la sérialisation.
    val objectType = objType
}

class ObjectDetectedResponse @JsonbCreator constructor(objDetected : List<ObjectDetected>) {
    @JsonbProperty("objects") 
    val objectsDetected = objDetected
} 