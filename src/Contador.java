import java.util.Scanner;

public class Contador extends Thread{

    private char consonante;
    private String texto;
    private int contador;
    public static int contadorTotal = 0;

    public Contador(char consonante, String texto){

        this.consonante = consonante;
        this.texto = texto;
        this.contador = 0;
    }

    @Override 

    public void run(){
        
        for (int i = 0; i < texto.length(); i++){

            if (texto.charAt(i) == consonante){
                contador ++;
            }
        }

        synchronized (Contador.class){
            contadorTotal += contador;
        }

        System.out.println("El hilo" + Thread.currentThread().getName() + " contó que la letra se repite " + contador + " veces");
    }

    public static void main (String[] args) throws InterruptedException{

        Scanner teclado = new Scanner (System.in);

        char[] consonantes = new char[5];

        for (int i = 0; i < consonantes.length; i++){
            
            System.out.println("Dime una consonante: ");
            String letra = teclado.nextLine();

            while (letra.length() != 1 || !Character.isLetter(letra.charAt(0)) || "aeiou".indexOf(letra.charAt(0)) != -1){

                System.out.println("Error. Dime una CONSONANTE válida:");
                letra = teclado.nextLine();
            }

            consonantes[i] = letra.charAt(0);
        }
    }
}
