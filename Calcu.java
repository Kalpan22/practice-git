interface calcu
{
public int sum(int a,int b);
public int diff(int a.int b);
}
class calcuImpl implements calcu
{
    public int sum(int a,int b)
    {
        return a+b;
    }
    public int diff(int a,int b)
    {
        return a-b;
    }

}
import java.util.Scanner;
class calcuDriver
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        calcuImpl c=new calcuImpl();
        System.out.println("Enter 1st number");
        int n1=s.nextInt();
        System.out.println("Enter 2nd number");
        int n2=s.nextInt();
        System.out.println(n1+"+"+n2+"="+c.sum(n1,n2));
        System.out.println(n1+"-"+n2+"="+c.diff(n1,n2));
    }
}