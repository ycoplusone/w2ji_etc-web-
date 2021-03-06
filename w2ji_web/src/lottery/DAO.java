package lottery;


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
            
            //String url = "jdbc:mysql://localhost:3306/tjs828912";
            String url = "jdbc:mysql://tjs828912.cafe24.com:3306/tjs828912";
            
            conn = DriverManager.getConnection(url, "tjs828912", "qwe123asd");
            
            /*
            String url = "jdbc:mysql://localhost:3306/com_mall";
            conn = DriverManager.getConnection(url, "com", "com01");
            */
            
        }
        catch (ClassNotFoundException e) {
            System.out.println(" 드라이버 로딩 실패 ");
        }

        return conn;
    }
    
    public String[] getThisLottery(String nickname){	// 전체 목록 (), 대여량 기준 정렬(sort) , 대여된 책(rentaled) , 대여 가능한 책리스트(rental)
        String[] list = new String[7];
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "";
            sql += " select a.id , a.title , if( use_yn = 'y' , date_format( now()   , '%Y.%m.%d %H:%i:%s' ) , date_format( d_day   , '%Y.%m.%d %H:%i:%s' ) )  d_day , concat( a.title , if( a.use_yn='c',' - 마감 - ' ,' - 진행중 - ')  , '( 마감일 : ' , if( use_yn = 'y' , date_format( now()   , '%Y.%m.%d %H:%i:%s' ) , date_format( d_day   , '%Y.%m.%d %H:%i:%s' ) ) , ' )') txt   ";
            sql += " , (select IFNULL(max(option_yn),'n') from  lottery_gift where  nick_nm = '"+nickname+"' ) auto  ";
            sql += " , a.use_yn as use_yn ";
            sql += " , (select count(1) from  user_lottery where a.id = info_id and nick_nm = '"+nickname+"' ) user_cnt   ";
            sql += " from lottery_info a where a.id = ( select max( id ) from lottery_info )   ";
            
            
            
            System.out.println("getThisLottery = sql : "+sql);
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            list[0] = rs.getString(1);
            list[1] = rs.getString(2);
            list[2] = rs.getString(3);
            list[3] = rs.getString(4);
            list[4] = rs.getString(5);
            list[5] = rs.getString(6);
            list[6] = rs.getString(7);

        }
        catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        return list;
    }        
    
    public List<String[]> getList(String str){
        List<String[]> list = new ArrayList<String[]>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "select  movieid, movietitle, releasedate, videoreleasedate, imdburl, genre, cdate  from movie_item where movieid < 10 ";
             
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	String [] vo = new String[5];
            	vo[0] = rs.getString(1);
            	vo[1] = rs.getString(2);
            	vo[2] = rs.getString(3);
            	vo[3] = rs.getString(4);
            	vo[4] = rs.getString(5);
                list.add(vo);
            }
        }
        catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        return list;
    }
    
    public List<String[]> setUserLottery(String id , String nick , String num1,String num2,String num3,String num4,String num5,String num6 ){
    	List<String[]> list = new ArrayList<String[]>();
    	boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        String sql = "";
        try {
            conn = getConnection();
            
			sql += " select ";			
			sql += " a.use_yn "; 
			sql += " , count(1) cnt ";
			sql += " from lottery_info a "; 
			sql += " left outer join user_lottery b on ( a.id = b.info_id ) ";
			sql += " where a.id = '"+id+"' ";
			sql += " and b.nick_nm = '"+nick+"'  ";
			
			pstmt2 = conn.prepareStatement(sql);
            rs = pstmt2.executeQuery();
            rs.next();
            String use_yn = rs.getString(1); 
            String user_cnt = rs.getString(2);
	    	
            if( use_yn.equals("y") && user_cnt.equals("0") ) {
                sql = "INSERT INTO user_lottery( info_id, nick_nm, num1, num2, num3, num4, num5, num6, create_dt) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, now() )";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(	1, Integer.parseInt(id) );
                pstmt.setString(2, nick					);
                pstmt.setInt(	3, Integer.parseInt( num1 )	);
                pstmt.setInt(	4, Integer.parseInt( num2 )	);
                pstmt.setInt(	5, Integer.parseInt( num3 )	);
                pstmt.setInt(	6, Integer.parseInt( num4 )	);
                pstmt.setInt(	7, Integer.parseInt( num5 )	);
                pstmt.setInt(	8, Integer.parseInt( num6 )	);
                
                int count = pstmt.executeUpdate();
                result = (count == 1);            	
            }else {
            	result = false;
            }
            

                        
            conn = getConnection();
            sql = "";
			sql += " SELECT a.id, a.info_id, a.nick_nm ";
			sql += " , concat(a.num1 ,' , ', a.num2,' , ', a.num3,' , ', a.num4,' , ', a.num5,' , ', a.num6  ) mynum ";
			sql += " , case when b.use_yn='c' then concat('/ ', b.num1 ,' , ', b.num2,' , ', b.num3,' , ', b.num4,' , ', b.num5,' , ', b.num6  ) else '미추첨' end as goal ";
			sql += " ,case when b.use_yn = 'c' then ";
			sql += " (case when a.num1 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " + case when a.num2 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " + case when a.num3 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " + case when a.num4 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " + case when a.num5 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " + case when a.num6 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " ) else '' end as point   ";
			sql += " FROM user_lottery a ";
			sql += " join lottery_info b on ( a.info_id = b.id ) ";
			sql += " where a.info_id = ? ";
			sql += " and a.nick_nm = ?   ";  
			sql += " order by 1 desc "; 
             
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(	1, Integer.parseInt(id) );
			pstmt1.setString(2, nick					);
            System.out.println("sql : "+pstmt1);
            rs = pstmt1.executeQuery();
            while(rs.next()) {
            	String [] vo = new String[6];
            	vo[0] = rs.getString(1);
            	vo[1] = rs.getString(2);
            	vo[2] = rs.getString(3);
            	vo[3] = rs.getString(4);
            	vo[4] = rs.getString(5);
            	vo[5] = rs.getString(6);
                list.add(vo);
            }
        }
        catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        return list;
    }

    public List<String[]> getMyLottery(String id , String nick  ){
    	List<String[]> list = new ArrayList<String[]>();
    	boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;

        try {        	
            conn = getConnection();
            String sql = "";
			sql += " SELECT a.id, a.info_id, a.nick_nm ";
			sql += " , concat(a.num1 ,' , ', a.num2,' , ', a.num3,' , ', a.num4,' , ', a.num5,' , ', a.num6  ) mynum ";
			sql += " , case when b.use_yn='c' then concat('', b.num1 ,' , ', b.num2,' , ', b.num3,' , ', b.num4,' , ', b.num5,' , ', b.num6  ) else '미추첨' end as goal ";
			sql += " ,case when b.use_yn = 'c' then ";
			sql += " (case when a.num1 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " + case when a.num2 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " + case when a.num3 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " + case when a.num4 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " + case when a.num5 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " + case when a.num6 in  (b.num1 ,b.num2 , b.num3 , b.num4 , b.num5 , b.num6) then 1 else 0 end ";
			sql += " ) else '' end as point   ";
			sql += " FROM user_lottery a ";
			sql += " join lottery_info b on ( a.info_id = b.id ) ";
			sql += " where a.info_id = ? ";
			sql += " and a.nick_nm = ?   ";   
			sql += " order by 1 desc";
             
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(	1, Integer.parseInt(id) );
			pstmt1.setString(2, nick					);
            System.out.println("sql : "+pstmt1);
            rs = pstmt1.executeQuery();
            while(rs.next()) {
            	String [] vo = new String[6];
            	vo[0] = rs.getString(1);
            	vo[1] = rs.getString(2);
            	vo[2] = rs.getString(3);
            	vo[3] = rs.getString(4);
            	vo[4] = rs.getString(5);
            	vo[5] = rs.getString(6);
                list.add(vo);
            }
        }
        catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        return list;
    }
    
    public List<String[]> getPastLottery(){
    	List<String[]> list = new ArrayList<String[]>();
    	boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;

        try {        	
            conn = getConnection();
            String sql = "";
            sql += " select a.id , ifnull( concat( a.title , if( a.use_yn='c',' - 마감 - ' ,' - 진행중 - ') , ' ( 마감일 : ' , if( use_yn = 'y' , date_format( now()   , '%Y.%m.%d %H:%i:%s' ) , date_format( d_day   , '%Y.%m.%d %H:%i:%s' ) ) ,  ' )') ,'정보 없음') txt  , a.use_yn    "; 
            sql += " from lottery_info a where a.use_yn in( 'c','y') order by 1 desc ";
             
			pstmt1 = conn.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            while(rs.next()) {
            	String [] vo = new String[3];
            	vo[0] = rs.getString(1); // id
            	vo[1] = rs.getString(2); // txt
            	vo[2] = rs.getString(3); // txt
                list.add(vo);
            }
        }
        catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        return list;
    }    

    public String getNotice(){
    	
    	boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        String str = "";

        try {        	
            conn = getConnection();
            String sql = "";
            sql += " select a.txt from lottery_notice a ";
       		sql += " where a.seq = ( select max(seq) from lottery_notice where use_yn = 'y' ) ";
             
			pstmt1 = conn.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            rs.next();
            str = rs.getString(1); // txt
        
        }catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        return str;
    }
    
    //  이번 회차 당첨 번호 리스트
    public LotteryInfo getThisLotteryinfo(String str) {
    	LotteryInfo li = new LotteryInfo();
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {        	
            conn = getConnection();
            String sql = "";
			sql += " select ";
			sql += " a.id , a.title , if( use_yn = 'y' , date_format( now()   , '%Y.%m.%d %H:%i:%s' ) , date_format( d_day   , '%Y.%m.%d %H:%i:%s' ) ) d_day "; 
			sql += " , case when a.use_yn = 'c' then a.num1 else '' end num1 ";
			sql += " , case when a.use_yn = 'c' then a.num2 else '' end num2 ";
			sql += " , case when a.use_yn = 'c' then a.num3 else '' end num3 ";
			sql += " , case when a.use_yn = 'c' then a.num4 else '' end num4 ";
			sql += " , case when a.use_yn = 'c' then a.num5 else '' end num5 ";
			sql += " , case when a.use_yn = 'c' then a.num6 else '' end num6 ";
			sql += " from lottery_info a ";
			sql += " where a.id = ( select max(id)  from lottery_info where id like '"+str+"'  ) ";    
			System.out.println( sql );
       		
			pstmt1 = conn.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            rs.next();
	            li.setId(    rs.getString(1) );
	            li.setTitle( rs.getString(2) );
	            li.setD_day( rs.getString(3) );
	            li.setNum1( rs.getString(4) );
	            li.setNum2( rs.getString(5) );
	            li.setNum3( rs.getString(6) );
	            li.setNum4( rs.getString(7) );
	            li.setNum5( rs.getString(8) );
	            li.setNum6( rs.getString(9) );
            
        }catch (SQLException e) {
            System.out.println("getThisLotteryinfo 에러: " + e);
        }    	
    	return li;
    }
    
    public List<LotteryList> getThisLotteryList(String str) {
    	List<LotteryList> lll = new ArrayList<LotteryList>();
    	Connection conn = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {        	
            conn = getConnection();
            String sql = "";
            sql += " select     ";
            sql += "   b.nick_nm   ";
            sql += " , b.num1   ";
            sql += " , case when a.use_yn !='c' then 'false' else case when b.num1 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 'true' else 'false' end end num1_chk   ";
            sql += " , b.num2   ";
            sql += " , case when a.use_yn !='c' then 'false' else case when b.num2 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 'true' else 'false' end end num2_chk   ";
            sql += " , b.num3   ";
            sql += " , case when a.use_yn !='c' then 'false' else case when b.num3 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 'true' else 'false' end end num3_chk   ";
            sql += " , b.num4   ";
            sql += " , case when a.use_yn !='c' then 'false' else case when b.num4 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 'true' else 'false' end end num4_chk   ";
            sql += " , b.num5   ";
            sql += " , case when a.use_yn !='c' then 'false' else case when b.num5 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 'true' else 'false' end end num5_chk   ";
            sql += " , b.num6   ";
            sql += " , case when a.use_yn !='c' then 'false' else case when b.num6 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 'true' else 'false' end end num6_chk   ";
            sql += " , case when a.use_yn !='c' then '' else (case when b.num1 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end  + case when b.num2 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end  + case when b.num3 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end  + case when b.num4 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end  + case when b.num5 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end  + case when b.num6 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end ) end as cnt   ";
            sql += " from user_lottery b   ";
            sql += " left outer join lottery_info a on ( a.id = b.info_id )   ";
            sql += " where 1=1  and a.id = ( select max(id)  from lottery_info where id like '"+str+"'  )  ";
			//sql += " and a.use_yn ='c'              ";
			System.out.println( sql );
			pstmt1 = conn.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            while( rs.next() ){
            	LotteryList l_list = new LotteryList();
            	l_list.setNick_nm( rs.getString(1));
            	l_list.setNum1( rs.getString(2) );
            	l_list.setNum1_chk( rs.getString(3) );
            	
            	l_list.setNum2( rs.getString(4) );
            	l_list.setNum2_chk( rs.getString(5) );
            	
            	l_list.setNum3( rs.getString(6) );
            	l_list.setNum3_chk( rs.getString(7) );
            	
            	l_list.setNum4( rs.getString(8) );
            	l_list.setNum4_chk( rs.getString(9) );
            	
            	l_list.setNum5( rs.getString(10) );
            	l_list.setNum5_chk( rs.getString(11) );
            	
            	l_list.setNum6( rs.getString(12) );
            	l_list.setNum6_chk( rs.getString(13) );
            	l_list.setCnt( rs.getString(14) );
            	
            	lll.add(l_list);
            }
        }catch (SQLException e) {
            System.out.println("getThisLotteryList 에러: " + e);
        }  
    	return lll;    			
    }

    public List<LotterySummery> getThisLotterySummery(String id) {
    	List<LotterySummery> lls = new ArrayList<LotterySummery>();
    	Connection conn = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {        	
            conn = getConnection();
            String sql = "";
			sql += " select ";
			sql += " a.cnt , count(1) tot_cnt ";
			sql += " from  ";
			sql += " ( ";
			sql += " select ";
			sql += " case when b.num6 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 'true' else 'false' end num6_chk ";
			sql += " , (case when b.num1 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end ";
			sql += " + case when b.num2 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end ";
			sql += " + case when b.num3 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end ";
			sql += " + case when b.num4 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end ";
			sql += " + case when b.num5 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end ";
			sql += " + case when b.num6 in ( a.num1 , a.num2 , a.num3 , a.num4 , a.num5 , a.num6  ) then 1 else 0 end ) as cnt ";
			sql += " from lottery_info a ";
			sql += " join user_lottery b on ( a.id = b.info_id ) ";
			sql += " where 1=1 ";
			sql += " and a.id = '"+id+"' ";
			//sql += " and a.use_yn ='c' ";
			sql += " ) a ";
			//sql += " where a.cnt != 0 ";
			sql += " group by a.cnt desc ";
			System.out.println(sql);
       		
			pstmt1 = conn.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            while( rs.next() ){
            	LotterySummery l_list = new LotterySummery();
            	l_list.setCnt( rs.getString(1) );
            	l_list.setTot_cnt( rs.getString(2) );
            	lls.add(l_list);
            }
        }catch (SQLException e) {
            System.out.println("getThisLotterySummery 에러: " + e);
        }  
    	return lls;	
    }  
    
    public List<String[]> getLotteryInfoList() {
    	List<String[]> lls = new ArrayList<String[]>();
    	Connection conn = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {        	
            conn = getConnection();
            String sql = "";
			sql += " select a.id , concat( a.title , if( a.use_yn='c',' - 마감 - ' ,' - 진행중 - ')  , '( 마감일 : ' , if( use_yn = 'y' , date_format( now()   , '%Y.%m.%d %H:%i:%s' ) , date_format( d_day   , '%Y.%m.%d %H:%i:%s' ) ) , ' )')  txt from lottery_info a order by 1 desc ";			
       		
			pstmt1 = conn.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            while( rs.next() ){
            	String[] l_list = new String[2];
            	l_list[0] =rs.getString(1);
            	l_list[1] =rs.getString(2);
            	lls.add(l_list);
            }
        }catch (SQLException e) {
            System.out.println("getLotteryInfo 에러: " + e);
        }  
    	return lls;	
    } 
    
    // 회차 정보 전체 리스트
    public List<LotteryInfo> getLotteryinfo() {
    	List<LotteryInfo> li = new ArrayList<LotteryInfo>();
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {        	
            conn = getConnection();
            String sql = "";
			sql += " select ";
			sql += " a.id , a.title ,  if( use_yn = 'y' , date_format( now()   , '%Y.%m.%d %H:%i:%s' ) , date_format( d_day   , '%Y.%m.%d %H:%i:%s' ) ) d_day  , a.num1  , a.num2 , a.num3 , a.num4 , a.num5 , a.num6 , a.use_yn , case when a.use_yn = 'y' then '접수중' else '마감' end use_nm ";
			sql += " from lottery_info a ";
			sql += " order by 1 desc ";    
       		
			pstmt1 = conn.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            while( rs.next() ) {
            	LotteryInfo l_list = new LotteryInfo();
            	l_list.setId(    rs.getString(1) );
            	l_list.setTitle( rs.getString(2) );
            	l_list.setD_day( rs.getString(3) );
            	l_list.setNum1( rs.getString(4) );
            	l_list.setNum2( rs.getString(5) );
            	l_list.setNum3( rs.getString(6) );
            	l_list.setNum4( rs.getString(7) );
            	l_list.setNum5( rs.getString(8) );
            	l_list.setNum6( rs.getString(9) );
            	l_list.setUse_yn( rs.getString(10) );
            	l_list.setUse_nm( rs.getString(11) );
            	li.add( l_list );
            }            
        }catch (SQLException e) {
            System.out.println("getLotteryinfo 에러: " + e);
        }    	
    	return li;
    }
    
    
	 // 후원 문구 불러 오기
    public LotteryNotice getLotterynotice_seq(String seq) {
    	LotteryNotice li = new LotteryNotice();
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {        	
            conn = getConnection();
            String sql = "";
			sql += " select ";
			sql += " a.seq , a.txt , a.use_yn ";
			sql += " from lottery_notice a ";
			sql += " where a.seq like ? ";
			
       		
			pstmt1 = conn.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            rs.next();
            	
            li.setSeq(   rs.getString(1) );
            li.setTxt(   rs.getString(2) );
            li.setUse_yn(   rs.getString(3) );
            
        }catch (SQLException e) {
            System.out.println("getLotterynotice_seq 에러: " + e);
        }    	
    	return li;
    }    
    
    public boolean setLotteryInfo(LotteryInfo li ) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        try {
            conn = getConnection();
            String sql = ""; //"update book_store set rental_yn = 'Y' where rental_yn='N' and id = ?";
            if(li.getId() == null || li.getId().equals("")) {
            	sql +=" INSERT INTO lottery_info( title,  num1, num2, num3, num4, num5, num6,  create_dt , use_yn)  ";
            	sql +=" VALUES( ? , ? , ? , ? , ? , ? , ? , now() , ?) ";
            	pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , li.getTitle()	);
                pstmt.setString(2 , li.getNum1() 	);
                pstmt.setString(3 , li.getNum2() 	);
                pstmt.setString(4 , li.getNum3() 	);
                pstmt.setString(5 , li.getNum4() 	);
                pstmt.setString(6 , li.getNum5() 	);
                pstmt.setString(7 , li.getNum6() 	);
                pstmt.setString(8 , "y" 	);
                int count = pstmt.executeUpdate();
                result = (count == 1);            	
            }else {
            	sql = "";
            	sql +=" UPDATE lottery_info SET  title=?, num1=?, num2=?, num3=?, num4=?, num5=?, num6=?, create_dt=now()  ";
            	sql +="  WHERE  id= '"+li.getId()+"' ";
            	pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , li.getTitle()	);                
                pstmt.setString(2 , li.getNum1() 	);
                pstmt.setString(3 , li.getNum2() 	);
                pstmt.setString(4 , li.getNum3() 	);
                pstmt.setString(5 , li.getNum4() 	);
                pstmt.setString(6 , li.getNum5() 	);
                pstmt.setString(7 , li.getNum6() 	);                
                
                int count = pstmt.executeUpdate();
                result = (count == 1);      
                
                
                sql = "";
                sql += " update lottery_info set d_day = DATE_FORMAT( now() , '%Y%m%d%H%i%s' ) , use_yn = "; 
                sql += " if(case when num1 is null or num1 = 0  then 0 else 1 end ";  
				sql += " + case when num2 is null or num2 = 0   then 0 else 1 end  ";
				sql += " + case when num3 is null or num3 = 0   then 0 else 1 end  ";
				sql += " + case when num4 is null or num4 = 0   then 0 else 1 end  ";
				sql += " + case when num5 is null or num5 = 0   then 0 else 1 end  ";
				sql += " + case when num6 is null or num6 = 0   then 0 else 1 end = 6 , 'c' , 'y') ";
				sql += " where id = '"+li.getId()+"' ";
				pstmt = conn.prepareStatement(sql);				
				count = pstmt.executeUpdate();
                result = (count == 1);               
            }
        }
        catch (SQLException e) {
        	System.out.println(" 하나 ");
            e.printStackTrace();
        }finally {
            try {
                if( conn != null ) {conn.close(); }
                if( pstmt != null ) { pstmt.close(); }
            }catch(SQLException e) {
            	System.out.println(" 두울 ");
            	e.printStackTrace(); 
            }
        }
        return result;
    }    
    
    
    
    // 후원-정보
    public List<LotteryNotice> getLotteryNoice(String str) {
    	List<LotteryNotice> li = new ArrayList<LotteryNotice>();
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {        	
            conn = getConnection();
            String sql = "";
			sql += " select ";
			sql += " a.seq , a.txt , a.use_yn , case when a.use_yn = 'y' then '사용' else '미사용' end use_nm ";
			sql += " from lottery_notice a ";
			sql += " where a.use_yn like '%"+str+"%' ";    
			sql += " order by 1 desc ";    
       		
			pstmt1 = conn.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            while( rs.next() ) {
            	LotteryNotice l_list = new LotteryNotice();
            	l_list.setSeq(    rs.getString(1) );
            	l_list.setTxt(    rs.getString(2) );            	
            	l_list.setUse_yn( rs.getString(3) );
            	l_list.setUse_nm( rs.getString(4) );
            	li.add( l_list );
            }            
        }catch (SQLException e) {
            System.out.println("getLotteryNoice 에러: " + e);
        }    	
    	return li;
    }

    public boolean setLotteryNotice(LotteryNotice li ) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = ""; //"update book_store set rental_yn = 'Y' where rental_yn='N' and id = ?";
            if(li.getSeq() == null || li.getSeq().equals("")) {
            	sql +=" INSERT INTO lottery_notice( txt , use_yn , create_dt)  ";
            	sql +=" VALUES(  ? , ? , now()) ";
            	pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , li.getTxt()	);
                pstmt.setString(2 , li.getUse_yn() 	);
                int count = pstmt.executeUpdate();
                result = (count == 1);            	
            }else {
            	sql +=" UPDATE lottery_notice SET  txt=?, use_yn=?, create_dt=now()  ";
            	sql +="  WHERE  seq = ? ";
            	pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , li.getTxt()	);
                pstmt.setString(2 , li.getUse_yn() 	);               
                pstmt.setString(3 , li.getSeq() 	);
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
    
    
    //아이디 체크
    public List<String[]> id_check(String id ){
    	boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        String sql="";        
        //String str[] = new String[2];
        List<String[]> list = new ArrayList<String[]>();
        
        try {            
            conn = getConnection();
            sql += " select   count(1) id_cnt "; 
            sql += " , case when  DATE_FORMAT( DATE_ADD(update_dt, INTERVAL 8 DAY)  , '%Y%m%d') <=  DATE_FORMAT( now() , '%Y%m%d' )  then 'f' else 't' end as id_val ";
            sql += " from lottery_member  ";
            sql += " where nick_nm = '"+id+"'  ";           
            
			pstmt1 = conn.prepareStatement(sql);
            rs = pstmt1.executeQuery();
            
            rs.next();
            String str[] = new String[2];
            str[0] = rs.getString(1);
            str[1] = rs.getString(2);
            list.add(str);
        }catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        return list;
    }// 아이디 체크 끝
    
    public boolean id_create(String id ) {
    	System.out.println("server - id_create ");
    	boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
        	conn = getConnection();
        	
        	sql = "select count(1) cnt from lottery_member where nick_nm = '"+id+"' ";
        	pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();            
            rs.next();
            
            if( rs.getString(1).equals("1") ){
            	result = false;
            }else{
            	sql ="";
               	sql +=" INSERT INTO lottery_member(nick_nm, update_dt)  VALUES('"+id+"', now())  ";
               	pstmt = conn.prepareStatement(sql);
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

    public boolean insert_lottery_gift(
    	     String _nickname 	,                
	         String _local 		,
	         String _rankgift 	,
	         String _tel 		,
	         String _kakao		,
	         String _facebook 	,
	         String _teletc 	,
	         String _photo_comment 	,
	         String _photo_etc 		,         
	         String _amt 			,
	         String _prodct 		,	  
	         String _info_id		,
	         String [] org_file_name ,
	         String [] chage_file_name 
    	) {
    	System.out.println(" insert_lottery_gift ");
    	boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        
        String file1 = "";
        String file1nm ="";
        String file2 = "";
        String file2nm ="";
        String file3 = "";
        String file3nm ="";
        
        if(org_file_name.length>=1) {
        	file1   = chage_file_name[0];
        	file1nm = org_file_name[0];
        }
        if(org_file_name.length>=2) {
        	file2   = chage_file_name[1];
        	file2nm = org_file_name[1];
        }
        if(org_file_name.length>=3) {
        	file3   = chage_file_name[2];
        	file3nm = org_file_name[2];
        }
        
        try {
        	conn = getConnection();
	       	sql +=" INSERT INTO lottery_gift(nick_nm, local, rankgift, tel, kakao, facebook, teletc, file1, file1_nm, file2, file2_nm, file3, file3_nm, comment, etc, amt, prodct, update_dt, info_id , option_yn)  ";
	       	sql +=" VALUES(                       ? ,    ? ,       ? ,  ? ,    ? ,       ? ,     ? ,    ? ,       ? ,    ? ,       ? ,    ? ,       ? ,      ? ,  ? ,  ? ,     ? , now()    , ? , 'n') ";	       	
        	
        	pstmt = conn.prepareStatement(sql);
            pstmt.setString(1 , _nickname	);
            pstmt.setString(2 , _local 	);
            pstmt.setString(3 , _rankgift 	);
            pstmt.setString(4 , _tel 	);
            pstmt.setString(5 , _kakao 	);
            pstmt.setString(6 , _facebook 	);
            pstmt.setString(7 , _teletc 	);            
            pstmt.setString(8 , file1 	);
            pstmt.setString(9 , file1nm 	);            
            pstmt.setString(10 , file2 	);
            pstmt.setString(11 , file2nm 	);            
            pstmt.setString(12 , file3 	);
            pstmt.setString(13 , file3nm 	);
            
            pstmt.setString(14 , _photo_comment 	);
            pstmt.setString(15 , _photo_etc 	);
            pstmt.setString(16 , _amt 	);
            pstmt.setString(17 , _prodct 	);
            pstmt.setString(18 , _info_id 	);
	       	
            System.out.println("sql : "+pstmt);
	       	
	        int count = pstmt.executeUpdate();
	        result = (count == 1);
               
                 
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if( conn != null ) {conn.close(); }
                if( pstmt != null ) { pstmt.close(); }
            }catch(SQLException e) {   e.printStackTrace(); }
        }
        return result;
    	
    }
    
    public List<String[]> select_lottery_gift(String nickname , String info_id){
    	boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="";        
        List<String[]> list = new ArrayList<String[]>();
        
        try {            
			conn = getConnection();
			sql +=" SELECT ";
			sql +=" a.seq, a.nick_nm, a.local, a.rankgift, a.tel, a.kakao,a. facebook, a.teletc, a.file1, a.file1_nm, a.file2, a.file2_nm, a.file3, a.file3_nm, a.comment, a.etc, a.amt, a.prodct, a.update_dt, a.info_id, a.option_yn "; 
			sql +=" FROM lottery_gift a ";
			sql +=" where a.nick_nm like '"+nickname+"' ";
			if(info_id.equals("test")) {				
			}else {
				sql += " and a.info_id like '"+info_id+"' ";
			}
			
			sql +=" order by 1 desc ";
            
			System.out.println("select_lottery_gift : "+sql);
			pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while( rs.next() ){
            	String str[] = new String[21];
                for(int i = 0 ; 21>i ; i++){
                	str[i] = rs.getString(i+1);
                }
                list.add(str);
            }
            
        }catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        return list;
    }// 아이디 체크 끝
    
    
    public boolean update_gift_option(String seq , String yn ) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = ""; //"update book_store set rental_yn = 'Y' where rental_yn='N' and id = ?";
        
            	sql +=" UPDATE lottery_gift SET OPTION_YN = ? WHERE SEQ = ?  ";            	
            	pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , yn		);
                pstmt.setString(2 , seq 	);              
                
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
    
    
    public boolean update_use_yn(String seq ) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = ""; //"update book_store set rental_yn = 'Y' where rental_yn='N' and id = ?";
        
            	sql +=" UPDATE lottery_info SET use_yn = 'c' WHERE id = ?  ";            	
            	pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , seq		);
                System.out.println("update_use_yn : "+sql);
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
    
}