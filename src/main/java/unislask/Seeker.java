package unislask;

import java.util.Vector;

class Seeker {
	Vector<Book> seekByBookAttr(Data currentData, String attrName, String attr) {

		Vector<Book> rtn = new Vector<>();
		//
		for (Book b : currentData.books) {
			if (b.attributes.get(attrName).contains(attr))
				rtn.add(b);
		}
		return rtn;
	}

	Vector<Book> seekByAuthorAttr(Data currentData, String attrName, String attr) {
		Vector<Book> rtn = new Vector<>();

		return rtn;
	}
}