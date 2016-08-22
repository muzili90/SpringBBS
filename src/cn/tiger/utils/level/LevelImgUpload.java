package cn.tiger.utils.level;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LevelImgUpload {
	
	private String dbDriver;
    private String dbURL;
    private String dbUser;
    private String dbPassword;
    private Connection con;
    private PreparedStatement ps;
    
    public LevelImgUpload(){
    	
    	dbDriver = "com.mysql.jdbc.Driver";
        dbURL = "jdbc:mysql://localhost:3306/myspringbbssampledb";
        dbUser = "myspringbbs";
        dbPassword = "myspringbbs";
        initDB();
        
    }
    
    public LevelImgUpload(String strDriver, String strURL,
            String strUser, String strPwd){
    	
    	dbDriver = strDriver;
        dbURL = strURL;
        dbUser = strUser;
        dbPassword = strPwd;
        initDB();
        
    }
    
    public void initDB() {
        try {
            // Load Driver
            Class.forName(dbDriver).newInstance();
            // Get connection
            con = DriverManager.getConnection(dbURL,
                    dbUser, dbPassword);           
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean storeImg(String strFile) throws Exception {
        boolean written = false;
        if (con == null)
            written = false;
        else {
            int id = 0;
            File file = new File(strFile);
            FileInputStream fis = new FileInputStream(file);
           
            try {              
                ps = con.prepareStatement("SELECT MAX(id) FROM community_level");
                ResultSet rs = ps.executeQuery();
               
                if(rs != null) {
                    while(rs.next()) {
                        id = rs.getInt(1)+1;
                    }
                } else {       
                    return written;
                }
               
                ps = con.prepareStatement("insert "
                        + "into community_level(id,img) values (?,?)");
                ps.setInt(1, id);
                ps.setBinaryStream(2, fis, (int) file.length());
                ps.executeUpdate();
               
                written = true;
            } catch (SQLException e) {
                written = false;
                System.out.println("SQLException: "
                        + e.getMessage());
                System.out.println("SQLState: "
                        + e.getSQLState());
                System.out.println("VendorError: "
                        + e.getErrorCode());
                e.printStackTrace();
            } finally {            
                ps.close();
                fis.close();
                // close db con
                con.close();
            }
        }
        return written;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String imgFile="filePathAndName";
		String imgFile="E:\\Program Files\\muzili\\enjoy good mood\\我的社交网络\\SpringBBSSample\\levelImg\\level9.fw.png";
	
        boolean flag = false;
        LevelImgUpload sp = new LevelImgUpload();
        try {
            flag = sp.storeImg(imgFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag) {
            System.out.println("Picture uploading is successful.");
        } else {
            System.out.println("Picture uploading is failed.");
        }
	}
}
