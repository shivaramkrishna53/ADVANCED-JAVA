class a
{
	static
	{
		System.out.println("class a loading");
	}
}
class b extends a
{
	static
	{
		System.out.println("class b loading");
	}
}
class inheritancestaticloading extends b
{
	static
	{
		System.out.println("class inheritancestaticloading is loading");
	}
	
	public static void main(String args[])
	{
	}
}