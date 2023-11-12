import filaCircularDupla.DLL;
import filaCircularDupla.Node;

import java.io.Console;
import java.util.Scanner;
import static Interface.MenuInterface.menu;

public class Main {
    public static void main(String[] args)
    {
        //comit
        DLL listaFonte = new DLL();
        DLL listaTemp = new DLL();
        int on =1;
        while ( on == 1)
        {
            Scanner scanner = new Scanner(System.in);
            String comando = scanner.nextLine();
            listaTemp = menu(comando, listaFonte, listaTemp);
        }
    }
}