# Website Automation OpenMRS

Repository ini merupakan website automation dari URL [OpenMRS](https://o2.openmrs.org/openmrs/login.htm) yang dibuat oleh **Andhiny Fatikha Rizki** (saat ini QA Automation di Bank BRI). Project ini dibuat untuk memenuhi Online Assesment posisi Quality Assurance Engineer di PT Infokes Indonesia.

## Test Cases
Pada automation website kali ini, beberapa test case yang dibuat adalah sebagai berikut:

### ✅ Positive Test Cases ✅
1. **Login with valid Username and Password Only**
(Reusable Login since Data Binding at free Katalon Studio only at maximum of 1 excel at Test Suite)
2. **Login with valid Username and Password**
3. **Register for a new Patient**
4. **Make Appointment for the first time**

### ❌ Negative Test Cases ❌
5. **Login with invalid Username and valid Password**
6. **Login with valid Username and invalid Password**

## Data Binding
Dalam pengambilan data, saya menggunakan metode **Data Binding** dengan memasukkan file Excel pada test suite yang telah dibuat di **Data Files**. Jika ingin melakukan perubahan data, dapat dilakukan pada file berikut:

📂 **Data Files/02_Register New Patient.xlsx**

Pada file Excel ini, data dapat diubah pada seluruh kolom sesuai kebutuhan.

## Login dan Data Iteration
Untuk login, **Location** dapat dipilih berdasarkan **Data Iteration**. Data ini bisa dipilih dengan langkah berikut:

1. Masuk ke **Test Suites**
2. Pilih **Folder Test Suite**
3. Pilih **Test Suite** yang ingin dijalankan
4. Pilih **Test Case Login**
5. Klik **Show Data Binding**
6. Pilih **Data Iteration** sesuai kebutuhan, contoh:
   - **Data Iteration 1** untuk **Location: Inpatient Ward**

## Video Hasil Running
Untuk melihat video hasil running, silakan akses tautan berikut:

[Report PDF + Video](https://drive.google.com/drive/folders/1LhpJNlToOHZHUzgc_Dv4H8jBpXzoYgnE?usp=drive_link)

---
