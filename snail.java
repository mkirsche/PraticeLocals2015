import java.util.*;
public class snail {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int t = -1;
	while(true)
	{
		int n = input.nextInt(), m = input.nextInt();
		if(n == 0) break;
		t++;
		System.out.println("Matrix #" + (t+1)+":");
		System.out.println("Original:");
		char[][] data = new char[n][m];
		for(int i = 0; i<n; i++)
		{
			for(int j = 0; j<m; j++)
			{
				data[i][j] = input.next().charAt(0);
				System.out.print(data[i][j] + (j == m - 1 ? "" : " "));
			}
			System.out.println();
		}
		System.out.println("Snail:");
		int[] di = new int[]{0, 1, 0, -1};
		int[] dj = new int[]{1, 0, -1, 0};
		int dir = 0;
		int ati = 0, atj = 0;
		int count = 0;
		boolean[][] used = new boolean[n][m];
		while(count < n*m)
		{
			System.out.print(data[ati][atj]);
			used[ati][atj] = true;
			count++;
			int ni = ati + di[dir];
			int nj = atj + dj[dir];
			if(ni < 0 || nj < 0 || ni >= n || nj >= m || used[ni][nj])
			{
				dir++;
				dir %= 4;
				System.out.println();
				ati += di[dir];
				atj += dj[dir];
			}
			else
			{
				ati = ni;
				atj = nj;
			}
		}
		System.out.println();
	}
}
}