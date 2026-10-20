import java.util.Scanner;

public class Contador extends Thread{

    private char consonante;
    private String texto;
    private int contador;
    private static int contadorTotal = 0;

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

        System.out.println("La consonante " + consonante + " aparece " + contador + " veces");
    }
}
