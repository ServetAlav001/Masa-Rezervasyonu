import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class KafeYonetimi {
    private List<Masa> masalar;
    private int toplamMusteriGunluk;
    private double ciroGunluk;
    private Map<String, Integer> satilanUrunIstatistikleri;

    public KafeYonetimi() {
        this.masalar = new ArrayList<>();
        this.toplamMusteriGunluk = 0;
        this.ciroGunluk = 0.0;
        this.satilanUrunIstatistikleri = new HashMap<>();
    }

    // Helper metot: Verilen masa nosuna göre masayı bulur
    private Masa masaBul(int masaNo) {
        for (Masa masa : masalar) {
            if (masa.getMasaNo() == masaNo) {
                return masa;
            }
        }
        return null;
    }

    public void masaOlustur(int masaNo, int kapasite) {
        if (masaBul(masaNo) != null) {
            System.out.println("Hata: " + masaNo + " numaralı masa zaten mevcut!");
            return;
        }
        masalar.add(new Masa(masaNo, kapasite));
        System.out.println("Masa " + masaNo + " başarıyla oluşturuldu.");
    }

    public void musteriOturt(int musteriSayisi) {
        Masa uygunMasa = null;
        for (Masa masa : masalar) {
            if (masa.getDurum() == MasaDurumu.BOS && masa.getKapasite() >= musteriSayisi) {
                // En uygun (kapasitesi en yakın) masayı bulmak için küçük bir optimizasyon yapılabilir 
                // ancak basitlik açısından ilk bulduğu uygun masaya oturtuyoruz.
                if (uygunMasa == null || masa.getKapasite() < uygunMasa.getKapasite()) {
                    uygunMasa = masa;
                }
            }
        }

        if (uygunMasa != null) {
            uygunMasa.setDurum(MasaDurumu.DOLU);
            toplamMusteriGunluk += musteriSayisi;
            System.out.println("Müşteriler Masa " + uygunMasa.getMasaNo() + "'a yerleştirildi.");
        } else {
            System.out.println("Hata: " + musteriSayisi + " kişi için uygun boş masa bulunamadı!");
        }
    }

    public void siparisEkle(int masaNo, String urunAdi, int adet, double fiyat) {
        Masa masa = masaBul(masaNo);
        if (masa == null) {
            System.out.println("Hata: Masa bulunamadı.");
            return;
        }
        if (masa.getDurum() == MasaDurumu.BOS) {
            System.out.println("Hata: Bu masa boş, önce müşteri oturtmalısınız.");
            return;
        }

        Siparis yeniSiparis = new Siparis(urunAdi, adet, fiyat);
        masa.siparisEkle(yeniSiparis);
        System.out.println("Sipariş eklendi: " + urunAdi);

        // Gün sonu istatistiği için satılan ürünü kaydet
        satilanUrunIstatistikleri.put(
                urunAdi,
                satilanUrunIstatistikleri.getOrDefault(urunAdi, 0) + adet
        );
    }

    public void hesapGoruntule(int masaNo) {
        Masa masa = masaBul(masaNo);
        if (masa != null) {
            System.out.println("\n--- Masa " + masaNo + " Siparişleri ---");
            if (masa.getSiparisler().isEmpty()) {
                System.out.println("Sipariş bulunmuyor.");
            } else {
                for (Siparis s : masa.getSiparisler()) {
                    System.out.println(s.toString());
                }
                System.out.println("-------------------------");
                System.out.println("Toplam Hesap: " + masa.toplamHesap() + " TL");
            }
        } else {
            System.out.println("Hata: Masa bulunamadı.");
        }
    }

    public void hesapKapat(int masaNo) {
        Masa masa = masaBul(masaNo);
        if (masa != null && masa.getDurum() != MasaDurumu.BOS) {
            double odenecekTutar = masa.toplamHesap();
            ciroGunluk += odenecekTutar; // Günlük ciroya ekle

            System.out.println("Masa " + masaNo + " hesabı (" + odenecekTutar + " TL) tahsil edildi.");
            masa.masayiSifirla(); // Masayı boşalt ve siparişleri temizle
        } else {
            System.out.println("Hata: Masa bulunamadı veya zaten boş.");
        }
    }

    public void gunSonuRaporu() {
        System.out.println("\n====== GÜN SONU RAPORU ======");
        System.out.println("Toplam Ağırlanan Müşteri: " + toplamMusteriGunluk);
        System.out.println("Toplam Ciro: " + ciroGunluk + " TL");

        String enCokSatanUrun = "Yok";
        int maxSatis = 0;

        for (Map.Entry<String, Integer> entry : satilanUrunIstatistikleri.entrySet()) {
            if (entry.getValue() > maxSatis) {
                maxSatis = entry.getValue();
                enCokSatanUrun = entry.getKey();
            }
        }

        System.out.println("En Çok Sipariş Edilen Ürün: " + enCokSatanUrun + " (" + maxSatis + " adet)");
        System.out.println("=============================");
    }
}