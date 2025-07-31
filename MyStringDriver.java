package MyStr;

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
	
	public MyString() //1
	{
		arr = new char[0];
		
	}
	
	public MyString(String str)//2
	{
		arr=new char[str.length()];
		for(int i=0;i<str.length();i++)
			arr[i]=str.charAt(i);
	}
	
	public MyString(char [] arr)//3
	{
		this.arr=arr.clone();
	}
	public MyString(byte [] arr)//4
	{
		this.arr=new char[arr.length];
		
		for(int i=0;i<arr.length;i++)
		{
			this.arr[i]=(char)arr[i];
		}
	}
	
	public MyString(char [] arr,int start,int count)//5
	{
		if((start+count)>arr.length)
		    throw new MyStringIndexOutOfBoundException("INVALID ["+start+" ,"+start+"+"+count+"] out of bounds for length "+arr.length);
		    
		    this.arr = new char[count];
		    
		    for(int i=start,j=0;i<(start+count);i++,j++)
		    	this.arr[j]=arr[i];
		
	}
	public MyString(byte[] arr, int start, int count) //6
	{
	    if (start < 0 || count < 0 || start + count > arr.length) {
	        throw new MyStringIndexOutOfBoundException("INVALID [" + start + ", " + start + "+" + count + "] out of bounds for length " + arr.length);
	    }
	    this.arr = new char[count];
	    for (int i = 0, j = start; i < count; i++, j++) {
	        this.arr[i] = (char) arr[j];
	    }
	}
	
	public MyString(int[] arr, int start, int count) //7
	{
	    if (start < 0 || count < 0 || start + count > arr.length) {
	        throw new MyStringIndexOutOfBoundException("INVALID [" + start + ", " + start + "+" + count + "] out of bounds for length " + arr.length);
	    }
	    this.arr = new char[count];
	    for (int i = 0, j = start; i < count; i++, j++) {
	        this.arr[i] = (char) arr[j];
	    }
	}

	public MyString(StringBuffer sb)//8
	{
		this(sb.toString());
	}
	
	public MyString(StringBuilder sb)//9
	{
		this(sb.toString());
	}
	//---------------------------------------------------------------
	public String toString()//1
	{
		String str="";
		for(char ele:arr) str+=ele;
		return str;
	}
	
	public int length()//2
	{
		return arr.length;
	}
	
	public int indexOf(int ch)//3
	{
		if(arr.length>0)
		{
			return arr[ch];
		}
		return -1;
	}
	
	public boolean isEmpty()//4
	{
		return arr.length>0 ? false : true;
	}
	
	public MyString toUpperCase()//5
	{
		char[] newArr=new char[arr.length];
		for(int i=0;i<arr.length;i++)
		{
			char ch=arr[i];
			newArr[i]=(ch>=97 && ch<=122)?(char)(ch-32) : ch;
		}
		return new MyString(newArr);
	}
	
	public MyString toLowerCase()//6
	{
		char[] newArr=new char[arr.length];
		for(int i=0;i<arr.length;i++)
		{
			char ch=arr[i];
			newArr[i]=(ch>=65 && ch<=90)?(char)(ch+32) : ch;
		}
		return new MyString(newArr);
	}
	
	public boolean startsWith(MyString prefix,int start)//7
	{
		if(prefix.length()>arr.length || prefix.length()>(arr.length-start))
			return false;
		
		for(int i=0;i<prefix.length();i++)
			if(arr[start++]!=prefix.charAt(i))return false;
			
		return true;
	}
	
	public boolean startsWith(MyString prefix)//8
	{
		return startsWith(prefix,0);
	}
	
	public boolean contains(MyString str)//9
	{
		for(int i=0;i<arr.length;i++)
		
			if(startsWith(str,i))return true;
			
		
		return false;	
	}
	
	public MyString substring(int start,int end)//10
	{
		if(start<end || start<0 || end<0 || end>arr.length)
			throw new MyStringIndexOutOfBoundException("Range ["+start+","+end+"] out of bounds for length "+arr.length);
		
		char [] newArr=new char[end-start];
		for(int i=0;i<newArr.length;i++)
			newArr[i]=arr[start++];
		
		return new MyString(newArr);
	}

	public boolean endsWith(MyString suffix)//11
	{
		if(arr.length<suffix.length())return false;
		
		for(int i=suffix.length()-1,j=arr.length-1;i>=0;i--,j--) {
			if(arr[j]!=suffix.charAt(i))return false;
		}
		return true;
	}
	
	public boolean equals(Object obj)//12
	{
		if(!(obj instanceof MyString))return false;
		MyString str=(MyString)obj;
		
		if(arr.length!=str.length())return false;
		
		for(int i=0;i<str.length();i++)
			if(arr[i]!=str.charAt(i))return false;
		
		return true;
	}
	
	public boolean equalsIgnoreCase(MyString str)//13
	{
		return this.toLowerCase().equals(str.toLowerCase());
	}
	
	public boolean contentEquals(StringBuffer sb)//14
	{
		MyString obj=new MyString(sb);
		return this.equals(obj);
	}
	
	public char charAt(int indx)//15
	{
			if(indx<0 || indx >=arr.length)
			{
				throw new MyStringIndexOutOfBoundException("Index "+indx+" out of bounds for length "+arr.length);
			}
		return arr[indx];
	}
	
	public int codePointAt(int indx)//16
	{
		if(indx<0 || indx >=arr.length)
		{
			throw new MyStringIndexOutOfBoundException("Index "+indx+" out of bounds for length "+arr.length);
		}
		return arr[indx];
	}
	
	public int codePointBefore(int indx)//17
	{
		return codePointAt(indx-1);
	}
	
	public int codePointCount(int start,int end)//18
	{
		if(start>end || start<0 || end<0 || start>=arr.length)
		{
			throw new  IndexOutOfBoundsException("Range ["+start+","+end+" out of bound for length "+arr.length);
			
		}
		return end-start;
	}
	
	public MyString concat(MyString merge)//19
	{
		char[] newArr=new char[arr.length+merge.length()];
		int indx=0;
		for(char ele:arr)
		    newArr[indx++]=ele;
		
		for(int i=0;i<merge.length();i++)
		     newArr[indx++]=merge.charAt(i);
		
		return new MyString(newArr);
		
	}
	
	public MyString replace(char oldChar,char newChar)//20
	{
		char [] newArr=arr.clone();
		for(int i=0;i<arr.length;i++)
		{
			if(newArr[i]==oldChar)
				newArr[i]=newChar;
			
		}
		
		return new MyString(newArr);
	}
	
	public int indexOf(int assci,int start)//21
	{
		for(int i=0;i<arr.length;i++)
		{
			if(assci==arr[i])return i;
		}
		return -1;
	}
	
	public MyString[] split(MyString regex)//22 
	{
	    int count = 0;
	    for (int i = 0; i < arr.length; i++) {
	        if (arr[i] == regex.charAt(0)) count++;
	    }

	    MyString[] newArr = new MyString[count + 1];
	    int indx = 0;

	    // Commented example: "JAVA IS EASY JAVA IS PROG LANG JAVA IS HLL"
	    MyString str = new MyString(""); 

	    for (char ele : arr) {
	        if (ele != regex.charAt(0)) {
	            str = str.concat(new MyString(ele + ""));
	        } else {
	            newArr[indx++] = str;
	            str = new MyString(""); 
	        }
	    }

	    newArr[indx] = str;
	    return newArr;
	}
	
	public MyString trim() //23
	{
        int left = 0, right = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') left++;
            else break;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == ' ') right++;
            else break;
        }

        return substring(left, arr.length - right);
    }

	
	public MyString replaceAll(MyString searchStr, MyString repStr) //24
	{
	    MyString[] newArr = new MyString(arr).split(new MyString(" "));
	    System.out.println(Arrays.toString(newArr));
	    MyString op = new MyString("");
	    int indx = 0;

	    for (MyString ele : newArr) {
	        if (ele.equals(searchStr)) {
	            newArr[indx] = repStr;
	        }
	        op = op.concat(new MyString(newArr[indx++] + " "));
	    }

	    return op.trim();
	}
	
	public MyString substring(int offset) //25
	{
		if (offset==0) return new MyString(arr);
		
		return substring(offset,arr.length);
	}
	
	public MyString replaceFirst(MyString searchStr, MyString repStr) //26
	{
		MyString[] newArr=new MyString(arr).split(new MyString(" "));

		MyString op =new MyString("");
		
		int indx=0;
		boolean flag=true;
		for (int i = 0; i < newArr.length; i++) {
			if(newArr[i].equals(searchStr) && flag==true) {
				newArr[indx]=repStr;
				flag=false;
			}
			op=op.concat(new MyString(newArr[indx++]+" "));

		}
		
		return op.trim();
	}
	
	public int lastIndexOf(int ch, int startIndx)//27
	{
		char ele=(char)ch;
		if(startIndx>=arr.length)
			startIndx=arr.length-1;
		
		for (int i = startIndx; i>=0; i--) {
			if (arr[i]==ele) {
				return i;
			}
		}
		
		return -1;
	}
	
	public int lastIndexOf(int ch)//28
	{
		return  lastIndexOf(ch,arr.length);
	}
	
	public char[] toCharArray() //29
	{
	    return arr.clone(); 
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
		
//		System.out.println(str.length());
		MyString mstr8=new MyString("hello");
		MyString mstr10=new MyString("HELLO");
		MyString mstr9=new MyString("");
		System.out.println(mstr8.length());//1
		System.out.println(mstr8.isEmpty());//2
		System.out.println(mstr9.isEmpty());
		System.out.println(mstr8.charAt(3));//3
		System.out.println(mstr8.charAt(2));
		System.out.println(mstr8.codePointAt(4));//4
		System.out.println(mstr8.codePointBefore(4));//5
		System.out.println(mstr8.codePointCount(1,3));//6
		System.out.println(mstr8.toUpperCase());//7
		System.out.println(mstr10.toLowerCase());//8
		System.out.println(mstr8.concat(mstr10));//9
		System.out.println(mstr10.startsWith(new MyString("H"), 0));//10
		
		System.out.println(str.codePointAt(4));
		

		
	}

}
