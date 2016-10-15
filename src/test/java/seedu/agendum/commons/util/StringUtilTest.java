package seedu.agendum.commons.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isUnsignedPositiveInteger() {
        assertFalse(StringUtil.isUnsignedInteger(null));
        assertFalse(StringUtil.isUnsignedInteger(""));
        assertFalse(StringUtil.isUnsignedInteger("a"));
        assertFalse(StringUtil.isUnsignedInteger("aaa"));
        assertFalse(StringUtil.isUnsignedInteger("  "));
        assertFalse(StringUtil.isUnsignedInteger("-1"));
        assertFalse(StringUtil.isUnsignedInteger("0"));
        assertFalse(StringUtil.isUnsignedInteger("+1")); //should be unsigned
        assertFalse(StringUtil.isUnsignedInteger("-1")); //should be unsigned
        assertFalse(StringUtil.isUnsignedInteger(" 10")); //should not contain whitespaces
        assertFalse(StringUtil.isUnsignedInteger("10 ")); //should not contain whitespaces
        assertFalse(StringUtil.isUnsignedInteger("1 0")); //should not contain whitespaces

        assertTrue(StringUtil.isUnsignedInteger("1"));
        assertTrue(StringUtil.isUnsignedInteger("10"));
    }

    @Test
    public void getDetails_exceptionGiven(){
        assertThat(StringUtil.getDetails(new FileNotFoundException("file not found")),
                   containsString("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_assertionError(){
        thrown.expect(AssertionError.class);
        StringUtil.getDetails(null);
    }
    
    @Test
    public void isValidFilePath(){
        // non-absolute file paths
        assertFalse(StringUtil.isValidFilePath(null));
        assertFalse(StringUtil.isValidFilePath("")); // empty path
        assertFalse(StringUtil.isValidFilePath("a")); // missing file type
        assertFalse(StringUtil.isValidFilePath("data/xml")); // missing file name/type
        assertFalse(StringUtil.isValidFilePath("data/.xml")); // missing file name
        assertFalse(StringUtil.isValidFilePath("data/ .xml")); // invalid file name
        assertFalse(StringUtil.isValidFilePath("data /valid.xml")); // invalid folder name with spaces after
        assertFalse(StringUtil.isValidFilePath(" data/valid.xml")); // invalid folder name with spaces before
        assertFalse(StringUtil.isValidFilePath("data.xml/data.xml")); // invalid folder name

        assertTrue(StringUtil.isValidFilePath("a/a.xml"));
        assertTrue(StringUtil.isValidFilePath("Program Files/data.xml"));
        assertTrue(StringUtil.isValidFilePath("folder/some-other-folder/data.dat"));
        
        // absolute file paths
        assertFalse(StringUtil.isValidFilePath("CC:/valid.xml")); // invalid drive
        assertFalse(StringUtil.isValidFilePath("asd:/valid.xml")); // invalid drive
        assertFalse(StringUtil.isValidFilePath("C:/")); // missing file name
        assertFalse(StringUtil.isValidFilePath("C:/Program Files")); // missing file name
        assertFalse(StringUtil.isValidFilePath("C:/a")); // file name missing type
        assertFalse(StringUtil.isValidFilePath("C:/data/xml")); // file missing name/type
        assertFalse(StringUtil.isValidFilePath("C:/data/.xml")); // file missing name
        assertFalse(StringUtil.isValidFilePath("C:/data/ .xml")); // invalid file name
        assertFalse(StringUtil.isValidFilePath("C:/data /valid.xml")); // invalid folder name with spaces after
        assertFalse(StringUtil.isValidFilePath("C:/ data/valid.xml")); // invalid folder name with spaces before
        assertFalse(StringUtil.isValidFilePath("C:/data.xml/data.xml")); // invalid folder name
        assertFalse(StringUtil.isValidFilePath("1:/data.xml")); // invalid drive
        assertFalse(StringUtil.isValidFilePath("/usr/.xml")); // invalid file name - no file name
        assertFalse(StringUtil.isValidFilePath("/ usr/data.xml")); // invalid folder with spaces before
        assertFalse(StringUtil.isValidFilePath("/usr /data.xml")); // invalid folder with spaces after

        assertTrue(StringUtil.isValidFilePath("C:/a/a.xml"));
        assertTrue(StringUtil.isValidFilePath("C:/Program Files/data.xml"));
        assertTrue(StringUtil.isValidFilePath("Z:/folder/some-other-folder/data.dat"));
        assertTrue(StringUtil.isValidFilePath("a:/folder/some-other-folder/data.dat"));
        assertTrue(StringUtil.isValidFilePath("/usr/bin/data.xml"));
        assertTrue(StringUtil.isValidFilePath("/home/data.xml"));
        assertTrue(StringUtil.isValidFilePath("/Users/test/Desktop/data.xml"));
        
    }
    
}
