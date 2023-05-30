import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
//    private String str;
//    private long id;
    private boolean otv;
    ConnectionBD connectionBD = new ConnectionBD();

//    public Login(long id,String str) {
//        this.str = str;
//        this.id = id;
//    }

    public boolean reg(long id,String str) throws SQLException, ClassNotFoundException {
        String [] mas = str.split(" ");
        if ((mas.length<3)&&(mas[0].equals(mas[1]))){
            String query = "insert into users values ('"+id+"','"+mas[0]+"')";
            connectionBD.setResult(query);
            otv = true;
        }else{
            otv = false;
        }
        return otv;
    }

    public boolean enter(Long id, String str) throws SQLException, ClassNotFoundException {
        String query = "select password from users where id ='"+id+"';";
        ResultSet rs = connectionBD.getResult(query);
        rs.next();
        String psw = rs.getString(1);
        if (str.equals(psw)){
            otv = true;
        }else{
            otv = false;
        }
        return otv;
    }

    public boolean enterID(String str) throws SQLException, ClassNotFoundException {
        String [] mas = str.split(" ");
        Long id = Long.parseLong(mas[0]);
        System.out.println(id);
        String query = "select password from users where id ='"+id+"';";
        ResultSet rs = connectionBD.getResult(query);
        rs.next();
        String psw = rs.getString(1);
        if (mas[1].equals(psw)){
            otv = true;
        }else{
            otv = false;
        }
        return otv;
    }
}
