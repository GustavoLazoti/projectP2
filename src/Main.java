import filaCircularDupla.DLL;
import java.util.Scanner;
import static Interface.MenuInterface.menu;

public class Main {
    public static void main(String[] args)
    {
        //comit
        DLL listaFonte = new DLL();
        DLL listaTemp = new DLL();
        int on =1;
        
        listaTemp = menu(listaFonte, listaTemp);
        System.out.println("você escolheu sair.");
    }
}