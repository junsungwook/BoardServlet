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
	//수정시 비번 조회 한 후 수정하기
	   public boolean updateBoard(int BOARD_NUM,String BOARD_PASS,String BOARD_SUBJECT,String BOARD_CONTENT) {
	      Connection con =null;
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      Statement st = null;
	      String sql="";
	      boolean b=false;
	         try {
	        	 con=getConnection();
		            sql = "select BOARD_PASS from board where BOARD_NUM=?";
		            ps = con.prepareStatement(sql);
		            ps.setInt(1, BOARD_NUM);
		            rs = ps.executeQuery();
		            if(rs.next()) {
		            	if(rs.getString("BOARD_PASS").equals(BOARD_PASS)) {
		            		sql = "update board set BOARD_SUBJECT='"+BOARD_SUBJECT+"',BOARD_CONTENT='"+BOARD_CONTENT+"' where BOARD_NUM="+BOARD_NUM;
		            		st = con.createStatement();
		                    st.executeQuery(sql);
			            	b = true;
	            	}	 
	            }
	            System.out.println("dd:"+sql);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }finally {
	            closeCon(con,ps,rs);
	         }
			return b;
	   }
	//삭제시 비번 조회 한 후 삭제하기
	public boolean delBoard(int BOARD_NUM,String BOARD_PASS) {
	      Connection con =null;
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String sql="";
	      boolean b=false;
	         try {
	        	con=getConnection();
	            sql = "select BOARD_PASS from board where BOARD_NUM=?";
	            ps = con.prepareStatement(sql);
	            ps.setInt(1, BOARD_NUM);
	            rs = ps.executeQuery();
	            if(rs.next()) {
	            	if(rs.getString("BOARD_PASS").equals(BOARD_PASS)) {
	            		sql = "delete from board where BOARD_NUM=?";
		            	 PreparedStatement ps1 =con.prepareStatement(sql); 
		            	 ps1.setInt(1, BOARD_NUM);
		            	 ps1.executeUpdate();
		            	 b = true;
	            	}	 
	            }
	         } catch (Exception e) {
	            e.printStackTrace();
	         }finally {
	            closeCon(con,ps,rs);
	         }
			return b;
	   }
	//조회수 업데이트
	   public void updateReadCount(int board_num){
		    Connection con= null;
			PreparedStatement ps = null;
			int updateCount = 0;
			String sql="update board set BOARD_READCOUNT = "+
					"BOARD_READCOUNT+1 where BOARD_NUM = "+board_num;
			try{
				con = getConnection();
				ps=con.prepareStatement(sql);
				updateCount = ps.executeUpdate();
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			finally{
				closeCon(ps);
			}
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
	//답글 쓰기
	public void boardReInsert(BoardVO b) {
        Connection con= null;
        PreparedStatement ps =  null;
        ResultSet rs = null;
        int number = 0;
        String sql="";
           try {
              con = getConnection();
              //부모글
              int BOARD_NUM = b.getBOARD_NUM();
              int BOARD_RE_REF=b.getBOARD_RE_REF();
              int BOARD_RE_SEQ=b.getBOARD_RE_SEQ();
              int BOARD_RE_LEV=b.getBOARD_RE_LEV();
              ps=con.prepareStatement("select max(BOARD_RE_SEQ) from board where BOARD_RE_REF="+BOARD_RE_REF);
              rs=ps.executeQuery();
              if(rs.next()) {
           	   number = rs.getInt(1)+1;
              }
        	  BOARD_RE_SEQ=BOARD_RE_SEQ+1;
         	  BOARD_RE_LEV=BOARD_RE_LEV+1;
              
           	  sql = "insert into board values(board_num_seq.nextval,?,?,?,?,?,?,?,?,0,sysdate)";
              ps = con.prepareStatement(sql);
              ps.setString(1, b.getBOARD_NAME());
              ps.setString(2, b.getBOARD_PASS());
              ps.setString(3, b.getBOARD_SUBJECT());
              ps.setString(4, b.getBOARD_CONTENT());
              ps.setString(5, b.getBOARD_FILE());
              ps.setInt(6, BOARD_RE_REF);
              ps.setInt(7, BOARD_RE_LEV);
              ps.setInt(8, number);
              ps.executeQuery();
           } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
           }finally {
              closeCon(con,ps,rs);
           }
           
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
	     sql = "select * from (select rownum rn,aa.* from (select * from board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc)aa) where rn>="+startRow+" and rn<="+endRow;
		 st = con.createStatement();
		 rs = st.executeQuery(sql);
		 while(rs.next()) {
			 BoardVO b = new BoardVO();
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
			 arr.add(b);
	     }
	  } catch (Exception e) {
	    e.printStackTrace();
	  }finally {
	     closeCon(con,st,rs);
	  }
	  return arr;
	}
	//댓글 뿌리기
   public ArrayList<CommentVO> commentList(int bnum) {
       Connection con= null;
       PreparedStatement ps =  null;
       ResultSet rs = null;
       ArrayList<CommentVO> arr = new ArrayList<>();
       String sql="";
          try {
             con = getConnection();
             sql = "select * from commentboard where bnum=? order by cnum desc";
             ps = con.prepareStatement(sql);
             ps.setInt(1, bnum);
             rs = ps.executeQuery();
             while(rs.next()) {
            	 CommentVO cb = new CommentVO();
            	 cb.setCnum(rs.getInt("cnum"));
            	 cb.setUserid(rs.getString("userid"));
            	 cb.setRegdate(rs.getString("regdate"));
            	 cb.setMsg(rs.getString("msg"));
            	 cb.setBnum(rs.getInt("bnum")); 
 				 arr.add(cb);
             }
          } catch (Exception e) {
            e.printStackTrace();
          }finally {
             closeCon(con,ps,rs);
          }
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
	private void closeCon(PreparedStatement ps){
	      
	      try {
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
