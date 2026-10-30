# SegurApp

## Descripción del proyecto

SegurApp es una aplicación móvil Android enfocada en la seguridad ciudadana, desarrollada para facilitar la comunicación y el seguimiento de situaciones de emergencia dentro de una comunidad.

La aplicación permite que los ciudadanos registren situaciones que consideren sospechosas o que requieran atención, indicando una descripción de lo ocurrido y el sector correspondiente. Una vez enviada la emergencia, esta queda registrada como una alarma que puede ser consultada posteriormente.

Por otra parte, SegurApp cuenta con una interfaz destinada a funcionarios, quienes pueden revisar las alarmas recibidas, iniciar su atención y cerrar los casos una vez finalizada su revisión.

También incorpora Google Maps, que permite visualizar el sector de San Joaquín, desplazarse por el mapa y buscar direcciones.

El objetivo principal de SegurApp es mantener organizada la información de las emergencias y permitir que los ciudadanos conozcan el estado de sus reportes, desde el envío inicial hasta su cierre.

## Funcionalidades

### Ciudadano

- Registro de cuentas de usuario.
- Inicio de sesión con las credenciales registradas.
- Pantalla de bienvenida con el nombre del usuario.
- Visualización de Google Maps y búsqueda de direcciones.
- Registro de emergencias mediante un formulario.
- Confirmación antes de enviar una emergencia.
- Consulta de alarmas registradas.
- Seguimiento de los estados: Pendiente, En revisión y Cerrada.
- Cierre de sesión.

### Funcionario

- Inicio de sesión mediante el perfil de funcionario.
- Pantalla principal con resumen de casos.
- Visualización de alarmas pendientes, en revisión y cerradas.
- Consulta de la descripción, zona y estado de cada alarma.
- Confirmación e inicio de revisión de una alarma.
- Cierre de casos mediante una ventana de confirmación.
- Actualización del estado de las alarmas.
- Cierre de sesión.

## Tecnologías utilizadas

- **Kotlin:** lenguaje de programación utilizado para desarrollar la aplicación.
- **Jetpack Compose:** herramienta utilizada para construir las interfaces de usuario.
- **Android Studio:** entorno de desarrollo de la aplicación.
- **Room:** biblioteca utilizada para almacenar información en una base de datos local.
- **Google Maps SDK:** integración del mapa interactivo.
- **Git y GitHub:** control de versiones y trabajo colaborativo.
- **Trello:** organización y seguimiento de las tareas del proyecto.


## Estructura del proyecto

```text
SegurAPP/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/duoc/segurapp/
│   │       │   ├── data/
│   │       │   │   ├── SegurAppDatabase.kt
│   │       │   │   └── UsuarioDao.kt
│   │       │   ├── model/
│   │       │   │   ├── Alarma.kt
│   │       │   │   └── Usuario.kt
│   │       │   ├── repository/
│   │       │   │   ├── AlarmaRepository.kt
│   │       │   │   └── UsuarioRepository.kt
│   │       │   ├── viewmodel/
│   │       │   │   ├── ViewAlarma.kt
│   │       │   │   └── UsuarioViewModel.kt
│   │       │   ├── ui/
│   │       │   │   ├── alarma/
│   │       │   │   ├── ciudadano/
│   │       │   │   ├── confirmarenvio/
│   │       │   │   ├── funcionario/
│   │       │   │   ├── googlemap/
│   │       │   │   ├── home/
│   │       │   │   ├── login/
│   │       │   │   ├── registro/
│   │       │   │   ├── seguimiento/
│   │       │   │   └── theme/
│   │       │   └── MainActivity.kt
│   │       ├── res/
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── .gitignore
└── README.md
```


## Arquitectura MVVM

SegurApp utiliza una estructura basada en el patrón **Model-View-ViewModel (MVVM)**.

Cada capa cumple una responsabilidad específica:

- **Model:** define la estructura de los datos utilizados por la aplicación.
- **View:** corresponde a las pantallas desarrolladas con Jetpack Compose.
- **ViewModel:** administra los estados y coordina las operaciones solicitadas desde las interfaces.
- **Repository:** gestiona el acceso a los datos y comunica el ViewModel con sus fuentes de información.

Esta separación permite mantener el código más organizado y facilita su mantenimiento.

## Persistencia de datos

SegurApp utiliza **Room** para almacenar las cuentas de ciudadanos en una base de datos SQLite local.

### Registro de usuarios

Cuando un ciudadano completa el formulario de registro, la aplicación valida los datos ingresados y envía la solicitud a `UsuarioViewModel`.

El ViewModel se comunica con `UsuarioRepository`, que utiliza `UsuarioDao` para guardar la información en la base de datos.

Cada cuenta contiene un identificador único, un nombre de usuario y una contraseña.

Room también permite verificar si un nombre de usuario ya existe, evitando registrar cuentas duplicadas.

### Inicio de sesión

Al iniciar sesión como ciudadano, se consulta la base de datos para comprobar que el usuario esté registrado y que la contraseña ingresada coincida con la almacenada.

Si los datos son correctos, la aplicación permite acceder a la pantalla del ciudadano y muestra su nombre en el mensaje de bienvenida.

Si las credenciales no coinciden, se informa al usuario que los datos son incorrectos.

### Conservación de los datos

Una de las principales ventajas de Room es que los usuarios registrados permanecen almacenados después de cerrar la aplicación.

De esta manera, no es necesario registrarse nuevamente cada vez que se utiliza SegurApp.

La información se mantiene en el almacenamiento local del dispositivo mientras no se eliminen los datos de la aplicación.

### Almacenamiento de alarmas

Actualmente, las alarmas se administran en memoria mediante `ViewAlarma` y `AlarmaRepository`.

Esto permite registrar emergencias, consultar sus datos y actualizar sus estados durante la ejecución de la aplicación.

A diferencia de las cuentas de ciudadanos, las alarmas todavía no utilizan persistencia con Room, por lo que pueden perderse cuando finaliza el proceso de la aplicación.

## Estados de las alarmas

Las alarmas pueden encontrarse en tres estados:

- **Pendiente:** la emergencia fue registrada y está esperando atención.
- **En revisión:** un funcionario confirmó el caso e inició su revisión.
- **Cerrada:** el funcionario finalizó la atención del caso.

Estos estados permiten organizar los reportes y mantener informado al ciudadano sobre el avance de su emergencia.

## Ejecución del proyecto

Para ejecutar SegurApp se necesita Android Studio y un emulador o dispositivo Android compatible.

1. Clonar o descargar el repositorio.
2. Abrir el proyecto en Android Studio.
3. Sincronizar las dependencias de Gradle.
4. Configurar localmente una clave válida para Google Maps.
5. Seleccionar un emulador o dispositivo Android.
6. Compilar y ejecutar la aplicación.

## Integrantes

- **Marcelo Muñoz:** [Mun-oz](https://github.com/Mun-oz)
- **Rayen Yanjari:** [tigremago](https://github.com/tigremago)