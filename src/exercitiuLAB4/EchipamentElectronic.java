package exercitiuLAB4;


import java.io.Serializable;

enum Status{
    ACHIZITIONAT,
    EXPUS,
    VANDUT
}
public abstract class EchipamentElectronic implements Serializable {
    protected String denumire;
    protected int nr_inv;
    protected float pret;
    protected String zona_mag;
    protected Status status;

    public EchipamentElectronic(String den, int inv, float pret, String zona_mag, Status stat){
        this.denumire=den;
        this.nr_inv=inv;
        this.pret=pret;
        this.zona_mag=zona_mag;
        this.status=stat;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public abstract void afisare();


}
