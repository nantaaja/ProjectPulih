package com.example.projectpulih

object JournalDataRepository {
    val allJournals = listOf(
        JournalModel(
            id = 1,
            moodName = "Tenang",
            moodEmoji = "😊",
            moodType = "tenang",
            date = "Hari ini, 08:30",
            content = "Pagi yang sejuk. Berhasil menyelesaikan review materi tanpa panik, dan menyempatkan jalan pagi keliling kompleks...",
            tags = listOf("Kuliah", "Istirahat"),
            period = "weekly"
        ),
        JournalModel(
            id = 2,
            moodName = "Sangat Baik",
            moodEmoji = "🤩",
            moodType = "sangat_baik",
            date = "23 Okt, 21:15",
            content = "Ngopi santai bareng teman lama. Rasanya beban pikiran langsung terangkat setelah bertukar cerita santai...",
            tags = listOf("Teman", "Hobi"),
            period = "weekly"
        ),
        JournalModel(
            id = 3,
            moodName = "Biasa Saja",
            moodEmoji = "😐",
            moodType = "biasa",
            date = "22 Okt, 20:40",
            content = "Hari yang agak monoton tapi cukup tenang. Tidur siang 20 menit sangat membantu memulihkan fokus...",
            tags = listOf("Istirahat"),
            period = "weekly"
        ),
        JournalModel(
            id = 4,
            moodName = "Lelah",
            moodEmoji = "😔",
            moodType = "lelah",
            date = "21 Okt, 22:00",
            content = "Deadline tugas menumpuk, tapi bersyukur bisa mengatur napas dan tidak terbawa panik berlebihan...",
            tags = listOf("Kuliah"),
            period = "weekly"
        ),
        JournalModel(
            id = 5,
            moodName = "Tenang",
            moodEmoji = "😊",
            moodType = "tenang",
            date = "19 Okt, 07:15",
            content = "Meditasi 10 menit sebelum kelas pagi. Pikiran terasa jauh lebih jernih dan lapang untuk memulai hari.",
            tags = listOf("Istirahat", "Hobi"),
            period = "monthly"
        ),
        JournalModel(
            id = 6,
            moodName = "Sangat Baik",
            moodEmoji = "🤩",
            moodType = "sangat_baik",
            date = "17 Okt, 19:30",
            content = "Mendapat feedback positif untuk tugas akhir kelompok. Usaha begadang minggu lalu terbayar lunas.",
            tags = listOf("Kuliah"),
            period = "monthly"
        ),
        JournalModel(
            id = 7,
            moodName = "Biasa Saja",
            moodEmoji = "😐",
            moodType = "biasa",
            date = "15 Okt, 14:00",
            content = "Hujan rintik di luar. Menikmati secangkir teh hangat sambil membaca novel favorit di sudut kamar.",
            tags = listOf("Hobi", "Istirahat"),
            period = "monthly"
        ),
        JournalModel(
            id = 8,
            moodName = "Lelah",
            moodEmoji = "😔",
            moodType = "lelah",
            date = "12 Okt, 23:10",
            content = "Badan terasa pegal setelah praktikum seharian. Saatnya istirahat penuh dan lepas dari layar HP sejenak.",
            tags = listOf("Kesehatan", "Istirahat"),
            period = "monthly"
        ),
        JournalModel(
            id = 9,
            moodName = "Tenang",
            moodEmoji = "😊",
            moodType = "tenang",
            date = "10 Okt, 08:45",
            content = "Berjalan tanpa terburu-buru. Merasakan hangatnya sinar matahari pagi yang lembut dan menenangkan.",
            tags = listOf("Kesehatan"),
            period = "monthly"
        ),
        JournalModel(
            id = 10,
            moodName = "Sangat Baik",
            moodEmoji = "🤩",
            moodType = "sangat_baik",
            date = "08 Okt, 16:20",
            content = "Berhasil menyelesaikan target self-care 5 hari berturut-turut. Bangga pada kemajuan diri sendiri!",
            tags = listOf("Teman", "Hobi"),
            period = "monthly"
        ),
        JournalModel(
            id = 11,
            moodName = "Tenang",
            moodEmoji = "😊",
            moodType = "tenang",
            date = "05 Okt, 09:00",
            content = "Memulai hari dengan napas perlahan dan menuliskan 3 hal yang patut disyukuri di pagi hari.",
            tags = listOf("Istirahat"),
            period = "monthly"
        ),
        JournalModel(
            id = 12,
            moodName = "Sangat Baik",
            moodEmoji = "🤩",
            moodType = "sangat_baik",
            date = "01 Okt, 20:00",
            content = "Awal bulan dengan niat dan komitmen baru untuk merawat kesehatan mental lebih teratur.",
            tags = listOf("Keluarga", "Kesehatan"),
            period = "monthly"
        )
    )

    val weeklyJournals = allJournals.filter { it.period == "weekly" }
    val monthlyJournals = allJournals
}
