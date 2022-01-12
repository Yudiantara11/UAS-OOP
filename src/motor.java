import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

public class motor {
    private int id;
    private String merk_motor = null;
    private String nama_motor = null;
    private String warna = null;
    private String harga = null;
    private String kode_motor = null;
    public Object conn;

    public motor(int inputId, String inputmerk_motor, String inputnama_motor, String inputwarna, String inputHarga, String inputkode_motor) {
        this.id = inputId;
        this.merk_motor = inputmerk_motor;
        this.nama_motor = inputnama_motor;
        this.warna = inputwarna;
        this.harga = inputHarga;
        this.kode_motor = inputkode_motor;
    }



    public int getId(){
        return id;
    }

    public String getMerk_motor(){
        return merk_motor;
    }

    public String getNama_motor(){
        return nama_motor;
    }

    public String getWarna(){
        return warna;
    }

    public String getHarga(){
        return harga;
    }

    public String getKode_motor(){
        return kode_motor;
    }


    public void setId(String text) {
    }


    public void setHarga() {
    }


    public void setHarga(String text) {
    }


    public void setHarga(double parseDouble) {
    }


    public void setMerk_motor(String text) {
    }


    public void setNama_motor(String text) {
    }

    public void setWarna(String text) {
    }

    public void setKode_motor(String text) {
    }



}