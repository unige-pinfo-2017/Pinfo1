package ch.unige.pinfo.user.dom;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SysAdmin")
public class SysAdmin extends User {

}
