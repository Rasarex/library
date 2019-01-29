package unislask;

import java.util.Vector;

/**
 * <p>Author class.</p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class Author {
	String ID;
	String name;
	String surname;
	Vector<String> otherNames;
	String bornDate;

	Author() {
		ID = new String();
		name = new String();
		surname = new String();
		otherNames = new Vector<>();
		bornDate = new String();
	}

	/**
	 * <p>
	 * emplace.
	 * </p>
	 *
	 * @param attr     a {@link java.lang.String} object.
	 * @param attrname a {@link java.lang.String} object.
	 * @param attrname a {@link java.lang.String} object.
	 */
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

	/**
	 * <p>
	 * other.
	 * </p>
	 *
	 * @param otherNames__ a {@link java.util.Vector} object.
	 */
	public void other(Vector<String> otherNames__) {
		otherNames = otherNames__;
	}

	/** {@inheritDoc} */
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
