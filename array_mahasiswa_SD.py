class Mahasiswa:
    def __init__(self, nim, nama, kelas):
        self.nim = nim
        self.nama = nama
        self.kelas = kelas

# Konfigurasi Awal
KAPASITAS = 10
data_mhs = [None] * KAPASITAS  # Simulasi Fixed Size Array
jumlah_data = 0

def input_mahasiswa():
    nim = input("Masukkan NIM  : ")
    nama = input("Masukkan Nama : ")
    kelas = input("Masukkan Kelas: ")
    return Mahasiswa(nim, nama, kelas )

# 1. Insert at Beginning
def insert_at_beginning():
    global jumlah_data
    if jumlah_data >= KAPASITAS:
        print("Error: Array Penuh!")
        return

    # Geser elemen ke kanan (dari belakang ke depan)
    for i in range(jumlah_data, 0, -1):
        data_mhs[i] = data_mhs[i-1]
    
    data_mhs[0] = input_mahasiswa()
    jumlah_data += 1
    print("Data disimpan di awal.")

# 2. Insert at Position
def insert_at_position():
    global jumlah_data
    if jumlah_data >= KAPASITAS:
        print("Error: Array Penuh!")
        return

    try:
        pos = int(input(f"Masukkan posisi (1 - {jumlah_data + 1}): "))
    except ValueError:
        print("Input bukan angka.")
        return

    if pos < 1 or pos > jumlah_data + 1:
        print("Error: Posisi tidak valid.")
        return
    
    index = pos - 1
    # Geser elemen dari posisi target ke kanan
    for i in range(jumlah_data, index, -1):
        data_mhs[i] = data_mhs[i-1]
        
    data_mhs[index] = input_mahasiswa()
    jumlah_data += 1
    print(f"Data disimpan di posisi {pos}.")

# 3. Insert at End
def insert_at_end():
    global jumlah_data
    if jumlah_data >= KAPASITAS:
        print("Error: Array Penuh!")
        return
    
    data_mhs[jumlah_data] = input_mahasiswa()
    jumlah_data += 1
    print("Data disimpan di akhir.")

# 4. Delete from Beginning
def delete_from_beginning():
    global jumlah_data
    if jumlah_data == 0:
        print("Error: Data Kosong!")
        return
    
    # Geser ke kiri
    for i in range(0, jumlah_data - 1):
        data_mhs[i] = data_mhs[i+1]
        
    data_mhs[jumlah_data - 1] = None
    jumlah_data -= 1
    print("Data awal dihapus.")

# 5. Delete Given Position
def delete_given_position():
    global jumlah_data
    if jumlah_data == 0:
        print("Error: Data Kosong!")
        return

    try:
        pos = int(input(f"Masukkan posisi hapus (1 - {jumlah_data}): "))
    except ValueError:
        print("Input harus angka.")
        return

    if pos < 1 or pos > jumlah_data:
        print("Error: Posisi tidak valid.")
        return

    index = pos - 1
    # Geser ke kiri menutup lubang
    for i in range(index, jumlah_data - 1):
        data_mhs[i] = data_mhs[i+1]
        
    data_mhs[jumlah_data - 1] = None
    jumlah_data -= 1
    print(f"Data posisi {pos} dihapus.")

# 6. Delete from End
def delete_from_end():
    global jumlah_data
    if jumlah_data == 0:
        print("Error: Data Kosong!")
        return
    
    data_mhs[jumlah_data - 1] = None
    jumlah_data -= 1
    print("Data akhir dihapus.")

# 7. Delete First Occurrence
def delete_first_occurrence():
    global jumlah_data
    if jumlah_data == 0:
        print("Error: Data Kosong!")
        return
    
    target_nim = input("Masukkan NIM yang akan dihapus: ")
    found_index = -1
    
    for i in range(jumlah_data):
        if data_mhs[i].nim == target_nim:
            found_index = i
            break
            
    if found_index == -1:
        print("Data tidak ditemukan.")
    else:
        for i in range(found_index, jumlah_data - 1):
            data_mhs[i] = data_mhs[i+1]
            
        data_mhs[jumlah_data - 1] = None
        jumlah_data -= 1
        print(f"Data NIM {target_nim} berhasil dihapus.")

# 8. Show Data
def show_data():
    if jumlah_data == 0:
        print("\n[ Data Kosong ]")
    else:
        print("\n--- DAFTAR MAHASISWA ---")
        # Header Tabel
        # :<4 artinya rata kiri (left-align) dengan lebar kolom 4 karakter
        print(f"{'No':<4} {'NIM':<15} {'Nama':<20} {'Kelas':<10}") 
        print("-" * 53) # Garis pembatas
        
        # Isi Tabel
        for i in range(jumlah_data):
            # Mengakses data dari object Mahasiswa di dalam list
            print(f"{i + 1:<4} {data_mhs[i].nim:<15} {data_mhs[i].nama:<20} {data_mhs[i].kelas:<10}")
            
        print("-" * 53)

# Main Program Loop
def main():
    while True:
        print("\n=== MENU ARRAY MAHASISWA (PYTHON) ===")
        print("\n1. Insert at Beginning\n2. Insert at Given Position\n3. Insert at End")
        print("4. Delete at Beginning\n5. Delete at Given Position\n6. Delete from End")
        print("7. Delete First Ocurrence (by NIM)\n8. Show Data\n9. Exit")
        
        try:
            pilihan = int(input("Pilih: "))
        except ValueError:
            print("Input harus angka!")
            continue

        if pilihan == 1: insert_at_beginning()
        elif pilihan == 2: insert_at_position()
        elif pilihan == 3: insert_at_end()
        elif pilihan == 4: delete_from_beginning()
        elif pilihan == 5: delete_given_position()
        elif pilihan == 6: delete_from_end()
        elif pilihan == 7: delete_first_occurrence()
        elif pilihan == 8: show_data()
        elif pilihan == 9: 
            print("Keluar...")
            break
        else:
            print("Pilihan tidak valid.")

if __name__ == "__main__":
    main()