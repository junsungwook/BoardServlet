package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.BoardVO;
import vo.CommentVO;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/board");
		return ds.getConnection();
	}
	//게시판 글 카운팅
	public int boardCount() {
		int count = 0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select count(*) from board";
			rs = st.executeQuery(sql);
			if(rs.next())
			count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return count;
	}
	//게시판 글 쓰기
	public void boardInsert(BoardVO bv) {
        Connection con= null;
        PreparedStatement ps =  null;
        ResultSet rs = null;
        String sql="";
           try {
              con = getConnection();
              sql = "insert into board values(board_num_seq.nextval,?,?,?,?,?,board_num_seq.nextval,0,0,0,sysdate)";
              ps = con.prepareStatement(sql);
              System.out.println(sql);
              ps.setString(1, bv.getBOARD_NAME());
              ps.setString(2, bv.getBOARD_PASS());
              ps.setString(3, bv.getBOARD_SUBJECT());
              ps.setString(4, bv.getBOARD_CONTENT());
              ps.setString(5, bv.getBOARD_FILE());
              ps.executeQuery();
           } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
           }finally {
              closeCon(con,ps,rs);
           }    
     }
	//게시판 글 목록(전체보기)
	public ArrayList<BoardVO> boardList(int startRow, int endRow) {
	   Connection con= null;
	   Statement st = null;
	   ResultSet rs = null; 
	   ArrayList<BoardVO> arr = new ArrayList<>();
	   String sql="";
	   try {
	     con = getConnection();
	     sql = "select * from (select rownum rn,aa.* from (select * from board order by board_num desc)aa) where rn>="+startRow+" and rn<="+endRow;
		 st = con.createStatement();
		 rs = st.executeQuery(sql);
		 while(rs.next()) {
			 BoardVO b = new BoardVO();
			 b.setBOARD_NUM(rs.getInt("BOARD_NUM"));
			 b.setBOARD_NAME(rs.getString("BOARD_NAME"));
			 b.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
			 b.setBOARD_DATE(rs.getDate("BOARD_DATE"));
			 b.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
			 arr.add(b);
	     }
	  } catch (Exception e) {
	    e.printStackTrace();
	  }finally {
	     closeCon(con,st,rs);
	  }
	  System.out.println(arr.size());
	  return arr;
	}
	//제목 눌렀을 때 상세보기
	public BoardVO boardView(int num) {
		   Connection con= null;
		   Statement st = null;
		   ResultSet rs = null;
		   BoardVO b = null;
		   String sql="";
		   try {
		     con = getConnection();
		     sql = "select * from board where BOARD_NUM="+num;
			 st = con.createStatement();
			 rs = st.executeQuery(sql);
			 if(rs.next()) {
				 b = new BoardVO();
				 b.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				 b.setBOARD_NAME(rs.getString("BOARD_NAME"));
				 b.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				 b.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				 b.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				 b.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				 b.setBOARD_FILE(rs.getString("BOARD_FILE"));
				 b.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				 b.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				 b.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				 b.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
		     }
		  } catch (Exception e) {
		    e.printStackTrace();
		  }finally {
		     closeCon(con,st,rs);
		  }
		  return b;
		}
	//댓글 쓰기
	   public void commentInsert(CommentVO cb) {
	       Connection con= null;
	       PreparedStatement ps =  null;
	       ResultSet rs = null;
	       String sql="";
	          try {
	             con = getConnection();
	             sql = "insert into commentboard (cnum,userid,regdate,msg,bnum) values(cnum_seq.nextval,?,sysdate,?,?)";
	             ps = con.prepareStatement(sql);
	             ps.setString(1, cb.getUserid());
	             ps.setString(2, cb.getMsg());
	             ps.setInt(3, cb.getBnum());
	             ps.executeUpdate();
	          } catch (Exception e) {
	            e.printStackTrace();
	          }finally {
	             closeCon(con,ps,rs);
	          }
	    }
	//닫아주는 것들
	private void closeCon(Connection con, PreparedStatement ps){
	      try {
	         if(con!=null)con.close();
	         if(ps!=null)ps.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	}
	private void closeCon(Connection con,Statement st, ResultSet rs){  
	   try {
	      if(con!=null)con.close();
	      if(st!=null)st.close();
	      if(rs!=null)rs.close();
	   } catch (SQLException e) {
	      e.printStackTrace();
	   }
	}
}
