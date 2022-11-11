package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    Student std;

    @BeforeEach
    void init (){
        std = new Student("tes",12);
    }

    @Test
    void getNilaiTimesTen() {
        Student student = new Student("Abed",20);
        int hasil = student.getNilaiTimesTen(); // testing this method
        assertEquals(200,hasil);
    }

    @Test
    void getNama() {
        assertEquals("tes",std.getNama());
    }

    @Test
    void setNama() {
    }

    @Test
    void getNilai() {
    }

    @Test
    void setNilai() {
    }

    @Test
    void cekNilai() {
        Student student = new Student("Abed",100);
        assertEquals("luar biasa",student.cekNilai());
        student = new Student("Abed",20);
        assertEquals("jelek",student.cekNilai());
        student = new Student("Abed",60);
        assertEquals("lumaya",student.cekNilai());
    }
}