import java.util.*;
public class pagealloc {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int t = -1;
	while(true)
	{
		t++;
		int n = input.nextInt(), m = input.nextInt();
		if(n == 0) break;
		System.out.println("Program "+(t+1));
		int[] data = new int[n];
		Arrays.fill(data,  -1);
		boolean[] old = new boolean[n];
		int ptr = 0;
		int fault = 0;
		for(int ii = 0; ii<m; ii++)
		{
			int page = input.nextInt();
			int free = -1;
			int good = -1;
			for(int i = 0; i<n; i++)
			{
				if(data[i] == page)
				{
					good = i;
					break;
				}
			}
			if(good != -1)
			{
				old[good] = false;
				System.out.println("Access page "+page+" in cell "+(good+1)+".");
				continue;
			}
			fault++;
			for(int i = 0; i<n; i++)
			{
				if(data[i] == -1)
				{
					free = i;
					break;
				}
			}
			if(free != -1)
			{
				System.out.println("Page "+page+" loaded into cell " + (free+1)+".");
				data[free] = page;
				continue;
			}
			else
			{
				while(true)
				{
					if(old[ptr])
					{
						old[ptr] = false;
						data[ptr] = page;
						System.out.println("Page "+page+" loaded into cell "+(ptr+1)+".");
						ptr++;
						ptr %= n;
						break;
					}
					else
					{
						old[ptr] = true;
						ptr = (ptr+1)%n;
					}
				}
			}
		}
		System.out.println("There are a total of "+fault+" page faults.");
		System.out.println();
	}
}
}