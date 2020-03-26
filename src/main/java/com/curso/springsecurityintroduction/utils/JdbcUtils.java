package com.curso.springsecurityintroduction.utils;

public class JdbcUtils {
    public static final String USER_BY_USERNAME = "SELECT username, password, active FROM user"
            + " WHERE username = ?";

    public static final String PERMISSIONS_BY_USER = "SELECT u.username, p.name as name_permission FROM user_permission up"
            + " JOIN user u ON u.id = up.user_id"
            + " JOIN permission p ON p.id = up.permission_id"
            + " WHERE u.username = ?";

    public static final String PERMISSIONS_BY_GROUP = "SELECT g.id, g.name, p.name as name_permission FROM team_permission tp"
            + " JOIN group g ON g.id = tp.group_id"
            + " JOIN permission p ON p.id = tp.permission_id"
            + " JOIN user_group ug ON ug.group_id = g.id"
            + " JOIN user u ON u.id = ug.user_id"
            + " WHERE u.username = ?";
}
