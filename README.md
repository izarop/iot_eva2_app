AppIot Eva 2

Nombre: Elisa Oayzrun
Carrera: Analista Programador
Asignatura: Aplicaciones Móviles para IoT
Docente: Felipe Oyarzún  
Fecha: 21 de Octubre 2025  

Objetivo: Desarrollar una aplicación móvil Android en Kotlin que simule la interconexión entre actividades dentro de un entorno IoT, utilizando elementos de diseño estándar, navegación entre pantallas y AlertDialogs para representar las interacciones del usuario.

La app está compuesta por 4 Activities principales:

SplashActivity: Muestra el logo de la aplicación y simula la lectura del estado Bluetooth mediante un AlertDialog.
LoginActivity: Permite ingresar email y contraseña. Incluye botones para Registrar y Recuperar Clave.
RegisterActivity: Simula el registro de un nuevo usuario con un AlertDialog de confirmación. 
RecoverActivity: Simula el envío de correo de recuperación con un AlertDialog. 

Flujo de navegación

1. SplashActivity ->  AlertDialog Bluetooth -> LoginActivity
2. LoginActivity:
   - Si se selecciona "Registrar", pasa a RegisterActivity  
   - Si se selecciona "Recuperar clave", pasa a RecoverActivity

Tecnologías utilizadas

- Android Studio
- Lenguaje: Kotlin  
- SDK mínimo: 26 (Android 8.0 Oreo)  
- Diseño: XML con `ConstraintLayout`  
- Componentes: AlertDialog, Intents, Handler

Seguridad aplicada (ISO 27400)

- No se utilizan datos sensibles reales.  
- No se requieren permisos externos.  
- Cumple el principio de mínima exposición.  
- Se aplican buenas prácticas básicas de desarrollo seguro.
