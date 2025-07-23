
import java.util.*;

class MyStringIndexOutOfBoundException extends RuntimeException
{
	public MyStringIndexOutOfBoundException(String message)
	{
		super(message);
	}
}

final class MyString{
	
char [] arr;
	
	public MyString() {
		arr = new char[0];
		
	}
	
	public MyString(String str)
	{
		arr=new char[str.length()];
		for(int i=0;i<str.length();i++)
			arr[i]=str.charAt(i);
	}
	
	public MyString(char [] arr)
	{
		this.arr=arr.clone();
	}
	public MyString(byte [] arr)
	{
		this.arr=new char[arr.length];
		
		for(int i=0;i<arr.length;i++)
		{
			this.arr[i]=(char)arr[i];
		}
	}
	
	public MyString(char [] arr,int start,int count)
	{
		if((start+count)>arr.length)
		    throw new MyStringIndexOutOfBoundException("INVALID ["+start+" ,"+start+"+"+count+"] out of bounds for length "+arr.length);
		    
		    this.arr = new char[count];
		    
		    for(int i=start,j=0;i<(start+count);i++,j++)
		    	this.arr[j]=arr[i];
		
	}

	public MyString(StringBuffer sb)
	{
		this(sb.toString());
	}
	
	public MyString(StringBuilder sb)
	{
		this(sb.toString());
	}
	
	public String toString()
	{
		String str="";
		for(char ele:arr) str+=ele;
		return str;
	}
	
	public int length()
	{
		return arr.length;
	}
	
	public int indexOf(int ch)
	{
		if(arr.length>0)
		{
			return arr[ch];
		}
		return -1;
	}
	
	public boolean isEmpty()
	{
		return arr.length>0 ? false : true;
	}
	
	public MyString toUpperCase()
	{
		char[] newArr=new char[arr.length];
		for(int i=0;i<arr.length;i++)
		{
			char ch=arr[i];
			newArr[i]=(ch>=97 && ch<=122)?(char)(ch-32) : ch;
		}
		return new MyString(newArr);
	}
	
	public MyString toLowerCase()
	{
		char[] newArr=new char[arr.length];
		for(int i=0;i<arr.length;i++)
		{
			char ch=arr[i];
			newArr[i]=(ch>=65 && ch<=90)?(char)(ch+32) : ch;
		}
		return new MyString(newArr);
	}
	
	public boolean startsWith(MyString prefix,int start)
	{
		if(prefix.length()>arr.length || prefix.length()>(arr.length-start))
			return false;
		
		for(int i=0;i<prefix.length();i++)
			if(arr[start++]!=prefix.charAt(i))return false;
			
		return true;
	}
	
	public boolean startsWith(MyString prefix)
	{
		return startsWith(prefix,0);
	}
	
	public boolean contains(MyString str)
	{
		for(int i=0;i<arr.length;i++)
		
			if(startsWith(str,i))return true;
			
		
		return false;	
	}
	
	public MyString substring(int start,int end)
	{
		if(start<end || start<0 || end<0 || end>arr.length)
			throw new MyStringIndexOutOfBoundException("Range ["+start+","+end+"] out of bounds for length "+arr.length);
		
		char [] newArr=new char[end-start];
		for(int i=0;i<newArr.length;i++)
			newArr[i]=arr[start++];
		
		return new MyString(newArr);
	}

	public boolean endsWith(MyString suffix)
	{
		if(arr.length<suffix.length())return false;
		
		for(int i=suffix.length()-1,j=arr.length-1;i>=0;i--,j--) {
			if(arr[j]!=suffix.charAt(i))return false;
		}
		return true;
	}
	
	public boolean equals(Object obj)
	{
		if(!(obj instanceof MyString))return false;
		MyString str=(MyString)obj;
		
		if(arr.length!=str.length())return false;
		
		for(int i=0;i<str.length();i++)
			if(arr[i]!=str.charAt(i))return false;
		
		return true;
	}
	
	public boolean equalsIgnoreCase(MyString str)
	{
		return this.toLowerCase().equals(str.toLowerCase());
	}
	
	public boolean contentEquals(StringBuffer sb)
	{
		MyString obj=new MyString(sb);
		return this.equals(obj);
	}
	
	public char charAt(int indx)
	{
			if(indx<0 || indx >=arr.length)
			{
				throw new MyStringIndexOutOfBoundException("Index "+indx+" out of bounds for length "+arr.length);
			}
		return arr[indx];
	}
	
	public int codePointAt(int indx)
	{
		if(indx<0 || indx >=arr.length)
		{
			throw new MyStringIndexOutOfBoundException("Index "+indx+" out of bounds for length "+arr.length);
		}
		return arr[indx];
	}
	
	public int codePointBefore(int indx)
	{
		return codePointAt(indx-1);
	}
	
	public int codePointCount(int start,int end)
	{
		if(start>end || start<0 || end<0 || start>=arr.length)
		{
			throw new  IndexOutOfBoundsException("Range ["+start+","+end+" out of bound for length "+arr.length);
			
		}
		return end-start;
	}
	
	public MyString concat(MyString merge)
	{
		char[] newArr=new char[arr.length+merge.length()];
		int indx=0;
		for(char ele:arr)
		    newArr[indx++]=ele;
		
		for(int i=0;i<merge.length();i++)
		     newArr[indx++]=merge.charAt(i);
		
		return new MyString(newArr);
		
	}
	
	public MyString replace(char oldChar,char newChar)
	{
		char [] newArr=arr.clone();
		for(int i=0;i<arr.length;i++)
		{
			if(newArr[i]==oldChar)
				newArr[i]=newChar;
			
		}
		
		return new MyString(newArr);
	}
	
}

public class MyStringDriver {

	
	public static void main(String[] args) {
		String str="hello";
		String str1=new String();
		
		StringBuffer sb=new StringBuffer("hello stringbuffer");
		StringBuilder sb1=new StringBuilder("hello stringbuiler");
		
		MyString mstr1=new MyString();
		MyString mstr2=new MyString("hello");
		MyString mstr3=new MyString(sb);
		MyString mstr4=new MyString(sb1);
		
		System.out.println(mstr1);//no args constructor
		System.out.println(mstr2);//String constructor
		System.out.println(mstr3);//StringBuffer constructor
		System.out.println(mstr4);//StringBuilder Constructor
		
		char [] arr1= {'a','m','e','y'};
		
		MyString mstr5=new MyString(arr1);
		System.out.println(mstr5);//char [] constructor
		
		MyString mstr6=new MyString(arr1,0,3);
		System.out.println(mstr6);// char[],int start ,int count constructor
		
		byte [] barr= {97,98,99,100,101,102,65,66};
		
		MyString mstr7=new MyString(barr);
		System.out.println(mstr7);
		
		System.out.println(str.length());
		MyString mstr8=new MyString("hello");
		MyString mstr10=new MyString("HELLO");
		MyString mstr9=new MyString("");
		System.out.println(mstr8.length());
		System.out.println(mstr8.isEmpty());
		System.out.println(mstr9.isEmpty());
		System.out.println(mstr8.charAt(3));
		System.out.println(mstr8.charAt(2));
		System.out.println(mstr8.codePointAt(4));
		System.out.println(mstr8.codePointBefore(4));
		System.out.println(mstr8.codePointCount(1,3));
		System.out.println(mstr8.toUpperCase());
		System.out.println(mstr10.toLowerCase());
		System.out.println(mstr8.concat(mstr10));
		System.out.println(mstr10.startsWith(new MyString("H"), 0));


		
	}

}
