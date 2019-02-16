import java.io.IOException;
import java.util.Scanner;

public class RSA {
	
	public static void main(String[] args) 
	{
		String  mesaj;
		int asal1, asal2,bayrak,n,t;
		char[] m2=new char[100];
		int[] m=new int[100];
		int[] temp=new int[100];
		int[] en=new int[100];
		int[] e=new int[100];
		int[] d=new int[100];
		Scanner al=new Scanner(System.in);
		System.out.println("Birinci Asal Sayý Anahtarýný giriniz: ");
		asal1=al.nextInt();
		bayrak=Asal(asal1);
		if(bayrak==0) {
			System.out.println("Yanlýþ sayý yazdýnýz");
			System.exit(1);
		}
		System.out.println("Ýkinci Asal Sayý Anahtarýný giriniz: ");
		asal2=al.nextInt();
		bayrak=Asal(asal2);
		if(bayrak==0 || asal1==asal2) {
			System.out.println("Yanlýþ sayý yazdýnýz");
			System.exit(1);
		}
		System.out.println("Þifrelenecek mesajý giriniz:  ");
		mesaj=al.next();
		
		int uzunluk=mesaj.length();
		char[] dmesaj= new char[100];
		dmesaj=mesaj.toCharArray();
		
		for(int i=0;i<uzunluk;i++){	
			m[i]=dmesaj[i];
		}
		n=asal1*asal2;
		t=(asal1-1)*(asal2-1);
		ce(t, asal1, asal2, e,d);
		System.out.println("\ne ve d deðerleri\n");
		for(int i=0; i<10; i++)
		System.out.println(e[i]+"\t "+d[i]+"\n ");
		sifrele(e,dmesaj,m,n,temp,en);
		desifrele(d, en, temp, n, m);
		
	}
	public static void ce(int t ,int asal1,int asal2,int[]e,int[]d) {
		int k=0;
		for(int i=2;i<t;i++) {
			if(t%i==0)
				continue;
			int bayrak=Asal(i);
			if(bayrak==1&& i!=asal1&&i!=asal2) {
				e[k]=i;
				bayrak=cd(e[k],t);
				if(bayrak>0) {
					d[k]=bayrak;
					k++;
				}
				if(k==99)
					break;
			}
		}
	}
	public static int  cd(int  x,int t) {
		int k=1;
		while(true) {
			k=k+t;
			if(k%x==0)
				return (k/x);
		}
	}
	public static void sifrele(int[]e,char[]mesaj,int[]m,int n,int[]temp,int[]en)
	{
		int pt, ct, key = e[0], k, len;
		int i = 0;
		len = mesaj.length;
		while (i != len)
		{
			pt = m[i];
			pt = pt - 96;
			k = 1;
			for (int j = 0; j < key; j++)
			{
				k = k * pt;
				k = k % n;
			}
		 temp[i] = k;
		 ct = k + 96;
		 en[i] = ct;
		 i++;
	   }
	   en[i] = -1;
	   System.out.println("\nMesajýnýzýn Þifrelenmiþ Hali:\n");
	   for (i = 0; en[i] != -1; i++)
		   System.out.printf("%c", en[i]);
	}
	static void desifrele(int[]d,int[]en,int[]temp,int n,int[]m)
	{
		int pt, ct, key = d[0], k;
		int i = 0;
		while (en[i] != -1)
		{
		 ct = temp[i];
		 k = 1;
		 for (int j = 0; j < key; j++)
		 {
		 k = k * ct;
		 k = k % n;
		 }
		 pt = k + 96;
		 m[i] = pt;
		 i++;
		}
		m[i] = -1;
		System.out.println("\nÞifrelenmiþ mesajýn çözülmül hali:\n");
		for (i = 0; m[i] != -1; i++)
		System.out.printf("%c", m[i]);
		System.out.println("\n");
		 
	}
	
	public static int Asal(int asal) {
		int i;
		double j=Math.sqrt(asal);
		for(i=2;i<=j;i++) {
			if(asal%i==0) {
				return 0;
			}
		}
		return 1;
		
	}
}
