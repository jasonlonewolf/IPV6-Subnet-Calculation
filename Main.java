
import java.util.Arrays;  
import java.util.List;  
import java.util.ArrayList; 


public class Main {

	public static void main(String[] args) {



		int i=0;
		for(String tmp:args){
			i++;
		}
		if(i>0)
		{
			calculate(args[0]);

		}else{

			//System.out.println(i);
			calculate("");

		}

	}

	static String calculate(String message){

		String result = "";
		//		String a="123a:134b::/64";
		//		String a="2001:db8::/64";
		//String a="2001:db8:/64";
		//String a="2001:db8::/40";
		String a = message;

//		String a= "2001:0db8:85a3:0000:0000:8a2e:0370:7334/64";
		//				String a= "2001:0db8:85a3:0000:0000:8a2e:0370:7334/32";

		a = a.replaceAll("\\s", a);

		String[] d = a.split("/");



		if(d.length>1&&!(d[0]).trim().equals("::")){
			String b = d[0];
			String c = d[1];

			System.out.println("IPv6 address: "+ a );
			result = result + "IPv6 address: "+ a + "\n";
			System.out.println("Compressed Address: "+b);
			result = result + "Compressed Address: "+ b + "\n";
			//System.out.println(c);

			if(checkipv6(b)){

				System.out.println("validation: true");	
				result = result + "validation: true" + "\n";
				String fb = formatipv6preferred(b);
				String bb=expand(b);

				System.out.println("Expanded Address: \n"+bb);
				result = result + "Expanded Address: \n"+bb + "\n";

				List<String> pf = findprefix(Integer.parseInt(c));

				String ppf="";

				
				for(String tmp:pf){
					ppf= ppf+tmp;

				}

				ppf = expand(ppf+"::");

				List<String> eel = bitand(ppf,bb);

				String ee="";

				for(String tmp:eel){
					ee= ee+tmp;
				}

				System.out.println("first IP\n"+ee);	
				result = result + "first IP\n"+ee + "\n";

				List<String> ffl = last(ppf,ee);
				String ff="";

				for(String tmp:ffl){
					ff= ff+tmp;
				}
				System.out.println("last IP\n"+ff);	
				result = result + "last IP\n" + ff + "\n";

				System.out.println("Prefix\n"+ppf);	

				result = result + "Prefix\n"+ppf + "\n";

				System.out.println("Number of /64s:\n"+(int)Math.pow(2 , 64 - Integer.parseInt(c)));	
				result = result + "Number of /64s:\n"+(int)Math.pow(2 , 64 - Integer.parseInt(c)) + "\n";


			}else{

				System.out.println(a + "ip address is wrong:false");
				result = result + "ip address is wrong:false" + "\n";

			}

		}else{
			if(d[0].trim().equals("::")){
				System.out.println("loop back ipv6address is not available");
			}
			System.out.println(a + " usage : ipv6address /digits");
			result = result + "ip address is wrong:false" + "\n";

		}

		return result;
	}




	static boolean checkipv6(String a){
		boolean rt = false;
		if(a.matches("^\\s*(([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))\\s*$")
				||a.matches("^\\s*([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:)\\s*$")			
				||a.matches("^\\s*(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))\\s*$")
				||a.matches("^\\s*(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))\\s*$")		
				||a.matches("^\\s*(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))\\s*$")			
				||a.matches("^\\s*(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))\\s*$")			
				||a.matches("^\\s*(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))\\s*$")		
				||a.matches("^\\s*(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))\\s*$")
				){

			rt = true;
		}


		return rt;

	}

	static String formatipv6preferred(String e){
		String d;
		String b="Not a valid IPv6 Address";
		String c[];
		String a;
		List<String> wordList;;  

		if(checkipv6(e)){
			d=e.toLowerCase();
			c=d.split(":");
			wordList = trimcolonsfromends(c);
			b = assemblebestrepresentation(wordList);
		}

		return b;
	}

