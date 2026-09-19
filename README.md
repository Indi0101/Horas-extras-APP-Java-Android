# Sistema de Control de Horas Extra y Gestión de Tareas

Aplicación Android desarrollada como parte de un sistema para el **control de horas extra, asignación de tareas, seguimiento de trabajos y finalización de servicios**.

La aplicación permite que los empleados consulten tareas, registren su entrada y salida, visualicen ubicaciones mediante mapas, completen hojas de servicio y autoricen la finalización de trabajos mediante firma o credenciales de un responsable.

---

## 📋 Descripción

La aplicación móvil forma parte de una solución diseñada para centralizar la gestión de tareas, trabajos de campo y horas extraordinarias del personal.

El sistema utiliza una arquitectura cliente-servidor:

```text
┌───────────────────────────┐
│      Aplicación Android   │
│                           │
│  • Tareas                 │
│  • Perfil                 │
│  • Ubicación              │
│  • Hoja de servicio       │
│  • Firma / autenticación  │
└─────────────┬─────────────┘
              │ HTTP
              ▼
┌───────────────────────────┐
│          API PHP          │
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│          MySQL            │
│ bd_control_horas_extra    │
└───────────────────────────┘
```

La aplicación Android se comunica con una API desarrollada en PHP, encargada de consultar y actualizar la información almacenada en una base de datos MySQL/MariaDB.

---

# ✨ Funcionalidades principales

## 🔐 Autenticación

La aplicación permite identificar al empleado mediante sus credenciales.

También utiliza autenticación biométrica en operaciones relacionadas con el control de asistencia.

---

## 🕐 Registro de entrada y salida

La aplicación permite registrar información relacionada con la jornada del empleado:

- Fecha de entrada.
- Hora de entrada.
- Fecha de salida.
- Hora de salida.
- Identificador del empleado.
- Coordenadas de ubicación.

El registro de salida puede ser validado mediante autenticación biométrica.

---

## 📋 Gestión de tareas

Los empleados pueden consultar las peticiones disponibles y visualizar información relacionada con cada trabajo.

Entre los datos disponibles se encuentran:

- Agencia.
- Dirección.
- Tipo de problema.
- Descripción.
- Fecha.
- Hora.
- Estado de la tarea.

Las tareas pueden pasar por diferentes estados durante su ciclo de atención.

```text
Disponible
    │
    ▼
En proceso
    │
    ▼
Finalizada
```

---

## 👨‍🔧 Asignación de tareas

Cuando un empleado acepta una tarea, el sistema registra al empleado encargado de atenderla.

Esto permite mantener la trazabilidad del trabajo desde su asignación hasta su finalización.

---

# 📝 Hoja de servicio

Al finalizar un trabajo, el empleado puede completar una **Hoja de Servicio** con la información correspondiente a la actividad realizada.

La Hoja de Servicio puede incluir:

- Agencia.
- Número de petición.
- Fecha.
- Hora de inicio.
- Hora de finalización.
- Título del trabajo.
- Categoría.
- Descripción.
- Trabajo realizado.
- Materiales utilizados.
- Empleado responsable.
- Conductor.
- Personal de apoyo.
- Hora de salida.
- Hora de retorno.
- Sector.
- Gastos de transporte.
- Alimentación.
- Hospedaje.
- Total de gastos.
- Resultado del trabajo.
- Responsable que autoriza el servicio.

Cada Hoja de Servicio queda relacionada con la petición correspondiente.

---

## ✅ Resultado del trabajo

El sistema permite registrar diferentes resultados para una tarea.

Entre ellos se encuentra la finalización completa del trabajo y otros estados que permiten continuar posteriormente con la atención de la petición.

Cuando el trabajo se resuelve completamente, la petición puede pasar al estado:

```text
Finalizada
```

---

# ✍️ Autorización mediante firma

La finalización de una Hoja de Servicio puede ser autorizada mediante la firma de un responsable.

La aplicación utiliza **OpenCV** para realizar procesamiento de imágenes y comparar la firma realizada en el dispositivo con una firma previamente registrada.

El flujo de autorización contempla varios intentos de reconocimiento.

```text
Firma del responsable
        │
        ▼
Comparación con firma registrada
        │
        ├── Firma reconocida
        │        │
        │        ▼
        │     Autorizar
        │
        └── Intentos agotados
                 │
                 ▼
        Usuario + contraseña
                 │
                 ▼
              Autorizar
```

Si la firma no puede ser reconocida después de los intentos permitidos, el responsable puede autorizar la operación mediante sus credenciales.

---

# 🗺️ Mapa

La aplicación integra mapas utilizando:

- OpenStreetMap
- osmdroid

El mapa permite visualizar diferentes ubicaciones relacionadas con la operación.

