# Sistema de Control de Horas Extra y Gestión de Tareas

Sistema desarrollado para administrar el registro de **horas extra**, la **asignación y seguimiento de tareas de servicio**, el **control de entrada y salida de empleados** y la generación de información para el área administrativa.

El proyecto está compuesto por **tres aplicaciones principales**, una API desarrollada en PHP y una base de datos MySQL/MariaDB compartida.

---

## 📌 Descripción general

El sistema permite integrar diferentes procesos relacionados con la operación de personal y trabajos técnicos:

- Registro de horas extra trabajadas.
- Asignación y seguimiento de tareas.
- Control de entrada y salida de empleados.
- Gestión de empleados, agencias y puestos.
- Elaboración de hojas de servicio.
- Registro de responsables, conductores y personal de apoyo.
- Autorización de trabajos mediante firma.
- Autorización alternativa mediante credenciales.
- Consulta de ubicaciones mediante mapas.
- Gestión administrativa de solicitudes.
- Consulta y generación de reportes.
- Visualización de tareas finalizadas.
- Consulta de horas extra aprobadas.

---

# 🏗️ Arquitectura del sistema

El proyecto está dividido en los siguientes componentes:

```text
                        ┌──────────────────────────┐
                        │     MySQL / MariaDB      │
                        │ bd_control_horas_extra   │
                        └────────────┬─────────────┘
                                     │
                    ┌────────────────┼─────────────────┐
                    │                │                 │
                    │                │                 │
           ┌────────▼────────┐ ┌─────▼──────────┐ ┌────▼───────────┐
           │ Java Admin      │ │ Java Empleado │ │    PHP API     │
           │ Desktop         │ │ Desktop       │ └──────┬─────────┘
           └─────────────────┘ └───────────────┘        │
                                                        │
                                                 ┌──────▼───────┐
                                                 │ Android App  │
                                                 │ Java         │
                                                 └──────────────┘
```

---

# 📂 Estructura del repositorio

```text
Horas-extras-APP-Java-Android/
│
├── android-app-control-hoja-de-trabajo/
│   ├── app/
│   ├── openCVLibrary3411/
│   ├── gradle/
│   ├── build.gradle
│   ├── settings.gradle
│   ├── gradlew
│   └── gradlew.bat
│
├── java-admin-control-horas-extras-desktop/
│   ├── src/
│   ├── lib/
│   ├── nbproject/
│   ├── build.xml
│   └── manifest.mf
│
├── java-empleado-control-horas-extras-desktop/
│   ├── src/
│   ├── lib/
│   ├── nbproject/
│   └── build.xml
│
├── api-php/
│   ├── finalizar_tarea.php
│   ├── validar_gerente.php
│   └── ...
│
├── database/
│   └── schema.sql
│
├── docs/
│   └── screenshots/
│
├── Librerias/
│
├── .gitignore
└── README.md
```

---

# 📱 1. Aplicación Android

La aplicación móvil está orientada al personal encargado de recibir y ejecutar tareas de servicio.

Está desarrollada principalmente en **Java para Android**.

## Funciones principales

### 🔐 Inicio de sesión

Permite el acceso de empleados registrados en el sistema.

El sistema utiliza la información almacenada en la base de datos para validar el usuario.

---

### 📋 Gestión de tareas

Los empleados pueden visualizar las tareas disponibles y asignadas.

Cada tarea puede contener información como:

- Agencia.
- Problema reportado.
- Descripción.
- Fecha.
- Hora.
- Dirección.
- Estado de la tarea.
- Ubicación.

El empleado puede aceptar una tarea para comenzar su ejecución.

---

### 📍 Geolocalización

La aplicación utiliza mapas para mostrar:

- Agencias.
- Ubicaciones relacionadas con las tareas.
- Ubicación de empleados.
- Referencias geográficas necesarias para realizar el trabajo.

Para la visualización de mapas se utiliza:

- OpenStreetMap.
- osmdroid.

---

### 🕒 Control de entrada y salida

El sistema permite registrar eventos de entrada y salida del empleado.

