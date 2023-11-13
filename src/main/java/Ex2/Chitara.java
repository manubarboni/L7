package Ex2;

public class Chitara extends InstrumentMuzical{
    TipChitara tipChitara;
    int nr_corzi;

    public Chitara() {
    }

    public Chitara(String producator, int pret, TipChitara tipChitara, int nr_corzi) {
        super(producator, pret);
        this.tipChitara = tipChitara;
        this.nr_corzi = nr_corzi;
    }

    public TipChitara getTipChitara() {
        return tipChitara;
    }

    public void setTipChitara(TipChitara tipChitara) {
        this.tipChitara = tipChitara;
    }

    public int getNr_corzi() {
        return nr_corzi;
    }

    public void setNr_corzi(int nr_corzi) {
        this.nr_corzi = nr_corzi;
    }

    @Override
    public String toString() {
        return "Chitara{" +
                "tipChitara=" + tipChitara +
                ", nr_corzi=" + nr_corzi +
                "} " + super.toString();
    }
}
