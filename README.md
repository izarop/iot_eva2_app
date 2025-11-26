AppIot Eva 2

Nombre: Elisa Oyarzun
Carrera: Analista Programador
Asignatura: Aplicaciones Móviles para IoT
Docente: Felipe Oyarzún  
Fecha: 21 de Octubre 2025  

Objetivo: Desarrollar una aplicación móvil Android en Kotlin que simule la interconexión entre actividades dentro de un entorno IoT, utilizando elementos de diseño estándar, navegación entre pantallas y AlertDialogs para representar las interacciones del usuario.

La app está compuesta por 4 Activities principales:

SplashActivity: Pantalla inicial que muestra un logo o animación durante 2.5 segundos y luego abre automáticamente LoginActivity. Termina para que no se regrese al presionar atrás.
LoginActivity: Pantalla donde el usuario ingresa su usuario y contraseña para iniciar sesión. Tiene botones para iniciar sesión (con validación simulada), registrar un nuevo usuario (RegisterActivity) y recuperar contraseña (RecoverActivity).
RegisterActivity: Pantalla para registrar un nuevo usuario. Valida que los campos no estén vacíos, el correo sea válido, la contraseña tenga mínimo 6 caracteres y que la contraseña y su confirmación coincidan. Si todo está bien, muestra un mensaje de registro exitoso y vuelve a la pantalla de login. 
RecoverActivity: Pantalla para recuperar contraseña. El usuario ingresa su correo y, si el campo no está vacío, simula el envío de un enlace de recuperación. Tiene un enlace o botón para volver a la pantalla de login.

Flujo de navegación

1. LoginActivity:
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
