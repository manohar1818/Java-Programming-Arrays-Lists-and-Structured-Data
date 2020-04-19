package java_assignments.coursera2.week3;


import java.util.*;

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {

        LogAnalyzer logAnalyzer=new LogAnalyzer();
        logAnalyzer.readFile("short-test_log");
        logAnalyzer.printAll();
        logAnalyzer.uniqueIPVisitsOnDay("jsj");
    }

    public void testUniqueIp()
    {
        LogAnalyzer logAnalyzer1=new LogAnalyzer();
        logAnalyzer1.readFile("short-test_log");
        int uniqueIps=logAnalyzer1.countUniqueIps();
        System.out.println("uniqu ips are "+uniqueIps);
        logAnalyzer1.printAllHigherThanNum(200);
    }

    public void testUniqueVisitsADay()
    {
        LogAnalyzer logAnalyzer2=new LogAnalyzer();
        logAnalyzer2.readFile("weblog-short_log");
        ArrayList<String>uniqueIpOfDay=logAnalyzer2.uniqueIPVisitsOnDay("Sep 14");
        for(int i=0;i<uniqueIpOfDay.size();i++)
            System.out.println(uniqueIpOfDay.get(i));
    }


    public void testCountUniqueIPsInRange()
    {
        LogAnalyzer log=new LogAnalyzer();
        log.readFile("short-test_log");
        int range=log.countUniqueIPsInRange(200,299);
        System.out.println("range of ip address in 200 to 299 are "+range);
    }

    public void testCountsPerIp()
    {
        LogAnalyzer logAnalyzer=new LogAnalyzer();
        logAnalyzer.readFile("weblog2-short_log");
        HashMap<String,Integer>counts=logAnalyzer.counVisitsPerIp();
        System.out.println(counts);
        int maxIpVisits=logAnalyzer.mostNumberVisitsByIP();
        System.out.println("most number of visists by ip is "+maxIpVisits);
        ArrayList<String>frequentIps=logAnalyzer.iPsMostVisits();
        System.out.println(frequentIps);
    }

    public void testIPForDays() {

        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipForDays = analyzer.ipsForDays();
        for (String w : ipForDays.keySet())
            System.out.println(w + "\t\t" + ipForDays.get(w));

        String maxIpsDay = analyzer.dayWithMostIPVisits(ipForDays);
        System.out.println("the day most visited is " + maxIpsDay);
        ArrayList<String> mostAccessedIpInaDay = analyzer.iPsWithMostVisitsOnDay(ipForDays, "Sep 30");
        System.out.println("Most Accessed ips on Sep 30 are ");
        for (int i=0;i<mostAccessedIpInaDay.size();i++)
            System.out.println(mostAccessedIpInaDay.get(i));

    }



    public static void main(String args[])
    {
        Tester test=new Tester();
        test.testLogAnalyzer();
        test.testUniqueIp();
        test.testUniqueVisitsADay();
        test.testCountUniqueIPsInRange();
        test.testCountsPerIp();
        test.testIPForDays();

    }



}