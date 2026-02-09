import java.util.Scanner;

// Class untuk merepresentasikan data Mahasiswa
class Mahasiswa {
    String nama;
    String nim;
    String kelas;

    public Mahasiswa(String nama, String nim, String kelas) {
        this.nama = nama;
        this.nim = nim;
        this.kelas = kelas;
    }
}

public class ArrayMahasiswa {
    // Variabel Global
    static int capacity = 10; // Kapasitas awal
    static Mahasiswa[] mhsArray = new Mahasiswa[capacity];
    static int count = 0; // Menghitung jumlah data yang terisi
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n=== MENU DATA MAHASISWA ===");
            System.out.println("Capacity: " + capacity + " | Data Count: " + count);
            System.out.println("1. Insert at Beginning");
            System.out.println("2. Insert at Given Position");
            System.out.println("3. Insert at End");
            System.out.println("4. Delete from Beginning");
            System.out.println("5. Delete Given Position");
            System.out.println("6. Delete from End");
            System.out.println("7. Delete First Occurrence (by NIM)");
            System.out.println("8. Show Data");
            System.out.println("9. Exit");
            System.out.print("Pilih menu: ");
            
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer newline

            switch (pilihan) {
                case 1: insertAtBeginning(); break;
                case 2: insertAtPosition(); break;
                case 3: insertAtEnd(); break;
                case 4: deleteFromBeginning(); break;
                case 5: deleteGivenPosition(); break;
                case 6: deleteFromEnd(); break;
                case 7: deleteFirstOccurrence(); break;
                case 8: showData(); break;
                case 9: System.out.println("Keluar dari program..."); break;
                default: System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 9);
    }

    // --- FUNGSI BANTUAN ---

    // Fungsi untuk menambah kapasitas array secara manual jika penuh
    static void checkAndResize() {
        if (count >= capacity) {
            System.out.println(">> Array penuh! Menambah kapasitas array...");
            int newCapacity = capacity + 10; // Tambah 10 slot baru
            Mahasiswa[] newArray = new Mahasiswa[newCapacity];

            // Copy data manual dari array lama ke array baru
            for (int i = 0; i < count; i++) {
                newArray[i] = mhsArray[i];
            }

            mhsArray = newArray; // Arahkan pointer ke array baru
            capacity = newCapacity;
        }
    }

    // Fungsi untuk input data user
    static Mahasiswa inputData() {
        System.out.print("Masukkan Nama  : ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan NIM   : ");
        String nim = scanner.next();
        System.out.print("Masukkan Kelas : ");
        String kelas = scanner.next();
        return new Mahasiswa(nama, nim, kelas);
    }

    // --- OPERASI INSERT ---

    // 1. Insert at Beginning
    static void insertAtBeginning() {
        checkAndResize(); // Cek kapasitas

        // Geser semua elemen ke kanan (dari belakang ke depan)
        for (int i = count; i > 0; i--) {
            mhsArray[i] = mhsArray[i - 1];
        }

        mhsArray[0] = inputData();
        count++; // Tambah count
        System.out.println("Data berhasil ditambahkan di awal.");
    }

    // 2. Insert at Given Position
    static void insertAtPosition() {
        checkAndResize();

        System.out.print("Masukkan posisi (1 - " + (count + 1) + "): ");
        int pos = scanner.nextInt();
        scanner.nextLine(); // Bersihkan buffer

        if (pos < 1 || pos > count + 1) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        int index = pos - 1; // Konversi ke index array (0-based)

        // Geser elemen dari posisi target ke kanan
        for (int i = count; i > index; i--) {
            mhsArray[i] = mhsArray[i - 1];
        }

        mhsArray[index] = inputData();
        count++; // Tambah count
        System.out.println("Data berhasil ditambahkan di posisi " + pos + ".");
    }

    // 3. Insert at End
    static void insertAtEnd() {
        checkAndResize();
        
        // Langsung masukkan di index 'count' (posisi kosong terakhir)
        mhsArray[count] = inputData();
        count++; // Tambah count
        System.out.println("Data berhasil ditambahkan di akhir.");
    }

    // --- OPERASI DELETE ---

    // 4. Delete from Beginning
    static void deleteFromBeginning() {
        if (count == 0) {
            System.out.println("Data kosong, tidak bisa menghapus.");
            return;
        }

        // Geser semua elemen ke kiri (menimpa index 0)
        for (int i = 0; i < count - 1; i++) {
            mhsArray[i] = mhsArray[i + 1];
        }

        mhsArray[count - 1] = null; // Hapus referensi data terakhir
        count--; // Kurangi count
        System.out.println("Data pertama berhasil dihapus.");
    }

    // 5. Delete Given Position
    static void deleteGivenPosition() {
        if (count == 0) {
            System.out.println("Data kosong.");
            return;
        }

        System.out.print("Masukkan posisi hapus (1 - " + count + "): ");
        int pos = scanner.nextInt();
        
        if (pos < 1 || pos > count) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        int index = pos - 1;

        // Geser elemen dari kanan ke kiri untuk menutupi lubang
        for (int i = index; i < count - 1; i++) {
            mhsArray[i] = mhsArray[i + 1];
        }

        mhsArray[count - 1] = null;
        count--; // Kurangi count
        System.out.println("Data di posisi " + pos + " berhasil dihapus.");
    }

    // 6. Delete from End
    static void deleteFromEnd() {
        if (count == 0) {
            System.out.println("Data kosong.");
            return;
        }

        mhsArray[count - 1] = null; // Cukup hapus data terakhir
        count--; // Kurangi count
        System.out.println("Data terakhir berhasil dihapus.");
    }

    // 7. Delete First Occurrence (Cari berdasarkan NIM)
    static void deleteFirstOccurrence() {
        if (count == 0) {
            System.out.println("Data kosong.");
            return;
        }

        System.out.print("Masukkan NIM yang akan dihapus: ");
        String targetNim = scanner.next();
        int foundIndex = -1;

        // Cari index data
        for (int i = 0; i < count; i++) {
            if (mhsArray[i].nim.equals(targetNim)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex == -1) {
            System.out.println("Data dengan NIM " + targetNim + " tidak ditemukan.");
        } else {
            // Lakukan shifting ke kiri
            for (int i = foundIndex; i < count - 1; i++) {
                mhsArray[i] = mhsArray[i + 1];
            }
            mhsArray[count - 1] = null;
            count--; // Kurangi count
            System.out.println("Data dengan NIM " + targetNim + " berhasil dihapus.");
        }
    }

    // 8. Show Data
    static void showData() {
        if (count == 0) {
            System.out.println("\n[ Data Kosong ]");
            return;
        }

        System.out.println("\n--- DAFTAR MAHASISWA ---");
        System.out.printf("%-4s %-15s %-20s %-10s\n", "No", "NIM", "Nama", "Kelas");
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.printf("%-4d %-15s %-20s %-10s\n", 
                (i + 1), mhsArray[i].nim, mhsArray[i].nama, mhsArray[i].kelas);
        }
        System.out.println("-----------------------------------------------------");
    }
}