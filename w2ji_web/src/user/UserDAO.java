package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost/com_mall";
            conn = DriverManager.getConnection(url, "com_mall", "com_mall");
        }
        catch (ClassNotFoundException e) {
            System.out.println(" 드라이버 로딩 실패 ");
        }

        return conn;
    }

    public boolean insert(UserVO vo ) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            // Column
            // PK , name , email , password
            String sql = "INSERT INTO user VALUES ( ?, ?, ?);";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getEmail());
            pstmt.setString(3, vo.getPwd());
            int count = pstmt.executeUpdate();

            result = (count == 1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if( conn != null ) {
                    conn.close();
                }
                if( pstmt != null ) {
                    pstmt.close();
                }
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}