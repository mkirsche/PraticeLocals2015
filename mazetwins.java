import java.util.*;
public class mazetwins {
	static int n;
	static char[][] grid;
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int T = input.nextInt();
	HashSet<Character> valida = new HashSet<Character>(), validb = new HashSet<Character>();
	valida.add('3');
	valida.add('1');
	validb.add('3');
	validb.add('2');
	for(int t = 0; t<T; t++)
	{
		n = input.nextInt();
		grid = new char[n][n];
		for(int i = 0; i<n; i++) grid[i] = input.next().toCharArray();
		int[][] mack = bfs('M', valida), zack = bfs('Z', validb); 
		int min = oo;
		int bmi = -1, bmj = -1, bzi = -1, bzj = -1;
		for(int i = 0; i<n; i++)
			for(int j = 0; j<n; j++)
				for(int k = 0; k<4; k++)
				{
					int ni = i + di[k], nj = j + dj[k];
					if(ni < 0 || nj < 0 || ni >= n || nj >= n) continue;
					int cur = mack[i][j] + zack[ni][nj];
					if(cur < min)
					{
						min = cur;
						bmi = i;
						bmj = j;
						bzi = ni;
						bzj = nj;
					}
				}
		System.out.println("Maze #"+(t+1)+":");
		Stack<String> ms = new Stack<String>();
		Stack<String> zs = new Stack<String>();
		int ati = bmi, atj = bmj;
		for(int x = 0; x < mack[bmi][bmj]; x++)
		{
			for(int k = 0; k<4; k++)
			{
				int ni = ati + di[k];
				int nj = atj + dj[k];
				if(ni < 0 || nj < 0 || ni >= n || nj >= n)
				{
					continue;
				}
				if(mack[ni][nj] == mack[ati][atj] - 1)
				{
					ms.add(dirs[(k+2)%4]);
					ati = ni;
					atj = nj;
					break;
				}
			}
		}
		ati = bzi;
		atj = bzj;
		for(int x = 0; x < zack[bzi][bzj]; x++)
		{
			for(int k = 0; k<4; k++)
			{
				int ni = ati + di[k];
				int nj = atj + dj[k];
				if(ni < 0 || nj < 0 || ni >= n || nj >= n)
				{
					continue;
				}
				if(zack[ni][nj] == zack[ati][atj] - 1)
				{
					zs.add(dirs[(k+2)%4]);
					ati = ni;
					atj = nj;
					break;
				}
			}
		}
		while(!ms.isEmpty())
		{
			System.out.println("   Mack move "+ms.pop());
		}
		while(!zs.isEmpty())
		{
			System.out.println("   Zack move "+zs.pop());
		}
		System.out.println("   Total number of moves: "+min+"\n");
	}
}
static String[] dirs = new String[]{"south", "east", "north", "west"};
static int[] di = new int[]{1, 0, -1, 0};
static int[] dj = new int[]{0, 1, 0, -1};
static int oo = 987654;
static int[][] bfs(char start, HashSet<Character> valid)
{
	int si = 0, sj = 0;
	for(int i = 0; i<n;i++)
		for(int j = 0; j<n; j++)
		{
			if(grid[i][j] == start)
			{
				si = i;
				sj = j;
			}
		}
	Queue<Integer> qi = new LinkedList<Integer>(), qj = new LinkedList<Integer>();
	int[][] res = new int[n][n];
	for(int[] A : res) Arrays.fill(A,  oo);
	qi.add(si);
	qj.add(sj);
	res[si][sj] = 0;
	while(!qi.isEmpty())
	{
		int ati = qi.poll(), atj = qj.poll();
		for(int k = 0; k<4; k++)
		{
			int ni = ati + di[k], nj = atj + dj[k];
			if(ni < 0 || nj < 0 || ni >= n || nj >= n || !valid.contains(grid[ni][nj])) continue;
			if(res[ni][nj] <= 1 + res[ati][atj]) continue;
			res[ni][nj] = 1 + res[ati][atj];
			qi.add(ni);
			qj.add(nj);
		}
	}
	return res;
}
}