Los registros pueden almacenar información asociada con:

- Fecha.
- Hora.
- Empleado.
- Ubicación geográfica.

---

### 📝 Hoja de servicio

Después de realizar una tarea, el empleado puede completar una hoja de servicio.

Entre los datos registrados se encuentran:

- Agencia.
- Número de petición.
- Fecha.
- Hora de inicio.
- Hora de finalización.
- Título del problema.
- Categoría.
- Comentarios.
- Trabajo realizado.
- Materiales utilizados.
- Responsable.
- Conductor.
- Personal de apoyo.
- Hora de salida.
- Hora de retorno.
- Sector.
- Transporte.
- Alimentación.
- Hospedaje.
- Total de gastos.
- Resultado o solución del trabajo.
- Gerente o supervisor que autoriza.

Los empleados de apoyo son opcionales.

---

### ✍️ Autorización mediante firma

La aplicación permite que un gerente o supervisor autorice la finalización de un trabajo mediante firma.

Para el procesamiento y comparación de firmas se utiliza:

```text
OpenCV 3.4.11
```

Como mecanismo alternativo, también existe autorización mediante:

```text
Usuario + Contraseña
```

---

### 👤 Perfil del empleado

Desde el perfil se puede consultar información relacionada con el empleado, incluyendo:

- Nombre.
- Hora de entrada.
- Tareas finalizadas.
- Horas extra aprobadas.
- Registro de salida.

---

# 💻 2. Aplicación administrativa Java

La aplicación administrativa está orientada a la gestión y supervisión del sistema.

Está desarrollada como una aplicación de escritorio en **Java**.

El proyecto mantiene estructura compatible con proyectos tradicionales de **NetBeans**.

## Funciones principales

La aplicación administrativa permite gestionar diferentes elementos del sistema.

### 👥 Empleados

Permite administrar información relacionada con los empleados.

Entre las operaciones disponibles se encuentran:

- Registro de empleados.
- Consulta de empleados.
- Información de puestos.
- Información departamental.
- Gestión de usuarios.

---

### 🏢 Agencias

Permite registrar y consultar agencias utilizadas dentro del sistema.

La información de las agencias puede ser utilizada posteriormente por la aplicación móvil para localizar los lugares donde deben realizarse los trabajos.

---

### 📋 Peticiones

Permite crear y consultar peticiones o tareas de trabajo.

Estas peticiones posteriormente pueden ser visualizadas desde la aplicación Android.

---

### 🛠️ Categorías de problemas

Permite administrar las categorías utilizadas para clasificar problemas o solicitudes de servicio.

---

### 🧑‍💼 Puestos de trabajo

Permite administrar información relacionada con puestos y departamentos.

---

### ⏱️ Horas extra

La aplicación permite consultar y administrar registros relacionados con las horas extra ingresadas por los empleados.

---

### 📊 Reportes

El sistema administrativo incluye generación y visualización de reportes.

Se utilizan tecnologías como:

```text
JasperReports
iText
```

Entre los reportes disponibles se encuentran información relacionada con:

- Horas extra.
- Entrada y salida.
- Peticiones.
- Hojas de servicio.
- Información de empleados.

---

# 🕒 3. Aplicación Java para empleados

Esta aplicación de escritorio está destinada al registro de las horas extra trabajadas por los empleados.

Está desarrollada en Java y utiliza la misma base de datos del sistema.

## Funciones principales

### 🔐 Inicio de sesión

El empleado debe identificarse antes de acceder al sistema.

---

### ➕ Registro de horas extra

Permite registrar información relacionada con el trabajo realizado fuera de la jornada ordinaria.

Entre los datos manejados se encuentran:

- Empleado.
- Petición o trabajo relacionado.
- Comentario.
- Fecha de inicio.
- Fecha de finalización.
- Hora de inicio.
- Hora de finalización.
- Total de horas extra.

---

### 📋 Consulta de registros

Los empleados pueden consultar registros previamente ingresados.

---

### ✏️ Modificación

El sistema permite modificar registros de horas extra cuando corresponde.

---

