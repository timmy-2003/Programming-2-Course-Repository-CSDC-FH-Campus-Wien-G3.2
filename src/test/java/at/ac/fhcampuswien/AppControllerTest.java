package at.ac.fhcampuswien;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppControllerTest {

    @Test
    public void getArticleCountTest(){
        AppController appController = new AppController();
        assertEquals(0,appController.getArticleCount());

    }


}
