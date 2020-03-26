package com.curso.springsecurityintroduction.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.curso.springsecurityintroduction.utils.JdbcUtils.PERMISSIONS_BY_USER;
import static com.curso.springsecurityintroduction.utils.JdbcUtils.USER_BY_USERNAME;

@Component
@RequiredArgsConstructor
public class UserSessionDetailsService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserSessionDetailsService.class.getSimpleName());

    private final DataSource dataSource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            UserSession userDetails = findUser(connection, username);

            Collection<GrantedAuthority> permissionsByUser = findPermissions(connection,
                    username, PERMISSIONS_BY_USER);

            /*Collection<GrantedAuthority> permissoesPorGrupo = findPermissions(connection,
                    login, PERMISSOES_POR_GRUPO);*/

            userDetails.getAuthorities().addAll(permissionsByUser);
//            userDetails.getAuthorities().addAll(permissoesPorGrupo);

            return userDetails;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Problemas com a tentativa de conexão!", e);
            throw new UsernameNotFoundException("Problemas com a tentativa de conexão!", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Problemas ao tentar fechar a conexão!", e);
                throw new UsernameNotFoundException("Problemas ao tentar fechar a conexão!", e);
            }
        }
    }

    public UserSession findUser(Connection connection, String username) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(USER_BY_USERNAME);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }

        String name = rs.getString("name");
        String password = rs.getString("password");
        boolean active = rs.getBoolean("active");

        rs.close();
        ps.close();

        return new UserSession(name, username, password, active);
    }

    public Collection<GrantedAuthority> findPermissions(Connection connection, String username, String sql) throws SQLException {
        List<GrantedAuthority> permissions = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            permissions.add(new SimpleGrantedAuthority(rs.getString("name_permission")));
        }

        rs.close();
        ps.close();

        return permissions;
    }
}
