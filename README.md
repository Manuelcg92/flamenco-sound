# Flamenco Sound 🎶

Portal web de eventos musicales centrado en el flamenco, donde los usuarios pueden descubrir grupos, ver eventos, dejar comentarios, valorar y comprar entradas.

## 🧩 Tecnologías utilizadas

- Java EE / JSP / Servlets
- Spring Framework (IoC, MVC, Inyección de Dependencias)
- JDBC y DAOs
- Apache Tomcat
- HTML5, CSS3, Bootstrap
- JavaScript (básico para validaciones o interactividad)
- MySQL

## ⚙️ Funcionalidades principales

### 👥 Gestión de grupos musicales
- Alta, modificación y baja de grupos.
- Visualización de datos públicos del grupo (nombre, descripción, estilo, etc.).

### 📅 Gestión de eventos
- Registro de eventos con fecha, lugar, grupo asociado y descripción.
- Visualización y búsqueda por fecha, grupo o ubicación.

### 🎫 Compra de entradas
- Sistema de compra de entradas para eventos.
- Control de aforo disponible.
- Registro de ventas por usuario.

### 💬 Comentarios y valoraciones (En desarrollo)
- Los usuarios pueden comentar y puntuar eventos.
- Moderación de comentarios por administradores.
- Valoración por estrellas (1 a 5).

### 👤 Gestión de usuarios
- Registro e inicio de sesión de usuarios.
- Roles: Usuario normal y administrador.
- Panel de administración para gestión global del sistema.

## 🧪 Estructura del proyecto

- `modelo/` – Clases Java para entidades como Usuario, Evento, Grupo, Entrada.
- `dao/` – Interfaces y clases DAO para acceso a base de datos.
- `controladores/` – Servlets que gestionan la lógica y el flujo de navegación.
- `vistas/` – Páginas JSP.
- `recursos/` – Imágenes, hojas de estilo y scripts JS.

🧑‍💻 Autor
Manuel C.G.
Desarrollador Java Back-End
