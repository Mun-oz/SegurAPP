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
import com.duoc.segurapp.ui.funcionario.FuncionarioScreen
import com.duoc.segurapp.ui.alarma.MisAlarmasScreen
import com.duoc.segurapp.ui.confirmarenvio.ConfirmarEnvioScreen
import com.duoc.segurapp.ui.confirmarenvio.EnvioExitosoScreen
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.duoc.segurapp.data.SegurAppDatabase
import com.duoc.segurapp.repository.UsuarioRepository
import com.duoc.segurapp.viewmodel.UsuarioViewModel
import com.duoc.segurapp.viewmodel.UsuarioViewModelFactory

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
                var alarmaPendiente by remember {
                    mutableStateOf<Alarma?>(null)
                }

                var usuarioSesion by remember {
                    mutableStateOf("")
                }

                val context = LocalContext.current

                val baseDatos = remember {
                    SegurAppDatabase.obtener(context)
                }

                val repositorioUsuarios = remember {
                    UsuarioRepository(baseDatos.usuarioDao())
                }

                val usuarioViewModel: UsuarioViewModel = viewModel(
                    factory = UsuarioViewModelFactory(repositorioUsuarios)
                )



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
                            usuarioViewModel = usuarioViewModel,
                            onLoginSuccess = { usuario ->
                                usuarioSesion = usuario
                                pantalla = "Ciudadano"
                            },
                            onFuncionarioLogin = { usuario ->
                                usuarioSesion = usuario
                                pantalla = "Funcionario"
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
                            usuarioViewModel = usuarioViewModel,
                            onRegistrarClick = {
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
                                alarmaPendiente = Alarma(
                                    descripcion = descripcion,
                                    zona = zona
                                )
                                pantalla = "ConfirmarEnvio"
                            },
                            onVolverClick = {
                                pantalla = "Inicio"
                            }
                        )
                    }

                    "ConfirmarEnvio" -> {
                        val alarma = alarmaPendiente

                        if (alarma == null) {
                            pantalla = "Registrar"
                        } else {
                            ConfirmarEnvioScreen(
                                alarma = alarma,
                                onConfirmarClick = {
                                    viewAlarma.registrarAlarma(alarma)
                                    alarmaPendiente = null
                                    pantalla = "EnvioExitoso"
                                },
                                onCancelarClick = {
                                    pantalla = "Registrar"
                                }
                            )
                        }
                    }

                    "EnvioExitoso" -> {
                        EnvioExitosoScreen(
                            onVolverInicioClick = {
                                pantalla = "Inicio"
                            }
                        )
                    }


                    "Mapa" -> {
                        GoogleMapsScreen()
                    }

                    "Ciudadano" -> {
                        CiudadanoScreen(
                            usuario = usuarioSesion,
                            onCerrarSesionClick = {
                                usuarioSesion = ""
                                pantalla = "Inicio"
                            },
                            onSeguimientoClick = {
                                pantalla = "Seguimiento"
                            },
                            onMisAlarmasClick = {
                                pantalla = "MisAlarmas"
                            }
                        )
                    }

                    "Funcionario" -> {
                        FuncionarioScreen(
                            usuario = usuarioSesion,
                            alarmas = viewAlarma.alarmas,
                            onConfirmarAlarmaClick = { id ->
                                viewAlarma.confirmarAlarma(id)
                            },
                            onCerrarAlarmaClick = { id ->
                                viewAlarma.cerrarAlarma(id)
                            },
                            onSalirClick = {
                                usuarioSesion = ""
                                pantalla = "Inicio"
                            }
                        )
                    }

                    "Seguimiento" -> {
                        SeguimientoScreen(
                            alarmas = viewAlarma.alarmas,
                            onVolverClick = {
                                pantalla = "Ciudadano"
                            }
                        )
                    }

                    "MisAlarmas" -> {
                        MisAlarmasScreen(
                            alarmas = viewAlarma.alarmas,
                            onVolverClick = {
                                pantalla = "Inicio"
                            },
                            onVolverCuidadanoClick = {
                                pantalla = "Ciudadano"
                            }
                        )
                    }



                }
            }
        }
    }
}
