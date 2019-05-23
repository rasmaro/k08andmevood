import java.util.*;
import java.util.stream.*;
import java.nio.file.*;
import java.io.*;

public class voog{
	public static int parseMoney(String s){
		return Integer.parseInt(s.split(",")[1]);
	}
	public static boolean filtreeri(String sourceStr){
		return sourceStr.startsWith("Ch");
	}
	public static void main(String[] arg) throws Exception{
		PrintWriter pw = new PrintWriter(new FileWriter("rahavastused.txt"));
		
		// Filtreerimine
		Files.readAllLines(Paths.get("rahad.txt")).
			stream().
			filter(voog::filtreeri).
			forEach(pw::println);
		
		pw.append("\n");
		// Ümber arvutamine
		pw.append(
			Files.readAllLines(Paths.get("rahad.txt")).
				stream().
				mapToInt(s -> Integer.parseInt(s.split(",")[1])).sum()+"" // .toString() kasutamine viskas mingi dereference errori, nii compiles ära :)
		);
		
		pw.append("\n");
		// Sorteerimine väiksemast suuremaks.
		Files.readAllLines(Paths.get("rahad.txt")).
		stream().
		sorted((s1, s2) -> parseMoney(s1)-parseMoney(s2)).
		collect(Collectors.toList()).
		forEach(pw::println);
		
		pw.close();
	}
}

/*
[Chris,25400,m, Clarence,40590,m, Marie,59302,n, Dave,60302,m, Kate,75400,n]

*/