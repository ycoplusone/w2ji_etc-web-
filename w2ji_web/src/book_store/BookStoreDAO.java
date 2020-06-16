package book_store;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookStoreDAO {
    private Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/com_mall";
            conn = DriverManager.getConnection(url, "com", "com01");
        }
        catch (ClassNotFoundException e) {
            System.out.println(" 드라이버 로딩 실패 ");
        }

        return conn;
    }
    public boolean insert(BookStore vo) {
    	return false;
    }
    
    public boolean BookReturn(String id ) {	//책 반납하기
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "update book_store set rental_yn = 'Y' where rental_yn='N' and id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(	1, id	);            
            int count = pstmt.executeUpdate();
            result = (count == 1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if( conn != null ) {conn.close(); }
                if( pstmt != null ) { pstmt.close(); }
            }catch(SQLException e) {   e.printStackTrace(); }
        }
        return result;
    }
    
    public boolean BookRental(String id) {	//책 대여하기
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "update book_store set rental_yn = 'N' , count = count+1 where rental_yn='Y' and id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(	1, id	);            
            int count = pstmt.executeUpdate();
            result = (count == 1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if( conn != null ) {conn.close(); }
                if( pstmt != null ) { pstmt.close(); }
            }catch(SQLException e) {   e.printStackTrace(); }
        }
        return result;
    }

    public boolean BookAdd(BookStore vo ) { //책 추가하기
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            String sql = "INSERT INTO book_store( nm, writer, price, rental_yn, count) VALUES( ?, ?, ?, 'Y', 0)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getNm()		);
            pstmt.setString(2, vo.getWriter()	);
            pstmt.setInt(	3, vo.getPrice()	);
            int count = pstmt.executeUpdate();
            result = (count == 1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if( conn != null ) {conn.close(); }
                if( pstmt != null ) { pstmt.close(); }
            }catch(SQLException e) {   e.printStackTrace(); }
        }
        return result;
    }
    
    public List<BookStore> getList(String str){	// 전체 목록 (), 대여량 기준 정렬(sort) , 대여된 책(rentaled) , 대여 가능한 책리스트(rental)
        List<BookStore> list = new ArrayList<BookStore>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "select  id , nm , writer , price , rental_yn , count  from book_store ";
            if( str.equals("rentaled")) {
            	sql += " where  rental_yn = 'N' ";
            }else if( str.equals("rental") ) {
            	sql += " where  rental_yn = 'Y' ";
            }else if( str.equals("sort") ) {
            	sql += " order by count desc ";
            }
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	BookStore vo = new BookStore();
            	vo.setId( rs.getInt(1) );
            	vo.setNm( rs.getString(2) );
            	vo.setWriter( rs.getString(3) );
            	vo.setPrice( rs.getInt(4) );
            	vo.setRentalYn( rs.getString(5) );
            	vo.setCount( rs.getInt(6) );
                list.add(vo);
            }
        }
        catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        return list;
    }
    
    
}