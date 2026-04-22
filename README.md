# ☕ Kafe Yönetim Sistemi (Console Application)

Bu proje, bir kafenin günlük operasyonel süreçlerini (masa yönetimi, sipariş takibi ve hesap işlemleri) simüle eden, **Java** ile geliştirilmiş nesne yönelimli (OOP) bir konsol uygulamasıdır.

## 🚀 Proje Amacı
Geleneksel ve manuel olarak yürütülen kafe sipariş süreçlerini dijitalleştirmek, insan hatasını en aza indirmek ve gün sonu ciro takibini otomatikleştirerek işletme verimliliğini artırmaktır. Proje, temel yazılım mühendisliği prensiplerini ve sınıf yapılarını pratikte uygulamak amacıyla tasarlanmıştır.

## ⚙️ Temel Özellikler

- **Masa Yönetimi:** Belirli kapasitelere sahip yeni masalar oluşturma.
- **Müşteri Yerleştirme:** Gelen müşteri sayısına göre sistemdeki en uygun boş masayı bularak statüsünü "Dolu" olarak güncelleme.
- **Sipariş Takibi:** Dolu masalara ürün, adet ve birim fiyat bilgisiyle dinamik sipariş ekleme.
- **Hesap İşlemleri:** Masaya ait tüm siparişleri ve toplam ödenecek tutarı görüntüleme, ardından hesabı kapatarak masayı tekrar "Boş" statüsüne getirme.
- **Gün Sonu Raporu:** Gün boyunca ağırlanan toplam müşteri sayısını, elde edilen toplam ciroyu ve en çok sipariş edilen ürünü istatistiksel olarak sunma.
- **Güvenli Kullanıcı Girişi:** Hatalı veri girişlerini (örneğin sayı yerine harf girilmesi) yakalayan ve uygulamanın çökmesini engelleyen kontrol mekanizmaları.

## 🛠️ Kullanılan Teknolojiler

- **Programlama Dili:** Java (JDK 8+)
- **Paradigma:** Nesne Yönelimli Programlama (Object-Oriented Programming - OOP)
- **Veri Yapıları:** Java Collections Framework (`ArrayList`, `HashMap`)
- **Geliştirme Ortamı:** Herhangi bir Java destekli IDE (IntelliJ IDEA, Eclipse, VS Code vb.) veya terminal.

## 🏗️ Proje Mimarisi (Sınıf Yapısı)

Proje, birbirleriyle uyumlu çalışan modüler sınıflardan oluşmaktadır:

* `Main.java`: Kullanıcı etkileşimini sağlayan, konsol menüsünü barındıran ve güvenli veri girişini kontrol eden ana sınıftır.
* `KafeYonetimi.java` (Service Katmanı): İş mantığının (business logic) yürütüldüğü, masaların, siparişlerin ve gün sonu istatistiklerinin (`List` ve `Map` yapılarıyla) tutulduğu yönetici sınıftır.
* `Masa.java` (Entity Katmanı): Masanın numarasını, kapasitesini, güncel durumunu (`Enum`) ve o masaya ait siparişler listesini sarmalayan (encapsulation) sınıftır.
* `Siparis.java` (Entity Katmanı): Tekil bir siparişin ürün adı, adet ve birim fiyat bilgilerini tutan ve ara toplamları hesaplayan model sınıfıdır.
* `MasaDurumu.java` (Enum): Masaların statülerini (`BOS`, `DOLU`, `REZERVE`) güvenli bir şekilde yönetmek için kullanılan yapıdır.

## 💻 Kurulum ve Çalıştırma

1.  Bu projeyi yerel bilgisayarınıza indirin (ZIP olarak indirin veya klonlayın).
2.  Bilgisayarınızda Java Development Kit (JDK) kurulu olduğundan emin olun.
3.  Terminal veya komut satırını açarak proje dosyalarının bulunduğu dizine gidin.
4.  Kodları derlemek için aşağıdaki komutu çalıştırın:
    ```bash
    javac Main.java
    ```
5.  Uygulamayı başlatmak için şu komutu girin:
    ```bash
    java Main
    ```

## 📋 Örnek Kullanım Akışı

Sistem çalıştırıldığında kullanıcıyı interaktif bir menü karşılar:

1. Önce **1**'e basarak birkaç masa (örn: Masa 1, Kapasite 4) oluşturun.
2. **2**'ye basarak gelen müşterileri (örn: 3 kişi) uygun masaya oturtun.
3. **3**'e basarak dolu olan masaya sipariş girin (örn: Çay, 2 adet, 15 TL).
4. **4**'e basarak masanın anlık hesabını kontrol edin.
5. **5**'e basarak ödemeyi alın ve masayı boşaltın.
6. İşletme kapandığında **6**'ya basarak o günün özet raporunu (ciro, popüler ürün vb.) alın.

---
*Bu proje, Java ile backend geliştirme temelleri ve algoritmik düşünce yapısını güçlendirmek amacıyla geliştirilmiştir.*