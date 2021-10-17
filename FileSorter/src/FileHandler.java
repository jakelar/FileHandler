import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import static java.nio.file.StandardCopyOption.*;

public class FileHandler {

	public static void FolderMaker(Pattern pattern, Matcher matcher, Path mypath) {

		try (Stream<Path> files = Files.walk(mypath)) {
			List<Path> bob = new ArrayList<Path>();
			bob = files.toList();
			for (int i = 0; i < bob.size(); i++) {
				String title = bob.get(i).toString();
				;

				matcher = pattern.matcher(title);
				boolean matchFound = matcher.find();

				if (matchFound) {

					// System.out.println(bob.get(i).toString());
					String bill = matcher.group();

					String directoryName = mypath.toString() + "\\" + bill;

					File file = new File(directoryName);

					if (!file.exists()) {

						file.mkdir();

					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void FileRenamer(Pattern pattern, Matcher matcher, Path mypath, String lastName) {
		String newName = "";
		try (Stream<Path> files = Files.walk(mypath)) {
			List<Path> bob = new ArrayList<Path>();
			bob = files.toList();
			Pattern initP = pattern;
			Matcher initialM = matcher;
			for (int i = 0; i < bob.size(); i++) {

				pattern = initP;
				matcher = initialM;
				newName = "";
				String title = bob.get(i).toString();
				if (title.charAt(title.length() - 1) == 'x') {
					matcher = pattern.matcher(title);
					boolean matchFound = matcher.find();
					if (matchFound) {

						File fileName = new File(bob.get(i).toString());
						String className = fileName.getName();
						// Class
						String bill = matcher.group();
						matcher = pattern.matcher(className);
						matchFound = matcher.find();
						if (matchFound) {
							String currentClass = matcher.group();

							System.out.println(currentClass);
							newName += currentClass + "_";

						}
						// module
						pattern = Pattern.compile("M\\d", pattern.CASE_INSENSITIVE);
						matcher = pattern.matcher(className);
						matchFound = matcher.find();
						if (matchFound) {

							newName += "_M" + matcher.group().charAt(1) ;
							System.out.println(newName);

						}
						// part
						pattern = Pattern.compile("p\\d", pattern.CASE_INSENSITIVE);
						matcher = pattern.matcher(className);
						matchFound = matcher.find();
						if (matchFound) {

							newName += "_P" + matcher.group().charAt(1);
							System.out.println(newName);

						}

						newName += "_" + lastName + ".docx";

						String directoryName = mypath.toString() + "\\" + bill;

						Path target = Paths.get(directoryName + "//" + newName);

						Files.move(bob.get(i), target, REPLACE_EXISTING);

					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
