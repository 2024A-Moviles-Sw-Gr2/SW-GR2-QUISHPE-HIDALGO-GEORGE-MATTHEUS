package com.example.a02_sqlite_maps.model.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng

class Pedido(
    var pedidoID: Int,
    var clienteID: Int,
    var fechaPedido: String,
    var montoTotal: Double,
    var pagado: String,
    var descripcion: String,
    var ubicacion: LatLng
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(LatLng::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pedidoID)
        parcel.writeInt(clienteID)
        parcel.writeString(fechaPedido)
        parcel.writeDouble(montoTotal)
        parcel.writeString(pagado)
        parcel.writeString(descripcion)
        parcel.writeParcelable(ubicacion, flags)
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