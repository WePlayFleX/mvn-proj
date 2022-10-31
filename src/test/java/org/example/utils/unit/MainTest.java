package org.example.utils.unit;

import org.example.utils.IOUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    public static final String WRITE_THIS = "write this";
    private static final String RES_PATH = "src\\test\\resources\\";
    private static File file;

    @BeforeAll
    public static void singleInit() throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateFormat = format.format(new Date());
        String fileName = "dummy_" + dateFormat;
        file = new File(RES_PATH + fileName);
        System.out.println("Dummy file is "+ ( file.createNewFile() ? "created": "not created"));
    }

    @BeforeEach
    public void init(){
        System.out.println("Init before each");
    }


    @Test
    public void m1(){
        System.out.println("test1");
    }

    @Test
    public void m2(){
        System.out.println("test2");
    }

    @Test
    public void m3(){
        System.out.println("test3");
    }

    @Test
    public void m4(){
        System.out.println("test4");
        String data = null;
        assertNull(data);
    }

    @Test
    public void testReadFile(){
        String content = IOUtils.readFile("src\\test\\resources\\test.txt");
        assertEquals("read me", content);

        assertNull(IOUtils.readFile("src\\test\\resources\\test_123.txt"));
    }

    @Test
    public void testWriteFile(){
        String content = "write this";

        String path = "src\\test\\resources\\output.txt";
        IOUtils.writeFile(path, content);
        IOUtils.writeFile(path, content, false);

        String actual = IOUtils.readFile(path);

        assertEquals(content, actual);

        String extra = " extra";
        IOUtils.writeFile(path, extra, true);

        actual = IOUtils.readFile(path);
        assertEquals(content + extra, actual);

    }

    @Test
    public void testWriteDummyFile(){
        IOUtils.writeFile(file.getPath(), WRITE_THIS);
        IOUtils.writeFile(file.getPath(), WRITE_THIS, false);

        String actual = IOUtils.readFile(file.getPath());

        assertEquals(WRITE_THIS, actual);
    }


    @Test()
    public void testWriteFilePathError(){
        String content = "write this";

        String path = "src2\\test\\resources\\output.txt";

        Throwable exception = assertThrows(RuntimeException.class,
                ()->{IOUtils.writeFile(path, content);} );
    }

    @AfterAll
    public static void singleFinalization(){
        if(file.exists()){
            System.out.println("Dummy file is "+ ( file.delete() ? "deleted": "not deleted"));
        }
    }

    @AfterEach
    public void finalizationEach(){
        System.out.println("Finalization after each");
    }

}
