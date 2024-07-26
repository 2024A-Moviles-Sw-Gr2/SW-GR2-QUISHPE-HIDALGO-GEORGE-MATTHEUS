package com.example.a02_crud_gui.model.entities

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

class Pedido(
    var pedidoID: Int,
    var clienteID: Int,
    var fechaPedido: String,
    var montoTotal: Double,
    var pagado: String,
    var descripcion: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pedidoID)
        parcel.writeInt(clienteID)
        parcel.writeString(fechaPedido)
        parcel.writeDouble(montoTotal)
        parcel.writeString(pagado)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pedido> {
        override fun createFromParcel(parcel: Parcel): Pedido {
            return Pedido(parcel)
        }

        override fun newArray(size: Int): Array<Pedido?> {
            return arrayOfNulls(size)
        }
    }

}