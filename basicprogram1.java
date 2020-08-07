class a
{
static
{
System.out.println("static block of a class");
}
public a()
{
System.out.println("a constructor");
}
}
class b
{
static
{
System.out.println("static block of b class");
}
public b()
{
System.out.println("b contructor");
}
}
class c
{
static
{
System.out.println("c static block");
}
}
class demo
{
static
{
System.out.println("demo static block");
}
public demo()
{
System.out.println("demo constructor");
}
}
class basicprogram1
{
 int k,m;
static int q;
static
{
q=10;
System.out.println("basicprogram1 class loaded");
}
public void x()
{
k=20;
}
public basicprogram1()
{
System.out.println("constructors of basicprog");
m=20;
}
public static void main(String args[]) throws Exception
{
basicprogram1 bs=new basicprogram1();
basicprogram1 bs2=new basicprogram1();
basicprogram1 bs3=new basicprogram1();
b B=new b();
a A=new a();
Class.forName("demo"); 
demo de=new demo();
}
}