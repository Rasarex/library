package unislask;

import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Vector;
import com.mifmif.common.regex.Generex;

class AuthorFactory {
	final static private String[] AUTHOR_ATTR = { "ID", "NAME", "SURNAME", "OTHER", "BORNDATE" };

	Author parseAuthor(String path) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(path));
		Author author = new Author();
		HashMap<String, String> attr = new HashMap<>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] splited = line.split("=");

			if (splited.length != 2) {
				scanner.close();
				throw new FileNotFoundException();
			}
			attr.put(splited[1], splited[0]);
		}
		scanner.close();
		for (Map.Entry<String, String> entry : attr.entrySet()) {
			boolean correct = false;
			for (String val : AUTHOR_ATTR) {
				if (entry.getValue().equals(val)) {
					correct = true;
				}
			}
			if (!correct) {
				scanner.close();
				throw new FileNotFoundException();
			}
		}
		if (authors.get(attr.get("ID")) == null) {
			for (Map.Entry<String, String> entry : attr.entrySet()) {
				author.emplace(entry.getValue(), entry.getKey());
			}
			return author;
		} else {
			Author another = authors.get(attr.get("ID"));
			boolean sameID = false;
			boolean sameName = false;
			boolean sameSurname = false;
			boolean sameOtherNames = false;
			boolean sameBornDate = false;
			if (author.name.equals(another.name))
				sameName = true;
			if (author.surname.equals(another.surname))
				sameSurname = true;
			if (author.otherNames.equals(another.otherNames))
				sameOtherNames = true;
			if (author.bornDate.equals(another.bornDate))
				sameBornDate = true;
			if (author.ID.equals(another.ID))
				sameID = true;
			if (sameID)
				if ((sameName || sameSurname || sameBornDate || sameOtherNames)) {
					author.ID = genID();
					return author;
				} else {
					return another;
				}
			return author;
			// TODO REVISIT DATE
		}
	}

	Vector<String> toPaths(String path) throws FileNotFoundException {
		Vector<String> rtnval = new Vector<>();
		File fpath = new File(path);
		Scanner scanner = new Scanner(fpath);
		while (scanner.hasNext()) {
			rtnval.add(scanner.nextLine());
		}
		scanner.close();
		return rtnval;
	}

	String genID() {
		String generated = null;
		do {
			Generex generex = new Generex("[A-F0-9]+");
			generated = generex.random();
			assert generated.matches("[A-F0-9]+");
		} while (authors.get(generated) != null);
		return generated;
	}

	Vector<Author> getVectorized() {
		Vector<Author> rtnval = new Vector<>();
		for (Map.Entry<String, Author> entry : authors.entrySet()) {
			rtnval.add(entry.getValue());

		}
		return rtnval;
	}

	HashMap<String, Author> authors;
}