package exercitiuLAB4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        List<EchipamentElectronic> echipamente=new ArrayList<>();

        //echipamente.add(new Imprimanta("Sony", 12, 1300, "imprimanta",Status.EXPUS, 20,12,200,Tiparire.COLOR));
        //echipamente.add(new Copiator("Xerox 3000", 2002, 2500, "copiator", Status.ACHIZITIONAT, 5000,Format.A4));

        Scanner fisier=new Scanner(new File("/Users/andreirusu/Documents/INFO_SEM2AN2/PJ/lab4/src/exercitiuLAB4/echipamente.txt"));
        String linie;
        while(fisier.hasNext()){
            linie=fisier.nextLine();
            String[]info=linie.split(",");
            switch(info[0]){
                case "Imprimanta":
                    echipamente.add(new Imprimanta(info[1], Integer.parseInt(info[2]), Float.parseFloat(info[3]), info[4],Status.valueOf(info[5]), Integer.parseInt(info[6]),Integer.parseInt(info[7]),Integer.parseInt(info[8]),Tiparire.valueOf(info[9])));
                    break;
                case "Copiator":
                    echipamente.add(new Copiator(info[1], Integer.parseInt(info[2]), Float.parseFloat(info[3]), info[4],Status.valueOf(info[5]), Integer.parseInt(info[6]), Format.valueOf(info[7])));
                    break;
                case "SistemCalcul":
                    echipamente.add(new SistemCalcul(info[1],Integer.parseInt(info[2]), Float.parseFloat(info[3]), info[4], Status.valueOf(info[5]),info[6], Integer.parseInt(info[7]), Integer.parseInt(info[8]), So.valueOf(info[9])));
                    break;
            }
        }



        System.out.println("Echipamentele au fost adaugate corect in lista din fisierul echipamente.txt!!");

        Scanner s=new Scanner(System.in);
        int opt;
        do {
            System.out.println("1. afisare echipament\n2.afisare imprimante\n3.afisare sistem de calcul\n4. afisare copiatoare\n5.modificare stare echipament\n6. setare mod imprimanta\n7.setare mod copiere copiatoare\n8. modificare sistem de operare\n9. afisare echipamente vandute\n10. serializare in echip.bin\n 11.deserializare din echip.bin\n12. iesire");
            opt = s.nextInt();
            switch(opt) {
                case 1:
                    afisareEchipamente(echipamente);
                    break;
                case 2:
                    afisareImprimante(echipamente);
                    break;
                case 3:
                    afisareSistemCalcul(echipamente);
                    break;
                case 4:
                    afisareCopiatoare(echipamente);
                    break;
                case 5: {
                    System.out.println("Ce echipament vrei sa modifici:introdu nr_inventar: ");
                    int nr_inv = s.nextInt();
                    System.out.println("in ce stare vrei sa modifici echipamentul cu numar inventar " + nr_inv);
                    Status stare = Status.valueOf(s.next().toUpperCase());
                    modificareStare(nr_inv, echipamente, stare);
                    break;
                }
                case 6:
                    //setareModImprimanta();
                    break;
                case 7: {
                    System.out.println("Ce format vrei sa folosesti pentru copiatoare? ");
                    Format f=Format.valueOf(s.next().toUpperCase());
                    modificareFormatCopiatoare(echipamente, f);
                    break;
                }
                case 8:
                {
                    System.out.println("pe ce sistem de calcul vrei sa modifici sistemul de operare? Din cele propuse introdu numar inventar");
                    afisareSistemCalcul(echipamente);
                    int inv=s.nextInt();
                    System.out.println("Ce sistem de operare vrei sa introduci? ");
                    So so=So.valueOf(s.next().toUpperCase());
                    modificareSo(echipamente, inv, so);
                    break;
                }
                case 9:
                    afisareVandute(echipamente);
                    break;
                case 10: {
                    try {
                        serializeaza(echipamente, "/Users/andreirusu/Documents/INFO_SEM2AN2/PJ/lab4/src/exercitiuLAB4/echip.bin");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 11: {
                    try {
                        List<EchipamentElectronic> echipDeserializat;
                        echipDeserializat = (List<EchipamentElectronic>)deserializare("/Users/andreirusu/Documents/INFO_SEM2AN2/PJ/lab4/src/exercitiuLAB4/echip.bin");
                        for (EchipamentElectronic e : echipDeserializat) {
                            e.afisare();
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 12:
                    System.out.println("La reverdere!!");
                    break;
                default:
                    System.out.println("Alege una dintr optiunile propuse");
            }
        }while(opt!=12);

    }

    public static void modificareFormatCopiatoare (List<EchipamentElectronic> echipamente, Format f){
        for (EchipamentElectronic e : echipamente) {
            if (e instanceof Copiator) {
                ((Copiator) e).setFormat(f);
            }
        }
    }


    public static void afisareEchipamente(List<EchipamentElectronic> echipamente){
        for(EchipamentElectronic e: echipamente){
            e.afisare();
        }
    }
    public static void afisareImprimante(List<EchipamentElectronic>echipamente){
        System.out.println("Imprimantele sunt: ");
        for(EchipamentElectronic e : echipamente){
            if(e instanceof Imprimanta){
                e.afisare();
            }
        }
    }
    public static void afisareSistemCalcul(List<EchipamentElectronic>echipamente){
        System.out.println("Sistemele de calcul sunt: ");
        for(EchipamentElectronic e : echipamente){
            if(e instanceof SistemCalcul){
                e.afisare();
            }
        }
    }
    public static void afisareCopiatoare(List<EchipamentElectronic>echipamente){
        System.out.println("Copiatoare sunt: ");
        for(EchipamentElectronic e : echipamente){
            if(e instanceof Copiator){
                e.afisare();
            }
        }
    }

    public static void modificareStare(int inventar, List<EchipamentElectronic>echipamente, Status s) {
        int gasit=0;
        for (EchipamentElectronic e : echipamente) {
            if (e.nr_inv == inventar) {
                e.setStatus(s);
                gasit=1;
            }
            else{
                gasit=0;
            }
        }
        if(gasit==0){
            System.out.println("Nu am gasit niciun echipament cu numarul de inventar: " + inventar);
        }
    }
    public static void modificareSo(List<EchipamentElectronic>echipamente, int inv, So so){
        for(EchipamentElectronic e:echipamente){
            if(e.nr_inv==inv && e instanceof SistemCalcul){
                ((SistemCalcul)e).setSo(so);
            }
        }
    }
    public static void afisareVandute(List<EchipamentElectronic>echipamente){
        for(EchipamentElectronic e: echipamente){
            if(e.getStatus()==Status.VANDUT){
                e.afisare();
            }
        }

    }
    public static void serializeaza(List<EchipamentElectronic>echipamente,String fis) throws IOException {
        FileOutputStream f=new FileOutputStream(fis);
        ObjectOutputStream o=new ObjectOutputStream(f); //e nevoie pentru serializare deserializare
        o.writeObject(echipamente); //realizeaza serializare
        System.out.println("serializare realizata!!");
        o.close();
        f.close();
    }
    public static Object deserializare(String fis) throws IOException, ClassNotFoundException {
        FileInputStream f=new FileInputStream(fis);
        ObjectInputStream o=new ObjectInputStream(f);
        Object oo=o.readObject();
        o.close();
        f.close();
        return o;
    }
}




