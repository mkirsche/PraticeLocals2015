import java.util.*;
public class magicbeans {
	static long mod = (long)(1e9+7);
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int T = input.nextInt();
	for(int t = 0; t<T; t++)
	{
		int n = input.nextInt(), dx = input.nextInt(), dy = input.nextInt();
		int[] xs = new int[n], ys = new int[n];
		for(int i = 0; i<n; i++)
		{
			xs[i] = input.nextInt();
			ys[i] = input.nextInt();
		}
		int half = n/2;
		HashMap<String, Long> map = new HashMap<String, Long>();
		for(int i = 0; i<(1<<half); i++)
		{
			long nx = 0, ny = 0;
			for(int j = 0; j<half; j++)
			{
				if((i & (1<<j)) > 0)
				{
					nx += xs[j];
					ny += ys[j];
				}
			}
			String key = nx+" "+ny+" "+Integer.bitCount(i);
			if(map.containsKey(key)) map.put(key,  map.get(key)+1);
			else map.put(key,  1L);
		}
		long res = 0;
		for(int i = 0; i<(1<<(n-half)); i++)
		{
			long nx = 0, ny = 0;
			for(int j = 0; j<n-half; j++)
			{
				if((i & (1<<j)) > 0)
				{
					nx += xs[half+j];
					ny += ys[half+j];
				}
			}
			long needX = dx - nx, needY = dy - ny;
			String key = needX+" "+needY;
			for(int j = 0; j<=half; j++)
			{
				String nkey = key+" "+j;
				res = (res + (map.containsKey(nkey) ? (map.get(nkey)) * fact(Integer.bitCount(i)+j) : 0))%mod;
			}
			
		}
		System.out.println(res);
	}
}
static long fact(int x)
{
	long res = 1;
	for(int i = 2; i<=x; i++) res = (res * i)%mod;
	return res;
}
}

