class test
{
	static
	{
		System.out.println("static block of test is loaded bro");
	}
	public static void	 marks()
	{
		System.out.println("static method marks");
    }
}
class sample2
{
	static
	{
		System.out.println("static block-class is loading");
	}
	public sample2()
	{
		System.out.println("constructor of sample2");
	}
	public static void main(String args[])
	{
		test t1=new test();
	}
}