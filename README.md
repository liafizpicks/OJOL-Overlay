# Floater InDrive

APK launcher untuk mencoba membuka InDrive dalam mode freeform/floating pada Android yang mendukungnya.

## Target
- Package InDrive: `sinet.startup.inDriver`
- Tombol buka InDrive
- Dukungan `resizeableActivity`
- Percobaan resize lewat `su`/root

## Build
Buka folder ini di Android Studio lalu Build > Build APK(s).

Catatan: Android/XOS tidak selalu mengizinkan aplikasi biasa memaksa aplikasi lain menjadi freeform. Implementasi root di project ini adalah percobaan dan perlu disesuaikan dengan ROM perangkat.
