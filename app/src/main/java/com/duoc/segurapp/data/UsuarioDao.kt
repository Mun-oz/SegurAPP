package com.duoc.segurapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duoc.segurapp.model.Usuario

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuario(usuario: Usuario): Long

    @Query(
        "SELECT * FROM usuarios " +
                "WHERE nombreUsuario = :nombreUsuario LIMIT 1"
    )
    suspend fun buscarUsuario(
        nombreUsuario: String
    ): Usuario?
}
