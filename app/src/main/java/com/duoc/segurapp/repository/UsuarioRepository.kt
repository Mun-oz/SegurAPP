package com.duoc.segurapp.repository

import com.duoc.segurapp.data.UsuarioDao
import com.duoc.segurapp.model.Usuario

class UsuarioRepository(
    private val dao: UsuarioDao
) {

    suspend fun registrarUsuario(
        nombreUsuario: String,
        contrasena: String
    ): Boolean {

        val nombre = nombreUsuario.trim().lowercase()

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            return false
        }

        val usuario = Usuario(
            nombreUsuario = nombre,
            contrasena = contrasena
        )

        val resultado = dao.insertarUsuario(usuario)

        return resultado != -1L
    }

    suspend fun iniciarSesion(
        nombreUsuario: String,
        contrasena: String
    ): String? {

        val nombre = nombreUsuario.trim().lowercase()

        val usuario = dao.buscarUsuario(nombre)
            ?: return null

        if (usuario.contrasena != contrasena) {
            return null
        }

        return usuario.nombreUsuario
    }
}
