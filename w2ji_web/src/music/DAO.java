package music;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DAO {
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
    
    public List<Musicgame> getMusicList(String filter ,String use){
    	List<Musicgame> list = new ArrayList<Musicgame>();
    	
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "";
            sql += " select id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn  from musicgame where id like '"+filter+"' and use_yn like '"+use+"'";
            if(!use.equals("%")) {
            	sql += " order by rand() limit 10 ";
            }
            
            System.out.println("getThisLottery = sql : "+sql);
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	Musicgame mg = new Musicgame();
            	mg.setId(   rs.getString(1) );
            	mg.setQues( rs.getString(2) );
            	mg.setAns1( rs.getString(3) );
            	mg.setAns2( rs.getString(4) );
            	mg.setAns3( rs.getString(5) );
            	mg.setAns4( rs.getString(6) );
            	mg.setCorrect( 	rs.getString(7) );
            	mg.setName( 	rs.getString(8) );
            	mg.setMusicFile(rs.getString(9) );
            	mg.setImgFile( 	rs.getString(10) );
            	mg.setUseYn( 	rs.getString(11) );
            	list.add(mg);
            }
        }
        catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        return list;
    }      
    
    public boolean setMusicgame(Musicgame mg ) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = ""; //"update book_store set rental_yn = 'Y' where rental_yn='N' and id = ?";
            if(mg.getId() == null || mg.getId().equals("")) {
            	sql +=" INSERT INTO musicgame( ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)   ";
            	sql +=" VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , now(), now()) ";
            	pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , mg.getQues()		);
                pstmt.setString(2 , mg.getAns1() 		);
                pstmt.setString(3 , mg.getAns2() 		);
                pstmt.setString(4 , mg.getAns3() 		);
                pstmt.setString(5 , mg.getAns4() 		);
                pstmt.setString(6 , mg.getCorrect()		);
                pstmt.setString(7 , mg.getName()		);
                pstmt.setString(8 , mg.getMusicFile() 	);
                pstmt.setString(9 , mg.getImgFile() 	);
                pstmt.setString(10 , mg.getUseYn() 		);                
                
                System.out.println("insert = sql : "+pstmt);
                int count = pstmt.executeUpdate();
                result = (count == 1);            	
            }else {
            	sql +=" UPDATE musicgame   ";
            	sql +=" set ques=?, ans1=?, ans2=?, ans3=?, ans4=?, correct=?, name=?, use_yn=?, update_dt = now()  ";
            	if( !mg.getMusicFile().equals("") ) {
            		sql +=" , music_file='"+mg.getMusicFile()+"'";
            	}
            	if( !mg.getImgFile().equals("")  ) {
            		sql +=" , img_file='"+mg.getImgFile()+"'";
            	}            	
            	sql +=" WHERE  id= ?  ";
            	pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , mg.getQues()	);
                pstmt.setString(2 , mg.getAns1() 	);
                pstmt.setString(3 , mg.getAns2() 	);
                pstmt.setString(4 , mg.getAns3() 	);
                pstmt.setString(5 , mg.getAns4() 	);
                pstmt.setString(6 , mg.getCorrect() 	);
                pstmt.setString(7 , mg.getName() 	);
                //pstmt.setString(8 , mg.getMusicFile() 	);
                //pstmt.setString(9 , mg.getImgFile() 	);
                pstmt.setString(8 , mg.getUseYn() 	);
                pstmt.setString(9 , mg.getId() 	);
                System.out.println("update = sql : "+pstmt);
                int count = pstmt.executeUpdate();
                result = (count == 1);   
            }
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
    
    

}
