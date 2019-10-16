package home.guardian.azure

import javax.json.bind.annotation.JsonbProperty
import javax.json.bind.annotation.JsonbCreator 
import javax.json.bind.annotation.JsonbTransient

data class Rectangle (var x : Int = 0, var y : Int = 0, var w : Int = 0, var h : Int = 0) //Nécessaire de préciser des valeurs par défaut pour pouvoir bénéficier d'un constructeur par défaut pour le json-b
                                                                                          // de plus, pour la désérialisation il ne faut pas que les attributs soit en final. 

class ObjectDetected constructor (
    var rectangle : Rectangle? = null,    // le modificateur val provoque la création d'un attribut de la classe qui sera sérialisé par JSON-B.
    objType : String? = null,             // l'annotation @JsonbProperty ne fonctionne  pas ici (et on en a besoin parce qu'on veut le terme "object" qui est un mot clé Kotlin), 
                                  //il faut passer par une variable (pas de val ni de var dans le constructeur et l'initialiser dans un attibut plus tard).
    var confidence : Float = 0.0F
    )
{
    @JsonbProperty("object") // c'est ici qu'on peut personnaliser le nom de l'attribut à prendre pour la sérialisation.
    var objectType = objType

    override fun equals(other: Any?) : Boolean { // Nécessaire pour comparer deux ObjectDetected car ce n'est pas une data class (Kotlin compare les reférences dans ce cas)
        if( other == null || other !is ObjectDetected) return false

        val equalRect = this.rectangle?.equals(other?.rectangle) ?: false
        val equalType = this.objectType?.equals(other?.objectType) ?: false
        val equalConf = this.confidence?.equals(other?.confidence) ?: false

        return equalRect && equalType && equalConf
    }
}

class ObjectDetectedResponse (objDetected : List<ObjectDetected>? ) {
    @JsonbProperty("objects") 
    var objectsDetected = objDetected

    @JsonbCreator
    constructor() : this(null) {} // pour je ne sais quelle raison json-b veut un constructeur par défaut pour cette classe. 

    override fun equals(other: Any?) : Boolean { 
        if( other == null || other !is ObjectDetectedResponse) return false

        val objDetectedList = this.objectsDetected ?: return false
        val otherDetectedList = other.objectsDetected ?: return false

        return objDetectedList.containsAll(otherDetectedList) && otherDetectedList.containsAll(objDetectedList)
    }
} 