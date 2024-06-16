import java.util.Date

fun main() {
    println("Hello World")
    // Inmutables (No se pueden RE ASIGNAR "=")
    val inmutable: String = "George"
    // inmutable = "Pito" // ERROR!

    // Mutables
    var mutable = "Mattheus" // OK
    // VAL > VAR

    // Dock Typing
    var ejemploVariable = "George Quishpe"
    var edadEjemplo: Int = 12
    ejemploVariable.trim()
    // ejemploVariable = edadEjemplo // EROOR! tipo incorrecto

    // Variables primitivas
    val nombre: String = "George"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    // Clases en Java
    val fechaNacimiento: Date = Date()

    // When (Switch)
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") -> {
            print("Casado")
        }
        ("S") -> {
            print("Soltero")
        }
        else -> {
            print("No sabemos")
        }
    }

    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No" // if else chiquito

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)

    // Name parameters
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 15.00)

    // Uso de clases
    val sumaUno = Suma(1,1) // new Suma(1,1) en KOTLIN no hya "new"
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    // Arreglos
    // Estáticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico);

    // Dinàmicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    )

    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // Operadores
    // FOR EACH => Unit

    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int -> // ->
            println("Valor actual: ${valorActual}");
        }

    // "it" (en inglés "eso") significa elemento iterado
    arregloDinamico.forEach { println("Valor Actual (it): ${it}")}

    // MAP -> MUTA(Modifica cambia) el arreglo
    // 1) Enviamos el nuevo valor de a iteración
    // 2) Nos devuelve un NUEVO ARREGLO con valores
    // de las iteraciones
    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)

    val respuestMapDos = arregloDinamico.map { it + 15 }
    println(respuestMapDos)

    // Filter -> Filtrar el ARREGLO
    // 1) Devolver una expresión (TRUE o FALSE)
    // 2) Nuevo arreglo FILTRADO
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            // Expresión o CONDICIÓN
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 } // <=
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    // OR -> ANY (Alguno Cumple?)
    // AND -> ALL (Todos cumplen?)
    val respuestaAny: Boolean =arregloDinamico
        .any{ valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // True
    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // False

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (siempre empieza en 0 el calculo)
    //  [1, 2, 3, 4, 5] -> Acumular "SUMAR" estyos valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 =  0 + 1 = 1 -> Iteracion1
    // valorIteracion2 = valorAcumulado1 + 2 =  1 + 2 = 3 -> Iteracion2
    // valorIteracion3 = valorAcumulado2 + 3 =  3 + 3 = 6 -> Iteracion3
    // valorIteracion3 = valorAcumulado3 + 4 =  6 + 4 = 10 -> Iteracion4
    // valorIteracion3 = valorAcumulado4 + 5 =  10 + 5 = 15 -> Iteracion5
    val respuestaReduce: Int = arregloDinamico
        .reduce{ acumulado: Int, valorActual:Int ->

        return@reduce (acumulado + valorActual)
        }
    println(respuestaReduce);
    // return@reduce acumulado + (itemCarrito.cantidad * itemCarrito.precio)
}

// void -> Unit
fun imprimirNombre (nombre:String): Unit {
    println("Nombre: ${nombre}") // Template Strings
}

fun calcularSueldo(
    sueldo: Double, // Requerido
    tasa: Double = 12.00, // Opcional (defecto)
    bonoEspecial: Double? = null, // Opcional (nullable)
    // variable? -> *?* Es Nullable (osea que puede en algún momento ser nulo)
): Double {
    // Int -> Int? (nullable)
    // String -> String? (nullable)
    // Date ->  Date? (nullable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) * bonoEspecial
    }
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int,
    ){
        this.numeroUno = uno
        this.numeroDos = dos
                println("Inicializando")
    }
}

abstract class Numeros( // Constructor Primario
    // Caso 1: Parametro normal
    // uno: Int, (parámetro (sin modificador acceso))

    // Caso 2: Parametro y propiedad (atributo) (private)
    // private var uno: Int (propiedad "instancia.uno")

    protected val numeroUno: Int, // instancia.numeroUno
    protected val numeroDos: Int, // instancia.numeroDos
){
    init { // bloque constructor primario
        this.numeroUno
        this.numeroDos
        println("Inicializando")
    }
}

class Suma(// Constructor primario
    unoParametro: Int, // Parametro
    dosParametro: Int, // Parametro
): Numeros( // Clase papa, Numeros (extendiendo)
    unoParametro,
    dosParametro
){
    public val soyPublicoExplicito: String = "Explicito" // Publicos
    val soyPublicoImplicito: String = "Implicito" // Publicos (propiedades, metodos)

    init { // Bloque Codigo Constructor primario
        // this.unoParametro // ERROR no existe
        this.numeroUno
        this.numeroDos
        numeroUno // this. OPCIONAL (propiedades, metodos)
        numeroDos // this. OPCIONAL (propiedades, metodos)
        this.soyPublicoExplicito
        soyPublicoImplicito // this. OPCIONAL (propiedades, metodos)
    }

    constructor( // Constructor secundario
        uno:Int?,
        dos:Int,
    ): this(
        if(uno == null) 0 else uno,
        dos,
    )

    constructor( // Constructor terciario
        uno:Int,
        dos:Int?,
    ): this(
        uno,
        if(dos == null) 0 else dos,
    )

    constructor( // Constructor cuarto
        uno:Int?,
        dos:Int?,
    ): this( // Constructor cuarto
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos,
    )

    // public fun sumar()Int { Modificar "public" es OPCIONAL
    fun sumar(): Int {
        val total = numeroUno + numeroDos
        // Suma.agregarHistorial(total) ("Suma")
        agregarHistorial(total)
        return total
    }

    companion object { // Comparte entre todas las instancias, similar al Static
        // funciones y variables
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }

        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma: Int) {
            historialSumas.add(valorTotalSuma)
        }
    }
}