	static List<String> trimcolonsfromends(String[] a){
		int b=a.length;

		List<String> wordList = Arrays.asList(a);  
		int i=0;
		
		for (String e : wordList)  
		{  
			i++;
			//System.out.println(i+"  "+e);  
		}  	
		
		if(a.length>0){
			if((a[0]=="")&&(a[1]=="")&&(a[2]=="")){
				wordList.remove(0);
				wordList.remove(1);

			}else{

				if((a[0]=="")&&(a[1]=="")){
					wordList.remove(0);
				}

				else{
					if((a[b-1]=="")&&(a[b-2]=="")){
						wordList.remove(a.length);
					}

				}

			}
		}
		return wordList;
	}



	static String assemblebestrepresentation(List<String> c){
		String a="";
		int b=c.size();
		if(!c.isEmpty()){
			if(c.get(0)==""){
				a=":";
			}

			for(int i=0;i<b;i++){
				a=a+c.get(i);
				if(i==b-1){
					break;
				}

				a = a + ":";
			}

			if(c.get(b-1)==""){
				a=a+":";
			}
		}
		return a;
	}

	static List<String> last(String b,String c){

		String []d = c.split(":");

		String []e = b.split(":");

		List<String> anded = new ArrayList<String>();

		for(int a=0;a<8;a++){
			String tmp= "";

			d[a] =Integer.toString( Integer.parseInt(d[a].trim(), 16 ));
			e[a] =Integer.toString( Integer.parseInt(e[a].trim(), 16 ));

			e[a]=Integer.toString(Integer.parseInt(e[a])^65535);

			tmp = Integer.toString(Integer.parseInt(e[a])^Integer.parseInt(d[a]));
			if(a==7){
				anded.add(a, Integer.toHexString(Integer.parseInt(tmp)));
			}else{
				anded.add(a, Integer.toHexString(Integer.parseInt(tmp)) + ":");

			}
		}
		return anded;
	}

	static List<String> bitand(String b,String c){
		String []d = c.split(":");

		String []e = b.split(":");

		List<String> anded = new ArrayList<String>();

		for(int a=0;a<8;a++){
			String tmp= "";
			d[a] =Integer.toString( Integer.parseInt(d[a], 16 ));
			e[a] =Integer.toString( Integer.parseInt(e[a], 16 ));
			tmp =  Integer.toString(Integer.parseInt(d[a])&Integer.parseInt(e[a]));

			if(a==7){			
				anded.add(a, Integer.toHexString(Integer.parseInt(tmp)));
			}else{
				anded.add(a, Integer.toHexString(Integer.parseInt(tmp)) + ":");
			}
		}

		return anded;
	}


	static List<String> findprefix(int b){
		int c=b;
		String d="";
		List<String> aa = new ArrayList<String>();

		for(int a=0;a < c;a++){
			d+="1";
			if((a+1)%16==0){
				d+=":";
			}


		}

		String [] e = d.split(":");
		while(e[e.length-1].length () <16){
			e[e.length-1]+="0";
		}

		for(int a=0;a<e.length;a++){
			
			e[a]=Integer.toString(Integer.parseInt(e[a],2));
			e[a]=Integer.toHexString((Integer.parseInt(e[a],10)));

			aa.add(a, e[a]+":");

		}

		return aa;
	}

	static String expand(String k){

		String a="";
		String h="";
		int e=8;
		int b=4;
		if(k.indexOf("::")==-1){
			a = k;
		}else{
			
			String [] d = k.split("::");
			
			if(d.length>0){
				int g=0;
				
				for(int f=0;f<d.length;f++){
					g+=d[f].split(":").length;
				}
				
				a+=d[0]+":";

				for(int f=0;f<e-g;f++){
					a+="0000:";
				}

			}
		}

		String[] c = a.split(":");


		for(int f=0;f<e;f++){
			
			while(c[f].length()<b){
				c[f]="0"+c[f];
			}
			
			h+=(f!=e-1)?c[f]+":":c[f];
		}
		return h;
	}

}

