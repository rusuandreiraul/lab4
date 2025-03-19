package exercitiuLAB4;


import java.io.Serializable;

enum Tiparire{
    COLOR,
    ALB_NEGRU
}
public class Imprimanta extends EchipamentElectronic implements Serializable {
    private int ppm;
    private int dpi;
    private int p_car;
    private Tiparire tiparire;

    public Imprimanta(String denumire, int inventar, float pret, String zona_mag, Status status, int ppm, int dpi, int p_car, Tiparire tiparire){
        super(denumire, inventar, pret, zona_mag, status);
        this.ppm=ppm;
        this.dpi=dpi;
        this.p_car=p_car;
        this.tiparire=tiparire;

    }

    @Override
    public void afisare(){
        System.out.println("Imprimanta: "+denumire + " numar inventar: " + nr_inv+  " Pret: " + pret + "Pagini/Minut " + ppm + " tiparire: " + tiparire);
    }

}
