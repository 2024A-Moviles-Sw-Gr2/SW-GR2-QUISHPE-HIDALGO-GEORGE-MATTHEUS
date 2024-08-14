package com.example.a02_sqlite_maps.model.entities

import android.os.Parcel
import android.os.Parcelable

class Cliente(
    val clienteID: Int,
    var nombre: String,
    var email: String,
    var fechaRegistro: String,
    var activo: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(clienteID)
        parcel.writeString(nombre)
        parcel.writeString(email)
        parcel.writeString(fechaRegistro)
        parcel.writeString(activo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cliente> {
        override fun createFromParcel(parcel: Parcel): Cliente {
            return Cliente(parcel)
        }

        override fun newArray(size: Int): Array<Cliente?> {
            return arrayOfNulls(size)
        }
    }

}