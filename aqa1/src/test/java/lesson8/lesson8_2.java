package lesson8;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.*;
import testNgUtils.Listener;

import static pageObjects.herokuapp.NavigationItems.*;
@Listeners(Listener.class)
public class lesson8_2 extends BaseTest {
    @BeforeMethod
    public void precondition() {
        new NavigationPage()
                .open();
    }

    @Test(enabled = false)
    public void dynamicLoading_Test() {
        new NavigationPage()
                .navigateTo(DYNAMIC_LOADING);
        new DynamicLoadingPage()
                .clickOnExample("Example 1")
                .clickOnStart()
                .pageIsLoaded();
    }

    @Test(priority = 1, description = "scroll test")
    public void scrollPage_Test() {
        new NavigationPage()
                .navigateTo(INFINITE_SCROLL);
        new InfiniteScrollPage().infiniteScroll(10);
    }

    @Test(priority = 3,description ="context menu test", dependsOnMethods ="scrollPage_Test")
    public void contextMenu_Test() {
        new NavigationPage()
                .navigateTo(CONTEXT_MENU);
        new ContextMenuPage().clickContext().verifyAlert("You selected a context menu");
    }

    @Test(priority = 2, description = "frame test")
    public void frame_Test() {
        new NavigationPage()
                .navigateTo(FRAMES);
        new FramesPage()
                .clickFrameLink("iFrame")
                .switchToFrame()
                .enterText("HELLO WORLD!")
                .unSwitchFrame()
                .clickBoldTxt();
    }
}
