package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */


/**
 * Menginisialisasi kelas abstrak yang mendefinisikan database Room.
 * dengan versi database 1 dan exportschema difalse kan supaya tidak mengekspor skema database.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    /**
     * Fungsi abstrak untuk mengakses itemDao yang sudah dibuat supaya dapat mengakses fitur CRUD Item
     */
    abstract fun itemDao(): ItemDao
    /**
     * Menyimpan instansi dari InventoryDatabase dan perubahan yang terjadi akan langsung terlihat pada
     * semua thread
     */
    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        /**
         * Mengembalikan instansi InventoryDatabase, namun apabila instansi belum ada akan dibuatkan database baru
         * menggunakan dataBaseBuilder dengan nama item_database
         */
        fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}