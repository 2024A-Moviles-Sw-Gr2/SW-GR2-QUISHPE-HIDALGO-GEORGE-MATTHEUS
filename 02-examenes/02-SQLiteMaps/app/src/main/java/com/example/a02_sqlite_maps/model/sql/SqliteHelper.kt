package com.example.a02_sqlite_maps.model.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.a02_sqlite_maps.model.entities.Cliente
import com.example.a02_sqlite_maps.model.entities.Pedido
import com.google.android.gms.maps.model.LatLng


class SqliteHelper (
    context: Context?
): SQLiteOpenHelper(
    context,
    "deber02-moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Creación de tablas
        val scriptCreacionTablaCliente =
            """
                CREATE TABLE Cliente (
                    clienteID INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    email VARCHAR(50),
                    fechaRegistro TEXT,
                    activo INTEGER
                );   
            """.trimIndent()

        val scriptCreacionTablaPedido =
            """
                CREATE TABLE Pedido (
                    pedidoID INTEGER PRIMARY KEY AUTOINCREMENT,
                    clienteID INTEGER NOT NULL,
                    fechaPedido TEXT,
                    montoTotal REAL,
                    pagado INTEGER,
                    descripcion VARCHAR(150),
                    latitude REAL,
                    longitude REAL,
                    FOREIGN KEY (clienteID) REFERENCES Cliente(clienteID)
                    ON DELETE CASCADE
                );      
            """.trimIndent()

        db?.execSQL(scriptCreacionTablaCliente)
        db?.execSQL(scriptCreacionTablaPedido)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    // Funciones para la tabla Cliente
    fun consultarClientes(): ArrayList<Cliente>? {
        val conexionEscritura = readableDatabase

        val scriptConsultaLectura =
            """
                SELECT * FROM Cliente
            """.trimIndent()

        val resultadoConsultaLectura = conexionEscritura
            .rawQuery(
                scriptConsultaLectura,
                null
            )

        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<Cliente>()
        if(existeAlMenosUno){
            do{
                val cliente = Cliente(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getString(3),
                    resultadoConsultaLectura.getString(4)
                )
                arregloRespuesta.add(cliente)
            }while(resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        conexionEscritura.close()

        return if(arregloRespuesta.size>0) arregloRespuesta else null
    }

        fun consultarClientePorID(id:Int): Cliente? {
        val conexionEscritura = readableDatabase

        val scriptConsultaLectura =
            """
                SELECT * FROM Cliente WHERE clienteID = ?
            """.trimIndent()

        val idClienteConsultar = arrayOf(id.toString())
        val resultadoConsultaLectura = conexionEscritura
            .rawQuery(
                scriptConsultaLectura,
                idClienteConsultar
            )

        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<Cliente>()
        if(existeAlMenosUno){
            do{
                val cliente = Cliente(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getString(3),
                    resultadoConsultaLectura.getString(4)
                )
                arregloRespuesta.add(cliente)
            }while(resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        conexionEscritura.close()

        return if(arregloRespuesta.size>0) arregloRespuesta[0] else null
    }

    fun crearCliente(
        nombre: String,
        email:String,
        fechaRegistro: String,
        activo: String
    ):Boolean {
        val conexionEscritura = writableDatabase

        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("email", email)
        valoresAGuardar.put("fechaRegistro", fechaRegistro)
        valoresAGuardar.put("activo", activo)

        val resultadoGuardar = conexionEscritura
            .insert(
                "Cliente",
                null,
                valoresAGuardar
            )

        conexionEscritura.close()
        return resultadoGuardar.toInt() != -1
    }

    fun actualizarCliente(
        id: Int,
        nombre: String,
        email:String,
        fechaRegistro: String,
        activo: String
    ):Boolean{
        val conexionEscritura = writableDatabase

        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("email", email)
        valoresAActualizar.put("fechaRegistro", fechaRegistro)
        valoresAActualizar.put("activo", activo)

        val idClienteActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "Cliente",
                valoresAActualizar,
                "clienteID=?",
                idClienteActualizar
            )

        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun eliminarCliente(id:Int):Boolean{
        val conexionEscritura = writableDatabase

        val idClienteEliminar = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "Cliente",
                "clienteID=?",
                idClienteEliminar
            )
        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    // Funciones para la tabla Pedido
    fun consultarPedidosPorCliente(clienteID:Int): ArrayList<Pedido>? {
        val conexionEscritura = readableDatabase

        val scriptConsultaLectura =
            """
                SELECT * FROM Pedido WHERE clienteID = ?
            """.trimIndent()

        val idClienteConsultar = arrayOf(clienteID.toString())
        val resultadoConsultaLectura = conexionEscritura
            .rawQuery(
                scriptConsultaLectura,
                idClienteConsultar
            )

        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<Pedido>()
        if(existeAlMenosUno){
            do{
                val pedido = Pedido(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getInt(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getDouble(3),
                    resultadoConsultaLectura.getString(4),
                    resultadoConsultaLectura.getString(5),
                    LatLng(resultadoConsultaLectura.getDouble(6), resultadoConsultaLectura.getDouble(7))
                )
                arregloRespuesta.add(pedido)
            }while(resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        conexionEscritura.close()

        return if(arregloRespuesta.size>0) arregloRespuesta else null
    }

    fun consultarPedidoPorID(id:Int): Pedido? {
        val conexionEscritura = readableDatabase

        val scriptConsultaLectura =
            """
                SELECT * FROM Pedido WHERE pedidoID = ?
            """.trimIndent()

        val idPedidoConsultar = arrayOf(id.toString())
        val resultadoConsultaLectura = conexionEscritura
            .rawQuery(
                scriptConsultaLectura,
                idPedidoConsultar
            )

        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<Pedido>()
        if(existeAlMenosUno){
            do{
                val pedido = Pedido(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getInt(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getDouble(3),
                    resultadoConsultaLectura.getString(4),
                    resultadoConsultaLectura.getString(5),
                    LatLng(resultadoConsultaLectura.getDouble(6), resultadoConsultaLectura.getDouble(7))
                )
                arregloRespuesta.add(pedido)
            }while(resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        conexionEscritura.close()

        return if(arregloRespuesta.size>0) arregloRespuesta[0] else null
    }

    fun consultarUbicacionPorID (id:Int): LatLng? {
        val conexionEscritura = readableDatabase

        val scriptConsultaLectura =
            """
                SELECT latitude, longitude FROM Pedido WHERE pedidoID = ?
            """.trimIndent()

        val idPedidoConsultar = arrayOf(id.toString())
        val resultadoConsultaLectura = conexionEscritura
            .rawQuery(
                scriptConsultaLectura,
                idPedidoConsultar
            )

        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        var ubicacion: LatLng? = null
        if(existeAlMenosUno){
            ubicacion = LatLng(
                resultadoConsultaLectura.getDouble(0),
                resultadoConsultaLectura.getDouble(1)
            )
        }

        resultadoConsultaLectura.close()
        conexionEscritura.close()

        return ubicacion
    }

    fun crearPedido(
        clienteID: Int,
        fechaPedido: String,
        montoTotal: Double,
        pagado: String,
        descripcion: String,
        latitud: Double,
        longitud: Double
    ):Boolean {
        val conexionEscritura = writableDatabase

        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("clienteID", clienteID)
        valoresAGuardar.put("fechaPedido", fechaPedido)
        valoresAGuardar.put("montoTotal", montoTotal)
        valoresAGuardar.put("pagado", pagado)
        valoresAGuardar.put("descripcion", descripcion)
        valoresAGuardar.put("latitude", latitud)
        valoresAGuardar.put("longitude", longitud)

        val resultadoGuardar = conexionEscritura
            .insert(
                "Pedido",
                null,
                valoresAGuardar
            )

        conexionEscritura.close()
        return resultadoGuardar.toInt() != -1
    }

    fun actualizarPedido(
        id: Int,
        fechaPedido: String,
        montoTotal: Double,
        pagado: String,
        descripcion: String,
        latitud: Double,
        longitud: Double
    ):Boolean{
        val conexionEscritura = writableDatabase

        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("fechaPedido", fechaPedido)
        valoresAActualizar.put("montoTotal", montoTotal)
        valoresAActualizar.put("pagado", pagado)
        valoresAActualizar.put("descripcion", descripcion)
        valoresAActualizar.put("latitude", latitud)
        valoresAActualizar.put("longitude", longitud)

        val idPedidoActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "Pedido",
                valoresAActualizar,
                "pedidoID=?",
                idPedidoActualizar
            )

        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun eliminarPedido(id:Int):Boolean{
        val conexionEscritura = writableDatabase

        val idPedidoEliminar = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "Pedido",
                "pedidoID=?",
                idPedidoEliminar
            )
        conexionEscritura.close()
        return resultadoEliminacion != -1
    }
}