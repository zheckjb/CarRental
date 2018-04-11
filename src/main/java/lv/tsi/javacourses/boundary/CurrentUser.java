package lv.tsi.javacourses.boundary;

import lv.tsi.javacourses.entity.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 */
@SessionScoped
@Named
public class CurrentUser implements Serializable {
    private User signedInUser;

    public boolean isSignedIn() {
        return signedInUser  != null;
    }

    public User getSignedInUser() {
        return signedInUser;
    }

    public void setSignedInUser(User signedInUser) {
        this.signedInUser = signedInUser;
    }
}
