package unislask;

import java.util.Vector;

/**
 * <p>
 * Author is basic storage structure containging inforamtion about given author
 * </p>
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
	String path;

	Author() {
		ID = new String();
		name = new String();
		surname = new String();
		otherNames = new Vector<>();
		bornDate = new String();
		path = new String();
	}

	/**
	 * <p>
	 * emplace.
	 * </p>
	 * Emplaces value on inner state.
	 *
	 * @param attr     state value.
	 * @param attrname state name.
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
	 * @param otherNames__ emplaces {@link java.util.Vector} of other names
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
