
# Reto TAE - SauceDemo Serenity Screenplay

## Requisitos
- Java 17
- Maven 3.8+
- PostgreSQL (para TDM)
- Google Chrome

## Configuración de TDM (Test Data Management)
1. **Crea la base de datos y la tabla:**

```sql
CREATE DATABASE sauceDemoBD;
\c sauceDemoBD
CREATE TABLE usuarios (
	 id SERIAL PRIMARY KEY,
	 username VARCHAR(50) NOT NULL,
	 password VARCHAR(50) NOT NULL,
	 estado VARCHAR(20) NOT NULL
);
INSERT INTO usuarios (username, password, estado) VALUES
('standard_user', 'secret_sauce', 'activo'),
('locked_out_user', 'secret_sauce', 'bloqueado'),
('wrong_user', 'secret_sauce', 'activo');
```

2. **Crea la función para consulta TDM:**

```sql
CREATE OR REPLACE FUNCTION obtener_usuario_por_username(p_username VARCHAR)
RETURNS TABLE(username VARCHAR, password VARCHAR, estado VARCHAR) AS $$
BEGIN
	 RETURN QUERY
	 SELECT u.username, u.password, u.estado
	 FROM usuarios u
	 WHERE u.username = p_username;
END;
$$ LANGUAGE plpgsql;
```

3. **Configura las credenciales en `src/test/resources/tdm.properties`:**

```
TDM_DB_URL=jdbc:postgresql://localhost:5432/sauceDemoBD
TDM_DB_USER=postgres
TDM_DB_PASSWORD=admin
```

## Ejecución del Proyecto

1. **Clona el repositorio:**
   ```
   git clone https://github.com/cristianj24/reto-tae-saucedemo.git
   cd reto-tae-saucedemo
   ```

2. **Compila y ejecuta las pruebas:**
   ```
   mvn clean verify
   ```
3. **Genera el reporte de Serenity:**
   ```
   mvn serenity:aggregate
   ```
4. **Revisa los reportes Serenity:**
    - Los reportes HTML se generan en `target/site/serenity`.

## Notas
- Los escenarios usan TDM cuando la contraseña en el feature es `tdm`.
- Puedes modificar los datos de prueba en la tabla `usuarios`.
- Asegúrate de que PostgreSQL esté corriendo y accesible.

---

**Autor:** cristianj24
