import java.util.*;
public class fildir {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int t = -1;
	Node at = new Node("", null);
	while(true)
	{
		String s = input.next();
		if(s.equals("init"))
		{
			t++;
			System.out.println("File system #" + (t+1)+":\n");
			at = new Node("/home", null);
		}
		else if(s.equals("end")) break;
		else if(s.equals("mkfile"))
		{
			at.addFile(input.next());
		}
		else if(s.equals("mkdir"))
		{
			at.addSubDir(input.next());
		}
		else if(s.equals("ls"))
		{
			System.out.println("Listing for " + at.name+":");
			for(String ss : at.subdirs.keySet())
			{
				System.out.println(ss);
			}
			if(at.subdirs.size() == 0) System.out.println("There are no files/directories.");
			System.out.println();
		}
		else if(s.equals("cd"))
		{
			StringTokenizer str = new StringTokenizer(input.next(), "/");
			while(str.hasMoreTokens())
			{
				String token = str.nextToken();
				if(token.equals("..")) at = at.parent;
				else at = at.subdirs.get(token);
			}
		}
	}
}
static class Node
{
	String name;
	Node parent;
	TreeMap<String, Node> subdirs;
	public Node(String s, Node p)
	{
		name = s;
		parent = p;
		subdirs = new TreeMap<String, Node>();
	}
	void addFile(String s)
	{
		subdirs.put(s, null);
	}
	void addSubDir(String s)
	{
		subdirs.put(s,  new Node(name+'/'+s, this));
	}	
}
}