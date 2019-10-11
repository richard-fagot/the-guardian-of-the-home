package home.guardian.azure

data class DetectObjectResponse (val objects : List<Object> ) 

data class Object (val rectangle : Rectangle, val objectType : String, val confidence : Float)

data class Rectangle (val x : Int, val y : Int, val w : Int, val h : Int)