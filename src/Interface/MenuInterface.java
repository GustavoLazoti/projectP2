package Interface;
import filaCircularDupla.DLL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MenuInterface
{
    public static DLL menu(DLL fonte, DLL temp)
    {
        int on = 1;
        Scanner scanner = new Scanner(System.in);
        DLL textoMarcado = new DLL();
        while(on == 1){
        String escolha = scanner.nextLine();
        if(escolha.toLowerCase().startsWith(":h"))
        {
            help();
        } else if (escolha.toLowerCase().startsWith(":q!"))
        {
            System.out.println("Você tem certeza que deseja sair sem salvar? ( Y / N )");
            if (scanner.nextLine().equalsIgnoreCase("Y"))
            {
                
                System.out.println("Você escolheu sair sem salvar.");
                return null;
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
            if (temp.isEmpty()) {
                System.out.println("O arquivo não foi aberto ainda.");
            }
            System.out.println(temp);
        }else if (escolha.toLowerCase().startsWith(":w"))
        {
            if (escolha.length() < 3) {
                System.out.println("Falta informação na sua requisição, por favor verifique o comando :help\n");
            
            }else
            {
                escolha = (String) escolha.subSequence(3, escolha.length());
                temp.escreverArquivo(escolha, temp);
                System.out.println("Conteúdo salvo em \"" + escolha + "\".");
            }  
        }else if(escolha.startsWith(":xG"))
        {
            if (escolha.length() < 3) {
                System.out.println("Falta informação na sua requisição ou algo está incorreto, por favor verifique se enviou um valor inteiro (linha) e o comando :help\n");
            }else
            {
                escolha = (String) escolha.subSequence(3, escolha.length());
                escolha = escolha.replaceAll("\\s", "");
                try {
                    int numeroEmInt = Integer.parseInt(escolha);
                    temp.xG(numeroEmInt);
                } catch (NumberFormatException e) {
                    System.out.println("Lin não é um número válido.");
                }
            }
            
        }else if(escolha.startsWith(":XG"))
        {
            if (escolha.length() < 3) {
                System.out.println("Falta informação na sua requisição ou algo está incorreto, por favor verifique se enviou um valor inteiro (linha) e o comando :help\n");
            }else
            {
                escolha = (String) escolha.subSequence(3, escolha.length());
                escolha = escolha.replaceAll("\\s", "");
                try {
                    int numeroEmInt = Integer.parseInt(escolha);
                    temp.XG(numeroEmInt);
                } catch (NumberFormatException e) {
                    System.out.println("Lin não é um número válido.");
                }
            }
            
        }else if (escolha.toLowerCase().startsWith(":x"))
        {
             if (escolha.length() < 3) {
                System.out.println("Falta informação na sua requisição ou algo está incorreto, por favor verifique se enviou um valor inteiro (linha) e o comando :help\n");
            }else
            {
                escolha = (String) escolha.subSequence(3, escolha.length());
                escolha = escolha.replaceAll("\\s", "");
                temp.remove(Integer.parseInt(escolha));
            }
        }else if (escolha.toLowerCase().startsWith(":v")) {
                String trimmedInput = escolha.substring(2).trim();
                String[] parts = trimmedInput.split("\\s+");
                if (parts.length >= 2) {
                    int linIni = Integer.parseInt(parts[0]);
                    int linFim = Integer.parseInt(parts[1]);
                    textoMarcado = temp.marcarTexto(linIni, linFim);
                } else {
                    System.err.println("Entrada inválida. Certifique-se de incluir LinIni e LinFim.");
                }
            
        
        }else if (escolha.toLowerCase().startsWith(":y")) 
        {
            if (textoMarcado.isEmpty()) {
                System.out.println("Não existe texto marcado. Utilize :help para lhe ajudar.");
            }else
            {
                System.out.println("Texto marcado copiado para lista de cópia!");
            }
        }else if (escolha.toLowerCase().startsWith(":c")) 
        {
            temp.cortarMarcado(textoMarcado);
        }else
        {
            System.out.println("Não detectei sua escolha. Tente utilizar :help para conhecer os comandos.\n");
        }
        temp.rearrangeNodesOrder();
        }
    scanner.close();
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
