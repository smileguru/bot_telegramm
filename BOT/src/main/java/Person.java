import java.sql.SQLException;

public class Person {
    private long id;
    private String mas[];

    public Person(long id,String str) throws SQLException, ClassNotFoundException {
        ConnectionBD connectionBD = new ConnectionBD();
        this.id = id;
        mas = str.split(" ");
        int d = 5;
        String query = "insert into person values ('"+id;
        if (mas.length<5){
            d = mas.length;
        }
        for (int i =0; i<d;i++){
            query = query+"','"+mas[i];
        }
        query = query +"')";
        connectionBD.setResult(query);
    }
}
