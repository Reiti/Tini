package server.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 07.10.13
 * Time: 09:24
 * Tests the command parser functionality.
 */
public class CommandParserTest {
    @Test
    public void testDefaultParse() {
        String message = "test string 1000";
        Command com = new Command("/say", message.split(" "));
        assertEquals(com, CommandParser.parse(message));
    }

    @Test
    public void testSpecificParse() {
        String action = "/ban";
        String message = "a spamming user ";
        Command com = new Command(action, message.split(" "));
        assertEquals(com, CommandParser.parse(action + " " + message));
    }

    @Test
    public void testCommandEquals() {
        Command com = new Command("/hurr", "durr furr zurr".split(" "));
        Command com2 = new Command("/hurr", "durr furr zurr".split(" "));
        assertEquals(com, com2);
    }
}
