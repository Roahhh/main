package seedu.agendum.commons.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

//@@author A0148095X
public class GuiSettingsTest {

    public Double windowWidth = 800.0;
    public Double windowHeight = 600.0;
    public int xPosition = 100;
    public int yPosition = 300;
    
    GuiSettings guiSettings;
    
    @Before
    public void setup() {
        guiSettings = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition);
    }
    
    @Test
    public void equals() {
        GuiSettings guiSettingsCopy = guiSettings;
        
        // same object
        assertTrue(guiSettings.equals(guiSettingsCopy));
        
        // not an instance
        assertFalse(guiSettings.equals(new Object()));
        
        // null
        assertFalse(guiSettings.equals(null));
        
        // different object, same settings
        GuiSettings sameSettings = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition);
        assertTrue(guiSettings.equals(sameSettings));
        

        // ----------- different settings ----------------
        GuiSettings differentSettings;

        Double differentWindowWidth = windowWidth*2;
        Double differentWindowHeight = windowHeight*2;
        int differentXPosition = xPosition*2;
        int differentYPosition = yPosition*2;

        // different width
        differentSettings  = new GuiSettings(differentWindowWidth, windowHeight, xPosition, yPosition);
        assertFalse(guiSettings.equals(differentSettings));
        
        // different height
        differentSettings  = new GuiSettings(windowWidth, differentWindowHeight, xPosition, yPosition);
        assertFalse(guiSettings.equals(differentSettings));
        
        // different x position
        differentSettings  = new GuiSettings(windowWidth, windowHeight, differentXPosition, yPosition);
        assertFalse(guiSettings.equals(differentSettings));
        
        // different y position
        differentSettings  = new GuiSettings(windowWidth, windowHeight, xPosition, differentYPosition);
        assertFalse(guiSettings.equals(differentSettings));
    }
    
}
