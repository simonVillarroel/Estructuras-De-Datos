package Conjuntista.TDA_Hash;

public class FuncionesHash {
    // Funciónes hash para elementos de tipo entero
    public static int hashEnteroUltimoDigito(Object numero) {
        return ((int) numero) % 10;
    }

    public static int hashEnteroUltimos2Digitos(int numero) {
        return numero % 100;
    }

    public static int hashEnteroUltimos3Digitos(int numero) {
        return numero % 1000;
    }

    // Función hash para elementos de tipo String
    public static int hashStringValorDigitos(String cadena) {
        int suma = 0;
        // Recorre cada caracter de la cadena y suma su valor ASCII
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            suma += (int) caracter;
        }
        return suma;
    }

    //Funciones reHash para elementos tipo entero
    public static int reHashsumarDigitos(Object numero) {
        // Convierte el número a una cadena para poder iterar sobre sus dígitos
        String numeroStr = Integer.toString((int) numero);
        // Inicializa la variable para almacenar la suma de los dígitos
        int suma = 0;
        // Itera sobre cada dígito en la cadena y suma su valor convertido a entero
        for (int i = 0; i < numeroStr.length(); i++) {
            char digitoChar = numeroStr.charAt(i);
            int digito = Character.getNumericValue(digitoChar);
            suma += digito;
        }
        return suma;
    }
}
