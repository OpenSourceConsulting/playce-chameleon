package weblogic.ch2;

import javax.ejb.*;
import java.util.*;
import javax.naming.*;

/**
 * Statefull Session Bean Example
 * 
 * @stereotype SessionBean
 * @remoteInterface weblogic.ch2.Cart
 * @homeInterface weblogic.ch2.CartHome
 * @statemode Stateful
 */
public class CartEJB implements javax.ejb.SessionBean {

	// ---------------------------------------------------------------
	// conversational state fields
	public String customerName;
	public String customerId;
	public Vector contents;

	// ---------------------------------------------------------------
	// private fields
	private SessionContext ctx;

	public CartEJB() {

		String test1 = new String("가나다라마바사".getBytes(), "euc-kr");
		String test2 = new String("가나다라마바사".getBytes("utf-8"), "EUC-KR");
		String test3 = new String("가나다라마바사".getBytes("ISO-8859-1"), "ksc5601");

		System.out.println("CartEJB.CartEJB()");
	}

	public void ejbCreate() {
		System.out.println("CartEJB.ejbCreate");
		// to do: code goes here.
	}

	// ---------------------------------------------------------------
	// SessionBean interface implementation
	public void ejbActivate() {
		System.out.println("CartEJB.ejbActivate()");
		// to do: code goes here.

	}

	public void ejbPassivate() {
		System.out.println("CartEJB.ejbPassivate()");
		// to do: code goes here.

	}

	public void ejbRemove() {
		System.out.println("CartEJB.ejbRemove()");
		// to do: code goes here.

	}

	public void setSessionContext(SessionContext ctx) {
		System.out.println("CartEJB.setSessionContext()");
		this.ctx = ctx;

		// to do: code goes here.

	}

	// ---------------------------------------------------------------
	// create methods
	public void ejbCreate(String person, String id) throws CreateException {
		System.out.println("CartEJB.ejbCreate()");
		// to do: code goes here.
		if (person == null) {
			throw new CreateException("Null person not allowed.");
		} else {
			customerName = person;
		}

		IdVerifier idChecker = new IdVerifier();
		if (idChecker.validate(id)) {
			customerId = id;
		} else {
			throw new CreateException("Invalid id: " + id);
		}

		contents = new Vector();
	}

	public void ejbCreate(String person) throws CreateException {
		System.out.println("CartEJB.ejbCreate()");
		// to do: code goes here.
		if (person == null) {
			throw new CreateException("Null person not allowed.");
		} else {
			customerName = person;
		}

		customerId = "0";
		contents = new Vector();
	}

	// ---------------------------------------------------------------
	// business methods
	public Vector getContents() {
		// to do: code goes here.
		System.out.println("CartEJB.getContents()");

		return contents;
	}

	public void removeBook(String title) throws BookException {
		System.out.println("CartEJB.removeBook()");
		boolean result = contents.removeElement(title);
		if (result == false) {
			throw new BookException(title + " not in cart.");
		}
		// to do: code goes here.

	}

	public void addBook(String title) {
		// to do: code goes here.
		System.out.println("CartEJB.addBook()");
		contents.addElement(title);
	}

	// ---------------------------------------------------------------
	// get conversational state
	public String getCustomerName() {
		System.out.println("CartEJB.getCustomerName()");
		return this.customerName;
	}

	public String getCustomerId() {
		System.out.println("CartEJB.getCustomerId()");
		return this.customerId;
	}

}