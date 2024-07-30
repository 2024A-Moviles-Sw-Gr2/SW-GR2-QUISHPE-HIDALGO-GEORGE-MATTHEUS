package com.example.a03_recycleview_crunchyroll.utils

import com.example.a03_recycleview_crunchyroll.R
import com.example.a03_recycleview_crunchyroll.entities.Inicio
import com.example.a03_recycleview_crunchyroll.entities.Recomendacion

class BaseDatosMemoria {
    companion object{
        val listaRecomendaciones = arrayListOf<Recomendacion>()
        val inicio = Inicio(
            "Dob | Sub",
            "Naruto es un joven ninja de la aldea ficticia de Konoha. " +
                    "Con un espíritu indomable y una determinación inquebrantable, " +
                    "Naruto sueña con convertirse en el Hokage, el líder de su aldea",
            R.drawable.portada_naruto_grande)

        init {
            listaRecomendaciones
                .add(
                    Recomendacion("Tokyo Ghoul","Japones", R.drawable.portada_tokyo_ghoul)
                )

            listaRecomendaciones
                .add(
                    Recomendacion("Black Clover","Japones", R.drawable.portada_black_clover)
                )

            listaRecomendaciones
                .add(
                    Recomendacion("Kaguya Sama","Japones", R.drawable.portada_kaguya)
                )
        }
    }
}