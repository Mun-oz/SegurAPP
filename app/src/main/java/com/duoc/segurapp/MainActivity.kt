package com.duoc.segurapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.duoc.segurapp.model.Alarma
import com.duoc.segurapp.ui.alarma.registrarAlarma
import com.duoc.segurapp.ui.home.HomeScreen
import com.duoc.segurapp.ui.login.LoginScreen
import com.duoc.segurapp.ui.theme.SegurAPPTheme
import com.duoc.segurapp.viewmodel.ViewAlarma
import com.duoc.segurapp.ui.googlemap.GoogleMapsScreen
import com.duoc.segurapp.ui.ciudadano.CiudadanoScreen
import com.duoc.segurapp.ui.registro.RegistroScreen
import com.duoc.segurapp.ui.seguimiento.SeguimientoScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SegurAPPTheme {

                var pantalla by remember {
                    mutableStateOf("Inicio")
                }

                val viewAlarma = remember {
                    ViewAlarma()
                }

                var cuentaPrueba by remember {
                    mutableStateOf<Pair<String, String>?>(null)
                }

                when (pantalla) {

                    "Inicio" -> {
                        HomeScreen(
                            onIniciarSesionClick = {
                                pantalla = "Login"
                            },
                            onEmergenciaClick = {
                                pantalla = "Registrar"
                            }
                        )
                    }

                    "Login" -> {
                        LoginScreen(
                            onLoginSuccess = {
                                pantalla = "Ciudadano"
                            },
                            onVolverClick = {
                                pantalla = "Inicio"
                            },
                            onRegistrarseClick = {
                                pantalla = "Registro"
                            }
                        )

                    }

                    "Registro" -> {
                        RegistroScreen(
                            onRegistrarClick = { usuario, contrasena ->
                                cuentaPrueba = usuario to contrasena
                                pantalla = "Login"
                            },
                            onVolverClick = {
                                pantalla = "Login"
                            }
                        )
                    }

                    "Registrar" -> {
                        registrarAlarma(
                                onRegistrarAlarmaClick = { descripcion, zona ->
                                val alarma = Alarma(
                                    descripcion = descripcion,
                                    zona = zona
                                )

                                viewAlarma.registrarAlarma(alarma)
                                pantalla = "Inicio"
                            },
                            onVolverClick = {
                                pantalla = "Inicio"
                            }
                        )
                    }

                    "Mapa" -> {
                        GoogleMapsScreen()
                    }

                    "Ciudadano" -> {
                        CiudadanoScreen(
                            onCerrarSesionClick = {
                                pantalla = "Inicio"
                            },
                            onSeguimientoClick = {
                                pantalla = "Seguimiento"
                            }
                        )
                    }

                    "Seguimiento" -> {
                        SeguimientoScreen(
                            onVolverClick = {
                                pantalla = "Ciudadano"
                            }
                        )
                    }

                }
            }
        }
    }
}
