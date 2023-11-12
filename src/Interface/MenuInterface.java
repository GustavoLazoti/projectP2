package Interface;
import filaCircularDupla.DLL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MenuInterface
{
    public static DLL menu(String escolha, DLL fonte, DLL temp)
    {

        Scanner scanner = new Scanner(System.in);
        if(escolha.toLowerCase().startsWith(":h"))
        {
            help();
        } else if (escolha.toLowerCase().startsWith(":q!"))
        {
            System.out.println("Você tem certeza que deseja sair sem salvar? ( Y / N )");
            if (scanner.nextLine().equalsIgnoreCase("Y"))
            {
                System.out.println("Você escolheu sair sem salvar.");
            }else
            {
                System.out.println("Abortado.");
            }
        } else if (escolha.toLowerCase().startsWith(":e"))
        {
            escolha = (String) escolha.subSequence(3,escolha.length());
            fonte = lerArquivo(escolha);
            temp = fonte;
        } else if (escolha.toLowerCase().startsWith(":s"))
        {
            System.out.println(temp);
        }
    return temp;
    }
    private static DLL lerArquivo(String caminho)
    {
        System.out.println(caminho);
        DLL arquivoFonte = new DLL();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            int key = 1;
            while ((linha = br.readLine()) != null) {
                arquivoFonte.insertAscending(key,linha);
                key++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return arquivoFonte;
    }
    private static void help() {
        System.out.println("=== Comandos ===");
        System.out.println("1. :e nomeArq.ext");
        System.out.println("2. :w nomeArq.ext");
        System.out.println("3. :q! ");
        System.out.println("4. :v LinIni LinFim");
        System.out.println("5. :y ");
        System.out.println("6. :c");
        System.out.println("7. :p LinIniPaste ");
        System.out.println("8. :s");
        System.out.println("9. :s LinIni LinFim");
        System.out.println("10. :x Lin");
        System.out.println("11. :xG Lin ");
        System.out.println("12. :XG Lin");
        System.out.println("13. :/ elemento");
        System.out.println("14. :a posLin");
        System.out.println("15. :i posLin [conteudo da nova linha]");
        System.out.println("16. :help");
    }
}
