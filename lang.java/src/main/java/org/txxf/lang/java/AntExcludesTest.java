package org.txxf.lang.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AntExcludesTest
 *
 */
public class AntExcludesTest 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Pattern regexPattern = Pattern.compile("\\S+\\.class$|\\S+\\.jar$|^web\\.xml$|^ibm-web-bnd\\.xml$");
        Matcher[] matchers = new Matcher[3];
        matchers[0] = regexPattern.matcher("a.class");
        matchers[1] = regexPattern.matcher("web.xml");
        matchers[2] = regexPattern.matcher("ibm-web-bnd.xml");

        System.out.println("=====================================");
        for (Matcher matcher : matchers) {
	        System.out.println(matcher.find());
            System.out.println(matcher.matches());
	        System.out.println(matcher.group());	
        }

        
    }
}