Entre ellas:

- Ubicación del empleado.
- Ubicación de personal registrado.
- Agencias registradas.
- Ubicación específica de la agencia relacionada con una tarea.

El detalle de una tarea también puede mostrar directamente en el mapa la ubicación de la agencia donde debe realizarse el trabajo.

---

# 👤 Perfil del empleado

La sección de perfil permite consultar información relacionada con el usuario.

Entre la información disponible se encuentra:

- Nombre del empleado.
- Hora de entrada.
- Tareas finalizadas.
- Horas extra aprobadas.
- Registro de salida.

Desde esta sección también puede realizarse el registro de salida mediante autenticación biométrica.

---

# 🛠️ Tecnologías utilizadas

## Android

- Java
- Android SDK
- AndroidX
- XML
- Gradle
- RecyclerView
- Volley
- SharedPreferences
- BiometricPrompt

---

## 🗺️ Mapas

- OpenStreetMap
- osmdroid

---

## 👁️ Procesamiento de imágenes

- OpenCV 3.4.11

OpenCV es utilizado en el proceso de análisis y comparación de firmas.

---

## 🌐 Backend

El backend utilizado por la aplicación está desarrollado con:

- PHP
- Apache
- XAMPP

La API PHP funciona como intermediario entre la aplicación Android y la base de datos.

---

## 🗄️ Base de datos

El sistema utiliza:

- MySQL
- MariaDB

La base de datos principal utilizada por el proyecto es:

```text
bd_control_horas_extra
```

---

# 🔥 Firebase

El proyecto dispone de configuración para servicios de Google/Firebase mediante:

```text
google-services.json
```

Por razones de configuración y seguridad, este archivo no se incluye en el repositorio.

Cada entorno debe utilizar su propia configuración de Firebase.

---

# 📁 Estructura del proyecto

```text
Proyecto_hora_extra/
│
├── app/
│   │
│   ├── src/
│   │   ├── main/
│   │   │
│   │   ├── java/
│   │   │   └── com/example/proyecto_hora_extra/
│   │   │
│   │   │       ├── MainActivity.java
│   │   │       ├── Marcar_hora_entrada.java
│   │   │       ├── Mostrar_info.java
│   │   │       │
│   │   │       ├── config/
│   │   │       │   └── ApiConfig.java
│   │   │       │
│   │   │       ├── fragmentos/
│   │   │       │   ├── Frag_lista_tareas.java
│   │   │       │   ├── Frag_mapa.java
│   │   │       │   └── Frag_perfil.java
│   │   │       │
│   │   │       ├── clases/
│   │   │       │
│   │   │       └── firma_clase/
│   │   │           └── CaptureBitmapView.java
│   │   │
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   ├── layout/
│   │   │   ├── values/
│   │   │   └── xml/
│   │   │
│   │   └── AndroidManifest.xml
│   │
│   ├── build.gradle
│   └── proguard-rules.pro
│
├── openCVLibrary3411/
│
├── gradle/
│   └── wrapper/
│
├── build.gradle
├── gradle.properties
├── settings.gradle
├── gradlew
├── gradlew.bat
├── .gitignore
└── README.md
```

---

# ⚙️ Requisitos de desarrollo

El proyecto utiliza el siguiente entorno base:

| Tecnología | Versión |
|---|---|
| Java | 8 |
| Gradle | 6.5 |
| Android Gradle Plugin | 4.1.1 |
| compileSdk | 30 |
| targetSdk | 30 |
| OpenCV | 3.4.11 |

Para trabajar con el proyecto se recomienda utilizar **Android Studio** con un JDK 8 compatible.

---

# 🚀 Instalación y configuración

## 1. Clonar el repositorio

```bash
git clone <URL-DEL-REPOSITORIO>
```

Después de clonar el repositorio, abrir la carpeta del proyecto desde Android Studio.

---

## 2. Configurar Java

El proyecto utiliza Java 8.

En Android Studio configurar el JDK desde:

```text
Settings
→ Build, Execution, Deployment
→ Build Tools
→ Gradle
→ Gradle JDK
```

Seleccionar una instalación compatible con Java 8.

---

# 🌐 Configuración de la API

La dirección principal del servidor se administra desde:

```text
app/src/main/java/com/example/proyecto_hora_extra/config/ApiConfig.java
```

La clase centraliza las rutas utilizadas para comunicarse con los servicios PHP.

Ejemplo:

```java
public class ApiConfig {

    public static final String BASE_URL =
            "http://SERVIDOR/conexion_hora_extra/";

    public static String endpoint(String archivoPhp) {
        return BASE_URL + archivoPhp;
    }
}
```

La dirección debe modificarse según el servidor donde se encuentre instalada la API.

---

# 🖥️ Configuración del backend

