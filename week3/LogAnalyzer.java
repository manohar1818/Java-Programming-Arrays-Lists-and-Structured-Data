package java_assignments.coursera2.week3;


import java.util.*;
import edu.duke.*;
public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    public LogAnalyzer()
    {
        records=new ArrayList<LogEntry>();
    }

    public void readFile(String filename)
    {
        FileResource file=new FileResource(filename);
        for(String line:file.lines())
        {
            LogEntry log=WebLogParser.parseEntry(line);
            records.add(log);
        }
    }

    public void printAll()
    {
        System.out.println("records are:");
        for(int i=0;i<records.size();i++)
        {
            System.out.println(records.get(i));
        }
    }

    public int countUniqueIps()
    {
        ArrayList<String> uniqueIps=new ArrayList<String>();
        for(LogEntry rec:records)
        {
            String ipaddress=rec.getIpAddress();
            if(!uniqueIps.contains(ipaddress))
                uniqueIps.add(ipaddress);
        }
        return  uniqueIps.size();
    }


    public void printAllHigherThanNum(int num)
    {
        for(LogEntry le:records)
        {
            if(le.getStatusCode()>num)
                System.out.println(le);
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday)
    {
        ArrayList<String>uniqueIpVisits=new ArrayList<String>();
        for(LogEntry le:records)
        {
            if(le.getAccessTime().toString().contains(someday)) {
                if (!uniqueIpVisits.contains(le.getIpAddress()))
                    uniqueIpVisits.add(le.getIpAddress());
            }
        }
        return uniqueIpVisits;
    }
    public int countUniqueIPsInRange(int low,int high)
    {
        ArrayList<String>uniqueIps=new ArrayList<String>();
        for(LogEntry le:records)
        {
            if(le.getStatusCode()>=low && le.getStatusCode()<=high)
                if(!uniqueIps.contains(le.getIpAddress()))
                    uniqueIps.add(le.getIpAddress());

        }
        return uniqueIps.size();
    }

    public HashMap<String,Integer> counVisitsPerIp()
    {
        HashMap<String,Integer>counts=new HashMap<String,Integer>();
        for(LogEntry logEntry:records)
        {
            String ipAddress=logEntry.getIpAddress();
            if(!counts.containsKey(ipAddress))
                counts.put(ipAddress,1);
            else
                counts.put(ipAddress,counts.get(ipAddress)+1);
        }
        return counts;
    }

    public int mostNumberVisitsByIP()
    {
        int maxCount=0;
        HashMap<String,Integer> counts=counVisitsPerIp();
        for(String w:counts.keySet()){
            if(counts.get(w)>maxCount)
                maxCount=counts.get(w);
        }
        return maxCount;
    }


    public ArrayList<String> iPsMostVisits()
    {
        ArrayList<String>frequentIps=new ArrayList<String>();
        HashMap<String,Integer>counts=counVisitsPerIp();
        int maxCount=mostNumberVisitsByIP();
        for(String w:counts.keySet())
        {
            if(counts.get(w)==maxCount)
                frequentIps.add(w);
        }
        return frequentIps;
    }

    public ArrayList<String> ipVisitsOnDay(String someday)
    {
        ArrayList<String>ipVisits=new ArrayList<String>();
        for(LogEntry le:records)
        {
            if(le.getAccessTime().toString().contains(someday)) {
                ipVisits.add(le.getIpAddress());
            }
        }
        return ipVisits;
    }

    public HashMap<String,ArrayList<String>> ipsForDays()
    {
        HashMap<String,ArrayList<String>>ipsFordays=new HashMap<String,ArrayList<String>>();
        for(LogEntry le:records)
        {
            String dateAndTime=le.getAccessTime().toString();
            String date=dateAndTime.substring(4,10);
            if(!ipsFordays.containsKey(date)) {
                ArrayList<String> newips = ipVisitsOnDay(date);
                ipsFordays.put(date, newips);
            }
        }
        return ipsFordays;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>>ipForDays)
    {
        String maxIpsDay="";
        int maxIpCount=0;
        for(String w:ipForDays.keySet())
        {
            if(ipForDays.get(w).size()>maxIpCount)
            {
                maxIpCount=ipForDays.get(w).size();
                maxIpsDay=w;
            }
        }
        return maxIpsDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>>ipForDays, String someday)
    {
        ArrayList<String>mostAccessedIps=new ArrayList<String>();
        HashMap<String ,Integer>counts=new HashMap<String,Integer>();
        ArrayList<String>ips=ipForDays.get(someday);
        for(int i=0;i<ips.size();i++)
        {
            if(!counts.containsKey(ips.get(i)))
                counts.put(ips.get(i),1);

            else
                counts.put(ips.get(i),i+1);
        }

        for(String w:counts.keySet())
        {
            if(counts.get(w)>=2)
                mostAccessedIps.add(w);
        }
        return mostAccessedIps;
    }
}