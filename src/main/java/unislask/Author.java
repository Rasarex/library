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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(256);
		builder.append("\nID:");
		builder.append(ID);
		builder.append("\nName:");
		builder.append(name);
		builder.append("\nSurname:");
		builder.append(surname);
		for (String other : otherNames) {

			builder.append(other);
		}

		builder.append("\nBorn date:");
		builder.append(bornDate);
		return builder.toString();
	}
}