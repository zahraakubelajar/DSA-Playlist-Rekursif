/*
 * ================================
 * DSA LN08 - Rekursif Playlist
 * ================================
 * Maxwell Lin           - 2902689521
 * Zahra Nazifa Wibowo   - 2902700820
 * ================================
 */




// Class Lagu (reuse dari tugas lama)
class Lagu {
    private String judul;
    private String artis;
    private double durasi;

    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    public String getJudul() {
        return judul;
    }

    public String getArtis() {
        return artis;
    }

    public double getDurasi() {
        return durasi;
    }

    // Menampilkan info lagu dengan format rapi (2 angka di belakang koma)
    public void tampilkanInfo() {
        System.out.printf("%s - %s (%.2f menit)\n", judul, artis, durasi);
    }
}

// Class utama
public class PlaylistRekursif {

    /*
     * Menghitung total durasi lagu secara rekursif
     * Base case: n = 0 → return 0
     * Recursive case: durasi[n-1] + totalDurasi(n-1)
     */
    static double totalDurasi(Lagu[] list, int n) {
        if (n == 0)
            return 0;
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    /*
     * Menampilkan lagu dari belakang (reverse)
     * Base case: index < 0 → stop
     * Recursive case: tampilkan lalu mundur
     */
    static void tampilkanMundur(Lagu[] list, int index) {
        if (index < 0)
            return;

        list[index].tampilkanInfo();
        tampilkanMundur(list, index - 1);
    }

    /*
     * Mencari lagu dengan durasi terpanjang
     * Base case: index = 0 → return lagu pertama
     * Recursive case: bandingkan dengan sebelumnya
     */
    static Lagu cariLaguTerpanjang(Lagu[] list, int index) {
        if (index == 0)
            return list[0];

        Lagu max = cariLaguTerpanjang(list, index - 1);

        if (list[index].getDurasi() > max.getDurasi())
            return list[index];
        else
            return max;
    }

    public static void main(String[] args) {

        // Data playlist
        Lagu[] playlist = {
            new Lagu("Perfect", "Ed Sheeran", 4.23),
            new Lagu("Shivers", "Ed Sheeran", 3.50),
            new Lagu("Fix You", "Coldplay", 4.52)
        };

        int n = playlist.length;

        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        System.out.println("Jumlah lagu : " + n);

        // Total durasi
        long start = System.nanoTime();
        double total = totalDurasi(playlist, n);
        long end = System.nanoTime();
        System.out.printf("Total durasi : %.2f menit\n", total);
        System.out.println("Execution Time (totalDurasi): " + (end - start)/1_000_000 + " ms");

        // Tampilkan terbalik
        start = System.nanoTime();
        System.out.println("\nDaftar lagu (terbalik):");
        tampilkanMundur(playlist, n - 1);
        end = System.nanoTime();
        System.out.println("Execution Time (tampilkanMundur): " + (end - start)/1_000_000 + " ms");

        // Lagu terpanjang
        start = System.nanoTime();
        Lagu max = cariLaguTerpanjang(playlist, n - 1);
        end = System.nanoTime();

        System.out.printf("\nLagu terpanjang: %s (%.2f menit)\n",
                max.getJudul(), max.getDurasi());
        System.out.println("Execution Time (cariDurasiTerpanjang): " + (end - start)/1_000_000 + " ms");
    }
}