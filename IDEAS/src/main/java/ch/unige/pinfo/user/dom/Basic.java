package ch.unige.pinfo.user.dom;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Basic")
public class Basic extends User {

	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = 937124364638035252L;

}
