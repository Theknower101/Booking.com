package utils;
import java.util.Random;
public class TestDataGenerator {
    static Random rand=new Random();
	public static String getCountryRandomly() {
		String [] names= {"Amman","Egypt","Cairo","Jeddah"};
		int countryIndex=rand.nextInt(names.length);
		return names[countryIndex];
	}

}
