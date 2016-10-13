package seedu.agendum.commons.util;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.agendum.commons.exceptions.FileDeletionException;
import seedu.agendum.testutil.SerializableTestClass;
import seedu.agendum.testutil.TestUtil;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileUtilTest {
    private static final File SERIALIZATION_FILE = new File(TestUtil.getFilePathInSandboxFolder("serialize.json"));


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    
    @Test
    public void deleteFileAtPath() throws FileDeletionException {
        
        // unable to delete
        thrown.expect(FileDeletionException.class);
        FileUtil.deleteFileAtPath(null);
        
        // able to delete
        File file = new File("test.file");
        try {
            FileUtil.createFile(file);
            FileUtil.deleteFileAtPath(file.getPath());
        } catch (IOException e) {
        }
    }
    
    @Test
    public void isPathAvailable() {
        String availablePath = "data/test/test.txt";
        String badPath = "1:/test.xml";
        
        // Path available
        // file does not exist
        assertTrue(FileUtil.isPathAvailable(availablePath));
        // file exists
        File file = new File(availablePath);
        try {
            FileUtil.createFile(file);
        } catch (IOException e) {
        }
        assertTrue(FileUtil.isPathAvailable(availablePath));
        
        // Path not available
        assertFalse(FileUtil.isPathAvailable(badPath));
    }
    
    @Test
    public void getPath(){

        // valid case
        assertEquals("folder" + File.separator + "sub-folder", FileUtil.getPath("folder/sub-folder"));

        // null parameter -> assertion failure
        thrown.expect(AssertionError.class);
        FileUtil.getPath(null);

        // no forwards slash -> assertion failure
        thrown.expect(AssertionError.class);
        FileUtil.getPath("folder");
    }

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        FileUtil.serializeObjectToJsonFile(SERIALIZATION_FILE, serializableTestClass);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), SerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.JSON_STRING_REPRESENTATION);

        SerializableTestClass serializableTestClass = FileUtil
                .deserializeObjectFromJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }
}
