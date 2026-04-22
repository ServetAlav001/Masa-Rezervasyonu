import java.util.ArrayList;
import java.util.List;

enum MasaDurumu {
    BOS, DOLU, REZERVE
}

class Siparis {
    private String urunAdi;
    private int adet;
    private double birimFiyat;

    public Siparis(String urunAdi, int adet, double birimFiyat) {
        this.urunAdi = urunAdi;
        this.adet = adet;
        this.birimFiyat = birimFiyat;
    }

    public String getUrunAdi() { return urunAdi; }
    public int getAdet() { return adet; }
    public double getToplamFiyat() { return adet * birimFiyat; }

    @Override
    public String toString() {
        return urunAdi + " - " + adet + " adet x " + birimFiyat + " TL = " + getToplamFiyat() + " TL";
    }
}

class Masa {
    private int masaNo;
    private int kapasite;
    private MasaDurumu durum;
    private List<Siparis> siparisler;

    public Masa(int masaNo, int kapasite) {
        this.masaNo = masaNo;
        this.kapasite = kapasite;
        this.durum = MasaDurumu.BOS;
        this.siparisler = new ArrayList<>();
    }

    public int getMasaNo() { return masaNo; }
    public int getKapasite() { return kapasite; }
    public MasaDurumu getDurum() { return durum; }

    public void setDurum(MasaDurumu durum) { this.durum = durum; }
    public List<Siparis> getSiparisler() { return siparisler; }

    public void siparisEkle(Siparis siparis) {
        this.siparisler.add(siparis);
    }

    public double toplamHesap() {
        double toplam = 0;
        for (Siparis s : siparisler) {
            toplam += s.getToplamFiyat();
        }
        return toplam;
    }

    public void masayiSifirla() {
        this.siparisler.clear();
        this.durum = MasaDurumu.BOS;
    }
}