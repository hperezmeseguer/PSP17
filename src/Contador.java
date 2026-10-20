import java.util.Scanner;

// Clase que contará las consonantes pedidas
public class Contador extends Thread{

    //Consonante que contará un hilo
    private char consonante;
    private String texto;
    //Nº de veces que se repite la consonante
    private int contador;
    //Variable compartida por todos los hilos que guarda la suma total
    public static int contadorTotal = 0;

    //Constructor que recibe la consonante y el texto
    public Contador(char consonante, String texto){

        this.consonante = consonante;
        this.texto = texto;
        //Inicializamos el contador en 0
        this.contador = 0; 
    }

    // Método que recorre un hilo al llamarse a start()
    @Override 

    public void run(){
        
        // Bucle que recorre todas las posiciones del texto
        for (int i = 0; i < texto.length(); i++){

            //Condicional que comprueba si el carácter coincide con el que buscamos
            //Si coincide, aumenta el contador
            if (texto.charAt(i) == consonante){
                contador ++;
            }
        }

        //Bloque sincronizado para evitar problemas al modificar la suma compartida
        synchronized (Contador.class){
            contadorTotal += contador;
        }

        //Mostramos el nombre del hilo, la consonante y el número de repeticiones
        System.out.println("El hilo " + Thread.currentThread().getName() + " contó que la letra se repite " + contador + " veces");
    }

    public static void main (String[] args) throws InterruptedException{

        //Scanner que lee los datos introducidos
        Scanner teclado = new Scanner (System.in);

        //Array que almacena las 5 consonantes dadas
        char[] consonantes = new char[5];

        //Bucle para pedir 5 consonantes al usuario
        for (int i = 0; i < consonantes.length; i++){
            
            System.out.println("Dime una consonante: ");
            String letra = teclado.nextLine();

            //Bucle que comprueba que se introduzca un solo caracter, que sea una letra y consonante
            while (letra.length() != 1 || !Character.isLetter(letra.charAt(0)) || "aeiou".indexOf(letra.charAt(0)) != -1){

                System.out.println("Error. Dime una CONSONANTE válida:");
                letra = teclado.nextLine();
            }

            //Guardamos la consonante válida en el array
            consonantes[i] = letra.charAt(0);
        }

        //Pedimos el texto al usuario
        System.out.println("Escribe un texto:");
        String texto = teclado.nextLine();

        //Repetimos el texto 2000 veces
        texto = texto.repeat(2000);

        //Array que guarda los 5 objetos
        Contador[] hilos = new Contador[5];
        
        //Bucle en el que se crean, nombran e inician los hilos
        for (int i = 0; i < hilos.length; i++){

            //Creamos sun hilo con su consonante y el texto
            hilos[i] = new Contador(consonantes[i], texto);
            //Asignamos un nombre identificativo a cada hilo
            hilos[i].setName("Hilo" + consonantes[i]);
            // Iniciamos el hilo
            hilos[i].start();
        }

        //Esperamos a que terminen todos los hilos
        for (int i = 0; i < hilos.length; i++){

            hilos[i].join();
        }

        //Mostramos la suma total de los 5 hilos
        System.out.println ("Suma de todas las consonantes comprobadas: " + contadorTotal);

        //Cerramos el Scanner
        teclado.close();
    }
}
