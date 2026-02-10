package com.example.kierowca2

const val GTFS_API_URL = "https://opendata.cui.wroclaw.pl/api/3/action/package_show?id=rozkladjazdytransportupublicznegoplik_data"
// Старая статическая ссылка больше не является основной, но сохранена для совместимости
const val GTFS_URL = "https://www.wroclaw.pl/open-data/87b09b32-f076-4475-8ec9-6020ed1f9ac0/OtwartyWroclaw_rozklad_jazdy_GTFS.zip"

object GtfsConstants {
    const val LOCAL_GTFS_PATH = "/mnt/data/OtwartyWroclaw_rozklad_jazdy_GTFS.zip"
}