# 🌐 4. API PHP

La aplicación Android se comunica con la base de datos mediante una API desarrollada en PHP.

La API recibe solicitudes desde Android y realiza las operaciones correspondientes en MySQL/MariaDB.

```text
Android
   │
   │ HTTP
   ▼
PHP API
   │
   │ SQL
   ▼
MySQL / MariaDB
```

Entre las operaciones realizadas por la API se encuentran:

- Consulta de tareas.
- Consulta de empleados.
- Consulta de agencias.
- Registro de entrada y salida.
- Actualización del estado de tareas.
- Validación de gerentes.
- Registro de hojas de servicio.
- Finalización de tareas.
- Consulta de tareas finalizadas.
- Consulta de horas extra.

---

# 🗄️ 5. Base de datos

El sistema utiliza una base de datos compartida denominada:

```sql
bd_control_horas_extra
```

La base de datos puede ejecutarse utilizando:

```text
MySQL
MariaDB
```

Entre las tablas principales se encuentran:

```text
agencias
categorias_problema
departamento_trabajo
empleado
hoja_de_servicio
ingreso_hora_extra
marcar_de_entrada_salida
peticiones
peticiones_finalizadas
problema
puesto_departamento
temporal_horas_extra
temporal_marca
temporal_peti
temporal_sysaid
```

La carpeta:

```text
database/
```

contiene la estructura necesaria para crear la base de datos.

---

# 🛠️ Tecnologías utilizadas

## Aplicación Android

```text
Java
Android SDK
AndroidX
Volley
OpenCV 3.4.11
osmdroid
OpenStreetMap
Biometric Authentication
RecyclerView
Glide
Gradle
```

---

## Aplicaciones de escritorio

```text
Java
Java Swing
JDBC
NetBeans
JasperReports
iText
MySQL Connector/J
JCalendar
```

---

## Backend

```text
PHP
Apache
XAMPP
JSON
HTTP
```

---

## Base de datos

```text
MySQL
MariaDB
phpMyAdmin
```

---

# ⚙️ Requisitos

## Android

Para abrir el proyecto Android se recomienda:

```text
Android Studio
Java 8
Gradle 6.5
Android Gradle Plugin 4.1.1
Android SDK 30
```

El proyecto Android se encuentra en:

```text
android-app-control-hoja-de-trabajo/
```

---

## Aplicaciones Java de escritorio

Para los proyectos Java se puede utilizar un IDE compatible con proyectos Java tradicionales, por ejemplo:

```text
NetBeans
IntelliJ IDEA
```

Los proyectos contienen sus fuentes dentro de:

```text
src/
```

y las librerías utilizadas dentro de:

```text
lib/
```

---

## Backend PHP

Se requiere un servidor con:

```text
Apache
PHP
MySQL / MariaDB
```

Para desarrollo local puede utilizarse:

```text
XAMPP
```

---

# 🔧 Configuración de la base de datos en Java

Por seguridad, las credenciales reales de la base de datos no están almacenadas en el repositorio.

Cada aplicación Java contiene un archivo:

```text
db.properties.example
```

Debe copiarse como:

```text
db.properties
```

y configurarse localmente.

Ejemplo:

```properties
db.url=jdbc:mysql://localhost:3306/bd_control_horas_extra
db.user=TU_USUARIO
db.password=TU_PASSWORD
```

El archivo:

```text
db.properties
```

está excluido mediante `.gitignore`.

---

# 🔧 Configuración de la API Android

La aplicación Android utiliza una clase de configuración para definir la dirección del servidor.

Archivo:

```text
config/ApiConfig.java
```

Ejemplo:

```java
public class ApiConfig {

    public static final String BASE_URL =
            "http://TU_SERVIDOR/conexion_hora_extra/";

    public static String endpoint(String archivoPhp) {
        return BASE_URL + archivoPhp;
    }
}
```

Durante desarrollo local, `TU_SERVIDOR` debe sustituirse por la dirección correspondiente al servidor donde se está ejecutando Apache/PHP.

---

# 🔐 Seguridad

