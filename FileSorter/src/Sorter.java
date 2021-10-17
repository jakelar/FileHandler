import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;
import java.io.*;
import static java.nio.file.StandardCopyOption.*;

public class Sorter {

	public static void main(String[] args) throws InterruptedException {

		Pattern pattern = Pattern.compile("IT\\d{3}");
		Matcher matcher = pattern.matcher("I");
		String pathString = "";
		String lastName = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your File path");
		try {
			pathString = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter your last name:");
		try {
			lastName = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Path mypath = Paths.get(pathString);

		FileHandler.FolderMaker(pattern, matcher, mypath);
		FileHandler.FileRenamer(pattern, matcher, mypath, lastName);

	}

}
