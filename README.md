# Sistema de Gestión de Productos

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![React](https://img.shields.io/badge/React-18.x-blue)
![JWT](https://img.shields.io/badge/JWT-Auth-yellow)

Este proyecto es un sistema completo para la gestión personalizada de productos. Permite a los usuarios registrarse, iniciar sesión y administrar su propio catálogo de productos de manera segura e independiente.

## Características principales

- **Autenticación y autorización**: Sistema completo de registro e inicio de sesión utilizando JWT.
- **Gestión de productos**: CRUD completo para productos, con acceso restringido por usuario.
- **API RESTful**: Endpoints protegidos con Spring Security.
- **Arquitectura moderna**: Backend en Spring Boot y frontend en React.
- **Seguridad**: Implementación de JWT para proteger rutas y asegurar la información del usuario.

## Estructura del proyecto

El proyecto está dividido en dos partes principales:

### Backend (Spring Boot)

```
src/
├── main/
│   ├── java/
│   │   └── com.jerorodriguez.springbootjwt/
│   │       ├── auth/           # Lógica de autenticación y autorización
│   │       ├── config/         # Configuraciones de Spring
│   │       ├── controllers/    # Controladores REST
│   │       ├── demo/           # Demostraciones de funcionalidad
│   │       ├── entities/       # Entidades JPA
│   │       ├── jwt/            # Configuración y utilidades JWT
│   │       ├── repository/     # Repositorios JPA
│   │       ├── services/       # Servicios de negocio
│   │       ├── KeyGenerator    # Generador de claves para JWT
│   │       └── SpringBootJwtApplication # Clase principal
```

### Frontend (React)

```
products-frontend/
├── node_modules/      # Dependencias
├── public/            # Archivos públicos
├── src/               # Código fuente
│   ├── components/    # Componentes React
│   ├── services/      # Servicios para comunicación con API
│   ├── pages/         # Páginas de la aplicación
│   └── utils/         # Utilidades
├── .gitignore         # Archivos ignorados por Git
├── package.json       # Dependencias y scripts
└── README.md          # Documentación
```

## Requisitos previos

- Java 17 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior
- Node.js 16 o superior
- npm 8 o superior

## Configuración del entorno

### Backend (Spring Boot)

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/sistema-gestion-productos.git
   cd sistema-gestion-productos
   ```

2. Configura la base de datos MySQL en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/db_products
   spring.datasource.username=root
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Ejecuta la aplicación Spring Boot:
   ```bash
   ./mvnw spring-boot:run
   ```
   La API estará disponible en `http://localhost:8080`

### Frontend (React)

1. Navega al directorio del frontend:
   ```bash
   cd products-frontend
   ```

2. Instala las dependencias:
   ```bash
   npm install
   ```

3. Inicia la aplicación:
   ```bash
   npm start
   ```
   La aplicación estará disponible en `http://localhost:3000`

## Documentación de la API

### Autenticación

#### Registro de usuario

```
POST /api/auth/register
```

**Request Body:**
```json
{
  "username": "username",
  "password": "password",
  "firstName": "firstName",
  "lastName": "lastName",
  "country": "country"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### Inicio de sesión

```
POST /api/auth/login
```

**Request Body:**
```json
{
  "username": "username",
  "password": "password"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### Información del usuario

```
GET /api/auth/user-info
```

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Response:**
```json
{
  "username": "username",
  "firstName": "firstname",
  "lastName": "lastname",
  "country": "country",
  "role": "role"
}
```

#### Endpoint de demostración protegido

```
GET /api/auth/demo
```

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Response:**
```
Hello world from secure endpoint, firstName lastName
```

### Gestión de Productos

#### Obtener todos los productos del usuario actual

```
GET /api/products/
```

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### Obtener un producto específico

```
GET /api/products/{id}
```

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### Crear un nuevo producto

```
POST /api/products/
```

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Request Body:**
```json
{
  "name": "Nombre del producto",
  "description": "Descripción del producto",
  "price": 99.99,
  "stock": 10
}
```

#### Actualizar un producto existente

```
PUT /api/products/{id}
```

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Request Body:**
```json
{
  "name": "Nuevo nombre",
  "description": "Nueva descripción",
  "price": 129.99,
  "stock": 15
}
```

#### Eliminar un producto

```
DELETE /api/products/{id}
```

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

## Seguridad y JWT

El sistema utiliza JSON Web Tokens (JWT) para gestionar la autenticación y autorización. Cada vez que un usuario se registra o inicia sesión, el servidor genera un token que debe incluirse en todas las solicitudes posteriores.

### Flujo de autenticación

1. El usuario se registra o inicia sesión.
2. El servidor valida las credenciales y genera un token JWT.
3. El cliente almacena este token (típicamente en localStorage).
4. Para cada solicitud a un endpoint protegido, el cliente incluye el token en el encabezado `Authorization`.
5. El servidor verifica la validez del token antes de procesar la solicitud.

### Implementación de seguridad

La seguridad está implementada utilizando Spring Security junto con filtros personalizados para JWT. La configuración principal se encuentra en el paquete `config` y `jwt`.

## Tecnologías utilizadas

### Backend
- **Spring Boot**: Framework para el desarrollo de aplicaciones Java.
- **Spring Security**: Para gestión de autenticación y autorización.
- **Spring Data JPA**: Para el acceso y manipulación de datos.
- **MySQL**: Como base de datos relacional.
- **JSON Web Token (JWT)**: Para la gestión de tokens de autenticación.

### Frontend
- **React**: Biblioteca JavaScript para construir interfaces de usuario.
- **Bootstrap**: Framework CSS para diseño responsivo.
- **Axios**: Cliente HTTP para realizar peticiones a la API.
- **React Router**: Para la navegación entre páginas.

## Contribuir

Si deseas contribuir a este proyecto, por favor:

1. Haz un fork del repositorio
2. Crea una rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Realiza tus cambios y haz commit (`git commit -m 'Añade nueva funcionalidad'`)
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Para cualquier pregunta o sugerencia, no dudes en contactarme:

- **Desarrollador**: Jeronimo Rodriguez Sepulveda
- **Email**: jeronimoroseag@gmail.com
