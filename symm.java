import java.util.*;
public class symm {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int t = -1;
	while(true)
	{
		t++;
		int n = input.nextInt();
		if(n == 0) break;
		char[][] data = new char[n][n];
		for(int i = 0; i<n; i++)
		{
			for(int j = 0; j<n; j++)
			{
				data[i][j] = input.next().charAt(0);
			}
		}
		System.out.println("Input matrix #" + (t+1)+":");
		for(int i = 0; i<n; i++)
		{
			for(int j = 0; j<n; j++)
			{
				System.out.print(data[i][j]+(j == n-1 ? "" : " "));
			}
			System.out.println();
		}
		int K = input.nextInt();
		for(int k = 0; k<K; k++)
		{
			int x = input.nextInt();
			System.out.println("Symmetric diagonals " + x+":");
			int ati = 0, atj = 0 + x - 1;
			while(ati < n && atj < n)
			{
				System.out.print(data[ati][atj]);
				ati++;
				atj++;
				if(ati < n && atj < n) System.out.print(" ");
			}
			System.out.println();
			if(x > 1)
			{
				ati = x - 1;
				atj = 0;
				while(ati < n && atj < n)
				{
					System.out.print(data[ati][atj]);
					ati++;
					atj++;
					if(ati < n && atj < n) System.out.print(" ");
				}
				System.out.println();
			}
		}
		System.out.println();
	}
}
}