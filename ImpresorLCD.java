import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class ImpresorLCD {

    // Puntos fijos
    private final int[] pf1;
    private final int[] pf2;
    private final int[] pf3;
    private final int[] pf4;
    private final int[] pf5;
    //private String[][] matrizImpr;

    static final String CARACTER_VERTICAL = "|";
    static final String CARACTER_HORIZONTAL = "-";
    static final String POSICION_X = "X";
    static final String POSICION_Y = "Y";

    private HashMap<Integer,int[]> segmentosNumero = new HashMap<Integer,int[]>();

    // TODO code application logic here
    //String entrada = JOptionPane.showInputDialog("Digite el numero");
    //private int size;

    // Calcula el numero de filasDig
    //private int filasDig;
    //private int columDig;
    //private int totalFilas;
    //private int totalColum;

    public ImpresorLCD() {
        // Inicializa variables
        this.pf1 = new int[2];
        this.pf2 = new int[2];
        this.pf3 = new int[2];
        this.pf4 = new int[2];
        this.pf5 = new int[2];

        int[] oneSegments = {3,4};
        segmentosNumero.put(1, oneSegments );

        int[] twoSegments = {5,3,6,2,7};
        segmentosNumero.put(2, twoSegments );

        int[] threeSegments = {5,3,6,4,7};
        segmentosNumero.put(3, threeSegments );

        int[] fourSegments = {1,6,3,4};
        segmentosNumero.put(4, fourSegments );

        int[] fiveSegments = {5,1,6,4,7};
        segmentosNumero.put(5, fiveSegments );

        int[] sixSegments = {5,1,6,2,7,4};
        segmentosNumero.put(6, sixSegments );

        int[] sevenSegments = {5,3,4};
        segmentosNumero.put(7, sevenSegments );

        int[] eightSegments = {1,2,3,4,5,6,7};
        segmentosNumero.put(8, eightSegments );


        int[] nineSegments = {1,3,4,5,6,7};
        segmentosNumero.put(9, nineSegments );

        int[] zeroSegments = {1,2,3,4,5,7};
        segmentosNumero.put(0, zeroSegments );        
    }

    /**
     *
     * Metodo encargado de añadir una linea a la matriz de Impresion
     *
     * @param matriz Matriz Impresion
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param size Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(String[][] matriz, int[] punto, 
        String posFija,int size, String caracter) {

        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (int y = 1; y <= size; y++) 
            {
                int valor = punto[1] + y;
                matriz[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= size; i++) 
            {
                int valor = punto[0] + i;
                matriz[valor][punto[1]] = caracter;
            }
        }
    }

    /**
     *
     * Metodo encargado de un segmento a la matriz de Impresion
     *
     * @param segmento Segmento a adicionar
     */  
    private void adicionarSegmento(int segmento, String[][] matrizImpr, int size) {

        switch (segmento) {
            case 1:
                adicionarLinea(matrizImpr, this.pf1, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 2:
                adicionarLinea(matrizImpr, this.pf2, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 3:
                adicionarLinea(matrizImpr, this.pf5, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 4:
                adicionarLinea(matrizImpr, this.pf4, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 5:
                adicionarLinea(matrizImpr, this.pf1, POSICION_X,
                        size, CARACTER_HORIZONTAL);
                break;
            case 6:
                adicionarLinea(matrizImpr, this.pf2, POSICION_X,
                        size, CARACTER_HORIZONTAL);
                break;
            case 7:
                adicionarLinea(matrizImpr, this.pf3, POSICION_X,
                        size, CARACTER_HORIZONTAL);
                break;
            default:
                break;
        }
    }

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
     */
    private void adicionarDigito(int numero, String[][] matrizImpr, int size) {

        // Establece los segmentos de cada numero
        List<Integer> segList = new ArrayList<>();

        for (int i = 0; i < segmentosNumero.get(numero).length; i++) {
            segList.add(segmentosNumero.get(numero)[i]);
        }

        Iterator<Integer> iterator = segList.iterator();

        while (iterator.hasNext()) {
            adicionarSegmento(iterator.next(), matrizImpr, size);
        }
    }

    /**
     *
     * Metodo encargado de construir el espacio para imprimir un numero
     *
     * @param size Tamaño Segmento Digitos
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     */    

    private String[][] construirMatriz(int size, String numeroImp, int espacio){

        String[][] matrizImpr;

        int filasDig;
        int columDig;
        int totalColum;
        int totalFilas;

        // Calcula el numero de filas cada digito
        filasDig = (2 * size) + 3;

        // Calcula el numero de columna de cada digito
        columDig = size + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        totalFilas = filasDig;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        totalColum = (columDig * numeroImp.length())
                + (espacio * numeroImp.length());

        // crea matriz para almacenar los numero a imprimir
        matrizImpr = new String[totalFilas][totalColum];


        /* How to access size of matriz through matriz itself
        totalFilas
        matrizImpr.length

        totalColum
        matrizImpr[0].length;

        filasDig
        matrizImpr.length

        columDig
        ((totalColum - (espacio * numeroImp.length()))/numeroImp.length())

        */

        return matrizImpr;
    }

    /**
     *
     * Metodo encargado de imprimir un numero
     *
     * @param size Tamaño Segmento Digitos
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     */    
    private void imprimirNumero(int size, String numeroImp, int espacio, String[][] matrizImpr) {
        int pivotX = 0;
        char[] digitos;

        int totalFilas = matrizImpr.length;
        int totalColum = matrizImpr[0].length;
        int filasDig = matrizImpr.length;
        int columDig = ((totalColum - (espacio * numeroImp.length()))/numeroImp.length());

        // crea el arreglo de digitos
        digitos = numeroImp.toCharArray();

        // Inicializa matriz
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColum; j++) {
                matrizImpr[i][j] = " ";
            }
        }

        for (char digito : digitos) {
            
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(digito))
            {
                throw new IllegalArgumentException("Caracter " + digito
                    + " no es un digito");
            }

            int numero = Integer.parseInt(String.valueOf(digito));

            //Calcula puntos fijos
            this.pf1[0] = 0;
            this.pf1[1] = 0 + pivotX;

            this.pf2[0] = (filasDig / 2);
            this.pf2[1] = 0 + pivotX;

            this.pf3[0] = (filasDig - 1);
            this.pf3[1] = 0 + pivotX;

            this.pf4[0] = (columDig - 1);
            this.pf4[1] = (filasDig / 2) + pivotX;

            this.pf5[0] = 0;
            this.pf5[1] = (columDig - 1) + pivotX;

            pivotX = pivotX + columDig + espacio;

            adicionarDigito(numero, matrizImpr, size);
        }

        // Imprime matriz
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColum; j++) {
                System.out.print(matrizImpr[i][j]);
            }
            System.out.println();
        }
    }

     /**
     *
     * Metodo encargado de procesar la entrada que contiene el size del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el size del segmento de los digito
     * y el numero a imprimir
     * @param espacioDig Espacio Entre digitos
     */  
    public void procesar(String comando, int espacioDig) {
        
        String[] parametros;
        String[][] matrizImpr;
        int tam;

        if (!comando.contains(",")) {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        //Se hace el split de la cadena
        parametros = comando.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " contiene mas caracter ,"); 
        }
        
        //Valida la cantidad de parametros
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene los parametros requeridos"); 
        }
        
        //Valida que el parametro size sea un numerico
        if(isNumeric(parametros[0]))
        {
            tam = Integer.parseInt(parametros[0]);
            
            // se valida que el size este entre 1 y 10
            if(tam <1 || tam >10)
            {
                throw new IllegalArgumentException("El parametro size ["+tam
                        + "] debe estar entre 1 y 10");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }

        // Construir el espacio de imprecion
        matrizImpr = construirMatriz(tam, parametros[1],espacioDig);

        // Realiza la impresion del numero
        imprimirNumero(tam, parametros[1],espacioDig, matrizImpr);

    }

    /**
     *
     * Metodo encargado de validar si una cadena es numerica
     *
     * @param cadena Cadena
     */  
    static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
