package com.example.listatareas2

import android.os.Parcel
import android.os.Parcelable

data class Tarea(
    val nombre: String,
    val descripcion: String,
    val fecha: String,
    var prioridad: String,
    var coste: Double?,
    var hecha: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeString(fecha)
        parcel.writeString(prioridad)
        parcel.writeDouble(coste!!)
        parcel.writeByte(if (hecha) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tarea> {
        override fun createFromParcel(parcel: Parcel): Tarea {
            return Tarea(parcel)
        }

        override fun newArray(size: Int): Array<Tarea?> {
            return arrayOfNulls(size)
        }
    }
}
