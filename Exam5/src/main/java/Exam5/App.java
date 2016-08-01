package Exam5;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String uri = "http://files.saas.hand-china.com/java/target.pdf";
        Geta get = new Geta(uri);
        get.start();
    }
}

