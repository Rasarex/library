package unislask;

import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import com.mifmif.common.regex.Generex;

/**
 * <p>AuthorFactory class.</p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class AuthorFactory {
	// final static private String[] AUTHOR_ATTR = { "ID", "NAME", "SURNAME",
	// "OTHER", "BORNDATE" };

	AuthorFactory() {
		authors = new ConcurrentHashMap<>();
	}

	void parseAuthor(Vector<String> file, String path) {
		// TODO BETTER ERROR LOGGING
		Author author = new Author();
		HashMap<String, String> attr = new HashMap<>();
		for (String line : file) {

			if (line.charAt(0) != '#') {
				String[] splited = line.split("=");
				if (splited.length != 2) {
					continue;
				}
				attr.put(splited[0], splited[1]);
			}
		}

		for (Map.Entry<String, String> entry : attr.entrySet()) {
			if (entry.getKey().equals("OTHER")) {
				Vector<String> other = new Vector<>();
				String[] othernames = entry.getValue().split(",");
				for (String othername : othernames) {
					other.add(othername);
				}
				author.otherNames = other;
			} else
				author.emplace(entry.getValue(), entry.getKey());
		}

		if (authors.get(author.ID) == null) {
			authors.put(path, author);
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
				if (!(sameName || sameSurname || sameBornDate || sameOtherNames)) {
					author.ID = genID();
					authors.put(path, author);
				} else {
					authors.put(path, another);
				}
			authors.put(path, author);
			// TODO REVISIT DATE
			// TODO REVISIT ID CORRECTIOn
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

	ConcurrentHashMap<String, Author> authors;
}
