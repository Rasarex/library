package unislask;

import java.util.Vector;

class Author {
	String ID;
	String name;
	String surname;
	Vector<String> otherNames;
	String bornDate;

	public void emplace(String attr, String attrname) {
		switch (attrname) {
		case "ID":
			ID = attr;
		case "NAME":
			name = attr;
		case "SURNAME":
			surname = attr;
		case "BORNDATE":
			bornDate = attr;
		}
	}

	public void other(Vector<String> otherNames__) {
		otherNames = otherNames__;
	}

}