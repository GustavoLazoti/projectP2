package filaCircularDupla;

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int qtde = 0;
        
        for(Node pAnda = this.head; qtde != this.count; pAnda = pAnda.getRight()) {
            sb.append(pAnda.getKey() + " " + pAnda.getData() + "\n");
            ++qtde;
        }
        return sb.toString();
    }
}
