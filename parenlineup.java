import java.util.*;
public class parenlineup {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int T = input.nextInt();
	input.nextLine();
	for(int t = 0; t<T; t++)
	{
		System.out.println("Data set #" + (t+1)+":");
		String s = input.nextLine();
		String real = "";
		for(int i = 0; i<s.length(); i++)
			if(s.charAt(i) == '(' || s.charAt(i) == ')')
				real += s.charAt(i);
		s = real;
		int n = s.length();
		int depth = 0,  mmd = 0;
		boolean good = true;
		for(int i = 0; i<n; i++)
		{
			if(s.charAt(i) == '(') depth++;
			else depth--;
			if(depth < 0) good = false;
			mmd = Math.max(mmd,  depth);
		}
		if(depth != 0) good = false;
		if(!good)
		{
			for(int i = 0; i<n-1; i++) System.out.print(s.charAt(i)+" ");
			System.out.println(s.charAt(n-1));
			System.out.println("Parentheses do not match!\n");
			continue;
		}
		char[][] res = new char[mmd+1][2*n-1];
		for(char[] A : res) Arrays.fill(A,  ' ');
		for(int i = 0; i<n; i++) res[0][2*i] = s.charAt(i);
		int[] ds = new int[n];
		for(int i = 0; i<n; i++)
		{
			if(s.charAt(i) == '(') depth++;
			else depth--;
			ds[i] = depth;
			if(s.charAt(i) == ')')
			{
				int match = i - 1;
				while(s.charAt(match) == ')' || ds[match] != ds[i]+1) match--;
				int maxInside = 0;
				for(int j = match; j <i; j++) maxInside = Math.max(maxInside, ds[j]);
				for(int j = 1; j<=maxInside - depth; j++)
				{
					res[j][2*i] = res[j][2*match] = '|';
				}
				for(int j = 2*match + 1; j < 2*i; j++) res[maxInside-depth][j] = '_';
			}
		}
		for(char[] A : res) System.out.println(new String(A));
		System.out.println();
	}
}
}

