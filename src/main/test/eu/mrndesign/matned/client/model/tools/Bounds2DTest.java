package eu.mrndesign.matned.client.model.tools;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Bounds2DTest {

    @Test
    void isSquareInBounds(){
        Vector2D vector = new Vector2D(0,1);
        Bounds2D bounds1 = new Bounds2D(vector, 5,5, new Point2D(0,0));
        Bounds2D bounds2 = new Bounds2D(vector, 2,2, new Point2D(1,1));

        assertTrue(bounds2.isIn(bounds1));
    }

    @Test
    void isSquareInBounds2(){
        Vector2D vector = new Vector2D(0,1);
        Bounds2D bounds1 = new Bounds2D(vector,5,5, new Point2D(0,0));
        Bounds2D bounds2 = new Bounds2D(vector,2,2, new Point2D(-1,1));

        assertTrue(bounds2.isIn(bounds1));
    }

    @Test
    void isTouched(){
        Vector2D vector = new Vector2D(0,1);
        Bounds2D box = new Bounds2D(vector, 10, 10, new Point2D(0, 0));
        Bounds2D touch = new Bounds2D(vector, 4, 4, new Point2D(5, 0));
        assertTrue(box.touchedBy(touch), "try 1");

        touch.setCenter(4,-5);
                assertTrue(box.touchedBy(touch), "try 1");
    }

    @ParameterizedTest
    @CsvSource({
            "3,8, 0,11, 5,10, 6,2, 2,2, 6,4",
            "845.2942769857734,603.1531305628457, -15.979279783347234,-36.66964163453892, -36.66964163453892,15.979279783347234, 809.2875789334909,509.55944572572236, 6.588801632815572,13.475447786378265, 13.475447786378265,-6.588801632815572",
            "0,0, 0,5, 2,0, 0,4, 0,-5, -2,0"
    })
    void  anotherTouchedTest(double aCenterX, double aCenterY, double vAHX, double vAHY, double aPerpX, double aPerpY, double bCenterX, double bCenterY, double vBHX, double vBHY, double bPerpX, double bPerpY) {
        Point2D aCenter = new Point2D(aCenterX, aCenterY);
        Vector2D vAHeight = new Vector2D(aCenter, new Point2D(vAHX,vAHY));
        Vector2D aPerp = new Vector2D(aCenter, new Point2D(aPerpX,aPerpY));
        Bounds2D a = new Bounds2D(vAHeight, vAHeight.magnitude(), aPerp.magnitude(), aCenter);

        Point2D bCenter = new Point2D(bCenterX, bCenterY);
        Vector2D vBHeight = new Vector2D(bCenter, new Point2D(vBHX,vBHY));
        Vector2D bPerp = new Vector2D(bCenter, new Point2D(bPerpX,bPerpY));
        Bounds2D b = new Bounds2D(vBHeight, vBHeight.magnitude(), bPerp.magnitude(), bCenter);

        assertTrue(b.touchedBy(a));
        assertTrue(a.touchedBy(b));
    }

    @ParameterizedTest
    @CsvSource ({
            "3,15, 0,15, 5,15, 6,2, 2,2, 6,4"
    })
    void  notTouchedTest(double aCenterX, double aCenterY, double vAHX, double vAHY, double aPerpX, double aPerpY, double bCenterX, double bCenterY, double vBHX, double vBHY, double bPerpX, double bPerpY) {
        Point2D aCenter = new Point2D(aCenterX, aCenterY);
        Vector2D vAHeight = new Vector2D(aCenter, new Point2D(vAHX,vAHY));
        Vector2D aPerp = new Vector2D(aCenter, new Point2D(aPerpX,aPerpY));
        Bounds2D a = new Bounds2D(vAHeight, vAHeight.magnitude(), aPerp.magnitude(), aCenter);

        Point2D bCenter = new Point2D(bCenterX, bCenterY);
        Vector2D vBHeight = new Vector2D(bCenter, new Point2D(vBHX,vBHY));
        Vector2D bPerp = new Vector2D(bCenter, new Point2D(bPerpX,bPerpY));
        Bounds2D b = new Bounds2D(vBHeight, vBHeight.magnitude(), bPerp.magnitude(), bCenter);

        boolean condition = a.touchedBy(b);
        assertFalse(condition);
    }






}