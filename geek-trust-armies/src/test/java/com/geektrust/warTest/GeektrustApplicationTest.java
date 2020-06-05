package com.geektrust.warTest;

import com.geektrust.war.army.Army;
import com.geektrust.war.army.ArmouryBuilder;
import com.geektrust.war.army.Weapon;

import java.util.Map;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class GeektrustApplicationTest {

    @Parameter(0)
    public Integer horseCount;
    @Parameter(1)
    public Integer elephantCount;
    @Parameter(2)
    public Integer tankCount;
    @Parameter(3)
    public Integer gunCount;
    @Parameter(4)
    public String resultString;

    public static Map<Weapon, Integer> lengaburuArmoury = ArmouryBuilder.createArmoury(100,50,10,5);
    public static Army lengaburuArmy = new Army(2.0,lengaburuArmoury);

    public static Map<Weapon, Integer> falcorinaArmoury = ArmouryBuilder.createArmoury(300,200,40,20);
    public static Army falcorinaArmy = new Army(1.0,falcorinaArmoury);


    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { 100, 101, 20, 5, "WINS 52H 50E 10AT 3SG"},
                { 150, 96, 26, 8, "WINS 75H 50E 10AT 5SG"},
                { 250, 50, 20, 15, "LOSES 100H 38E 10AT 5SG"}};
        return Arrays.asList(data);
    }

    @Test
    public void testWarResult()  throws Exception{
        Map<Weapon, Integer> armoury = ArmouryBuilder.createArmoury(horseCount,elephantCount,tankCount,gunCount);
        Army deployedArmy = falcorinaArmy.deployArmy(armoury);
        assertEquals("Result", resultString, lengaburuArmy.defend(deployedArmy).getResultString());
    }
}
