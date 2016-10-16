package seedu.agendum.logic;

import org.junit.Test;
import seedu.agendum.logic.parser.DateTimeParser;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateTimerParserTest {

    private void assertAbsoluteDates(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        assertEquals(dateTime1, dateTime2);
    }

    private void assertRelativeDates(LocalDateTime dateTime1, int seconds) {

    }



    @Test
    public void simpleDateTest() throws Exception {
        Optional<LocalDateTime> t = DateTimeParser.parseString("2016/01/01 00:00");
        assertAbsoluteDates(t.get(), LocalDateTime.of(2016,1,1,0,0));
    }

    public void relativeDateTest() throws Exception {
        // Mock the Date object here?
        Optional<LocalDateTime> t = DateTimeParser.parseString("next Monday");
    }

}
