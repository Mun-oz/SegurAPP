package com.duoc.segurapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.duoc.segurapp.model.Usuario

@Database(
    entities = [Usuario::class],
    version = 1,
    exportSchema = false
)
abstract class SegurAppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object {

        @Volatile
        private var instancia: SegurAppDatabase? = null

        fun obtener(context: Context): SegurAppDatabase {
            return instancia ?: synchronized(this) {

                instancia ?: Room.databaseBuilder(
                    context.applicationContext,
                    SegurAppDatabase::class.java,
                    "segurapp.db"
                ).build().also {
                    instancia = it
                }
            }
        }
    }
}
