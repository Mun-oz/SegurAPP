package com.duoc.segurapp.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "usuarios",
    indices = [Index(value = ["nombreUsuario"], unique = true)]
)
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombreUsuario: String,
    val contrasena: String
)
