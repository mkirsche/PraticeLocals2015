import java.util.*;
public class gasprice {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int T = input.nextInt();
	for(int t = 0; t<T; t++)
	{
		String a = input.next(), b = input.next(), c = input.next();
		System.out.println("Gas Station #"+(t+1)+":");
		System.out.println("   Input:  "+a+" "+b+" "+c);
		int min = 200, max = 500;
		for(int i = 0; i<10; i++)
		{
			if(replace(a, i) >= min && replace(a, i) <= max)
			{
				a = a.replace('-', (char)('0' + i));
				min = replace(a, i)+1;
				break;
			}
		}
		for(int i = 0; i<10; i++)
		{
			if(replace(b, i) >= min && replace(b, i) <= max)
			{
				b = b.replace('-', (char)('0' + i));
				min = replace(b, i)+1;
				break;
			}
		}
		for(int i = 0; i<10; i++)
		{
			if(replace(c, i) >= min && replace(c, i) <= max)
			{
				c = c.replace('-', (char)('0' + i));
				min = replace(c, i)+1;
				break;
			}
		}
		System.out.println("   Output: "+a+" "+b+" "+c);
		System.out.println();
	}
}
static int replace(String s, int x)
{
	int res = 0;
	res += (s.charAt(0) == '-') ? x : s.charAt(0) - '0';
	res *= 10;
	res += (s.charAt(1) == '-') ? x : s.charAt(1) - '0';
	res *= 10;
	res += (s.charAt(2) == '-') ? x : s.charAt(2) - '0';
	return res;
}
}