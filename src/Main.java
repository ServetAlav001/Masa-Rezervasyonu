import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KafeYonetimi yonetim = new KafeYonetimi();
        boolean calisiyor = true;

        System.out.println("--- Kafe Yönetim Sistemine Hoş Geldiniz ---");

        while (calisiyor) {
            System.out.println("\nLütfen bir işlem seçiniz:");
            System.out.println("1 - Masa Oluştur");
            System.out.println("2 - Müşteri Oturt (Masa Açılışı)");
            System.out.println("3 - Sipariş Ekle");
            System.out.println("4 - Masa Siparişlerini ve Hesabı Görüntüle");
            System.out.println("5 - Hesabı Kapat (Ödeme Al)");
            System.out.println("6 - Gün Sonu Raporu Al");
            System.out.println("0 - Çıkış");

            int secim = guvenliIntAl(scanner, "Seçiminiz: ");

            switch (secim) {
                case 1:
                    int masaNo = guvenliIntAl(scanner, "Masa No: ");
                    int kapasite = guvenliIntAl(scanner, "Kapasite: ");
                    yonetim.masaOlustur(masaNo, kapasite);
                    break;
                case 2:
                    int musteriSayisi = guvenliIntAl(scanner, "Müşteri Sayısı: ");
                    yonetim.musteriOturt(musteriSayisi);
                    break;
                case 3:
                    int siparisMasaNo = guvenliIntAl(scanner, "Masa No: ");
                    System.out.print("Ürün Adı: ");
                    String urunAdi = scanner.nextLine();
                    int adet = guvenliIntAl(scanner, "Adet: ");
                    double fiyat = guvenliDoubleAl(scanner, "Birim Fiyat: ");
                    yonetim.siparisEkle(siparisMasaNo, urunAdi, adet, fiyat);
                    break;
                case 4:
                    int goruntulenecekMasa = guvenliIntAl(scanner, "Masa No: ");
                    yonetim.hesapGoruntule(goruntulenecekMasa);
                    break;
                case 5:
                    int kapanacakMasa = guvenliIntAl(scanner, "Masa No: ");
                    yonetim.hesapKapat(kapanacakMasa);
                    break;
                case 6:
                    yonetim.gunSonuRaporu();
                    break;
                case 0:
                    calisiyor = false;
                    System.out.println("Sistemden çıkılıyor. İyi günler!");
                    break;
                default:
                    System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
            }
        }
        scanner.close();
    }

    // --- Güvenli Kullanıcı Girişi Fonksiyonları (Kod Tekrarını Önler) ---

    private static int guvenliIntAl(Scanner scanner, String mesaj) {
        while (true) {
            System.out.print(mesaj);
            if (scanner.hasNextInt()) {
                int deger = scanner.nextInt();
                scanner.nextLine(); // Buffer temizleme
                return deger;
            } else {
                System.out.println("Hata: Lütfen geçerli bir tam sayı giriniz.");
                scanner.nextLine();
            }
        }
    }

    private static double guvenliDoubleAl(Scanner scanner, String mesaj) {
        while (true) {
            System.out.print(mesaj);
            if (scanner.hasNextDouble()) {
                double deger = scanner.nextDouble();
                scanner.nextLine(); // Buffer temizleme
                return deger;
            } else {
                System.out.println("Hata: Lütfen geçerli bir sayı giriniz (Örn: 45,50).");
                scanner.nextLine();
            }
        }
    }
}