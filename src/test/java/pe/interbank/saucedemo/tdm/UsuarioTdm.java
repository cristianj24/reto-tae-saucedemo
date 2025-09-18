package pe.interbank.saucedemo.tdm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UsuarioTdm {
    private static final String PROPERTIES_FILE = "/tdm.properties";
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        Properties props = new Properties();
        try (InputStream input = UsuarioTdm.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input != null) {
                props.load(input);
                URL = props.getProperty("TDM_DB_URL");
                USER = props.getProperty("TDM_DB_USER");
                PASSWORD = props.getProperty("TDM_DB_PASSWORD");
            } else {
                throw new RuntimeException("No se encontró el archivo tdm.properties en resources");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error cargando tdm.properties", e);
        }
    }

    public static Usuario obtenerUsuarioPorUsername(String username) {
        String call = "SELECT * FROM obtener_usuario_por_username(?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(call)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class Usuario {
        public final String username;
        public final String password;
        public final String estado;

        public Usuario(String username, String password, String estado) {
            this.username = username;
            this.password = password;
            this.estado = estado;
        }
    }
}
