package exercitiuLAB4;


import java.io.Serializable;

enum Format{
    A3,
    A4
}
public class Copiator extends EchipamentElectronic implements Serializable {
    private int p_ton;
    private Format format;

    public Copiator(String den, int inv, float pret, String zona, Status stat, int p_ton, Format format){
        super(den, inv, pret, zona, stat);
        this.p_ton=p_ton;
        this.format=format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    @Override
    public void afisare(){
        System.out.println("Copiator: "+denumire + " numar inventar: " + nr_inv+  " Pret: " + pret + "Pagini/Toner: " + p_ton + " format: " + format);
    }
}
