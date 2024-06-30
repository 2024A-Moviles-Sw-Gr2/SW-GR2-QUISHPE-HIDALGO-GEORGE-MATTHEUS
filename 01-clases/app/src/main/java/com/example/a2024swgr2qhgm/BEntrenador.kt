package com.example.a2024swgr2qhgm

class BEntrenador(
    var id:Int,
    var nombre:String,
    var descripcion:String?
) {
    override fun toString(): String {
        return "$nombre ${descripcion}"
    }

}