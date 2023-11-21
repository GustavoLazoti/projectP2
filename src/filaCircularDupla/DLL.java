package filaCircularDupla;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DLL {
    private Node head = null;
    private int count = 0;

    public DLL() {
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int getCount() {
        return this.count;
    }

    public void showAscending() {
        if (this.head == null) {
            System.out.print("Lista vazia!\n");
        } else {
            System.out.print("Lista: [ ");

            Node pAnda;
            for(pAnda = this.head; pAnda.getRight() != this.head; pAnda = pAnda.getRight()) {
                System.out.print(pAnda.getKey() + " ");
            }

            System.out.print(pAnda.getKey() + "]");
        }

    }
    public void xG(int Lin)
    {
        Node pAnda = this.head;
        if (this.head == null) {
            System.out.print("Lista vazia!\n");
            return;
        }else if (this.head.getLeft().getKey() < Lin) {
            System.err.println("Lin é maior que a quantidade de linhas.\n");
            return;
        }
        while (pAnda.getKey() != Lin) {
            pAnda = pAnda.getRight();
        }
        while (pAnda != null && pAnda != this.head) {
            Node temp = pAnda;
            pAnda = pAnda.getRight();
            remove(temp.getKey());
        }
        rearrangeNodesOrder();
    }
    public void XG(int Lin)
    {
        Node pAnda = this.head;
        if (this.head == null) {
            System.out.print("Lista vazia!\n");
            return;
        }else if (this.head.getKey() > Lin) {
            System.err.println("Lin é menor que a quantidade de linhas.\n");
            return;
        }
        while (pAnda.getKey() != Lin) {
            pAnda = pAnda.getRight();
        }

        while (pAnda != null && pAnda.getKey() <= Lin) {
            if (pAnda == this.head) {
                this.head = this.head.getRight();
            }
            Node temp = pAnda;
            pAnda = pAnda.getLeft();
            remove(temp.getKey());
        }
        rearrangeNodesOrder();
    }

    public void showDescending() {
        if (this.head == null) {
            System.out.print("Lista vazia!\n");
        } else {
            System.out.print("Lista: [ ");
            Node pAnda = this.head;

            while(pAnda.getLeft() != this.head) {
                pAnda = pAnda.getLeft();
                System.out.print(pAnda.getKey() + " ");
            }

            System.out.print(pAnda.getLeft().getKey() + "]");
        }

    }

    public boolean insertAscending(int Key, String dado) {
        Node novoNo = new Node(Key, dado, (Node)null, (Node)null);
        if (this.head == null) {
            this.head = novoNo;
            novoNo.setRight(novoNo);
            novoNo.setLeft(novoNo);
        } else {
            Node pAnda = this.head;

            Node pAnt;
            for(pAnt = null; pAnda.getRight() != this.head && pAnda.getKey() < Key; pAnda = pAnda.getRight()) {
                pAnt = pAnda;
            }

            if (pAnt == null && pAnda.getKey() > Key) {
                novoNo.setLeft(this.head.getLeft());
                this.head.getLeft().setRight(novoNo);
                this.head.setLeft(novoNo);
                novoNo.setRight(this.head);
                this.head = novoNo;
            } else if (pAnda.getKey() > Key && pAnt != null) {
                pAnt.getRight().setLeft(novoNo);
                novoNo.setRight(pAnda);
                pAnt.setRight(novoNo);
                novoNo.setLeft(pAnt);
            } else {
                pAnda.getRight().setLeft(novoNo);
                novoNo.setRight(pAnda.getRight());
                pAnda.setRight(novoNo);
                novoNo.setLeft(pAnda);
            }
        }

        ++this.count;
        return true;
    }
    public boolean canFiNode(int Key) {
        if (this.isEmpty()) {
            return false;
        } else {
            Node pAnda;
            for(pAnda = this.head; pAnda.getRight() != this.head && pAnda.getKey() != Key; pAnda = pAnda.getRight()) {
            }

            return pAnda.getRight() == this.head && pAnda.getKey() != Key ? false : true;
        }
    }
    public Node search(int Key) {
        if (this.isEmpty()) {
            return null;
        } else {
            Node pAnda;
            for(pAnda = this.head; pAnda.getRight() != this.head && pAnda.getKey() != Key; pAnda = pAnda.getRight()) {
            }

            return pAnda.getRight() == this.head && pAnda.getKey() != Key ? null : pAnda;
        }
    }

    public boolean remove(int Key) {
        if (this.isEmpty()) {
            return false;
        } else {
            Node pAnda = this.head;

            Node pAnt;
            for(pAnt = null; pAnda.getRight() != this.head && pAnda.getKey() != Key; pAnda = pAnda.getRight()) {
                pAnt = pAnda;
            }

            if (pAnda.getRight() == this.head && pAnda.getKey() != Key) {
                return false;
            } else {
                if (pAnt == null && pAnda.getKey() == Key) {
                    pAnda.getLeft().setRight(pAnda.getRight());
                    this.head = this.head.getRight();
                    this.head.setLeft(pAnda.getLeft());
                } else {
                    pAnt.setRight(pAnda.getRight());
                    pAnda.getRight().setLeft(pAnt);

                }
                pAnda.setLeft((Node)null);
                pAnda.setRight((Node)null);
                pAnda = null;
                --this.count;
                return true;
            }
        }
    }

    public void cortarMarcado(DLL textoMarcado) {
        if (textoMarcado.isEmpty()) {
            System.err.println("Nada foi marcado para cortar.");
            return;
        }
        if (this.canFiNode(textoMarcado.head.getKey())) {
        Node pAndaMarcado = textoMarcado.head;
        int i = 1;
        while (pAndaMarcado != null && textoMarcado.count != i) {
            Node pAndaPrincipal = this.head;
    
            while (pAndaPrincipal != null && pAndaPrincipal.getKey() != pAndaMarcado.getKey()) {
                pAndaPrincipal = pAndaPrincipal.getRight();
            }
    
            if (pAndaPrincipal != null) {
                Node temp = pAndaPrincipal.getRight();
                this.remove(pAndaPrincipal.getKey());
            }
            
            pAndaMarcado = pAndaMarcado.getRight();
            i++;
        }
        System.out.println("Texto marcado cortado com sucesso da lista principal.");    
        }else
        {
            System.err.println("Intervalo marcado incorreto ou já modificado.");
        }
    }

    public DLL marcarTexto(int LinIni, int LinFim) {
        if (LinIni <= 0 || LinFim <= 0 || LinIni > LinFim || LinFim > this.getCount()) {
            System.err.println("Intervalo inválido. Verifique as linhas e tente novamente.");
            return null;
        }
        DLL textoMarcado = new DLL();

        Node pAnda = this.head;
        for (int i = 1; i < LinIni; i++) {
            pAnda = pAnda.getRight();
        }
        for (int i = LinIni; i <= LinFim; i++) {
            textoMarcado.insertAscending(i, pAnda.getData());
            pAnda = pAnda.getRight();
        }
        System.out.println("O texto foi marcado!");
        return textoMarcado;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int qtde = 0;
        
        for(Node pAnda = this.head; qtde != this.count; pAnda = pAnda.getRight()) {
            sb.append(pAnda.getKey() + " " + pAnda.getData() + "\n");
            ++qtde;
        }
        return sb.toString();
    }
    public void rearrangeNodesOrder() {
        Node pAnda = this.head;
        int newId = 1;
        for(int i = 0; i != this.count; i++ )
        {
         pAnda.setKey(newId);
         pAnda = pAnda.getRight();
         newId++;
        }
        
    }

    public void escreverArquivo(String caminho, DLL lista)
{
    try (PrintWriter writer = new PrintWriter(caminho)) {
        Node current = this.head;
        do {
            writer.println(current.getData());
            current = current.getRight();
        } while (current != this.head);
    } catch (FileNotFoundException e) {
        System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
    }
}
}
