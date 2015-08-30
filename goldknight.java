import java.util.*;
public class goldknight {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int T = input.nextInt();
	for(int t = 0; t<T; t++)
	{
	int n = input.nextInt(), r1 = input.nextInt()-1, c1 = input.nextInt()-1, r2 = input.nextInt()-1, c2 = input.nextInt()-1;
	int[][] ds = new int[n][n];
	for(int[] A : ds) Arrays.fill(A,  99999);
	ds[r1][c1] = 0;
	Queue<Integer> qi = new LinkedList<Integer>(), qj = new LinkedList<Integer>();
	qi.add(r1);
	qj.add(c1);
	int[] di = new int[]{2, 2, -1, -1, 1, 1, -2, -2};
	int[] dj = new int[]{1, -1, 2, -2, -2, 2, 1, -1};
	while(!qi.isEmpty())
	{
		int ati = qi.poll(), atj = qj.poll();
		for(int k = 0; k<8; k++)
		{
			int ni = ati + di[k], nj = atj + dj[k];
			if(ni < 0 || nj < 0 || ni >=n || nj >= n) continue;
			if(ds[ni][nj] > 1 + ds[ati][atj])
			{
				ds[ni][nj] = 1 + ds[ati][atj];
				qi.add(ni);
				qj.add(nj);
			}
		}
	}
	System.out.println("Case #" + (t+1)+": "+ds[r2][c2]+"\n");
	}
}
}
