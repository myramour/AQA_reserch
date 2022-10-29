import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.tripadvisor.ProfilePage;
import pageObjects.tripadvisor.StartPage;

/** пропиши ожидание того,что кнопка сохранить не бужет видна или ещен что-то,чтобы была пауза между методами*/
public class Load_Test extends BaseTest {

    @BeforeClass
    public void preconditions() {
        get(StartPage.class)
                .open().clickGoIn().switchToRegistrationForm()
                .clickLoginForEmail()
                .enterEmail(properties.getProperty("email")).enterPassword(properties.getProperty("password"))
                .clickLogin().verifyLoginToProfile().clickProfile().clickGoToProfile();;
    }

    @Test(description = "Test for upload cover image")
    public void uploadCoverTest() {
        pause(3);
        get(ProfilePage.class).clickProperty().clickEditCover().uploadCover();
        pause(4);
    }

    @Test(description = "Test for upload avatar image")
    public void uploadAvatarTest() {
        pause(3);
        get(ProfilePage.class).clickProperty().clickEditAvatar().uploadAvatar();
    }

    private void pause(Integer timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
