interface calcu
{
public int sum(int a,int b);
public int diffsum(int a.int b);
}
class calcuImpl implements calcu
{
    public int sum(int a,int b)
    {
        return a+b;
    }
    public int diffSum(int a,int b)
    {
        return a-b;
    }

}