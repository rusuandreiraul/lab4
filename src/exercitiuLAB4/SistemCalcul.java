package exercitiuLAB4;


import java.io.Serializable;

enum So{
    WINDOWS,
    LINUX
}
public class SistemCalcul extends EchipamentElectronic implements Serializable
{
    private String tip_mon;
    private int vit_proc;
    private int c_hdd;
    private So so;

    public SistemCalcul(String den, int inv, float pret,String zona, Status stat, String monitor, int viteza, int c_hdd, So so){
        super(den, inv, pret, zona, stat);
        this.tip_mon=monitor;
        this.vit_proc=viteza;
        this.c_hdd=c_hdd;
        this.so=so;
    }

    public void setSo(So so) {
        this.so = so;
    }

    @Override
    public void afisare(){
        System.out.println("Sistem de calculc: "+denumire + " Pret: " + pret + " numar inventar: " + nr_inv+ " viteza procesor:  " + vit_proc + " sistem de operare: " + so);
    }
}
