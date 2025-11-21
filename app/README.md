🎮 GameHub es una enciclopedia de juegos 
GameHub es una aplicación desarrollada en Kotlin usando Jetpack Compose.
Permite registrar juegos localmente y ver juegos desde una API externa, utilizando arquitectura MVVM, Room y Retrofit.

✅ Funcionalidades principales
1. Login
   Validación de email y contraseña

Manejo de estado con StateFlow

2. Base de datos local (Room)
   Guardar juegos con:

Nombre

Descripción

Imagen desde la galería

Los datos se mantienen aunque se cierre la app

3. API externa (Retrofit)
   Se consumen juegos desde la API:
   https://www.freetogame.com/api/games

Se muestran en la pantalla Online Games

4. Navegación
   Login

Home

Agregar Juego

Detalles

About

Juegos Online

5. Arquitectura MVVM
   ViewModel maneja el estado

Repositories manejan datos locales y remotos

La UI solo muestra información

🧪 Prueba unitaria
Incluye un test para validar el comportamiento del login.

📱 Cómo ejecutar
Abrir el proyecto en Android Studio

Ejecutar en un emulador o dispositivo real

Requiere permiso de Internet en AndroidManifest.xml

👤 Autor
Anthony Esquerre - Michael Giraldo.