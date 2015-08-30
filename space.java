import java.util.*;
public class space {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int t = -1;
	while(true)
	{
		t++;
		n = input.nextInt();
		if(n == 0) break;
		set = new HashSet<String>();
		for(int i = 0; i<n; i++) set.add(input.next());
		int m = input.nextInt();
		System.out.println("Data set #" + (t+1)+":");
		for(int i = 0; i<m; i++)
		{
			s = input.next();
			if(set.contains(s))
			{
				System.out.println("     "+s+" --- " + "the word is in dictionary");
			}
			else
			{
				memo = new HashMap<Integer, ArrayList<String>>();
				ArrayList<String> list = can(0);
				boolean found = list != null;
				if(found)
				{
					System.out.println("     "+s+" --- "+"the word is concatenation of");
					for(String ss : list)
					{
						System.out.println("          "+ss);
					}
				}
				if(!found) System.out.println("     "+s+" --- "+"misspelled word");
			}
		}
		System.out.println();
	}
}
static HashSet<String> set;
static int n;
static HashMap<Integer, ArrayList<String>> memo;
static String s;
static ArrayList<String> can(int at)
{
	if(at == s.length()) return new ArrayList<String>();
	if(memo.containsKey(at)) return memo.get(at);
	ArrayList<String> res = null;
	for(String ss : set)
	{
		if(ss.length() + at > s.length()) continue;
		if(s.substring(at,  at+ss.length()).equals(ss) && can(at + ss.length()) != null)
		{
			ArrayList<String> cur = can(at + ss.length());
			res = new ArrayList<String>();
			res.add(ss);
			for(String sss : cur) res.add(sss);
			break;
		}
	}
	memo.put(at,  res);
	return res;
}
}