El backend requiere un servidor con:

- Apache
- PHP
- MySQL o MariaDB

Durante el desarrollo puede utilizarse **XAMPP**.

La estructura puede instalarse dentro del directorio:

```text
xampp/htdocs/conexion_hora_extra/
```

La aplicación Android y el servidor deben encontrarse en una red que permita comunicación entre ambos dispositivos.

---

# 🔥 Configuración de Firebase

Si se utilizan los servicios de Firebase, debe agregarse el archivo:

```text
google-services.json
```

dentro de:

```text
app/google-services.json
```

Este archivo se encuentra excluido mediante `.gitignore`.

---

# 📱 Ejecutar la aplicación

Conectar un dispositivo Android mediante USB o utilizar un emulador.

Desde Android Studio ejecutar:

```text
Run 'app'
```

La aplicación será compilada e instalada en el dispositivo seleccionado.

---

# 🗄️ Estructura de datos

La aplicación trabaja principalmente con información almacenada en tablas relacionadas con:

- Empleados.
- Agencias.
- Peticiones.
- Hojas de servicio.
- Registros de entrada y salida.
- Horas extra.
- Categorías de problemas.
- Problemas.
- Puestos.
- Departamentos de trabajo.

Entre las principales tablas se encuentran:

```text
empleado
agencias
peticiones
hoja_de_servicio
marcar_de_entrada_salida
ingreso_hora_extra
categorias_problema
problema
puesto_departamento
departamento_trabajo
```

---

## Peticiones

La tabla de peticiones permite mantener información relacionada con:

- Creación de tareas.
- Empleado asignado.
- Agencia.
- Problema.
- Estado.
- Fecha.
- Hora.
- Fecha de finalización.
- Hora de finalización.

---

## Hoja de servicio

La tabla `hoja_de_servicio` almacena la información generada durante la atención de una tarea.

Permite mantener el registro del trabajo realizado y relacionarlo con la petición correspondiente.

---

# 🔄 Flujo principal de la aplicación

```text
Inicio de sesión
      │
      ▼
Registro de entrada
      │
      ▼
Tareas pendientes
      │
      ▼
Seleccionar tarea
      │
      ▼
Detalle de tarea
      │
      ▼
Aceptar tarea
      │
      ▼
Trabajo en proceso
      │
      ▼
Hoja de servicio
      │
      ▼
Registrar trabajo realizado
      │
      ▼
Resultado del trabajo
      │
      ▼
Autorización del responsable
      │
      ├── Firma
      │
      └── Usuario y contraseña
      │
      ▼
Tarea finalizada
      │
      ▼
Historial en perfil
      │
      ▼
Registro de salida
```

---

# 🔐 Seguridad del repositorio

Los archivos correspondientes al entorno local, compilaciones y configuraciones privadas están excluidos del repositorio mediante `.gitignore`.

Entre ellos:

```text
.idea/
.gradle/
build/
local.properties
google-services.json
*.apk
*.aab
*.jks
*.keystore
```

Esto permite mantener separado el código fuente de las configuraciones propias de cada computadora o entorno de ejecución.

---

# 🎓 Origen del proyecto

Este proyecto fue desarrollado originalmente como trabajo académico de graduación orientado a la digitalización del:

**Control e ingreso de horas extras y finalización de tareas.**

La solución completa integra diferentes componentes:

```text
Sistema de Control de Horas Extra
│
├── Aplicación de escritorio para empleados
│
├── Aplicación administrativa de escritorio
│
├── Aplicación móvil Android
│
├── API PHP
│
└── Base de datos MySQL
```

Todos los componentes trabajan sobre una arquitectura compartida para centralizar la información relacionada con empleados, tareas y horas extraordinarias.

Este repositorio corresponde específicamente al **componente Android del sistema**.

---

# 📸 Capturas de pantalla

Las capturas de la aplicación pueden almacenarse dentro de una carpeta:

```text
docs/
```

Ejemplo de estructura:

```text
docs/
├── login.png
├── tareas.png
├── detalle-tarea.png
├── mapa.png
├── hoja-servicio.png
├── firma.png
└── perfil.png
```

Posteriormente pueden mostrarse en este README utilizando:

```markdown
![Inicio de sesión](docs/login.png)

![Tareas pendientes](docs/tareas.png)

![Mapa](docs/mapa.png)

![Hoja de servicio](docs/hoja-servicio.png)

![Perfil](docs/perfil.png)
```

---

# 👩‍💻 Autora

**Indira Zaldivar**

Desarrollo de software y aplicación móvil Android.

---

## 📌 Proyecto académico

Sistema orientado a la gestión de tareas, control de horas extraordinarias, registro de servicios y seguimiento del personal mediante aplicaciones de escritorio y dispositivos móviles.
