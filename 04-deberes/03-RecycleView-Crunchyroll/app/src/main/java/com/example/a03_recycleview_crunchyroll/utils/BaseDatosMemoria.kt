package com.example.a03_recycleview_crunchyroll.utils

import com.example.a03_recycleview_crunchyroll.R
import com.example.a03_recycleview_crunchyroll.entities.Inicio
import com.example.a03_recycleview_crunchyroll.entities.Recomendacion
import com.example.a03_recycleview_crunchyroll.entities.SeguirViendo

class BaseDatosMemoria {
    companion object{
        val listaRecomendaciones = arrayListOf<Recomendacion>()
        val listaSeguirViendo = arrayListOf<SeguirViendo>()
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
                    Recomendacion("Black Clover","Lat|Jap", R.drawable.portada_black_clover)
                )

            listaRecomendaciones
                .add(
                    Recomendacion("Kaguya Sama","Varios idiomas", R.drawable.portada_kaguya)
                )

            listaRecomendaciones
                .add(
                    Recomendacion("Haikyuu!","Lat|Jap", R.drawable.portada_haikyuu)
                )

            listaRecomendaciones
                .add(
                    Recomendacion("Solo Leveling","Japones", R.drawable.portada_solo_leveling)
                )

            listaRecomendaciones
                .add(
                    Recomendacion("One Punch Man","Lat|Cas|Jap", R.drawable.portada_one_punch_man)
                )

            listaSeguirViendo
                .add(
                    SeguirViendo("Haikyuu!","Temporada 1 Episodio 5", 20, R.drawable.portada_haikyuu_horizontal)
                )

            listaSeguirViendo
                .add(
                    SeguirViendo("One Piece","Temporada 13 Episodio 1071", 23, R.drawable.portada_one_piece_horizontal)
                )

            listaSeguirViendo
                .add(
                    SeguirViendo("Spy x Family","Temporada 2 Episodio 9", 9, R.drawable.portada_spy_family_horizontal)
                )

            listaSeguirViendo
                .add(
                    SeguirViendo("Hell's Paradise","Temporada 1 Episodio 13", 16, R.drawable.portada_hells_paradise_horizontal)
                )

            listaSeguirViendo
                .add(
                    SeguirViendo("Dr. Stone","Temporada 3 Episodio 1", 1, R.drawable.portada_dr_stone_horizontal)
                )
        }
    }
}