package softaria;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PagesHistoryDetectorTest {
    private final static PagesState STATE = new PagesState(Map.of("home", "Home page", "about", "About page"));

    @Test
    void addingNewPage_shouldDetectIt() {
        var secondState = new PagesState(Map.of("home", "Home page", "about", "About page", "contact", "Contact page"));

        var comparator = new PagesHistoryDetector(STATE, secondState);
        comparator.detectHistory();

        assertThat(comparator.getAddedPages()).containsExactlyInAnyOrder("contact");
        assertThat(comparator.getDeletedPages()).isEmpty();
        assertThat(comparator.getUpdatedPages()).isEmpty();
    }

    @Test
    void deletingPage_shouldDetectIt() {
        var secondState = new PagesState((Map.of("home", "Home page")));

        var comparator = new PagesHistoryDetector(STATE, secondState);
        comparator.detectHistory();

        assertThat(comparator.getAddedPages()).isEmpty();
        assertThat(comparator.getDeletedPages()).containsExactlyInAnyOrder("about");
        assertThat(comparator.getUpdatedPages()).isEmpty();
    }

    @Test
    void changingPage_shouldDetectIt() {
        var secondState = new PagesState(Map.of("home", "Home page", "about", "New about page"));

        var comparator = new PagesHistoryDetector(STATE, secondState);
        comparator.detectHistory();

        assertThat(comparator.getAddedPages()).isEmpty();
        assertThat(comparator.getDeletedPages()).isEmpty();
        assertThat(comparator.getUpdatedPages()).containsExactlyInAnyOrder("about");
    }

    @Test
    void changingNothing_shouldDetectNoChanges() {
        var comparator = new PagesHistoryDetector(STATE, STATE);
        comparator.detectHistory();

        assertThat(comparator.getAddedPages()).isEmpty();
        assertThat(comparator.getDeletedPages()).isEmpty();
        assertThat(comparator.getUpdatedPages()).isEmpty();
    }
}
