package TPFinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import TDAsUsoEspecifico.Diccionario;
import TDAsUsoEspecifico.MapeoAMuchos;
import conjuntistas.ArbolAVL;
import grafos.Grafo_Etiquetado;
import lineales.dinamicas.Lista;
import utiles.TecladoIn;

public class Copa_America {
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String NEGRO = "\u001B[30m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Diccionario equipos = new Diccionario();
        Diccionario partidos = new Diccionario();
        Diccionario ciudades = new Diccionario();

        ArbolAVL arbol_equipos = new ArbolAVL();
        Grafo_Etiquetado grafo_ciudades = new Grafo_Etiquetado();
        MapeoAMuchos mapeo_partidos = new MapeoAMuchos();

        String cadena;
        boolean sigue = true;
        int opcion;

        SistemaLogger.eliminarContenidoLog(); // Utilizado para "limpiar" el archivo log
        SistemaLogger.escribirLog("Estado inicial del sistema:\n" + estadoSistema(grafo_ciudades, arbol_equipos, mapeo_partidos, ciudades, equipos, partidos));

        System.out.println('\n' + ANSI_CYAN_BACKGROUND + NEGRO + "* * * COPA AMERICA 2024 * * *" + RESET);
        while (sigue == true) {
            System.out.println('\n' + ANSI_YELLOW_BACKGROUND + NEGRO
                    + "Ingrese el numero correspondiente a la accion que desea realizar." + RESET);
            opcion = menu();
            switch (opcion) {
                case 1: // Alta de ciudad
                    altaDeCiudad(grafo_ciudades, ciudades);     
                    break;
                case 2: // Baja de ciudad
                    bajaDeCiudad(grafo_ciudades, ciudades);
                    break;
                case 3: // Alta de ruta
                    altaDeRuta(grafo_ciudades, ciudades);
                    break;
                case 4: // Alta de equipo
                    altaDeEquipo(arbol_equipos, equipos);
                    break;
                case 5: // Baja de equipo
                    bajaDeEquipo(arbol_equipos, equipos);
                    break;
                case 6: // Alta de partido
                    altaDePartido(mapeo_partidos, partidos, equipos, ciudades);
                    break;
                case 7: // Consulta pais
                    // Dado un país, mostrar puntos ganados, goles a favor, en contra y diferencia
                    // de goles
                    consultaEquipo(equipos);
                    break;
                case 8: // Dadas dos cadenas (min y max) devolver todos los equipos cuyo nombre esté
                    // alfabéticamente en el rango [min, max].
                    System.out.println(listarRangoEquipos(arbol_equipos));
                    break;
                case 9: // Dados 2 equipos, si jugaron algún partido entre sí, mostrar los resultados
                    consultarResultado(mapeo_partidos, equipos);
                    break;
                case 10: // Obtener el camino que llegue de A a B en menor tiempo
                    caminoMasCortoTiempo(grafo_ciudades, ciudades);
                    break;
                case 11: // Obtener el camino que llegue de A a B pasando por la mínima cantidad de
                         // ciudades
                    caminoMasCorto(grafo_ciudades, ciudades);
                    break;
                case 12:// Mostrar equipos ordenados por goles a favor
                    System.out.println(listarPorGolesAFavor(equipos, arbol_equipos));
                    break;
                case 13:// 
                    cargarInfoCopa(ciudades, equipos, partidos, grafo_ciudades, arbol_equipos,
                            mapeo_partidos);
                    SistemaLogger.escribirLog("Se ha cargado la informacion oficial de la Copa America 2024");
                    System.out.println("Se ha cargado la informacion oficial de la Copa America 2024");
                    break;
                case 14: // Mostrar sistema
                    cadena = estadoSistema(grafo_ciudades, arbol_equipos, mapeo_partidos, ciudades, equipos, partidos);
                    SistemaLogger.escribirLog("Se consulto el estado del sistema:\n" + cadena);
                    System.out.println(cadena);
                    break;
                case 15:
                    sigue = false;
                    break;
                default:
                    System.out.println("Opcion mal ingresada.");
                    break;
            }
        }
        SistemaLogger.escribirLog("Estado final del sistema:\n" + estadoSistema(grafo_ciudades, arbol_equipos, mapeo_partidos, ciudades, equipos, partidos));
    }

    private static int menu() {
        int opcion;
        System.out.println("Altas y bajas.\n"
                + "\t1: Alta de ciudad.\n"
                + "\t2: Baja de ciudad.\n"
                + "\t3: Establecer ruta aerea entre dos ciudades.\n"
                + "\t4: Alta de equipo.\n"
                + "\t5: Baja de equipo.\n"
                + "\t6: Alta de partido.\n"
                + "Consultas.\n"
                + "\t7: Dado un país, mostrar puntos ganados, goles a favor y en contra y diferencia de goles.\n"
                + "\t8: Dadas dos cadenas (min y max) devolver todos los equipos cuyo nombre esté alfabéticamente en el rango [min, max].\n"
                + "\t9: Mostrar resultado de partido entre dos equipos.\n"
                + "\t10: Obtener el camino que llegue de A a B en menor tiempo.\n"
                + "\t11: Obtener el camino que llegue de A a B pasando por la mínima cantidad de ciudades.\n"
                + "\t12: Mostrar equipos ordenados por goles a favor.\n"
                + "Sistema.\n"
                + "\t13: Cargar informacion de la Copa America 2024.\n"
                + "\t14: Mostrar sistema.\n"
                + "\t15: Terminar.\n");
        opcion = TecladoIn.readInt();
        return opcion;
    }

    // Opcion 1: Alta de ciudad
    private static void altaDeCiudad(Grafo_Etiquetado grafo_ciudades, Diccionario ciudades) {
        String nombre;
        char sede;
        System.out.println("Ingrese el nombre de la ciudad que desea cargar");
        nombre = TecladoIn.readLine().toUpperCase();
        if (ciudades.existeClave(nombre)) {
            System.out.println("ERROR: Ya existe una ciudad cargada con el mismo nombre.");
        } else {
            System.out.println("La ciudad es sede de la copa? (y/n)");
            sede = TecladoIn.readChar();
            ciudades.insertar(nombre, new Ciudad(nombre, sede == 'y'));
            grafo_ciudades.insertarVertice(nombre);
            System.out.println("OK!");
            SistemaLogger.registrarInsercion("Ciudad de " + nombre);
        }
    }

    // Opcion 2: Baja de ciudad
    private static void bajaDeCiudad(Grafo_Etiquetado grafo_ciudades, Diccionario ciudades) {
        String nombre;
        System.out.println("Ingrese el nombre de la ciudad que desea eliminar");
        nombre = TecladoIn.readLine().toUpperCase();
        if (ciudades.eliminar(nombre)) {
            grafo_ciudades.eliminarVertice(nombre);
            System.out.println("Ciudad eliminada correctamente.");
            SistemaLogger.registrarEliminacion("Ciudad de " + nombre);
        } else {
            System.out.println("ERROR: No existe una ciudad cargada con ese nombre.");
        }
    }

    // Opcion 3: Alta de ruta
    private static void altaDeRuta(Grafo_Etiquetado grafo_ciudades, Diccionario ciudades) {
        String nombre, nombre2;
        double minutos;
        System.out.println("Ingrese los nombres de las ciudades\nCiudad 1:");
        nombre = TecladoIn.readLine().toUpperCase();
        System.out.println("Ciudad 2:");
        nombre2 = TecladoIn.readLine().toUpperCase();
        // verifico que ambas ciudades esten en el sistema
        if (ciudades.existeClave(nombre) && ciudades.existeClave(nombre2)) {
            if (!grafo_ciudades.existeArco(nombre, nombre2)) {// verifico que no haya ruta entre ambas ciudades
                System.out.println("Ingrese el tiempo de vuelo aproximado entre ambas ciudades.");
                minutos = TecladoIn.readDouble();
                grafo_ciudades.insertarArco(nombre, nombre2, minutos);
                System.out.println("OK!");
                SistemaLogger.registrarInsercion("Ruta entre " + nombre + " y " + nombre2 + ", de " + minutos + " de vuelo.");
            } else {
                System.out.println("ERROR: Ya existe una ruta entre ambas ciudades.");
            }
        } else {
            System.out.println("ERROR: Hay (por lo menos) una ciudad que no esta cargada en el sistema.");
        }
    }

    // Opcion 4: Alta de equipo
    private static void altaDeEquipo(ArbolAVL arbol_equipos, Diccionario equipos) {
        String nombre;
        System.out.println("Ingrese el nombre del equipo que desea cargar");
        nombre = TecladoIn.readLine().toUpperCase();
        if (equipos.existeClave(nombre)) {
            System.out.println("ERROR: Ya existe un equipo cargado con el mismo nombre.");
        } else {
            arbol_equipos.insertar(nombre);
            System.out.println("Ingrese el apellido del director tecnico del equipo");
            String tecnico = TecladoIn.readLine();
            System.out.println("Ingrese el grupo al que pertenece el equipo dentro de la copa");
            String grupo = TecladoIn.readLine().toUpperCase();
            equipos.insertar(nombre, new Equipo(nombre, tecnico, grupo.charAt(0)));
            System.out.println("OK!");
            SistemaLogger.registrarInsercion("Equipo de " + nombre);
        }
    }

    // Opcion 5: Baja de equipo
    private static void bajaDeEquipo(ArbolAVL arbol_equipos, Diccionario equipos) {
        String nombre;
        System.out.println("Ingrese el nombre del equipo que desea eliminar");
        nombre = TecladoIn.readLine();
        if (equipos.eliminar(nombre)) {
            arbol_equipos.eliminar(nombre);
            equipos.eliminar(nombre);
            System.out.println("Equipo eliminado correctamente.");
            SistemaLogger.registrarEliminacion("Equipo de " + nombre);
        } else {
            System.out.println("ERROR: No existe un equipo cargado con ese nombre.");
        }
    }

    // Opcion 6: Alta de partido
    private static void altaDePartido(MapeoAMuchos mapeo_partidos, Diccionario partidos, Diccionario equipos,
            Diccionario ciudades) {
        String clave, nombre, nombre2;
        System.out.println("Ingrese los equipos que disputaron el partido\nEquipo1:");
        nombre = TecladoIn.readLine().toUpperCase();
        System.out.println("Equipo2:");
        nombre2 = TecladoIn.readLine().toUpperCase();
        if (equipos.existeClave(nombre) && equipos.existeClave(nombre2)) {
            Resultado resultado;
            // creacion de la clave para el diccionario de partidos
            if (nombre2.compareTo(nombre) < 0) {
                // Si nombre es mayor alfabeticamente que nombre2, les intercambio los valores
                String aux = nombre; nombre = nombre2; nombre2 = aux;
            }
            clave = nombre.substring(0, 3) + "-" + nombre2.substring(0, 3);
            partidos.insertar(clave, new Partido(nombre, nombre2));
            resultado = asociarResultado(clave, nombre, nombre2, ciudades);
            if (resultado != null) {
                mapeo_partidos.asociar(clave, resultado);
                asignarPuntos(nombre, nombre2, resultado, equipos);
                System.out.println("OK!");
                SistemaLogger.registrarInsercion("Partido entre" + nombre + " y " + nombre2);
            } else {
                System.out.println("ERROR: La ciudad ingresada no es sede de la competicion.");
            }
        } else {
            System.out.println("ERROR: Hay (por lo menos) un equipo que no esta cargado en el sistema");
        }
    }

    // Opcion 7: Consulta equipo
    private static void consultaEquipo(Diccionario equipos) {
        String nombre;
        Equipo equipo;
        System.out.println("Ingrese el nombre del equipo.");
        nombre = TecladoIn.readLine().toUpperCase();
        equipo = (Equipo) equipos.obtenerDato(nombre);
        if (equipo != null) {
            System.out.println(nombre + ": " + equipo.toString());
            SistemaLogger.registrarConsulta("Datos del equipo de " + nombre);
        } else {
            System.out.println("ERROR: El equipo ingresado no esta cargado en el sistema.");
        }
    }

    // Opcion 8: Listar equipos en rango
    private static Lista listarRangoEquipos(ArbolAVL arbol_equipos) {
        String min, max;
        System.out.println("Ingrese la cadena minima");
        min = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la cadena maxima");
        max = TecladoIn.readLine().toUpperCase();
        SistemaLogger.registrarConsulta("Listado de equipos dentro del rango " + min + " - " + max);
        return arbol_equipos.listarRango(min, max);
    }

    // Opcion 9: consultar resultado
    private static void consultarResultado(MapeoAMuchos mapeo_partidos, Diccionario equipos) {
        String clave, nombre, nombre2;
        System.out.println("Ingrese los equipos que disputaron el partido\nEquipo1:");
        nombre = TecladoIn.readLine().toUpperCase();
        System.out.println("Equipo2:");
        nombre2 = TecladoIn.readLine().toUpperCase();
        if (equipos.existeClave(nombre) && equipos.existeClave(nombre2)) {
            // creacion de la clave para el diccionario de partidos
            if (nombre2.compareTo(nombre) < 0) {
                // Si nombre es mayor alfabeticamente que nombre2, les intercambio los valores
                String aux = nombre; nombre = nombre2; nombre2 = aux;
            }
            clave = nombre.substring(0, 3) + "-" + nombre2.substring(0, 3);
            System.out.println(nombre + " vs " + nombre2 + ": " + mapeo_partidos.obtenerValores(clave));
            SistemaLogger.registrarConsulta("Resultado entre " + nombre + " y " + nombre2);
        } else {
            System.out.println("ERROR: Hay (por lo menos) un equipo que no esta cargado en el sistema");
        }
    }

    // Opcion 10: Consulta camino mas corto en tiempo
    private static void caminoMasCortoTiempo(Grafo_Etiquetado grafo_ciudades, Diccionario ciudades) {
        String nombre, nombre2;
        System.out.println("Ingrese el nombre de la primera ciudad");
        nombre = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el nombre de la segunda ciudad");
        nombre2 = TecladoIn.readLine().toUpperCase();
        if (ciudades.existeClave(nombre) && ciudades.existeClave(nombre2)) {
            Double[] tiempo = { 0. };
            System.out.println("El camino mas corto entre ambas ciudades es: "
                    + grafo_ciudades.caminoMasCortoEnTiempo(nombre, nombre2, tiempo)
                    + "\nTardando " + tiempo[0] + " minutos de vuelo aproximadamente.");
            SistemaLogger.registrarConsulta("Camino mas corto en minutos entre " + nombre + " y " + nombre2);
        } else {
            System.out.println("Nombre mal ingresados o ciudades no pertenecientes al mapa de ciudades.");
        }
    }

    // Opcion 11: Consulta camino mas corto entre ciudades
    private static void caminoMasCorto(Grafo_Etiquetado grafo_ciudades, Diccionario ciudades) {
        String nombre, nombre2;
        System.out.println("Ingrese el nombre de la primera ciudad");
        nombre = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el nombre de la primera ciudad");
        nombre2 = TecladoIn.readLine().toUpperCase();
        if (ciudades.existeClave(nombre) && ciudades.existeClave(nombre2)) {
            System.out.println("El camino mas corto entre ambas ciudades es: "
                    + grafo_ciudades.caminoMasCorto(nombre, nombre2).toString());
            SistemaLogger.registrarConsulta("Camino mas corto entre " + nombre + " y " + nombre2);
        } else {
            System.out.println("Nombre mal ingresados o ciudades no pertenecientes al mapa de ciudades.");
        }
    }

    // Opcion 12: Listar equipos por cantidad de goles a favor
    private static Lista listarPorGolesAFavor(Diccionario equipos, ArbolAVL arbol_equipos) {
        // Creo un nuevo arbolAVL donde inserto cadenas String que empiezan por la cantidad de goles
        // del equipo y luego el nombre de este, por lo que al insertarlas se ordenan a partir de la
        // cantidad de goles de lo equipos.
        ArbolAVL arbolAVL = arbol_equipos.clone();
        ArbolAVL arbolOrdenado = new ArbolAVL();
        Comparable elem;
        Equipo datosEquipo;
        while (!arbolAVL.esVacio()) {
            elem = arbolAVL.minimoElem();
            datosEquipo = (Equipo) equipos.obtenerDato(elem);
            arbolOrdenado.insertar("(" + datosEquipo.getGolesFavor() + " goles: " + datosEquipo.getPais() + ")");
            arbolAVL.eliminar(elem);
        }
        SistemaLogger.registrarConsulta("Listado de equipos ordenados por cantidad de goles a favor");
        return arbolOrdenado.listar();
    }

    // Opcion 13: Cargar informacion oficial Copa America 2024
    private static void cargarInfoCopa(Diccionario ciudades, Diccionario equipos, Diccionario partidos,
            Grafo_Etiquetado grafo_ciudades, ArbolAVL arbol_equipos, MapeoAMuchos mapeo_partidos) {

        String filePath = "C:\\Users\\smnvl\\Documents\\Facultad\\EDAT\\TPFinal\\Datos.txt";
        try (FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            String clave, nombre1, nombre2, pais, tecnico, ronda, ciudad, estadio; 
            boolean alojamiento, sede;
            int golesEq1, golesEq2;
            double tiempoVuelo;
            // Carga de ciudades
            while ((line = bufferedReader.readLine()) != null && !line.equals("***RUTAS***")) {
                StringTokenizer tokenizer = new StringTokenizer(line, "::");
                if (tokenizer.hasMoreTokens()) {
                    clave = tokenizer.nextToken();
                    alojamiento = Boolean.parseBoolean(tokenizer.nextToken());
                    sede = Boolean.valueOf(tokenizer.nextToken());
                    ciudades.insertar(clave, new Ciudad(clave, alojamiento, sede));
                    grafo_ciudades.insertarVertice(clave);
                }
            }
            while ((line = bufferedReader.readLine()) != null && !line.equals("***EQUIPOS***")) {
                StringTokenizer tokenizer = new StringTokenizer(line, "::");
                if (tokenizer.hasMoreTokens()) {
                    nombre1 = tokenizer.nextToken();
                    nombre2 = tokenizer.nextToken();
                    tiempoVuelo = Double.parseDouble(tokenizer.nextToken());
                    grafo_ciudades.insertarArco(nombre1, nombre2, tiempoVuelo);
                }
            }
            // Carga de equipos
            while ((line = bufferedReader.readLine()) != null && !line.equals("***PARTIDOS***")) {
                StringTokenizer tokenizer = new StringTokenizer(line, "::");
                if (tokenizer.hasMoreTokens()) {
                    pais = tokenizer.nextToken();
                    tecnico = tokenizer.nextToken();
                    char grupo = tokenizer.nextToken().charAt(0);
                    equipos.insertar(pais, new Equipo(pais, tecnico, grupo));
                    arbol_equipos.insertar(pais);
                }
            }
            // Carga de partidos
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "::");
                if (tokenizer.hasMoreTokens()) {
                    clave = tokenizer.nextToken();
                    nombre1 = tokenizer.nextToken();
                    nombre2 = tokenizer.nextToken();
                    ronda = tokenizer.nextToken();
                    ciudad = tokenizer.nextToken();
                    estadio = tokenizer.nextToken();
                    golesEq1 = Integer.parseInt(tokenizer.nextToken());
                    golesEq2 = Integer.parseInt(tokenizer.nextToken());
                    partidos.insertar(clave, new Partido(nombre1, nombre2));
                    Resultado resultado = new Resultado(ronda, ciudad, estadio, golesEq1, golesEq2);
                    mapeo_partidos.asociar(clave, resultado);
                    asignarPuntos(nombre1, nombre2, resultado, equipos);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Opcion 14: Mostrar sistema
    private static String estadoSistema(Grafo_Etiquetado grafo_ciudades, ArbolAVL arbol_equipos, MapeoAMuchos mapeo_partidos,
            Diccionario ciudades, Diccionario equipos, Diccionario partidos){
        String cadena = "";
        cadena += "Diccionario de ciudades:\n" + ciudades.toString()
            +"\nDiccionario de equipos:\n" + equipos.toString()
            +"\nDiccionario de partidos:\n" + partidos.toString()
            +"\nGrafo de ciudades:\n" + grafo_ciudades.toString()
            +"\nArbol de equipos:\n" + arbol_equipos.toString()
            +"\nMapeo de partidos:\n" + mapeo_partidos.toString() + "\n";
        return cadena;
    }

    // Metodos auxiliares
    private static Resultado asociarResultado(String clave, String nombre, String nombre2, Diccionario ciudades) {
        // Metodo que asocia una instancia Partido con una instacia Resultado en un Mapeo
        String ronda, ciudad, estadio;
        Resultado resultado = null;
        int goles1, goles2;

        System.out.println("En que ciudad se disputa el partido");
        ciudad = TecladoIn.readLine().toUpperCase();
        if (ciudades.existeClave(ciudad)) {
            Ciudad datosCiudad = ((Ciudad) ciudades.obtenerDato(ciudad));
            if (datosCiudad.esSede()) {
                System.out.println("En que ronda se disputa el partido");
                ronda = TecladoIn.readLine().toUpperCase();
                System.out.println("En que estadio se disputa el partido");
                estadio = TecladoIn.readLine().toUpperCase();
                System.out.println("Cuantos goles convirtio " + nombre);
                goles1 = TecladoIn.readInt();
                System.out.println("Cuantos goles convirtio " + nombre2);
                goles2 = TecladoIn.readInt();
                resultado = new Resultado(ronda, ciudad, estadio, goles1, goles2);
            }
        }
        return resultado;
    }

    private static void asignarPuntos(String eq1, String eq2, Resultado resultado, Diccionario equipos) {
        // Metodo que asigna puntos a los equipos involucrados en la carga de un partido entre ellos
        Equipo equipo1 = (Equipo) equipos.obtenerDato(eq1);
        Equipo equipo2 = (Equipo) equipos.obtenerDato(eq2);
        int golesEq1 = resultado.getGolesEq1(), golesEq2 = resultado.getGolesEq2();
        equipo1.sumarGolesFavor(golesEq1);
        equipo1.sumarGolesContra(golesEq2);
        equipo2.sumarGolesFavor(golesEq2);
        equipo2.sumarGolesContra(golesEq1);
        if (resultado.getRonda().equals("FASE DE GRUPOS")) {
            // Un equipo solo suma puntos en fase de grupos
            if (golesEq1 == golesEq2) {
                // EMPATE, 1 punto cada uno
                equipo1.sumarPuntos(1);
                equipo2.sumarPuntos(1);
            } else if (golesEq1 > golesEq2) {
                // GANA eq1, 3 puntos eq1 y 0 para eq2
                equipo1.sumarPuntos(3);
            } else {
                // GANA eq2, 3 puntos eq2 y 0 para eq1
                equipo2.sumarPuntos(3);
            }
        }
    }
}
