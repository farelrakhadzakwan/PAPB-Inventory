package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) berfungsi untuk mengelola data Item di database,
 * semua fungsi yang memiliki keyword suspend dapat berjalan secara asinkron dengan room.
 */

@Dao
interface ItemDao {
    /**
     * Menambahkan item baru ke database dengan syarat akan mengabaikan data apabila terjadi konflik
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)
    /**
     * Memperbarui data item ke dalam database Item
     */
    @Update
    suspend fun update(item: Item)
    /**
     * Menghapus data item dari database Item
     */
    @Delete
    suspend fun delete(item: Item)
    /**
     * Mengakses item berdasarkan id dalam bentuk flow
     */
    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>
    /**
     * Mengambil semua item berdasarkan name dengan format ascending dalam bentuk flow
     */
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

}