El repositorio excluye archivos locales y credenciales sensibles mediante `.gitignore`.

Entre los archivos que no deben almacenarse públicamente se encuentran:

```text
db.properties
local.properties
google-services.json
archivos .jks
archivos .keystore
configuraciones privadas del IDE
firmas reales
contraseñas reales
```

Los archivos `.example` contienen únicamente estructuras de configuración y deben completarse localmente.

---


# 🔄 Flujo general del sistema

```text
1. El administrador registra empleados, agencias y datos operativos.

                         ↓

2. Se crean peticiones o tareas de servicio.

                         ↓

3. El empleado visualiza las tareas desde Android.

                         ↓

4. El empleado acepta una tarea.

                         ↓

5. Se realiza el trabajo asignado.

                         ↓

6. Se completa la hoja de servicio.

                         ↓

7. El gerente o supervisor autoriza el trabajo.

                         ↓

8. La tarea se registra como finalizada.

                         ↓

9. El empleado puede registrar las horas extra trabajadas.

                         ↓

10. El área administrativa consulta y procesa la información.
```

---

# 📊 Flujo de una tarea Android

```text
Disponible
    │
    ▼
Aceptar tarea
    │
    ▼
En proceso
    │
    ▼
Realizar trabajo
    │
    ▼
Hoja de servicio
    │
    ▼
Autorización
   ┌┴──────────────┐
   │               │
 Firma        Credenciales
   │               │
   └───────┬───────┘
           ▼
       Finalizada
```

---

# 🧩 Integración entre componentes

Las tres aplicaciones trabajan sobre la misma información.

```text
┌─────────────────────────────────────────────┐
│          SISTEMA DE HORAS EXTRA             │
├─────────────────────────────────────────────┤
│                                             │
│  📱 Android                                 │
│     Gestión de tareas y hojas de servicio   │
│                                             │
│  💻 Java Admin                              │
│     Administración y supervisión            │
│                                             │
│  🕒 Java Empleado                           │
│     Registro de horas extra                 │
│                                             │
│  🌐 PHP API                                 │
│     Comunicación Android ↔ Base de datos    │
│                                             │
│  🗄️ MySQL / MariaDB                         │
│     Persistencia de información             │
│                                             │
└─────────────────────────────────────────────┘
```

---

# 🎓 Origen del proyecto

Este sistema fue desarrollado originalmente como proyecto académico de graduación.

Su objetivo es integrar diferentes procesos relacionados con:

- Gestión de tareas.
- Control de personal.
- Registro de horas extra.
- Control de entrada y salida.
- Hojas de servicio.
- Autorización de trabajos.
- Administración y generación de reportes.

Posteriormente el proyecto fue reorganizado y actualizado para conservar su estructura, mejorar su configuración y documentar sus diferentes componentes dentro de un único repositorio.

---

# 📚 Componentes principales

| Componente | Tecnología | Función |
|---|---|---|
| Android App | Java / Android | Gestión móvil de tareas y hojas de servicio |
| Admin Desktop | Java Swing | Administración y supervisión |
| Empleado Desktop | Java Swing | Registro de horas extra |
| API | PHP | Comunicación entre Android y base de datos |
| Base de datos | MySQL / MariaDB | Persistencia de información |
| Mapas | OpenStreetMap / osmdroid | Geolocalización |
| Firmas | OpenCV | Procesamiento de firma |
| Reportes | JasperReports | Generación de reportes |

---

# 👩‍💻 Autora

**Indira Zaldivar**

Proyecto académico y de portafolio orientado al desarrollo de aplicaciones Java, Android, PHP y bases de datos relacionales.

---

# 📄 Notas

Las configuraciones de conexión, credenciales y datos sensibles deben definirse localmente y no forman parte del repositorio público.

Para ejecutar el sistema completo es necesario configurar correctamente:

```text
1. MySQL / MariaDB
2. API PHP
3. Aplicación Android
4. Aplicación Java administrativa
5. Aplicación Java de empleados
```

Cada componente utiliza la misma estructura general de datos para mantener integrada la información del sistema.
